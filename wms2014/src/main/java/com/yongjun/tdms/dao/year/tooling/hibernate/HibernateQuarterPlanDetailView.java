package com.yongjun.tdms.dao.year.tooling.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.QuarterPlanDetailViewDao;
import com.yongjun.tdms.model.year.tooling.QuarterPlanDetailView;
public class HibernateQuarterPlanDetailView extends BaseHibernateDao implements QuarterPlanDetailViewDao{
	@SuppressWarnings("unchecked")
	public List<QuarterPlanDetailView> loadQuarterPlanDetail(final Long quarterPlanId) {
		return (List<QuarterPlanDetailView>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetQuarterPLANDETAILBYYEARPLANID")
								.setParameter("quarterPlanId", quarterPlanId).list();
					}
				});
		
	}

	public List<QuarterPlanDetailView> loadQuarterPlanDetailView(String[] array) throws HibernateException {
		Session session = getSession();
		Transaction tx = null; 
		List<QuarterPlanDetailView> result=null;
        try{
        	String hql="from QuarterPlanDetailView as detialView where 1=1";
        	if (array[0] != ""&&!array[0].equals("")) {
        		 hql = hql + " AND detialView.year =:year";
        	}
        	if (array[1] != ""&&!array[1].equals("")) {
        		 hql = hql + " AND detialView.deptName like :department.name" ;
        	}
        	if(array[2] !=""&&!array[2].equals("")){
        		 hql = hql + " AND detialView.qarterType like :qarterType" ;
        	}
        	hql = hql + " ORDER BY detialView.deptName , detialView.detailType";
        	tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (array[0] != ""&&!array[0].equals("")) {
			    query.setParameter("year", array[0].trim());
			}
			if(array[1] != ""&&!array[1].equals("")){
				query.setParameter("department.name",'%' + array[1].trim() + '%');
			}
			if(array[2] != ""&&!array[2].equals("")){
				query.setParameter("qarterType",'%' + array[2].trim() + '%');
			}
	      result=query.list();
	      tx.commit();
	      return result;
        } catch(HibernateException e){
        	e.printStackTrace();
        	return null;
        } finally {
        	//session.close();
        	releaseSession(session);
        }
	}
}
