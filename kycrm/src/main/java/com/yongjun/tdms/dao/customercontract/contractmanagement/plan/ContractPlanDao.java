package com.yongjun.tdms.dao.customercontract.contractmanagement.plan;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.plan.ContractPlan;

public abstract interface ContractPlanDao {
	public abstract void storeContractPlan(ContractPlan paramContractPlan);

	public abstract ContractPlan loadContractPlan(Long paramLong);

	public abstract List<ContractPlan> loadContractPlan();

	public abstract List<ContractPlan> loadAllContractPlan(Long[] paramArrayOfLong);

	public abstract void deleteContractPlan(ContractPlan paramContractPlan);

	public abstract void deleteAllContractPlan(List<ContractPlan> paramList);

	public abstract List<ContractPlan> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ContractPlan> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}
