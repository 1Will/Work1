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
package com.yongjun.tdms.dao.runmaintenance.checkpoint.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointReportDetailDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;

/**
 * @author wzou
 * @version $Id: HibernateCheckPointPlan.java 7925 2007-10-22 02:37:55Z qsun $
 */
public class HibernateCheckPointReportDetail extends BaseHibernateDao implements CheckPointReportDetailDao{

	public List<CheckPointReportDetail> loadAllCheckPointReportDetails(Long[] checkPointReportDetailID) {
		return this.loadAll(CheckPointReportDetail.class,checkPointReportDetailID);
	}

	public void storeCheckPointReportDetail(CheckPointReportDetail checkPointReportDetail) {
		this.store(checkPointReportDetail);
	}

	public CheckPointReportDetail loadCheckPointReportDetail(Long checkPointReportDetailID) {
		return this.load(CheckPointReportDetail.class,checkPointReportDetailID);
	}

	public void deleteAllCheckPointReportDetails(Collection<CheckPointReportDetail> details) {
		this.deleteAll(details);
	}
	
	@SuppressWarnings("unchecked")
	public CheckPointReportDetail loadDetailBydeviceID(final Long id,final String month) {
		return (CheckPointReportDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetDetailBydeviceId")
								.setParameter("id", id).setParameter("month", month).uniqueResult();
					}
				});
	}
}
