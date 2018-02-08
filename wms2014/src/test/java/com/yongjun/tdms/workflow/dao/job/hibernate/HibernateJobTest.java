package com.yongjun.tdms.workflow.dao.job.hibernate;

import com.yongjun.tdms.dao.asset.device.hibernate.BaseHibernateDaoTestCase;
import com.yongjun.tdms.workflow.dao.job.JobDao;

/**
 * @author qs
 * @version $Id: HibernateJobTest.java 8121 2007-10-30 03:37:41Z qsun $
 */
public class HibernateJobTest extends BaseHibernateDaoTestCase {
	private JobDao jobDao;
	
	public void setUp() {
		super.setUp();
		init("com/yongjun/tdms/workflow/dao/job/dbunit/job.xml");
		this.jobDao = (JobDao)ctx.getBean("wfJobDao");		
	}
	
	public void testNotNull() {
		assertNotNull(jobDao);
	}
}
