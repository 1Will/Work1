package com.yongjun.tdms.dao.runmaintenance.calibration.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.calibration.CalibrationDetailViewDao;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetailView;


public class HibernateCalibrationDetailView extends BaseHibernateDao implements CalibrationDetailViewDao{

	public List<CalibrationDetailView> loadAllCalibrationDetailView(String[] array) throws HibernateException {
		List<CalibrationDetailView> result=null;
		Transaction tx = null; 
		Session session = getSession();
        try{
        	String hql="from CalibrationDetailView as detialView where 1=1";
        	if (array[0] != "" && !array[0].equals("")) {
        		 hql = hql + " AND detialView.month = :month";
        	}
        	if (array[1] != "" && !array[1].equals("")) {
        		 hql = hql + " AND detialView.deptName = :department.name" ;
        	}
        	tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (array[0] != "" && !array[0].equals("")) {
			query.setParameter("month", array[0].trim());
			}
			if(array[1] != "" && !array[1].equals("")){
				query.setParameter("department.name",array[1].trim());
			}
	      result=query.list(); 
	      tx.commit();
	      return result;
        } catch(HibernateException e){
        	e.printStackTrace();
        	return null;
        } finally {
        	releaseSession(session);
        }
	}
}
