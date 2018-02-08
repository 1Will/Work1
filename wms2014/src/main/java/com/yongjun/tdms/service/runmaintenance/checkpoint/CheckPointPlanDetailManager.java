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

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;

/**
 * 
 * @author qs
 * @version $Id: CheckPointPlanDetailManager.java 8103 2007-10-29 05:24:22Z qsun $
 */
@Transactional(readOnly = true)
public interface CheckPointPlanDetailManager {
	@Transactional
	void storePlanDetail(CheckPointPlanDetail planDetail);
	
	double getRuleFee(CheckPointPlanDetail plan);
	
	List<CheckPointPlanDetail> loadAllCheckPointPlanDetail(Long[] checkPointPlanDetailIds);
	
	CheckPointPlanDetail loadCheckPointPlanDetail(Long id);
	
	@Transactional
	void deleteAllCheckPointPlanDetail(List list);
}
