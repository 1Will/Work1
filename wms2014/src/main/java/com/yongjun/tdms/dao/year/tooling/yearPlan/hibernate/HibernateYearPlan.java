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
package com.yongjun.tdms.dao.year.tooling.yearPlan.hibernate;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDao;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
/**
 * <p>Title: HibernateYearPlan
 * <p>Description: 年度计划数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDao
 * @version $Id:  $
 */
public class HibernateYearPlan extends BaseHibernateDao implements YearPlanDao {

	public YearPlan loadYearPlan(Long yearPlanId) {
		return this.load(YearPlan.class, yearPlanId);
	}

	public List<YearPlan> loadAllYearPlans(Long[] yearPlanIds) {
		return this.loadAll(YearPlan.class, yearPlanIds);
	}

	public List<YearPlan> loadAllYearPlans() {
		return this.loadAll(YearPlan.class);
	}

	public void storeYearPlan(YearPlan yearPlan) {
		this.store(yearPlan);
	}

	public void deleteYearPlan(YearPlan yearPlan) {
		this.delete(yearPlan);
	}

	public void deleteAllYearPlans(Collection<YearPlan> yearPlans) {
		this.deleteAll(yearPlans);
	}

	public List<Long> loadYearPlan(String[] array) throws HibernateException{
		List<Long> result=null;
        try{
        	
        	String hql="select yearPlan.id from YearPlan as yearPlan where 1=1";
        	if (array[0] != "") {
        		 hql = hql + " AND yearPlan.year =:year";
        		
        	}
        	if (array[1] != "") {
        		 hql = hql + " AND yearPlan.department.id =:department.id" ;
        	}
            Query query = getSession().createQuery(hql);
            if (array[0] != "") {
			query.setParameter("year", array[0].trim());
			}
			if(array[1] != ""){
				query.setParameter("department.id",Long.valueOf(array[1].trim()));
			}
	      result=query.list(); 
        } catch(HibernateException e){
        	e.printStackTrace();
        }
		return result;
	}
	public YearPlan loadYearPlanByDepartNameANDYear(final Long departmentId, final String year) {
		return (YearPlan) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetByDepartNameAndYear")
								.setParameter("departmentId", departmentId).setParameter("year",year).uniqueResult();
					}
				});}

//	public YearPlan loadYearPlanByDepartNameANDYear(final Long departmentId, final String year) {
//		@SuppressWarnings("unchecked")
//			return (YearPlan) this.getHibernateTemplate().execute(
//					new HibernateCallback() {
//						public Object doInHibernate(Session session)
//								throws HibernateException, SQLException {
//							return session.getNamedQuery("GetByDepartNameAndYear")
//									.setParameter("departmentId", departmentId).setParameter("year",year).uniqueResult();
//						}
//					});
//			
//		return YearPlan;
//	}
}


