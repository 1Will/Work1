package com.yongjun.tdms.service.customercontract.contractmanagement.state.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.state.ContractStateDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.state.ContractState;
import com.yongjun.tdms.service.customercontract.contractmanagement.state.ContractStateManager;

public class DefaultContractStateManager extends BaseManager implements ContractStateManager{
	private final ContractStateDao dao;

	public DefaultContractStateManager(ContractStateDao dao) {
		this.dao = dao;
	}

	public void storeContractState(ContractState t) {
		this.dao.storeContractState(t);
	}

	public ContractState loadContractState(Long id) {
		return this.dao.loadContractState(id);
	}

	public List<ContractState> loadContractState() {
		return this.dao.loadContractState();
	}

	public List<ContractState> loadAllContractState(Long[] tIds) {
		return this.dao.loadAllContractState(tIds);
	}

	public void deleteContractState(ContractState t) {
		this.dao.deleteContractState(t);
	}

	public void deleteAllContractState(List<ContractState> ts) {
		this.dao.deleteAllContractState(ts);
	}

	public List<ContractState> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<ContractState> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

}
