package com.yongjun.tdms.service.asset.device.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.asset.device.AccessoryDeviceDao;
import com.yongjun.tdms.dao.asset.device.DeviceCardDao;
import com.yongjun.tdms.model.asset.device.AccessoryDevice;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.AccessoryDeviceManager;

public class DefaultAccessoryDeviceManager implements AccessoryDeviceManager {
	private final AccessoryDeviceDao accessoryDeviceDao;
	private final DeviceCardDao deviceCardDao;
	
	public DefaultAccessoryDeviceManager(AccessoryDeviceDao accessoryDeviceDao, DeviceCardDao deviceCardDao) {
		this.accessoryDeviceDao = accessoryDeviceDao;
		this.deviceCardDao = deviceCardDao;
	}
	
	public AccessoryDevice loadAccessoryDevice(Long accessoryDeviceId) {
		return this.accessoryDeviceDao.loadAccessoryDevice(accessoryDeviceId);
	}

	public void storeAccessoryDevice(AccessoryDevice accessoryDevice) {
		this.accessoryDeviceDao.storeAccessoryDevice(accessoryDevice);
	}

	public void deleteAccessoryDevice(AccessoryDevice accessoryDevice) {
		this.accessoryDeviceDao.deleteAccessoryDevice(accessoryDevice);
	}

	public void deleteAllAccessoryDevice(
			Collection<AccessoryDevice> accessoryDevices) {
		this.accessoryDeviceDao.deleteAllAccessoryDevice(accessoryDevices);
	}

	public List<AccessoryDevice> loadAllAccessoryDevice(Long[] accessoryDeviceIds) {
		return this.accessoryDeviceDao.loadAllAccessoryDevice(accessoryDeviceIds);
	}
	
	public void createAccessory(DeviceCard device, Long[] slaveDeviceIds) {
		List<DeviceCard> slaveDevices = this.deviceCardDao.loadAllDevices(slaveDeviceIds);
		
		for (int i=0; i<slaveDevices.size(); i++) {
		 AccessoryDevice accessoryDevice = new AccessoryDevice();
		 accessoryDevice.setMasterDevice(device);
		 //accessoryDevice.setSlaveDevice(slaveDevices.get(i));
		 accessoryDevice.setName(((DeviceCard)slaveDevices.get(i)).getName());
		 device.getAccessoryDevices().add(accessoryDevice);
		}
		 deviceCardDao.storeDevice(device);
		 
	}

}
