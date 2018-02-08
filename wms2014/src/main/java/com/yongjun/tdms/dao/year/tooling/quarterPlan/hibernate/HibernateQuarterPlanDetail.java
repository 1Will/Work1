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
import com.yongjun.tdms.dao.year.tooling.quarterPlan.QuarterPlanDetailDao;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;

/**
 * <p>Title: HibernateQuarterPlanDetail
 * <p>Description: 季度计划明细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDetailDao
 * @version $Id:$
 */
public class HibernateQuarterPlanDetail extends BaseHibernateDao implements
		QuarterPlanDetailDao {

	public void deleteAllQuarterPlanDetails(
			Collection<QuarterPlanDetail> quarterPlanDetails) {
		this.deleteAll(quarterPlanDetails);
	}

	public void deleteQuarterPlanDetail(QuarterPlanDetail quarterPlanDetail) {
		this.delete(quarterPlanDetail);
	}

	public List<QuarterPlanDetail> loadAllQuarterPlanDetails(
			Long[] quarterPlanDetailIds) {
		return this.loadAll(QuarterPlanDetail.class, quarterPlanDetailIds);
	}

	public List<QuarterPlanDetail> loadAllQuarterPlanDetails() {
		return this.loadAll(QuarterPlanDetail.class);
	}

	public QuarterPlanDetail loadQuarterPlanDetail(Long quarterPlanDetailId) {
		return this.load(QuarterPlanDetail.class, quarterPlanDetailId);
	}

	public void storeQuarterPlanDetail(QuarterPlanDetail quarterPlanDetail) {
		this.store(quarterPlanDetail);
	}

	public Double getAllQuarterPlanDetailFeeByQuarterPlanId(final Long quarterPlanId, final String detailType) {
		return (Double) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllQuarterPlanDetailFeeByQuarterPlanId")
								.setParameter("quarterPlanId", quarterPlanId)
								.setParameter("detailType", detailType)
								.uniqueResult();
					}
				});
	}

}
