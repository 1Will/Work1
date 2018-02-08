package com.yongjun.tdms.model.asset.device;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.financeType.FinanceType;

public class DeviceDeprecitionDetail extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{

	private static final long serialVersionUID = 1L;
	
	private Double originalValue;	//原值
	private Double monthDeprecitionScale;	//月折旧率
	private Double deprecitionValue;			//折旧额
	private Double afterDepreValue;				//折旧后的余额
	private String month;			//月份
	private DeviceCard deviceCard;	//关联设备
	private DeviceFinanceInfo deviceFinanceInfo;//关联财务信息
	private FinanceType	financeType;	//关联财务类别
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) { return true; }
		if (!(obj instanceof DeviceDeprecitionDetail)) { return false; }
		return true;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public Double getDeprecitionValue() {
		return deprecitionValue;
	}

	public void setDeprecitionValue(Double deprecitionValue) {
		this.deprecitionValue = deprecitionValue;
	}

	public FinanceType getFinanceType() {
		return financeType;
	}

	public void setFinanceType(FinanceType financeType) {
		this.financeType = financeType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getMonthDeprecitionScale() {
		return monthDeprecitionScale;
	}

	public void setMonthDeprecitionScale(Double monthDeprecitionScale) {
		this.monthDeprecitionScale = monthDeprecitionScale;
	}

	public Double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}

	public Double getAfterDepreValue() {
		return afterDepreValue;
	}

	public void setAfterDepreValue(Double afterDepreValue) {
		this.afterDepreValue = afterDepreValue;
	}

	public DeviceFinanceInfo getDeviceFinanceInfo() {
		return deviceFinanceInfo;
	}

	public void setDeviceFinanceInfo(DeviceFinanceInfo deviceFinanceInfo) {
		this.deviceFinanceInfo = deviceFinanceInfo;
	}

	public DeviceCard getDeviceCard() {
		return deviceCard;
	}

	public void setDeviceCard(DeviceCard deviceCard) {
		this.deviceCard = deviceCard;
	}

}
