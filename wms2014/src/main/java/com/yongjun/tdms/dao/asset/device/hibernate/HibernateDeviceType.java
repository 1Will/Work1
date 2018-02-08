package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceTypeDao;
import com.yongjun.tdms.model.asset.device.DeviceType;

/**
 * @author qs
 * @version $Id: HibernateDeviceType.java 7166 2007-09-11 00:39:42Z qsun $
 */
public class HibernateDeviceType extends BaseHibernateDao implements
		DeviceTypeDao {

	public List<DeviceType> loadAllDeviceTypes() {
		return this.loadAll(DeviceType.class);
	}

	public DeviceType loadDeviceType(Long id) {
		return this.load(DeviceType.class, id);
	}

}
