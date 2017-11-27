package com.yongjun.tdms.dao.customercontract.contractmanagement.state.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.state.ContractStateDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.state.ContractState;

public class HibernateContractState extends BaseHibernateDao implements ContractStateDao{
	public void storeContractState(ContractState t) {
		store(t);
	}

	public ContractState loadContractState(Long id) {
		return (ContractState) load(ContractState.class, id);
	}

	public List<ContractState> loadContractState() {
		return loadAll(ContractState.class);
	}

	public List<ContractState> loadAllContractState(Long[] tIds) {
		return loadAll(ContractState.class, tIds);
	}

	public void deleteContractState(ContractState t) {
		delete(t);
	}

	public void deleteAllContractState(List<ContractState> ts) {
		deleteAll(ts);
	}

	public List<ContractState> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(ContractState.class, key, value);
	}

	public List<ContractState> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ContractState.class, keyNames, keyValues);
	}

}
