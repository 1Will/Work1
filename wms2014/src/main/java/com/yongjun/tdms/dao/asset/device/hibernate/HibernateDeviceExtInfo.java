package com.yongjun.tdms.dao.asset.device.hibernate;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceExtInfoDao;
import com.yongjun.tdms.model.asset.device.DeviceExtInfo;

public class HibernateDeviceExtInfo extends BaseHibernateDao implements DeviceExtInfoDao {

	public void storeDeviceExtInfo(DeviceExtInfo deviceExtInfo) {
		this.store(deviceExtInfo);
	}

	public DeviceExtInfo loadDeviceExtInfo(Long deviceExtInfoId) {
		return this.load(DeviceExtInfo.class, deviceExtInfoId);
	}

}
