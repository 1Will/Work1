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
package com.yongjun.tdms.workflow.service.job.pojo;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.workflow.dao.job.JobHistoryDao;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.model.job.JobHistory;
import com.yongjun.tdms.workflow.service.job.WfJobHistoryManager;

/**
 * @author qs
 * @version $Id: DefaultWfJobHistoryManager.java 7731 2007-10-09 02:55:18Z wzou $
 */
public class DefaultWfJobHistoryManager extends BaseManager implements
		WfJobHistoryManager {
	private JobHistoryDao jobHistoryDao;
	
	public DefaultWfJobHistoryManager(JobHistoryDao jobHistoryDao) {
		this.jobHistoryDao = jobHistoryDao;
	}
	
	public void makeHistory(Job job) {
		JobHistory history = new JobHistory();
		System.out.println("aaaaaa");
		history.setDocIdentify(job.getDocIdentify());
		history.setDocNo(job.getDocNo());
		history.setComment(job.getComment());
		history.setName(job.getName());
		jobHistoryDao.storeJobHistory(history);
		System.out.println("yyyyyy");
	}

}
