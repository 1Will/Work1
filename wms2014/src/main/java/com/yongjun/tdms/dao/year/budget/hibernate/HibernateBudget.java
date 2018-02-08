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
import com.yongjun.tdms.dao.year.budget.BudgetDao;
import com.yongjun.tdms.model.year.budget.Budget;

/**
 * 
 * <p>Title: HibernateBudget
 * <p>Description: 年度预算数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.budget.BudgetDao
 * @version $Id:$
 */
public class HibernateBudget extends BaseHibernateDao implements BudgetDao {

	public Budget loadBudget(Long budgetId) {
		return this.load(Budget.class, budgetId);
	}

	public List<Budget> loadAllBudgets(Long[] budgetIds) {
		return this.loadAll(Budget.class, budgetIds);
	}

	public List<Budget> loadAllBudgets() {
		return this.loadAll(Budget.class);
	}

	public void storeBudget(Budget budget) {
		this.store(budget);
	}

	public void deleteBudget(Budget budget) {
		this.delete(budget);
	}

	public void deleteAllBudgets(Collection<Budget> budgets) {
		this.deleteAll(budgets);
	}

	public Budget getYearBudgetByYear(final String year) {
		return (Budget) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetYearBudgetByYear")
						.setParameter("year",year.trim())
						.uniqueResult();
					}
				});
	}

}
