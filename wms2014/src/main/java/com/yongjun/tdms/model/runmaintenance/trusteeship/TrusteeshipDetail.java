package com.yongjun.tdms.model.runmaintenance.trusteeship;


import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
public class TrusteeshipDetail extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;

	//托管相关的资产[工装]|[设备]
	private DeviceCard asset;
	
	//关联的托管单
	private Trusteeship trusteeship;
	
	@Override
	public int hashCode() {
		
	  return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TrusteeshipDetail)) {
			return false;
		}
		TrusteeshipDetail trusteeshipDetail = (TrusteeshipDetail) o;
		if (trusteeshipDetail.getId().equals(this.getId())) {
			return true;
		}
		return false;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public Trusteeship getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(Trusteeship trusteeship) {
		this.trusteeship = trusteeship;
	}
	
}
