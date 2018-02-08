package com.yongjun.tdms.service.runmaintenance.migrate.pojo;

import java.util.Collection;
import java.util.List;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.runmaintenance.migrate.MigrateDao;
import com.yongjun.tdms.dao.runmaintenance.migrate.MigrateDetailDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.model.runmaintenance.migrate.MigrateDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateDetailManager;

public class DefaultMigrateDetailManager implements MigrateDetailManager {
	private final MigrateDetailDao migratedetaildao;
    private final MigrateDao migrateDao;
	private final DeviceCardManager deviceCardManager;

	private final UserManager userManager;

	private DeviceCard devicecard;

	public DefaultMigrateDetailManager(MigrateDetailDao migratedetaildao,
			DeviceCardManager deviceCardManager, UserManager userManager,
			MigrateDao migrateDao) {
		this.migratedetaildao = migratedetaildao;
		this.deviceCardManager = deviceCardManager;
		this.userManager = userManager;  
		this.migrateDao=migrateDao;
	}

	public MigrateDetail loadMigrateDetail(Long MigrateDetailId) {
		return migratedetaildao.loadMigrateDetail(MigrateDetailId);
	}

	public void storeMigrateDetail(MigrateDetail migrateDetail) {
		migratedetaildao.storeMigrateDetail(migrateDetail);
		//改变原来设备生产部门和生产线
		if(migrateDetail!=null){
			updateOriginProductDepartmentAndProductLine(migrateDetail);
		}

	}
	public void updateOriginProductAndDepartment(MigrateDetail migrateDetail){	//当转移单被保存之后改变原所属部门原所属生产线
		    DeviceCard asset=migrateDetail.getAsset();
			Migrate migrate=migrateDetail.getMigrate();
			asset.setDepartment(migrate.getNewDepartment());   
			asset.setProductionLine(migrate.getNewProductionLine());
	}
		 
		 
    public void updateOriginProductDepartmentAndProductLine(MigrateDetail migrateDetail){
    	Migrate migrate=migrateDetail.getMigrate();
    	DeviceCard asset=migrateDetail.getAsset();
    	asset.setDepartment(migrate.getNewDepartment());
    	asset.setProductionLine(migrate.getNewProductionLine());
    }
	public void deleteMigrateDetail(MigrateDetail migrateDetail) {
		migratedetaildao.deleteMigrateDetail(migrateDetail);

	}

	public void deleteAllMigrateDetails(Collection<MigrateDetail> MigrateDetails) {
		
		migratedetaildao.deleteAllMigrateDetails(MigrateDetails);
		if(MigrateDetails!=null){
			updateResumeDeviceOriginProductDepartmentAndProductLine(MigrateDetails);//当删除被转移的设备之后 恢复原来的设备的原生产线和部门
		}
       for( MigrateDetail Dtl:MigrateDetails){
    	   if(Dtl.getMigrate().getToolingDevFlag().equals(SysModel.TOOLING)){//当删除转移单明细的时候 新安装地点和新负责人也要改变为原来的安装地点和负责人
    		  if(Dtl.getOldInstallPlace()==null) 
    			  Dtl.getAsset().setInstallPlace(null);
    		  else
			    Dtl.getAsset().setInstallPlace(Dtl.getOldInstallPlace());
    		  if(Dtl.getOldManager()==null)
    			  Dtl.getAsset().setManager(null);
    		  else
    			  Dtl.getAsset().setManager(Dtl.getNewManager());  
    		  
    	   }else{
    		   if(Dtl.getOldInstallPlace()==null) 
     			  Dtl.getAsset().setInstallPlace(null);
     		  else
 			    Dtl.getAsset().setInstallPlace(Dtl.getOldInstallPlace());
    	   }
		  } 
	}
	//当删除被转移的设备之后 恢复原来的设备的原生产线和部门
	public void updateResumeDeviceOriginProductDepartmentAndProductLine(Collection<MigrateDetail> MigrateDetails){
	   for(MigrateDetail dtl:MigrateDetails){
		   dtl.getAsset().setDepartment(dtl.getMigrate().getOldDepartment());
		   dtl.getAsset().setProductionLine(dtl.getMigrate().getOldProductionLine());
	   }
	}
	public List<MigrateDetail> loadAllMigrateDetails(Long[] MigrateDetailIds) {
		return migratedetaildao.loadAllMigrateDetails(MigrateDetailIds);
	}
	public List<MigrateDetail> loadAllMigrateDetail() {
		return migratedetaildao.loadAllMigrateDetail();
	}
	public void storemigrateDetail(Migrate migrate, String newDeviceIds) {
		String[] deviceId = null;
		if (null != newDeviceIds) {
			deviceId = newDeviceIds.split(",");
		}
		addNewMigrateDetail(migrate, deviceId);//取所要转移的设备的id
	}

	public void addNewMigrateDetail(Migrate migrate, String[] deviceId) {//添加设备到转移明细列表中
		for (int i = 0; i < deviceId.length; i++) {
			MigrateDetail detail = new MigrateDetail();
			detail.setAsset(this.deviceCardManager.loadDevice(Long .valueOf(deviceId[i])));//根据设备的id获得每个设备的对象
			detail.setMigrate(migrate);
			if (migrate.getToolingDevFlag().equals(SysModel.TOOLING)) {//如果是工装,保存新负责人
				if(detail.getAsset().getManager()==null){
					detail.setOldManager(null);
				}else{
					detail.setOldManager(detail.getAsset().getManager().getName());
				}
				if(migrate.getNewDepartment()!=null){               //如果是工装  把所转移的部门保存到所选工装的部门中去
					detail.getAsset().setDepartment(migrate.getNewDepartment());
				}else{ 
					detail.getAsset().setDepartment(null);
				}
				
			} else {
				if(detail.getAsset().getInstallPlace()==null){//如果是设备,保存新负责人的同时也要保存新安装地点
					detail.setOldInstallPlace(null);
				}else{
					detail.setOldInstallPlace(detail.getAsset().getInstallPlace());
				}
				if (detail.getAsset().getManager() == null) {
					detail.setOldManager(null);
				} else {
					detail.setOldManager(detail.getAsset().getManager().getName());
				}
				if(migrate.getNewDepartment()!=null){               //如果是设备  把所转移的部门保存到所选设备的部门中去
					detail.getAsset().setDepartment(migrate.getNewDepartment());
				}else{
					detail.getAsset().setDepartment(null);
				}
			}
//			当转移单被保存之后改变原所属部门原所属生产线
			updateOriginProductAndDepartment( detail);
			this.migratedetaildao.storeMigrateDetail(detail);
            
		}
	}
    //根据传来的设备的ids 获得每一个设备的对象  同时保存每个所要转移的设备的新地点和新负责人
	public void storeMigrateDetail(String allMigrateDeviceId,String allDeviceMigratenewManager, String migrateDeviceNewPlace) {
		String[] MigratedeviceId = null;
		String[] migrateDeviceNewManager = null;
		String[] migratedeviceNewPlace = null;
		if (null != allMigrateDeviceId) {
			MigratedeviceId = allMigrateDeviceId.split(",");
		}
		if (null != allDeviceMigratenewManager) {
			migrateDeviceNewManager = allDeviceMigratenewManager.split(",");
		}
		if (null != migrateDeviceNewPlace) {
			migratedeviceNewPlace = migrateDeviceNewPlace.split(",");
		}
		updateMigrateDetail(MigratedeviceId, migrateDeviceNewManager,migratedeviceNewPlace);//
	}
	private void updateMigrateDetail(String[] MigratedeviceId,
			String[] migrateDeviceNewManager, String[] migratedeviceNewPlace) {
		int count = 0;
		while (count < MigratedeviceId.length) {
			MigrateDetail detail = this.migratedetaildao.loadMigrateDetail(Long
					.valueOf(MigratedeviceId[count]));
			if (null != migrateDeviceNewManager) {
				for (int i = 0; i < migrateDeviceNewManager.length; i = i + 2) {
					if (migrateDeviceNewManager[i].equals(MigratedeviceId[count])) {
						detail.setNewManager(userManager.loadUser(Long.valueOf(migrateDeviceNewManager[i + 1])));
						break;
					} else {
						detail.setNewManager(null);
					}
				}
			} else {
				detail.setNewManager(null);
			}
			if (null != migratedeviceNewPlace) {
				for (int i = 0; i < migratedeviceNewPlace.length; i = i + 2) {
					if (migratedeviceNewPlace[i].equals(MigratedeviceId[count])) {
						detail.setNewInstallPlace(migratedeviceNewPlace[i + 1]);
						break;
					} else {
						detail.setNewInstallPlace(null);
					}
				}
			} else {
				detail.setNewInstallPlace(null);
			}
			this.migratedetaildao.storeMigrateDetail(detail);
			devicecard = detail.getAsset();
			devicecard.setManager(detail.getNewManager());
			devicecard.setManager(detail.getNewManager());
			devicecard.setInstallPlace(detail.getNewInstallPlace());
			deviceCardManager.storeDevice(devicecard);
			count++;
		}
	}

	public DeviceCard getDevicecard() {
		return devicecard;
	}

	public void setDevicecard(DeviceCard devicecard) {
		this.devicecard = devicecard;
	}
}
