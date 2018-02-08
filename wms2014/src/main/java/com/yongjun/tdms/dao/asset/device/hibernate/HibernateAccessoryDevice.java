package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.AccessoryDeviceDao;
import com.yongjun.tdms.model.asset.device.AccessoryDevice;

/**
 * @author qs
 * @version $Id: HibernateAccessoryDevice.java 7940 2007-10-22 08:50:11Z qsun $
 */
public class HibernateAccessoryDevice extends BaseHibernateDao implements
		AccessoryDeviceDao {

	public AccessoryDevice loadAccessoryDevice(Long accessoryDeviceId) {
		return this.load(AccessoryDevice.class, accessoryDeviceId);
	}

	public void storeAccessoryDevice(AccessoryDevice accessoryDevice) {
		this.store(accessoryDevice);
	}

	public void deleteAccessoryDevice(AccessoryDevice accessoryDevice) {
		this.delete(accessoryDevice);
	}

	public void deleteAllAccessoryDevice(Collection<AccessoryDevice> accessoryDevices) {
		this.deleteAll(accessoryDevices);
	}

	public List<AccessoryDevice> loadAllAccessoryDevice(Long[] accessoryDeviceIds) {
		return this.loadAll(AccessoryDevice.class, accessoryDeviceIds);
	}

}
