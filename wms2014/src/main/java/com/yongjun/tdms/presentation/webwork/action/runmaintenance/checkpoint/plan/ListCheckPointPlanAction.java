package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.plan;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointPlanManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;


/**
 * @author qs
 * @version $Id: ListCheckPointPlanAction.java 8726 2007-11-28 09:51:48Z qsun $
 */
public class ListCheckPointPlanAction extends ValueListAction {
	private static final long serialVersionUID = -9037204540736358595L;
	private final CheckPointPlanManager checkPointPlanManager;
	private final DepartmentManager departmentManager;
	private final WfDocStateManager wfDocStateManager;
	
	private List<CheckPointPlan> plans;

	public ListCheckPointPlanAction(
			CheckPointPlanManager checkPointPlanManager,
			DepartmentManager departmentManager,
			WfDocStateManager wfDocStateManager) {
		this.checkPointPlanManager =  checkPointPlanManager;
		this.departmentManager = departmentManager;
		this.wfDocStateManager = wfDocStateManager;
	}
	
	public void prepare() throws Exception {
		if (this.plans == null && this.hasIds("checkPointPlanIds")) {
			this.plans = this.checkPointPlanManager.loadAllCheckPointPlan(this
					.getIds("checkPointPlanIds"));
		}
	}
	
	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public List<CheckPointPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<CheckPointPlan> plans) {
		this.plans = plans;
	}
	
	public String delete() {
		CheckPointPlan everyPlan;
		for(int i=0;i<this.plans.size();i++){
			everyPlan=(CheckPointPlan)this.plans.get(i);
			if(everyPlan.getDocStatus()==2){
				this.addActionMessage(this.getText("checkPointPlan.delete.error1"));
				return ERROR;
			}
		}
		try {
			this.checkPointPlanManager.deleteAllCheckPointPlan(this.plans);
			}catch(Exception e){
				this.addActionMessage(this.getText("checkPointPlan.delete.error2"));
				return ERROR;
			}
		this.addActionMessage(this.getText("checkPointPlan.delete.success"));
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "checkPointPlans";
	}
	
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
	public List getDocStates() {
		return wfDocStateManager.createSelectDocStates(this.getText("select.option.all"));
	}

}
