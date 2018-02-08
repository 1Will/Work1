package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceUseStatusDao;
import com.yongjun.tdms.model.report.DeviceUseStatus;

public class HibernateDeviceUseStatus extends BaseHibernateDao implements DeviceUseStatusDao{

	public void storeDeviceUseStatus(DeviceUseStatus deviceUseStatus) {
		this.store(deviceUseStatus);
	}
	
	public List Query(String month){
		List result=null;
		try {
			String hql = " from DeviceUseStatus as deviceUseStatus where 1=1  AND deviceUseStatus.month = '" +month+"'  order by deviceUseStatus.filaileId asc "; 
			Query query = getSession().createQuery(hql);
			result = query.list();
		}
		 catch (HibernateException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public List getMonths() {
		List result=null;
		try {
			String hql = "select distinct deviceUseStatus.month from DeviceUseStatus as deviceUseStatus"; 
			Query query = getSession().createQuery(hql);
			result = query.list();
		}
		 catch (HibernateException e) {
				e.printStackTrace();
			}
		return result;
	}

}
