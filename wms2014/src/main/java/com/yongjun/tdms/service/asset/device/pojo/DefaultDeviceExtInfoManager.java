package com.yongjun.tdms.service.asset.device.pojo;

import com.yongjun.tdms.dao.asset.device.DeviceExtInfoDao;
import com.yongjun.tdms.model.asset.device.DeviceExtInfo;
import com.yongjun.tdms.service.asset.device.DeviceExtInfoManager;

public class DefaultDeviceExtInfoManager implements DeviceExtInfoManager {
	private final DeviceExtInfoDao deviceExtInfoDao;

	public DefaultDeviceExtInfoManager(DeviceExtInfoDao deviceExtInfoDao) {
		this.deviceExtInfoDao = deviceExtInfoDao;
	}
	
	public void storeDeviceExtInfo(DeviceExtInfo deviceExtInfo) {
		this.deviceExtInfoDao.storeDeviceExtInfo(deviceExtInfo);
	}

	public DeviceExtInfo loadDeviceExtInfo(Long deviceExtInfoId) {
		return this.deviceExtInfoDao.loadDeviceExtInfo(deviceExtInfoId);
	}

}
