package com.yongjun.tdms.service.asset.device.pojo;

import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.device.DeviceTypeDao;
import com.yongjun.tdms.model.asset.device.DeviceType;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;

/**
 * @author qs
 * @version $Id: DefaultDeviceTypeManager.java 7962 2007-10-23 07:03:35Z qsun $
 */
public class DefaultDeviceTypeManager extends BaseManager implements
		DeviceTypeManager {
	private final DeviceTypeDao deviceTypeDao;
	
	public DefaultDeviceTypeManager(DeviceTypeDao deviceTypeDao) {
		this.deviceTypeDao = deviceTypeDao;
	}
	
	public List<DeviceType> loadAllDeviceTypes() {
		return deviceTypeDao.loadAllDeviceTypes();
	}

	public DeviceType loadDeviceType(Long id) {
		return deviceTypeDao.loadDeviceType(id);
	}

	public List createSelectDeviceTypes(String label) {
		List<DeviceType> list = deviceTypeDao.loadAllDeviceTypes();
		DeviceType type = new DeviceType();
		type.setId(Long.valueOf(-1L));
		type.setName(label);
		list.add(0, type);
		return list;
	}

}
