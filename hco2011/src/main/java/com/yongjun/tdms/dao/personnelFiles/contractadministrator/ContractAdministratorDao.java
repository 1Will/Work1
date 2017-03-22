package com.yongjun.tdms.dao.personnelFiles.contractadministrator;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
import java.util.Collection;
import java.util.List;

public abstract interface ContractAdministratorDao
{
  public abstract void storeContractAdministrator(ContractAdministrator paramContractAdministrator);

  public abstract void deleteContractAdministrator(ContractAdministrator paramContractAdministrator);

  public abstract void deleteAllContractAdministrator(Collection<ContractAdministrator> paramCollection);

  public abstract List<ContractAdministrator> loadAllContractAdministrator(Long[] paramArrayOfLong);

  public abstract ContractAdministrator loadContractAdministrator(Long paramLong);

  public abstract List<ContractAdministrator> loadAllContractAdministrator();

  public abstract List<ContractAdministrator> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ContractAdministrator> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.contractadministrator.ContractAdministratorDao
 * JD-Core Version:    0.6.2
 */