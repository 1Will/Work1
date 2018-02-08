package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.plan;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.plan.ContractPlan;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.plan.ContractPlanManager;

@SuppressWarnings({"rawtypes","uncheck"})
public class ListContractPlanAction extends ValueListAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ContractPlanManager contractPlanManager;
	private final ContractManagementManager contractManagementManager;
	private ContractManagement contractManagement;
	private List<ContractPlan> contractPlans = null;
	
	public ListContractPlanAction(ContractPlanManager contractPlanManager,ContractManagementManager contractManagementManager){
		this.contractPlanManager = contractPlanManager;
		this.contractManagementManager = contractManagementManager;
	}

	public void prepare() throws Exception {
		if(hasId("contractManagement.id")){
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
		}
		if ((null == this.contractPlans) && (hasIds("contractPlanIds"))){
			this.contractPlans = contractPlanManager.loadAllContractPlan(getIds("contractPlanIds"));
		}
	}

	protected String getAdapterName() {
		return "getContractPlan";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.contractPlanManager.deleteAllContractPlan(this.contractPlans);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public List<ContractPlan> getContractPlans() {
		return contractPlans;
	}

	public void setContractPlans(List<ContractPlan> contractPlans) {
		this.contractPlans = contractPlans;
	}

}
