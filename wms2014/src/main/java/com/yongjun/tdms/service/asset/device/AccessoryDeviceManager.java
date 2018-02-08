package com.yongjun.tdms.service.asset.device;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.AccessoryDevice;
import com.yongjun.tdms.model.asset.device.DeviceCard;

@Transactional(readOnly = true)
public interface AccessoryDeviceManager {
	
	AccessoryDevice loadAccessoryDevice(Long accessoryDeviceId);
	
	List<AccessoryDevice> loadAllAccessoryDevice(Long [] accessoryDeviceIds);
	
	@Transactional
	void storeAccessoryDevice(AccessoryDevice accessoryDevice);
	
	@Transactional
	void deleteAccessoryDevice(AccessoryDevice accessoryDevice);
	
	@Transactional
	void deleteAllAccessoryDevice(Collection<AccessoryDevice> accessoryDevices);

	@Transactional
	void createAccessory(DeviceCard device, Long[] ids);

}
