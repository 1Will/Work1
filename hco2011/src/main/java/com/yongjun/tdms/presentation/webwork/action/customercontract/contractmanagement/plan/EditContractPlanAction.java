package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.plan;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.plan.ContractPlan;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.plan.ContractPlanManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

public class EditContractPlanAction extends PrepareAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ContractPlanManager contractPlanManager;
	private final ContractManagementManager contractManagementManager;
	private final PersonnelFilesManager personnelFilesManager;
	private ContractManagement contractManagement;
	private ContractPlan contractPlan;
	
	public EditContractPlanAction(ContractPlanManager contractPlanManager,ContractManagementManager contractManagementManager,PersonnelFilesManager personnelFilesManager){
		this.contractPlanManager = contractPlanManager;
		this.contractManagementManager = contractManagementManager;
		this.personnelFilesManager = personnelFilesManager;
	}


	public void prepare() throws Exception {
		if(hasId("contractPlan.id")){
			this.contractPlan = contractPlanManager.loadContractPlan(getId("contractPlan.id"));
		}else {
			this.contractPlan = new ContractPlan();
		}
		if(hasId("contractManagement.id")){
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			this.contractPlan.setContractManagement(this.contractManagement);
		}
	}
	
	
	public String save() {
		boolean isNew = contractPlan.isNew();
		try {
			this.contractPlan.setExecutor(personnelFilesManager.loadPersonnel(getId("executor.id")));
			this.contractPlanManager.storeContractPlan(this.contractPlan);
			if (isNew) {
				addActionMessage(getText("contractPlan.add.success"));
				return SUCCESS;
			}else {
				addActionMessage(getText("contractPlan.update.success"));
				return SUCCESS;
			}
		} catch (Exception e) {
			if (isNew) {
				addActionMessage(getText("contractPlan.add.error"));
				return ERROR;
			}else {
				addActionMessage(getText("contractPlan.update.error"));
				return ERROR;
			}
		}
	}


	public ContractManagement getContractManagement() {
		return contractManagement;
	}


	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}


	public ContractPlan getContractPlan() {
		return contractPlan;
	}


	public void setContractPlan(ContractPlan contractPlan) {
		this.contractPlan = contractPlan;
	}
}
