package com.yongjun.tdms.dao.asset.device;

import java.util.List;

import com.yongjun.tdms.model.asset.device.DeviceType;

/**
 * @author qs
 * @version $Id: DeviceTypeDao.java 7166 2007-09-11 00:39:42Z qsun $
 */
public interface DeviceTypeDao {
	List<DeviceType> loadAllDeviceTypes();
	
	DeviceType loadDeviceType(Long id);
}
