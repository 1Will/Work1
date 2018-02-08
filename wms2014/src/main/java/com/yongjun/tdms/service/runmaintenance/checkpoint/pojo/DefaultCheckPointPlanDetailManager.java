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

import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointPlanDetailDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanDetailManager;

/**
 * @author qs
 * @version $Id: DefaultCheckPointPlanDetailManager.java 7962 2007-10-23 07:03:35Z qsun $
 */
public class DefaultCheckPointPlanDetailManager implements
		CheckPointPlanDetailManager {
	private final CheckPointPlanDetailDao checkPointPlanDetailDao;
	
	public DefaultCheckPointPlanDetailManager(CheckPointPlanDetailDao checkPointPlanDetailDao) {
		this.checkPointPlanDetailDao = checkPointPlanDetailDao;
	}

	public void storePlanDetail(CheckPointPlanDetail planDetail) {
		this.checkPointPlanDetailDao.storePlanDetail(planDetail);
	}
	
	public double getRuleFee(CheckPointPlanDetail plan){
		double ruleFee=0;
		CheckPointRuleDetail ruleDetail=plan.getRuleDetail();
		if(ruleDetail!=null)
		ruleFee=ruleDetail.getFee();
		return ruleFee;
	}

	public void deleteAllCheckPointPlanDetail(List list) {
		this.checkPointPlanDetailDao.deleteAllCheckPointPlanDetail(list);
	}

	public List<CheckPointPlanDetail> loadAllCheckPointPlanDetail(Long[] checkPointPlanDetailIds) {
		return checkPointPlanDetailDao.loadAllCheckPointPlanDetail(checkPointPlanDetailIds);
	}

	public CheckPointPlanDetail loadCheckPointPlanDetail(Long id) {
		return this.checkPointPlanDetailDao.loadCheckPointPlanDetail(id);
	}
	
}
