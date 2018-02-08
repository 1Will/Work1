package com.yongjun.tdms.service.customercontract.contractmanagement;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

import java.util.HashMap;
import java.util.List;

public abstract interface ContractManagementManager
{
  public abstract void storeContractManagement(ContractManagement paramContractManagement);

  public abstract ContractManagement loadContractManagement(Long paramLong);

  public abstract List<ContractManagement> loadContractManagement();
  
  public List<ContractManagement> loadContractManagementByPj(String pjIds);
  
  public List<ContractManagement> loadContractManagementByBType(String type);

  public abstract List<ContractManagement> loadAllContractManagement(Long[] paramArrayOfLong);

  public abstract void deleteContractManagement(ContractManagement paramContractManagement);

  public abstract void deleteAllContractManagement(List<ContractManagement> paramList);

  public abstract List<ContractManagement> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ContractManagement> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);

  public abstract void disabledAllContractManagement(List<ContractManagement> paramList);

  public abstract void enabledAllContractManagement(List<ContractManagement> paramList);

  public abstract double getSumReturnPrice(long paramLong)
    throws DaoException;
  public abstract HashMap getDataMap(String staDate,String endDate);
  public abstract String saveContractManagementfoByImp(List<ContractManagement> ContractManagement)throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager
 * JD-Core Version:    0.6.2
 */