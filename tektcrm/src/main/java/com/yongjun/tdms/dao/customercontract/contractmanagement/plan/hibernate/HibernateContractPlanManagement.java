package com.yongjun.tdms.dao.customercontract.contractmanagement.plan.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.plan.ContractPlanDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.plan.ContractPlan;

public class HibernateContractPlanManagement extends BaseHibernateDao implements ContractPlanDao {
	public void storeContractPlan(ContractPlan t) {
		store(t);
	}

	public ContractPlan loadContractPlan(Long id) {
		return (ContractPlan) load(ContractPlan.class, id);
	}

	public List<ContractPlan> loadContractPlan() {
		return loadAll(ContractPlan.class);
	}

	public List<ContractPlan> loadAllContractPlan(Long[] tIds) {
		return loadAll(ContractPlan.class, tIds);
	}

	public void deleteContractPlan(ContractPlan t) {
		delete(t);
	}

	public void deleteAllContractPlan(List<ContractPlan> ts) {
		deleteAll(ts);
	}

	public List<ContractPlan> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(ContractPlan.class, key, value);
	}

	public List<ContractPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ContractPlan.class, keyNames, keyValues);
	}
}
