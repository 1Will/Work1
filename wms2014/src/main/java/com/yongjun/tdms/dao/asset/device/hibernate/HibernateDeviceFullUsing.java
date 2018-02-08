package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.DeviceFullUsingDao;
import com.yongjun.tdms.model.report.DeviceFullUsing;

public class HibernateDeviceFullUsing extends BaseHibernateDao implements DeviceFullUsingDao{

	public void storeDeviceFullUsing(DeviceFullUsing deviceFullUsing) {
		
	}

	public List Query(String month){
		List result=null;
		try {
			String hql = " from DeviceFullUsing as deviceFullUsing where 1=1  AND deviceFullUsing.month = '" +month+"'"; 
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
			String hql = "select distinct deviceFullUsing.month from DeviceFullUsing as deviceFullUsing"; 
			Query query = getSession().createQuery(hql);
			result = query.list();
		}
		 catch (HibernateException e) {
				e.printStackTrace();
			}
		return result;
	}

}
