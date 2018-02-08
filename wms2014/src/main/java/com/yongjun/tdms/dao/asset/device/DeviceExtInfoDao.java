package com.yongjun.tdms.dao.asset.device;

import com.yongjun.tdms.model.asset.device.DeviceExtInfo;

/**
 * @author qs
 * @version $Id: DeviceExtInfoDao.java 7167 2007-09-11 00:40:01Z qsun $
 */
public interface DeviceExtInfoDao {
	public void storeDeviceExtInfo(DeviceExtInfo deviceExtInfo);
	
	public DeviceExtInfo loadDeviceExtInfo(Long deviceExtInfoId);
}
