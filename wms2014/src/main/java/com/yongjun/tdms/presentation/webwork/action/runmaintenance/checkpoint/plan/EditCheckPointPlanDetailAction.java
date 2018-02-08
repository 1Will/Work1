package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.plan;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanManager;



public class EditCheckPointPlanDetailAction extends PrepareAction {
	private static final long serialVersionUID = -6419768171459701248L;

	private final Log log = LogFactory.getLog(getClass());

	private CheckPointPlan plan;
	private CheckPointPlan planOfAmend;

	protected CheckPointPlanDetail detail;

	private final CheckPointPlanManager checkPointPlanManager;
	private final CheckPointPlanDetailManager checkPointPlanDetailManager;

	//private Set values;

	public EditCheckPointPlanDetailAction(
			CheckPointPlanManager checkPointPlanManager,
			CheckPointPlanDetailManager checkPointPlanDetailManager) {
		this.checkPointPlanManager = checkPointPlanManager;
		this.checkPointPlanDetailManager = checkPointPlanDetailManager;
	}


	public void prepare() throws Exception {
		if (this.plan == null) {
			if (this.hasId("plan.id")) {
				this.plan = this.checkPointPlanManager.loadPlan(this
						.getId("plan.id"));
			}
		}

		if (this.detail == null) {
			if (this.hasId("detail.id")) {
				detail = this.checkPointPlanDetailManager
						.loadCheckPointPlanDetail(this.getId("detail.id"));
			} else {
				detail = new CheckPointPlanDetail();
			}
		}
	}

	public String save() {
		if (log.isDebugEnabled()) {
			log.debug("plan [no,name] is " + plan.getPlanNo() + ","
					+ plan.getName());
		}

		boolean isNew = this.detail.isNew();

//		if (log.isDebugEnabled()) {
//			log.debug("device id is " + plan.getDevice().getId());
//		}
		detail.setPlan(this.plan);
		planOfAmend = checkPointPlanManager.ceatePlanByPlanId(plan.getId());
		planOfAmend.setFee(planOfAmend.getFee()+detail.getFee());
		System.out.println("=="+planOfAmend.getId());
		checkPointPlanManager.storePlan(planOfAmend);
		checkPointPlanDetailManager.storePlanDetail(detail);
		if (isNew) {
			this.addActionMessage(this.getText(
					"checkPointRuleDetail.add.success", Arrays
							.asList(new Object[] { plan.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText(
					"checkPointRuleDetail.edit.success", Arrays
							.asList(new Object[] { plan.getName() })));
			return SUCCESS;
		}
	}

	public String delete() {
		this.addActionMessage(this.getText(
				"checkPointRuleManager.invalid.success", Arrays
						.asList(new Object[] { plan.getName() })));
		return SUCCESS;
	}

	public CheckPointPlanDetail getDetail() {
		return detail;
	}

	public void setDetail(CheckPointPlanDetail detail) {
		this.detail = detail;
	}


	public CheckPointPlan getPlan() {
		return plan;
	}


	public void setPlan(CheckPointPlan plan) {
		this.plan = plan;
	}
}
