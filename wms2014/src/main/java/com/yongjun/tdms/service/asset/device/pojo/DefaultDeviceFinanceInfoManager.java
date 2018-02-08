package com.yongjun.tdms.service.asset.device.pojo;

import com.yongjun.tdms.dao.asset.device.DeviceFinanceInfoDao;
import com.yongjun.tdms.model.asset.device.DeviceFinanceInfo;
import com.yongjun.tdms.service.asset.device.DeviceFinanceInfoManager;

public class DefaultDeviceFinanceInfoManager implements
		DeviceFinanceInfoManager {
	private final DeviceFinanceInfoDao deviceFinanceInfoDao;
	
	public DefaultDeviceFinanceInfoManager(DeviceFinanceInfoDao deviceFinanceInfoDao) {
		this.deviceFinanceInfoDao = deviceFinanceInfoDao;
	}
	
	public void storeDeviceFinanceInfo(DeviceFinanceInfo deviceFinanceInfo) {
		this.deviceFinanceInfoDao.storeDeviceFinanceInfo(deviceFinanceInfo);
	}

	public DeviceFinanceInfo loadDeviceFinanceInfo(Long deviceFinanceInfoId) {
		return this.deviceFinanceInfoDao.loadDeviceFinanceInfo(deviceFinanceInfoId);
	}

}
