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
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointReportDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;

/**
 * @author wzou
 * @version $Id: HibernateCheckPointPlan.java 7925 2007-10-22 02:37:55Z qsun $
 */
public class HibernateCheckPointReport extends BaseHibernateDao implements CheckPointReportDao{

	public CheckPointReport loadCheckPointReport(Long checkPointReportID) {
		return load(CheckPointReport.class,checkPointReportID);
	}

	public void storeCheckPointReport(CheckPointReport report) {
		this.store(report);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> loadAllIdsByMonth(final String month) {
		return (List<Long>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllIdsByMonth")
								.setParameter("month", month).list();
					}
				});
	}

}
