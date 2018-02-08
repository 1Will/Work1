/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.dao.year.tooling.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.YearPlanDetailViewDao;
import com.yongjun.tdms.model.year.tooling.YearPlanDetailView;

/**
 * <p>Title: EditDailyRepairAction
 * <p>Description: 运维计划的日常维修维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class HibernateYearPlanDetailView extends BaseHibernateDao implements
		YearPlanDetailViewDao {

	/* (non-Javadoc)
	 * @see com.yongjun.tdms.dao.year.tooling.YearPlanDetailDao#loadYearPlanDetail(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<YearPlanDetailView> loadYearPlanDetail(final Long yearPlanId) {
		return (List<YearPlanDetailView>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetYEARPLANDETAILBYYEARPLANID")
								.setParameter("yearPlanId", yearPlanId).list();
					}
				});
	}
//	@SuppressWarnings("unchecked")
//	public List<YearPlanDetailView> loadYearPlanDetailYear(final String year,
//			final String departName) {
//		return (List<YearPlanDetailView>) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						return session.getNamedQuery("GetYearPlanDetailYear")
//								.setParameter("year", year).setParameter(
//										"departName", departName).list();
//					}
//				});
//	}
	public List<YearPlanDetailView> loadYearPlanDetailView(String[] array) throws HibernateException{
		List<YearPlanDetailView> result=null;
		Transaction tx = null; 
		Session session = getSession();
        try{
        	String hql="from YearPlanDetailView as detialView where 1=1";
        	if (array[0] != ""&&!array[0].equals("")) {
        		 hql = hql + " AND detialView.year =:year";
        	}
        	if (array[1] != ""&&!array[1].equals("")) {
        		 hql = hql + " AND detialView.deptName =:department.name" ;
        		 
        	}
        	hql = hql + " ORDER BY detialView.deptName,detialView.detailType";
        	tx = session.beginTransaction();
            Query query = getSession().createQuery(hql);
            if (array[0] != ""&&!array[0].equals("")) {
			    query.setParameter("year", array[0].trim());
			}
			if(array[1] != ""&&!array[1].equals("")){
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
	
//	@SuppressWarnings("unchecked")
//	public List<YearPlanDetailView> loadYearPlanDetailAndYear(final String year) {
//		return (List<YearPlanDetailView>) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						return session.getNamedQuery("GetYearPlanDetailAndYear")
//								.setParameter("year", year).
//										list();
//					}
//				});
//	}
	
}
