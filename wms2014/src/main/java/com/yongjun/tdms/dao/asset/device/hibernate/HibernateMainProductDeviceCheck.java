package com.yongjun.tdms.dao.asset.device.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.device.MainProductDeviceCheckDao;
import com.yongjun.tdms.model.report.MainProductDeviceCheck;

public class HibernateMainProductDeviceCheck extends BaseHibernateDao implements  MainProductDeviceCheckDao{
         
	public void storeMainProductDeviceCheck(MainProductDeviceCheck mainProductDeviceCheck) {
		this.store(mainProductDeviceCheck);
		
	}

	public List Query(String month){
		List result=null;
		try {
			String hql = "from MainProductDeviceCheck as mainProductDeviceCheck where 1=1  AND mainProductDeviceCheck.month = '" +month+"' order by mainProductDeviceCheck.filaileId asc"; 
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
			String hql = "select distinct mainProductDeviceCheck.month from MainProductDeviceCheck as mainProductDeviceCheck"; 
			Query query = getSession().createQuery(hql);
			result = query.list();
		}
		 catch (HibernateException e) {
				e.printStackTrace();
			}
		return result;
	}

}
