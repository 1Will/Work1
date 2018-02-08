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
package com.yongjun.tdms.dao.year.tooling.quarterPlan.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.quarterPlan.QuarterPlanDao;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;

/**
 * 
 * <p>Title: HibernateQuarterPlan
 * <p>Description: 季度计划数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class HibernateQuarterPlan extends BaseHibernateDao implements
		QuarterPlanDao {

	public QuarterPlan loadQuarterPlan(Long quarterPlanId) {
		return this.load(QuarterPlan.class, quarterPlanId);
	}

	public List<QuarterPlan> loadAllQuarterPlans(Long[] quarterPlanIds) {
		return this.loadAll(QuarterPlan.class, quarterPlanIds);
	}

	public List<QuarterPlan> loadAllQuarterPlans() {
		return this.loadAll(QuarterPlan.class);
	}

	public void storeQuarterPlan(QuarterPlan quarterPlan) {
		this.store(quarterPlan);
	}

	public void deleteQuarterPlan(QuarterPlan quarterPlan) {
		this.delete(quarterPlan);
	}

	public void deleteAllQuarterPlans(Collection<QuarterPlan> quarterPlans) {
		this.deleteAll(quarterPlans);
	}

	public QuarterPlan loadQuarterPlanByDepartIdAndYearAndQuarterId(final Long departmentId, final String year, final String qarterTypeId) {
		return (QuarterPlan) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetQuarterByDepartNameAndYearANDquarter")
								.setParameter("departmentId", departmentId)
								.setParameter("year",year)
								.setParameter("qarterTypeId",qarterTypeId)
								.uniqueResult();
					}
				});
		
	}

	public List loadQuarterPlanListByDepartIdAndYearAndQuarterId(final Long departmentId, final String year, final String qarterTypeId) {
		
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetQuarterListByDepartNameAndYearANDquarter")
								.setParameter("departmentId", departmentId)
								.setParameter("year",year)
								.setParameter("qarterTypeId",qarterTypeId).list();
								
					}
				});
	}

	public List<QuarterPlan> loadListQuarterPlanByDepartIdAndYearAndQuarterId(final Long departmentId, final String year, final String qarterTypeId) {
		return (List<QuarterPlan>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetListQuarterByDepartNameAndYearANDquarter")
								.setParameter("departmentId", departmentId)
								.setParameter("year",year)
								.setParameter("qarterTypeId",qarterTypeId)
								.list();
					}
				});
	}


}
