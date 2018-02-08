package com.yongjun.tdms.dao.asset.device;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.device.AccessoryDevice;

/**
 * @author qs
 * @version $Id: AccessoryDeviceDao.java 7940 2007-10-22 08:50:11Z qsun $
 */
public interface AccessoryDeviceDao {
	
	AccessoryDevice loadAccessoryDevice(Long accessoryDeviceId);
	
	List<AccessoryDevice> loadAllAccessoryDevice(Long [] accessoryDeviceIds);
	
	void storeAccessoryDevice(AccessoryDevice accessoryDevice);
	
	void deleteAccessoryDevice(AccessoryDevice accessoryDevice);
	
	void deleteAllAccessoryDevice(Collection<AccessoryDevice> accessoryDevices);
} 
