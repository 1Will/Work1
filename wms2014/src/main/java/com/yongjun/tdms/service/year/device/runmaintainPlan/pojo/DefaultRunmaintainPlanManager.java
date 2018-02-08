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
package com.yongjun.tdms.service.year.device.runmaintainPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.year.device.runmaintainPlan.RunmaintainPlanDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanManager;

/**
 * 
 * <p>Title: DefaultRunmaintainPlanManager
 * <p>Description: 设备运维计划业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanManager
 * @version $Id:$
 */
public class DefaultRunmaintainPlanManager implements RunmaintainPlanManager {
	
	private final RunmaintainPlanDao runmaintainPlanDao;
	private final SequenceManager sequenceManager;
	
	public DefaultRunmaintainPlanManager(RunmaintainPlanDao runmaintainPlanDao, 
			SequenceManager sequenceManager) {
		this.runmaintainPlanDao = runmaintainPlanDao;
		this.sequenceManager = sequenceManager;
	}
	
	public RunmaintainPlan loadRunmaintainPlan(Long runmaintainPlanId) {
		return this.runmaintainPlanDao.loadRunmaintainPlan(runmaintainPlanId);
	}

	public List<RunmaintainPlan> loadAllRunmaintainPlans(Long[] runmaintainPlanIds) {
		return this.runmaintainPlanDao.loadAllRunmaintainPlans(runmaintainPlanIds);
	}

	public List<RunmaintainPlan> loadAllRunmaintainPlans() {
		return this.runmaintainPlanDao.loadAllRunmaintainPlans();
	}

	public void storeRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		if (runmaintainPlan.isNew()) {
			//生成运维计划编号
			String planNo = (String)sequenceManager.generate("-");
			runmaintainPlan.setPlanNo(planNo);
		}
		this.runmaintainPlanDao.storeRunmaintainPlan(runmaintainPlan);
	}

	public void deleteRunmaintainPlan(RunmaintainPlan runmaintainPlan) {
		this.runmaintainPlanDao.deleteRunmaintainPlan(runmaintainPlan);
	}

	public void deleteAllRunmaintainPlans(Collection<RunmaintainPlan> runmaintainPlans) {
		this.runmaintainPlanDao.deleteAllRunmaintainPlans(runmaintainPlans);
	}

	public void disabledAllRunmaintainPlans(Collection<RunmaintainPlan> runmaintainPlans) {
		for (RunmaintainPlan plan : runmaintainPlans) {
			//失效该运维计划
			plan.setDisabled(true);
			this.runmaintainPlanDao.storeRunmaintainPlan(plan);
		}
	}

	public void enabledAllRunmaintainPlans(Collection<RunmaintainPlan> runmaintainPlans) {
		for (RunmaintainPlan plan : runmaintainPlans) {
			//有效该运维计划
			plan.setDisabled(false);
			this.runmaintainPlanDao.storeRunmaintainPlan(plan);
		}
	}

}
