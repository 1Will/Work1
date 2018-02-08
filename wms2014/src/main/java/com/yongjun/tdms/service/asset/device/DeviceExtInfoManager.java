package com.yongjun.tdms.service.asset.device;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceExtInfo;

@Transactional(readOnly = true)
public interface DeviceExtInfoManager {
	@Transactional
	public void storeDeviceExtInfo(DeviceExtInfo deviceExtInfo);
	
	public DeviceExtInfo loadDeviceExtInfo(Long deviceExtInfoId);

}
