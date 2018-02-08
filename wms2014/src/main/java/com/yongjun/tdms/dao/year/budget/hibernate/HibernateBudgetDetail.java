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
package com.yongjun.tdms.dao.year.budget.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.budget.BudgetDetailDao;
import com.yongjun.tdms.model.year.budget.BudgetDetail;

/**
 * 
 * <p>Title: HibernateBudgetDetail
 * <p>Description: 年度预算详细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.budget.BudgetDetailDao
 * @version $Id:$
 */
public class HibernateBudgetDetail extends BaseHibernateDao implements
		BudgetDetailDao {

	public BudgetDetail loadBudgetDetail(Long budgetDetailId) {
		return this.load(BudgetDetail.class, budgetDetailId);
	}

	public List<BudgetDetail> loadAllBudgetDetails(Long[] budgetDetailIds) {
		return this.loadAll(BudgetDetail.class, budgetDetailIds);
	}

	public List<BudgetDetail> loadAllBudgetDetails() {
		return this.loadAll(BudgetDetail.class);
	}

	public void storeBudgetDetail(BudgetDetail budgetDetail) {
		this.store(budgetDetail);
	}

	public void deleteBudgetDetail(BudgetDetail budgetDetail) {
		this.delete(budgetDetail);
	}

	public void deleteAllBudgetDetails(Collection<BudgetDetail> budgetDetails) {
		this.deleteAll(budgetDetails);
	}

	@SuppressWarnings("unchecked")
	public List<String> loadAllBudgetNoOfEnabled() {
			return (List<String>) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							return session.getNamedQuery("GetAllBudgetNoOfEnabled")
									.list();
						}
					});
	}

	public BudgetDetail getBudgetDetailByDepartmentAndBudget(final Long departmentId, final Long budgetId, final String budgetNo) {
		return (BudgetDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetYearBudgetByDepartmenyBudgetAndBudgetNo")
								.setParameter("departmentId", departmentId)
								.setParameter("budgetId", budgetId)
								.setParameter("budgetNo", budgetNo.trim())
								.uniqueResult();
					}
				});
	}

	public BudgetDetail getYearBudgetByDepartmentAndBudgetNoAndYear(final Long departmentId, final String budgetNo, final String year) {
		return (BudgetDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetYearBudgetByDepartmentAndBudgetNoAndYear")
								.setParameter("departmentId", departmentId)
								.setParameter("budgetNo", budgetNo.trim())
								.setParameter("year", year.trim())
								.uniqueResult();
					}
				});
	}

	public BudgetDetail getYearBudgetDetailByBudgetNo(final String budgetNo) {
		return (BudgetDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetYearBudgetDetailByBudgetNo")
								.setParameter("budgetNo", budgetNo)
								.uniqueResult();
					}
				});
	}

}
