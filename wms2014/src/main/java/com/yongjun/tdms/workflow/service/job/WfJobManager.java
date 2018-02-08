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
package com.yongjun.tdms.workflow.service.job;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.workflow.model.approver.JobApprover;
import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.model.job.Job;


/**
 * @author qs
 * @version $Id: WfJobManager.java 7925 2007-10-22 02:37:55Z qsun $
 */
@Transactional(readOnly=true)
public interface WfJobManager {
	
	/**
	 * 提交一个文档到工作流
	 * @param pojo 文档
	 * @param approverIds	审核人
	 * @param finalApproverId 最终审核人
	 * @param comment 提交备注
	 * @param docNo 文档号
	 * @param docName 文档名称
	 * @throws Exception
	 */
	@Transactional
	Job submitJob(BaseInfoEntity pojo, Long[] approverIds, Long finalApproverId,
			String comment, String docNo, String docName) throws Exception;
	
	/**
	 * 判断文档是否已经被提交
	 * @param pojo 文档
	 * @return true 已经提交，false 未提交
	 */
	boolean isJobSubmitted(BaseInfoEntity pojo);
	
	@Deprecated
	void approveJob(JobApprover approver);

	/**
	 * 审核一份文档
	 * @param job 文档
	 * @param docState 文档状态
	 */
	@Transactional
	void approveJob(Job job, DocState docState);

	/**
	 * 取消提交的文档
	 * @param pojo 文档
	 */
	@Transactional
	void cancelJob(BaseInfoEntity pojo);
	
	/**
	 * 根据文档ID，获取文档
	 * @param id 文档ID
	 * @return Job
	 */
	Job loadJobById(Long id);

	/**
	 * 根据文档，获得能审核文档的审核人名称
	 * @param pojo 文档
	 * @return String
	 */
	String getJobApprovers(BaseInfoEntity pojo);

	/**
	 * 根据文档，获得最终审核人ID
	 * @param pojo 文档
	 * @return Long
	 */
	Long getJobFinalApproverId(BaseInfoEntity pojo);

	/**
	 * 根据文档，获得除当前登陆用户外其它审核人
	 * @param job 文档
	 * @return List<JobApprover>
	 */
	List<JobApprover> loadApproversExceptMe(Job job);

	/**
	 * 根据文档，获得一个工作流对象?
	 * @param pojo 文档
	 * @return Job
	 */
	Job getJob(BaseInfoEntity pojo);

	boolean isInProc(List list);
	
}
