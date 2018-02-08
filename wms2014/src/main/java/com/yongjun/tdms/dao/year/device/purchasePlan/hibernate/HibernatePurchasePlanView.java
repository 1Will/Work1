package com.yongjun.tdms.dao.year.device.purchasePlan.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.device.purchasePlan.PurchasePlanViewDao;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanView;

public class HibernatePurchasePlanView extends BaseHibernateDao implements PurchasePlanViewDao{

	public List<PurchasePlanView> loadAllPurchasePlanView(String[] array) throws HibernateException{
		List<PurchasePlanView> result=null;
		Transaction tx = null; 
		Session session = getSession();
        try{
        	String hql="from PurchasePlanView as detialView where 1=1";
        	if (array[0] != "" && !array[0].equals("")) {
        		 hql = hql + " AND detialView.year =:year";
        	}
        	if (array[1] != "" && !array[1].equals("")) {
        		 hql = hql + " AND detialView.deptName =:department.name" ;
        	}
        	hql = hql + " ORDER BY detialView.deptName ";
        	tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (array[0] != "" && !array[0].equals("")) {
			query.setParameter("year", array[0].trim());
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
