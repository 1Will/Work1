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

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointPlanDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointRuleDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanManager;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * @author qs
 * @version $Id: DefaultCheckPointPlanManager.java 11138 2008-02-27 09:40:47Z zbzhang $
 */
public class DefaultCheckPointPlanManager extends BaseManager implements CheckPointPlanManager {
	private final CheckPointPlanDao checkPointPlanDao;
	private final CheckPointRuleDao checkPointRuleDao;
	private final SequenceManager sequenceManager;
	private final WfJobManager wfJobManager;
	
	public DefaultCheckPointPlanManager(CheckPointPlanDao checkPointPlanDao,
			CheckPointRuleDao checkPointRuleDao,
			SequenceManager sequenceManager,
			WfJobManager wfJobManager) {
		this.checkPointPlanDao = checkPointPlanDao;
		this.checkPointRuleDao = checkPointRuleDao;
		this.sequenceManager = sequenceManager;
		this.wfJobManager = wfJobManager;
	}

	public void storePlan(CheckPointPlan plan) {
		if(plan.isNew()){
			String planNo = (String)sequenceManager.generate("-");
			plan.setPlanNo(planNo);
		}
		checkPointPlanDao.storePlan(plan);
	}
	
	public void storePlan(CheckPointPlan plan, CheckPointRule rule) {
		if (plan.isNew()) {
			//copyRuleInfo(plan, rule);
		}
		else if (plan.getRule() != rule) {
			//copyRuleInfo(plan, rule);
		}
		storePlan(plan);
	}
	
//	private void copyRuleInfo(CheckPointPlan plan, CheckPointRule rule) {
//		Set<CheckPointRuleDetail> ruleSet=rule.getRuleDetails();
//		
//		Object[] ruleDetail=ruleSet.toArray();
//		for(int i=0;i<ruleDetail.length;i++){
//			CheckPointPlanDetail planDetail=new CheckPointPlanDetail();
//			planDetail.setPart(((CheckPointRuleDetail) ruleDetail[i]).getPart());
//			planDetail.setContent(((CheckPointRuleDetail) ruleDetail[i]).getContent());
//			planDetail.setMethod(((CheckPointRuleDetail) ruleDetail[i]).getMethod());
//			planDetail.setTool(((CheckPointRuleDetail) ruleDetail[i]).getTool());
//			planDetail.setRule(((CheckPointRuleDetail) ruleDetail[i]).getRule());
//			planDetail.setComment(((CheckPointRuleDetail) ruleDetail[i]).getRule());
//			planDetail.setRuleDetail((CheckPointRuleDetail)ruleDetail[i]);
//			planDetail.setPlan((plan));
//			planDetail.setFee(((CheckPointRuleDetail)ruleDetail[i]).getFee());
//			plan.getPlanDetails().add(planDetail);
//		}
//	}
	
	/*更改状态*/
	public void updateCheckPointPlanStatus(CheckPointPlan checkPointPlan,int status){
		//this.plan.setDocStatus(2);
		checkPointPlan.setDocStatus(status);
		storePlan(checkPointPlan);
	}

	public CheckPointPlan loadPlan(Long id) {
		return checkPointPlanDao.loadPlan(id);
	}

	public List<CheckPointPlan> loadAllCheckPointPlan(Long[] checkPointPlanIds) {
		return checkPointPlanDao.loadAllCheckPointPlan(checkPointPlanIds);
	}

	public void deleteAllCheckPointPlan(List list) throws WFJobInProcException{
		for (Iterator iter = list.iterator(); iter.hasNext(); ) {
			CheckPointPlan plan = (CheckPointPlan)iter.next();
			if (plan.getJob() != null) {
				throw new WFJobInProcException();
			}
		}
		this.checkPointPlanDao.deleteAllCheckPointPlan(list);
	}

	public List<CheckPointPlan> loadAllUnrelatedCheckPointPlans(){
		return checkPointPlanDao.loadAllUnrelatedCheckPointPlan();
	}
	
//	public void ceatePlanByScheduler() {
//		logger.debug("...");
//	}
//	public void ceatePlanByScheduler() {
//		logger.debug("started create plan from rule ...");
//		List<CheckPointRule> rules = checkPointRuleDao.loadAllCheckPointRules();
//		
//		for (CheckPointRule rule : rules) {
//			logger.debug("begin create plan from rule id is: [" + rule.getId() + "], and ruleNo is [" + rule.getRuleNo() + "]");
//			
//			Date d = rule.getLastCheckTime();
//			
//			CheckPointPlan lastPlan = checkPointPlanDao.loadLastPlanByCheckPointRuleId(rule.getId());
//			if (null != lastPlan) {
//				d = lastPlan.getScheduleTime();
//			}
//			Date x = d;
//			
//			d = DateUtil.addMonthsToDate(d, 1);
//			while(true) {
//				
//				CheckPointPlan plan = new CheckPointPlan();
//				
//				x = DateUtil.addDaysToDate(x, rule.getPeriod());
//				if (DateUtil.getMonth(x) > DateUtil.getMonth(d)) {
//					break;
//				}
//				
//				plan.createPlanFromRule(rule, x);
//				plan.setRule(rule);
//				
//				this.storePlan(plan);
//				
//				logger.debug("created plan id is : [" + plan.getId() + "], planNo is + [" + plan.getPlanNo() + "]");
//			}
//		}
//		
//		logger.debug("end create plan ...");
//	}
	public void cancelJob(CheckPointPlan plan) {
		plan.setJob(null);
		this.storePlan(plan);
		this.wfJobManager.cancelJob(plan);
	}
	
	public CheckPointPlan ceatePlanByPlanId(Long planId){
		CheckPointPlan plan = checkPointPlanDao.loadPlanByCheckPointPlanId(planId);
		return plan;
	}

	public void ceatePlanByScheduler() {
		// TODO Auto-generated method stub
		
	}

}
