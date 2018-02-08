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
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

/**
 * @author qs
 * @version $Id: CheckPointProcManager.java 8103 2007-10-29 05:24:22Z qsun $
 */
@Transactional(readOnly = true)
public interface CheckPointProcManager {

	@Transactional
	public void storeCheckPointProc(CheckPointProc Proc,CheckPointPlan plan);
	
	@Transactional
	public CheckPointProc loadCheckPointProc(Long checkPointProcIds);
	@Transactional
	double getPlanExpenseCollection(CheckPointProc proc);
	@Transactional
	double getActualExpenseCollection(CheckPointProc proc);
	
	@Transactional
	public void deleteCheckPointProc(CheckPointProc checkPointProc) throws WFJobInProcException;
	
	@Transactional
	public void deleteAllCheckPointProc(List<CheckPointProc> checkPointProcs) throws WFJobInProcException;
	
	@Transactional
	public List<CheckPointProc> loadAllCheckPointProc(Long [] checkPointProcIds);
	
	@Transactional
	void cancelJob(CheckPointProc proc);
	
	
}
