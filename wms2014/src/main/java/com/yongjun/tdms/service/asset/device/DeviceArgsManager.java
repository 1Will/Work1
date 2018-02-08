package com.yongjun.tdms.service.asset.device;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceArgs;

@Transactional(readOnly = true)
public interface DeviceArgsManager {
	List<DeviceArgs> loadAllDeviceArgs(Long [] deviceArgsIds);

	DeviceArgs loadDeviceArgs(Long deviceArgsId);
	
	@Transactional
	void storeDeviceArgs(DeviceArgs deviceArgs);
	
	@Transactional
	void deleteDeviceArgs(DeviceArgs deviceArg);
	
	@Transactional
	void deleteAllDeviceArgs(Collection<DeviceArgs> deviceArgs);
}
