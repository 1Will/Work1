package com.yongjun.tdms.service.asset.device;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceFinanceInfo;

@Transactional(readOnly = true)
public interface DeviceFinanceInfoManager {
	@Transactional
	void storeDeviceFinanceInfo(DeviceFinanceInfo deviceFinanceInfo);
	
	DeviceFinanceInfo loadDeviceFinanceInfo(Long deviceFinanceInfoId);
}
