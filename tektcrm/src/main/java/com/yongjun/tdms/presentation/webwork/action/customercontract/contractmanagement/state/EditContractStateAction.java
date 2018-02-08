package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.state;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.state.ContractState;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.state.ContractStateManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

public class EditContractStateAction extends FileTransportAction{
	private static final long serialVersionUID = 1L;
	private final ContractManagementManager contractManagementManager;
	private final ContractStateManager contractStateManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private final FileTransportManager fileTransportManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	
	private ContractManagement contractManagement;
	private ContractState contractState;

	public EditContractStateAction(ContractManagementManager contractManagementManager,ContractStateManager contractStateManager,
			UserManager userManager,CodeValueManager codeValueManager,FileTransportManager fileTransportManager,
			ProjectInfoPlanManager projectInfoPlanManager) {
		this.contractManagementManager = contractManagementManager;
		this.contractStateManager = contractStateManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
		this.fileTransportManager = fileTransportManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
	}

	public void prepare() throws Exception {
		if (hasId("contractManagement.id")) {
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
		}
		if(hasId("contractState.id")){
			this.contractState =contractStateManager.loadContractState(getId("contractState.id"));
		}else{
			this.contractState = new ContractState();
			this.contractState.setCreatorName(userManager.getUser().getName());
		}
	}

	public String save() {
		boolean isNew =this.contractState.isNew();
		this.contractState.setNewState(codeValueManager.loadCodeValue(getId("newStateid")));
		this.contractState.setBeforeState(codeValueManager.loadCodeValue(getId("beforeStateid")));
		this.contractState.setContractManagement(contractManagement);
		try {
			this.contractStateManager.storeContractState(this.contractState);
			//改变合同状态
			this.contractManagement.setState(codeValueManager.loadCodeValue(getId("newStateid")));
			this.contractManagementManager.storeContractManagement(contractManagement);
			
			CodeValue codeValue = codeValueManager.loadCodeValue(getId("newStateid"));
			List<ProjectInfoPlan> projectInfoPlans = projectInfoPlanManager.loadByKey("contractManagement.id",
					this.contractManagement.getId());
			ProjectInfoPlan projectInfoPlan = null;
			// 合同状态变为异常终止
			if (codeValue.getCode().equals("06605")) {
				if (projectInfoPlans != null && projectInfoPlans.size() > 0) {
					for (int i = 0; i < projectInfoPlans.size(); i++) {
						projectInfoPlan = projectInfoPlans.get(i);
						projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21105").get(0));
						projectInfoPlanManager.storeProjectInfoPlan(projectInfoPlan);
					}
				}
			}
			// 合同状态变为立项
			if (codeValue.getCode().equals("06603")) {
				if (projectInfoPlans != null && projectInfoPlans.size() > 0) {
					for (int i = 0; i < projectInfoPlans.size(); i++) {
						projectInfoPlan = projectInfoPlans.get(i);
						if(projectInfoPlan.getPercentt()==0){
							if(projectInfoPlan.getEndDate().after(new Date())){
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21101").get(0));
							}else {
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21103").get(0));
							}
						}
						if(projectInfoPlan.getPercentt()>0 && projectInfoPlan.getPercentt()<100){
							if(projectInfoPlan.getEndDate().after(new Date())){
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21102").get(0));
							}else {
								projectInfoPlan.setPlanState(codeValueManager.loadByKey("code", "21103").get(0));
							}
						}
						projectInfoPlanManager.storeProjectInfoPlan(projectInfoPlan);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("contractState.update.error"));
			return ERROR;
		}
		if (isNew) {
			addActionMessage(getText("contractState.add.success"));
			return "new";
		}else {
			addActionMessage(getText("contractState.update.success"));
		}

		return "success";
	}
	
	public List<CodeValue> getAllState() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("066"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public ContractState getContractState() {
		return contractState;
	}

	public void setContractState(ContractState contractState) {
		this.contractState = contractState;
	}

}
