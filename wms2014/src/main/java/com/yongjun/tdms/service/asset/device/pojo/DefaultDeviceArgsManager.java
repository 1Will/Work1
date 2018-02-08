package com.yongjun.tdms.service.asset.device.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.asset.device.DeviceArgsDao;
import com.yongjun.tdms.model.asset.device.DeviceArgs;
import com.yongjun.tdms.service.asset.device.DeviceArgsManager;

public class DefaultDeviceArgsManager implements DeviceArgsManager {
	private final DeviceArgsDao deviceArgsDao;
	
	public DefaultDeviceArgsManager(DeviceArgsDao deviceArgsDao) {
		this.deviceArgsDao = deviceArgsDao;
	}
	
	public List<DeviceArgs> loadAllDeviceArgs(Long[] deviceArgsIds) {
		return this.deviceArgsDao.loadAllDeviceArgs(deviceArgsIds);
	}

	public DeviceArgs loadDeviceArgs(Long deviceArgsId) {
		return this.deviceArgsDao.loadDeviceArgs(deviceArgsId);
	}

	public void storeDeviceArgs(DeviceArgs deviceArgs) {
		this.deviceArgsDao.storeDeviceArgs(deviceArgs);
	}

	public void deleteDeviceArgs(DeviceArgs deviceArg) {
		this.deviceArgsDao.deleteDeviceArgs(deviceArg);
	}

	public void deleteAllDeviceArgs(Collection<DeviceArgs> deviceArgs) {
		this.deviceArgsDao.deleteAllDeviceArgs(deviceArgs);
	}

}
