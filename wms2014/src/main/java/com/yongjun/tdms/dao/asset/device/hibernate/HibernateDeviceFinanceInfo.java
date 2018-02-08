package com.yongjun.tdms.dao.asset.device.hibernate;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceFinanceInfoDao;
import com.yongjun.tdms.model.asset.device.DeviceFinanceInfo;

public class HibernateDeviceFinanceInfo extends BaseHibernateDao implements DeviceFinanceInfoDao {

	public void storeDeviceFinanceInfo(DeviceFinanceInfo deviceFinanceInfo) {
		this.store(deviceFinanceInfo);
	}

	public DeviceFinanceInfo loadDeviceFinanceInfo(Long deviceFinanceInfoId) {
		return this.load(DeviceFinanceInfo.class, deviceFinanceInfoId);
	}

}
