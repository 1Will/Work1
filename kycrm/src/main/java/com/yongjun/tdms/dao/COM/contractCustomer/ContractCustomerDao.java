package com.yongjun.tdms.dao.COM.contractCustomer;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.COM.contractCustomer.ContractCustomer;
import java.util.Collection;
import java.util.List;

public abstract interface ContractCustomerDao
{
  public abstract void storeContractCustomer(ContractCustomer paramContractCustomer);

  public abstract void deleteContractCustomer(ContractCustomer paramContractCustomer);

  public abstract void deleteAllContractCustomer(Collection<ContractCustomer> paramCollection);

  public abstract List<ContractCustomer> loadAllContractCustomer(Long[] paramArrayOfLong);

  public abstract ContractCustomer loadContractCustomer(Long paramLong);

  public abstract List<ContractCustomer> loadAllContractCustomer();

  public abstract List<ContractCustomer> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ContractCustomer> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.COM.contractCustomer.ContractCustomerDao
 * JD-Core Version:    0.6.2
 */