package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.plan;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanManager;

/**
 * @author wzou
 * @version $Id: ListCheckPointPlanDetailAction.java 7961 2007-10-23 07:03:11Z qsun $
 */
public class ListCheckPointPlanDetailAction extends ValueListAction {
	private static final long serialVersionUID = 3087495020938455975L;
	private CheckPointPlanDetailManager checkPointPlanDetailManager;
	private CheckPointPlanManager checkPointPlanManager;
	private List planDetails;

	Long planId;
	private CheckPointPlanDetail planDetail;
	private CheckPointPlan planOfAmend;
	Double fee;

	

	public ListCheckPointPlanDetailAction(
			CheckPointPlanDetailManager checkPointPlanDetailManager,
			CheckPointPlanManager checkPointPlanManager) {
		this.checkPointPlanDetailManager = checkPointPlanDetailManager;
		this.checkPointPlanManager = checkPointPlanManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("plan.id")) {
			planId = this.getId("plan.id");
		}
		if (this.planDetails == null && this.hasIds("checkPointPlanDetailIds")) {
			this.planDetails = this.checkPointPlanDetailManager
					.loadAllCheckPointPlanDetail(this
							.getIds("checkPointPlanDetailIds"));
		}
	}

	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}


	public List getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(List planDetails) {
		this.planDetails = planDetails;
	}

	public String delete() {
		planOfAmend = checkPointPlanManager.ceatePlanByPlanId(planId);
		for(int i=0;i<planDetails.size();i++){
			planDetail=(CheckPointPlanDetail)(planDetails.get(i));
			fee=planOfAmend.getFee()-planDetail.getFee();
			planOfAmend.setFee(fee);
		}
		checkPointPlanManager.storePlan(planOfAmend);
		try {
			this.checkPointPlanDetailManager
			.deleteAllCheckPointPlanDetail(this.planDetails);
			}catch(Exception e){
				this.addActionMessage(this.getText("checkPointPlanDetail.delete.error"));
				return ERROR;
			}
		
		this.addActionMessage(this
				.getText("checkPointPlanDetail.delete.success"));
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "checkPointRules";
	}

}
