package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.state.ContractState;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.state.ContractStateManager;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ListContractStateAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ContractManagementManager contractManagementManager;
	private final ContractStateManager contractStateManager;
	private final FileTransportManager fileTransportManager;
	
	private List<ContractState> contractStates = null;
	private ContractState contractState;

	public ListContractStateAction(ContractManagementManager contractManagementManager,ContractStateManager contractStateManager,FileTransportManager fileTransportManager) {
		this.contractManagementManager = contractManagementManager;
		this.contractStateManager = contractStateManager;
		this.fileTransportManager = fileTransportManager;
	}
	public void prepare() throws Exception {
		if(hasId("contractState.id")){
			this.contractState =contractStateManager.loadContractState(getId("contractState.id"));
		}
		
		if(hasId("contractStates.id")){
			this.contractStates =contractStateManager.loadAllContractState(getIds("contractStates.id"));
		}
	}

	protected String getAdapterName() {
		return "contractStatesHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return "success";
	}

	public String download() {
		this.fileTransportManager.download(this.request, this.response, this.contractState.getFileName(),
				this.contractState.getPosition());
		return null;
	}
	
	public String delete() {
		//删除合同s
		addActionMessage(getText("contractManagement.delete.success"));
		return "success";
	}
	public List<ContractState> getContractStates() {
		return contractStates;
	}
	public void setContractStates(List<ContractState> contractStates) {
		this.contractStates = contractStates;
	}
	public ContractState getContractState() {
		return contractState;
	}
	public void setContractState(ContractState contractState) {
		this.contractState = contractState;
	}
}
