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
package com.yongjun.tdms.service.runmaintenance.checkpoint;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

/**
 * @author qs
 * @version $Id: CheckPointPlanManager.java 7925 2007-10-22 02:37:55Z qsun $
 */
@Transactional(readOnly = true)
public interface CheckPointPlanManager {
	@Transactional
	void storePlan(CheckPointPlan plan);
	
	@Transactional
	void storePlan(CheckPointPlan plan, CheckPointRule rule);
	
	void updateCheckPointPlanStatus(CheckPointPlan checkPointPlan,int status);
	
	List<CheckPointPlan> loadAllCheckPointPlan(Long[] checkPointPlanIds);

	CheckPointPlan loadPlan(Long id);
	
	@Transactional
	void deleteAllCheckPointPlan(List list) throws WFJobInProcException;
	
	List<CheckPointPlan> loadAllUnrelatedCheckPointPlans();

	@Transactional
	void ceatePlanByScheduler();
	
	@Transactional
	void cancelJob(CheckPointPlan plan);
	
	public CheckPointPlan ceatePlanByPlanId(Long planId);
}
