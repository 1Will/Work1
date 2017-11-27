package com.yongjun.tdms.dao.customerservicemanagement.customerservice;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customerservicemanagement.customerservice.CustomerService;
import java.util.List;

public abstract interface CustomerServiceDao
{
  public abstract void storeCustomerService(CustomerService paramCustomerService);

  public abstract CustomerService loadCustomerService(Long paramLong);

  public abstract List<CustomerService> loadCustomerService();

  public abstract List<CustomerService> loadAllCustomerService(Long[] paramArrayOfLong);

  public abstract void deleteCustomerService(CustomerService paramCustomerService);

  public abstract void deleteAllCustomerService(List<CustomerService> paramList);

  public abstract List<CustomerService> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CustomerService> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customerservicemanagement.customerservice.CustomerServiceDao
 * JD-Core Version:    0.6.2
 */