package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceArgsDao;
import com.yongjun.tdms.model.asset.device.DeviceArgs;

public class HibernateDeviceArgs extends BaseHibernateDao implements
		DeviceArgsDao {

	public List<DeviceArgs> loadAllDeviceArgs(Long[] deviceArgsIds) {
		return this.loadAll(DeviceArgs.class, deviceArgsIds);
	}

	public DeviceArgs loadDeviceArgs(Long deviceArgsId) {
		return this.load(DeviceArgs.class, deviceArgsId);
	}

	public void storeDeviceArgs(DeviceArgs deviceArgs) {
		this.store(deviceArgs);
	}

	public void deleteAllDeviceArgs(Collection<DeviceArgs> deviceArgs) {
		this.deleteAll(deviceArgs);
	}

	public void deleteDeviceArgs(DeviceArgs deviceArg) {
		this.delete(deviceArg);
	}

}
