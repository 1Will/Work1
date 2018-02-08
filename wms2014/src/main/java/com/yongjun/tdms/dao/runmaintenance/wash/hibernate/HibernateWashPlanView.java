package com.yongjun.tdms.dao.runmaintenance.wash.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.wash.WashPlanViewDao;
import com.yongjun.tdms.model.runmaintenance.wash.WashPlanView;



public class HibernateWashPlanView extends BaseHibernateDao implements WashPlanViewDao{

	public List<WashPlanView> loadAllWashPlanView(String[] array) throws HibernateException {
		List<WashPlanView> result=null;
		Transaction tx = null; 
		Session session = getSession();
        try{
        	String hql="from WashPlanView as detialView where 1=1";
        	if (array[0] != "" && !array[0].equals("")) {
        		 hql = hql + " AND detialView.month >=:palnBeginDate_start";
        	}
        	if (array[1] != "" && !array[1].equals("")) {
       		 	 hql = hql + " AND detialView.month <=:palnBeginDate_end";
       		}
        	if (array[2] != "" && !array[2].equals("")) {
        		 hql = hql + " AND detialView.deptName = :department.name" ;
        	}
        	tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (array[0] != "" && !array[0].equals("")) {
            	query.setParameter("palnBeginDate_start", array[0].trim());
			}
            if (array[1] != "" && !array[1].equals("")) {
            	query.setParameter("palnBeginDate_end", array[1].trim());
			}
			if(array[2] != "" && !array[2].equals("")){
				query.setParameter("department.name",array[2].trim());
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
