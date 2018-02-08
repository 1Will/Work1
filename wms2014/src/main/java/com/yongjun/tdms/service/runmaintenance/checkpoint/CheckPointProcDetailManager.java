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

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProcDetail;

/**
 * @author qs
 * @version $Id: CheckPointProcDetailManager.java 8103 2007-10-29 05:24:22Z qsun $
 */
@Transactional(readOnly = true)
public interface CheckPointProcDetailManager {


	@Transactional
	public void storeCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail,CheckPointProc proc);
	
	void loadAllCheckPointProcDetail(CheckPointPlan plan,CheckPointProc proc);
	
	double getPlanExpenseCollection(CheckPointProc proc);
	
	double getActualExpenseCollection(CheckPointProc proc);
	
	public CheckPointProcDetail loadCheckPointProcDetail(Long checkPointProcDetailIds);
	
	@Transactional
	public void deleteCheckPointProcDetail(CheckPointProcDetail checkPointProcDetail);
	
	@Transactional
	public void deleteAllCheckPointProcDetail(Collection<CheckPointProcDetail> checkPointProcDetailIds);
	
	public List<CheckPointProcDetail> loadAllCheckPointProcDetail(Long [] checkPointProcDetailIds);
	
	
}
