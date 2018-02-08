package com.yongjun.tdms.service.runmaintenance.discard.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.discard.DiscardBillDtlDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBillDtl;
import com.yongjun.tdms.model.runmaintenance.migrate.MigrateDetail;
import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
import com.yongjun.tdms.model.runmaintenance.trusteeship.TrusteeshipDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardBillDtlManager;

public class DefaultDiscardBillDtlManager implements DiscardBillDtlManager{
 private final DiscardBillDtlDao discardBillDtlDao;  //设备报废单明细数据业务接口
 private final DeviceCardManager deviceCardManager; 

 private final CodeValueManager codeValueManager;
     public DefaultDiscardBillDtlManager( DiscardBillDtlDao discardBillDtlDao,DeviceCardManager deviceCardManager
    		 ,CodeValueManager codeValueManager){
    	 this.discardBillDtlDao=discardBillDtlDao;
    	 this.deviceCardManager=deviceCardManager;
    	 this.codeValueManager=codeValueManager;
     }
	public DiscardBillDtl loadDiscardBillDtl(Long discardBillDtlId) {
		
		return discardBillDtlDao.loadDiscardBillDtl(discardBillDtlId);
	}

	public List<DiscardBillDtl> loadAllDiscardBillDtls(Long[] discardBillDtlIds) {
		
		return discardBillDtlDao.loadAllDiscardBillDtls(discardBillDtlIds);
	}

	public void storeDiscardBillDtl(DiscardBillDtl discardBillDtl) {
		this.discardBillDtlDao.storeDiscardBillDtl(discardBillDtl);
		
	}

	public void deleteDiscardBillDtl(DiscardBillDtl discardBillDtl) {
		this.discardBillDtlDao.deleteDiscardBillDtl(discardBillDtl);
		
	}

	public void deleteAllDiscardBillDtl(Collection<DiscardBillDtl> discardBillDtls) {
		 this.discardBillDtlDao.deleteAllDiscardBillDtl(discardBillDtls);
		
	}
	public void storeDiscardDevDetail(DiscardBill discardBill, String newDeviceIds) {//选择所要报废的设备
		 String [] deviceId = null;                   
			if (null != newDeviceIds) {
				deviceId = newDeviceIds.split(",");
			}
			addNewDiscardDevDetail(discardBill, deviceId);
	}
	public void addNewDiscardDevDetail(DiscardBill discardBill,String [] deviceId){
		for (int i=0; i<deviceId.length; i++) {
			DiscardBillDtl detail = new DiscardBillDtl();
			DeviceCard devicecard=this.deviceCardManager.loadDevice(Long.valueOf(deviceId[i]));
			detail.setDeviceCard(this.deviceCardManager.loadDevice(Long.valueOf(deviceId[i])));
			detail.setDiscardBill(discardBill);
			copyDeviceProTodicardDtlPro(detail,devicecard);//复制设备信息到报废单明细
			discardBillDtlDao.storeDiscardBillDtl(detail);//设备保存后  至设备状态为半报废
			devicecard.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_DISCARDING_STATUS));
			this.deviceCardManager.storeDevice(devicecard);
		}
		
	}
	public void copyDeviceProTodicardDtlPro(DiscardBillDtl detail,DeviceCard devicecard){
		 detail.setDevBillNo(devicecard.getDeviceNo());//把设备编号复制到报废单明细中的设备编号
		 detail.setModel(devicecard.getModel());//把设备模型复制到报废单明细中的设备模型
		 detail.setSpecification(devicecard.getSpecification());//把设备的规格复制单报废单明细中设备规格
		 if (null != devicecard.getDeviceFinanceInfo()) {
			 //把设备所关联的财政信息的设备原值复制到报废单的设备原值
			 detail.setOrigPrice(devicecard.getDeviceFinanceInfo().getOrigPrice());
			 //把设备所关联的财政信息的设备净值复制到报废单的设备净值
			 detail.setNetValue(devicecard.getDeviceFinanceInfo().getNetValue());
		 }
		
		 if(devicecard.getMadeDate()!=null){
			 detail.setManufactureDate(devicecard.getMadeDate());//把设备的制造日期复制到报废单中的设备制造日期 
		 }
		 if((devicecard.getDeviceFinanceInfo()!= null) && (devicecard.getDeviceFinanceInfo().getYearLimit()!=null)){
			 detail.setUseYear(devicecard.getDeviceFinanceInfo().getYearLimit());//把设备的使用年限复制到报废单中的使用年限 
		 }
	}
	public void storeDiscardDevBillDtl(String allDiscardDeviceId, String allmemoInfo) {//从Action中获得传来的所要报废的设备
		  String []discardDeviceId=null;                                               //的ids   所有报废设备的备注信息
		  String [] memoInfo=null;
		  if (null != allDiscardDeviceId) {
			  discardDeviceId = allDiscardDeviceId.split(",");
			}
		  if (null != allmemoInfo) {
			  memoInfo = allmemoInfo.split(",");
			}
	updateDiscardDevBillDetail(discardDeviceId, memoInfo);//解析每一个所要报废的设备的id  和设备id所对应的备注
					
	}
	
  public  void updateDiscardDevBillDetail(String []discardDeviceId,String []memoInfo){
		int count = 0;
		while (count < discardDeviceId.length) {
			DiscardBillDtl detail = this.discardBillDtlDao.loadDiscardBillDtl(Long
					.valueOf(discardDeviceId[count]));
		if (null != memoInfo) {
			for (int i = 0; i < memoInfo.length; i = i + 2) {
				if (memoInfo[i].equals(discardDeviceId[count])) {
					detail.setMemo(memoInfo[i + 1]);
					break;
				} else {
					detail.setMemo(null);
				}
			}
		} else {
			detail.setMemo(null);
		}
		this.discardBillDtlDao.storeDiscardBillDtl(detail);
		count++;
    }
  }
  
}
