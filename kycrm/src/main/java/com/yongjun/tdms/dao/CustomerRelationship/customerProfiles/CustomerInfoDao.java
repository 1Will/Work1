package com.yongjun.tdms.dao.CustomerRelationship.customerProfiles;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

import java.util.List;

public abstract interface CustomerInfoDao
{
  public abstract void storeCustomerInfo(CustomerInfo paramCustomerInfo);

  public abstract CustomerInfo loadCustomerInfo(Long paramLong);

  public abstract List<CustomerInfo> loadAllCustomerInfo();

  public abstract List<CustomerInfo> loadAllCustomerInfo(Long[] paramArrayOfLong);

  public abstract void deleteCustomerInfo(CustomerInfo paramCustomerInfo);

  public abstract void deleteAllCustomerInfo(List<CustomerInfo> paramList);

  public abstract List<CustomerInfo> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CustomerInfo> getCustomerByName(String paramString);
  /**
   * 根据客户名称进行准确查询
   * @param paramString
   * @return
   */
  public abstract List<CustomerInfo> getOneCustomerByName(String paramString);
  
  public List<CustomerInfo> getCustomerByCodeAndDate(String date,String code) ;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.customerProfiles.CustomerInfoDao
 * JD-Core Version:    0.6.2
 */