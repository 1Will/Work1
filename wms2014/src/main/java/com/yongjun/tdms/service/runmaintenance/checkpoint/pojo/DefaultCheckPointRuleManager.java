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
package com.yongjun.tdms.service.runmaintenance.checkpoint.pojo;

import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointRuleDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleManager;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: DefaultCheckPointRuleManager.java 11138 2008-02-27 09:40:47Z zbzhang $
 */
public class DefaultCheckPointRuleManager implements CheckPointRuleManager {
	private final CheckPointRuleDao checkPointRuleDao;
	private final SequenceManager sequenceManager;
	private final WfJobManager wfJobManager;
	
	public DefaultCheckPointRuleManager(CheckPointRuleDao checkPointPlanDao,
			SequenceManager sequenceManager, WfJobManager wfJobManager) {
		this.checkPointRuleDao = checkPointPlanDao;
		this.sequenceManager = sequenceManager;
		this.wfJobManager = wfJobManager;
	}

	public void storeRule(CheckPointRule rule) {
		if (rule.isNew()) {
			String ruleNo = (String)sequenceManager.generate("-");
			//rule.setRuleNo(ruleNo);
		}
		checkPointRuleDao.storeRule(rule);	
	}

	public CheckPointRule loadRule(Long id) {
		return this.checkPointRuleDao.loadRule(id);
	}

	public CheckPointRuleDetail loadCheckPointRuleDetail(Long id) {
		return this.checkPointRuleDao.loadCheckPointRuleDetail(id);
	}

	public void deleteAllCheckPointRule(List list) throws  WFJobInProcException {
		for (Iterator iter = list.iterator(); iter.hasNext(); ) {
			CheckPointRule r = (CheckPointRule)iter.next();
			if (r.getJob() != null) {
				throw new WFJobInProcException();
			}
		}
		this.checkPointRuleDao.deleteAllCheckPointRule(list);
	}

	public List<CheckPointRule> loadAllCheckPointRule(Long[] checkPointRuIds) {
		return checkPointRuleDao.loadAllCheckPointRule(checkPointRuIds);
	}

	public CheckPointRule GetCheckPointRuleByIdAndTypeId(final Long ruleId, final Long deviceId, final Long ruleTypeId) {
		return this.checkPointRuleDao.GetCheckPointRuleByIdAndTypeId(ruleId,deviceId,ruleTypeId);
	}

	public List<Long> searchRuleTypeId(Long deviceId) {
		return this.checkPointRuleDao.searchRuleTypeId(deviceId);
	}

	public void cancelJob(CheckPointRule rule) {
		rule.setJob(null);
		this.storeRule(rule);
		this.wfJobManager.cancelJob(rule);
	}
}
