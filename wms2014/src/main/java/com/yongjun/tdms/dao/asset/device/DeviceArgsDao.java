package com.yongjun.tdms.dao.asset.device;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.device.DeviceArgs;


public interface DeviceArgsDao {
	List<DeviceArgs> loadAllDeviceArgs(Long [] deviceArgsIds);
	
	DeviceArgs loadDeviceArgs(Long deviceArgsId);
	
	void storeDeviceArgs(DeviceArgs deviceArgs);
	
	void deleteAllDeviceArgs(Collection<DeviceArgs> deviceArgs);
	
	void deleteDeviceArgs(DeviceArgs deviceArg);
}
