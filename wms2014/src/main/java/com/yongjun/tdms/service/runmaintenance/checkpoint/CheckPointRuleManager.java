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

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

/**
 * @author qs
 * @version $Id: CheckPointRuleManager.java 7962 2007-10-23 07:03:35Z qsun $
 */
@Transactional(readOnly = true)
public interface CheckPointRuleManager {
	@Transactional
	void storeRule(CheckPointRule Rule);
	
	List<CheckPointRule> loadAllCheckPointRule(Long[] checkPointRuIds);
	
	CheckPointRule loadRule(Long id);
	
	CheckPointRuleDetail loadCheckPointRuleDetail(Long id);
	
	@Transactional
	void deleteAllCheckPointRule(List list) throws WFJobInProcException;
	
	List<Long> searchRuleTypeId(final Long deviceId);
	
	CheckPointRule GetCheckPointRuleByIdAndTypeId(final Long ruleId, final Long deviceId, final Long ruleTypeId);

	@Transactional
	void cancelJob(CheckPointRule rule);
}
