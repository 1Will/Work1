package com.yongjun.tdms.service.customercontract.contractmanagement.plan.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.plan.ContractPlanDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.plan.ContractPlan;
import com.yongjun.tdms.service.customercontract.contractmanagement.plan.ContractPlanManager;

public class DefaultContractPlanManager extends BaseManager implements ContractPlanManager {

	private final ContractPlanDao contractPlanDao;

	public DefaultContractPlanManager(ContractPlanDao contractPlanDao) {
		this.contractPlanDao = contractPlanDao;
	}

	public void storeContractPlan(ContractPlan t) {
		this.contractPlanDao.storeContractPlan(t);
	}

	public ContractPlan loadContractPlan(Long id) {
		return this.contractPlanDao.loadContractPlan(id);
	}

	public List<ContractPlan> loadContractPlan() {
		return this.contractPlanDao.loadContractPlan();
	}

	public List<ContractPlan> loadAllContractPlan(Long[] tIds) {
		return this.contractPlanDao.loadAllContractPlan(tIds);
	}

	public void deleteContractPlan(ContractPlan t) {
		this.contractPlanDao.deleteContractPlan(t);
	}

	public void deleteAllContractPlan(List<ContractPlan> ts) {
		this.contractPlanDao.deleteAllContractPlan(ts);
	}

	public List<ContractPlan> loadByKey(String key, Object value) throws DaoException {
		return this.contractPlanDao.loadByKey(key, value);
	}

	public List<ContractPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.contractPlanDao.loadByKeyArray(keyNames, keyValues);
	}

}
