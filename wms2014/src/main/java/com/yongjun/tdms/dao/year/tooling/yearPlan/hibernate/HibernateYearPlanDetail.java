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
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDetailDao;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;

/**
 * <p>Title: HibernateYearPlanDetail
 * <p>Description: 年度计划明细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDetailDao
 * @version $Id:$
 */
public class HibernateYearPlanDetail extends BaseHibernateDao implements
		YearPlanDetailDao {

	public YearPlanDetail loadYearPlanDetail(Long yearPlanDetailId) {
		return this.load(YearPlanDetail.class, yearPlanDetailId);
	}

	public List<YearPlanDetail> loadAllYearPlanDetails(Long[] yearPlanDetailIds) {
		return this.loadAll(YearPlanDetail.class, yearPlanDetailIds);
	}

	public List<YearPlanDetail> loadAllYearPlanDetails() {
		return this.loadAll(YearPlanDetail.class);
	}

	public void storeYearPlanDetail(YearPlanDetail yearPlanDetail) {
		this.store(yearPlanDetail);
	}

	public void deleteYearPlanDetail(YearPlanDetail yearPlanDetail) {
		this.delete(yearPlanDetail);
	}

	public void deleteAllYearPlanDetails(
			Collection<YearPlanDetail> yearPlanDetails) {
		this.deleteAll(yearPlanDetails);
	}

	public Double getAllPriceByDetailTypeAndYearPlanId(final String detailType, final Long yearPlanId) {
		return (Double) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllPriceByDetailTypeAndYearPlanId")
								.setParameter("yearPlanId", yearPlanId)
								.setParameter("detailType", detailType.trim())
								.uniqueResult();
					}
				});
	}

}
