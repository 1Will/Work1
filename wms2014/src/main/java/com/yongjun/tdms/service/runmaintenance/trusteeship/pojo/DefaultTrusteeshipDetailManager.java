package com.yongjun.tdms.service.runmaintenance.trusteeship.pojo;
import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.trusteeship.TrusteeshipDetailDao;

import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
import com.yongjun.tdms.model.runmaintenance.trusteeship.TrusteeshipDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;


import com.yongjun.tdms.service.runmaintenance.trusteeship.*;

public class DefaultTrusteeshipDetailManager implements TrusteeshipDetailManager{
	      private final DeviceCardManager deviceCardManager; 

	      private final CodeValueManager codeValueManager;
          private final TrusteeshipDetailDao trusteeshipDetaildao;
          public DefaultTrusteeshipDetailManager(TrusteeshipDetailDao trusteeshipDetaildao,
        		  DeviceCardManager deviceCardManager,CodeValueManager codeValueManager){
        	   this.trusteeshipDetaildao=trusteeshipDetaildao;
        	   this.deviceCardManager=deviceCardManager;
        	   this.codeValueManager=codeValueManager;
        	 
          }
	public TrusteeshipDetail loadTrusteeshipDetail(Long TrusteeshipDetailId) {
		
		return trusteeshipDetaildao.loadTrusteeshipDetail(TrusteeshipDetailId);
	}

	public List<TrusteeshipDetail> loadAllTrusteeshipDetails(Long[] TrusteeshipDetailIds) {
		
		return trusteeshipDetaildao.loadAllTrusteeshipDetails(TrusteeshipDetailIds);
	}

	public List<TrusteeshipDetail> loadTrusteeshipDetails() {
		
		return trusteeshipDetaildao.loadTrusteeshipDetails();
	}

	public void storeTrusteeshipDetail(TrusteeshipDetail trusteeshipDetail) {
		this.trusteeshipDetaildao.storeTrusteeshipDetail(trusteeshipDetail);
	}

	public void deleteTrusteeshipDetail(TrusteeshipDetail trusteeshipDetail) {
		  this.trusteeshipDetaildao.deleteTrusteeshipDetail(trusteeshipDetail);
	}

	public void deleteAllTrusteeshipDetails(Collection<TrusteeshipDetail> trusteeshipDetails) {
	   this.trusteeshipDetaildao.deleteAllTrusteeshipDetails(trusteeshipDetails);
	   //当被托管的设备被删除的时候,改变设备的状态为正常
	   updateDevStatusByDelTurDtl(trusteeshipDetails);
	}
	public void updateDevStatusByDelTurDtl(Collection<TrusteeshipDetail> trusteeshipDetails){
		for(TrusteeshipDetail truDtl:trusteeshipDetails){
			DeviceCard asset=truDtl.getAsset();
			asset.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_NORMAL_STATUS));
			this.deviceCardManager.storeDevice(asset);
		}
	}
	public void storeTrusteeshipDetail(Trusteeship trusteeship,String newDeviceIds) {
	      String [] deviceId = null;
			
			if (null != newDeviceIds) {
				deviceId = newDeviceIds.split(",");
			}
			addNewMigrateDetail(trusteeship, deviceId);
			
		}
		public void addNewMigrateDetail(Trusteeship trusteeship,String [] deviceId){
			
			for (int i=0; i<deviceId.length; i++) {
				TrusteeshipDetail detail = new TrusteeshipDetail();
				detail.setAsset(this.deviceCardManager.loadDevice(Long.valueOf(deviceId[i])));
				detail.setTrusteeship(trusteeship);
				trusteeshipDetaildao.storeTrusteeshipDetail(detail);
				DeviceCard devicecard=detail.getAsset();
				if (trusteeship.getSupplier() != null) {
					devicecard.setTrusteeshipSupplier(trusteeship.getSupplier());
					devicecard.setTrusteeshipSupplierName(trusteeship.getSupplier().getName());
				}
			    devicecard.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_TRUSTEESHIP_STATUS));
				devicecard.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_TRUSTEESHIP_STATUS));
				this.deviceCardManager.storeDevice(devicecard);
			}
			
		}
}
