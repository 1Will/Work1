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
package com.yongjun.tdms.workflow.dao.approver.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.workflow.dao.approver.JobApproverDao;
import com.yongjun.tdms.workflow.model.approver.JobApprover;
import com.yongjun.tdms.workflow.model.job.Job;

/**
 * @author qs
 * @version $Id: HibernateJobApprover.java 7925 2007-10-22 02:37:55Z qsun $
 */
public class HibernateJobApprover extends BaseHibernateDao implements JobApproverDao {

	public void storeJobApprover(JobApprover approver) {
		this.store(approver);
	}
    
	public JobApprover loadJobApprover(Long id){
		return this.load(JobApprover.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<JobApprover> loadApproversExceptSpecialOne(final Job job, final User user) {
		return (List<JobApprover>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadApproversExceptSpecialOne")
								.setParameter("jobId", job.getId())
								.setParameter("userId", user.getId())
								.list();
					}
				});
	}

}
