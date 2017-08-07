package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.state;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.state.ContractState;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.state.ContractStateManager;

public class EditContractStateAction extends FileTransportAction{
	private static final long serialVersionUID = 1L;
	private final ContractManagementManager contractManagementManager;
	private final ContractStateManager contractStateManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private final FileTransportManager fileTransportManager;
	
	private ContractManagement contractManagement;
	private ContractState contractState;

	public EditContractStateAction(ContractManagementManager contractManagementManager,ContractStateManager contractStateManager,
			UserManager userManager,CodeValueManager codeValueManager,FileTransportManager fileTransportManager) {
		this.contractManagementManager = contractManagementManager;
		this.contractStateManager = contractStateManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
		this.fileTransportManager = fileTransportManager;
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
		if (!isNew) {
			this.fileTransportManager.delete(this.request, this.contractState.getPosition());
		}
		try {
			String location = this.fileTransportManager.upload(this.request, getFile(), "origFileName");
			this.contractState.setPosition(location);
			this.contractStateManager.storeContractState(this.contractState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.contractManagement.setState(codeValueManager.loadCodeValue(getId("newStateid")));
		this.contractManagementManager.storeContractManagement(contractManagement);
		
		
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
