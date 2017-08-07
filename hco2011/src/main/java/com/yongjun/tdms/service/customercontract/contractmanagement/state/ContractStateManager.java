package com.yongjun.tdms.service.customercontract.contractmanagement.state;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.state.ContractState;

public interface ContractStateManager {
	public abstract void storeContractState(ContractState paramContractState);

	  public abstract ContractState loadContractState(Long paramLong);

	  public abstract List<ContractState> loadContractState();

	  public abstract List<ContractState> loadAllContractState(Long[] paramArrayOfLong);

	  public abstract void deleteContractState(ContractState paramContractState);

	  public abstract void deleteAllContractState(List<ContractState> paramList);

	  public abstract List<ContractState> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ContractState> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
