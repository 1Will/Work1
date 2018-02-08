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
package com.yongjun.tdms.workflow.dao.job.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.workflow.dao.job.JobDao;
import com.yongjun.tdms.workflow.model.job.Job;

/**
 * @author qs
 * @version $Id: HibernateJob.java 7690 2007-10-08 00:54:39Z qsun $
 */
public class HibernateJob extends BaseHibernateDao implements JobDao{
	
	public void storeJob(Job job) {
		this.store(job);
	}

	public Job loadJobById(Long id) {
		return this.load(Job.class, id);
	}
	
	public Job loadJobByDocIdAndType(final Long id, final String type) {
		return (Job) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadJobByDocIdAndType")
								.setParameter("docId", id)
								.setParameter("docType", type)
								.uniqueResult();
					}
				});
	}

	
	public void deleteJob(Job job) {
		this.delete(job);
	}	
}
