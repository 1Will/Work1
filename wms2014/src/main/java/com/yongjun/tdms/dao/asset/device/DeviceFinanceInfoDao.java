package com.yongjun.tdms.dao.asset.device;

import com.yongjun.tdms.model.asset.device.DeviceFinanceInfo;

public interface DeviceFinanceInfoDao {
	void storeDeviceFinanceInfo(DeviceFinanceInfo deviceFinanceInfo);
	
	DeviceFinanceInfo loadDeviceFinanceInfo(Long deviceFinanceInfoId);

}
