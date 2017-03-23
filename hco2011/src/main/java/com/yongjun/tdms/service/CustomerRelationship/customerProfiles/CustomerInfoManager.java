package com.yongjun.tdms.service.CustomerRelationship.customerProfiles;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import java.util.List;

public abstract interface CustomerInfoManager
{
  public abstract void storeCustomerInfo(CustomerInfo paramCustomerInfo);

  public abstract CustomerInfo loadCustomerInfo(Long paramLong);

  public abstract List<CustomerInfo> loadAllCustomerInfo();

  public abstract List<CustomerInfo> loadAllCustomerInfo(Long[] paramArrayOfLong);

  public abstract void deleteCustomerInfo(CustomerInfo paramCustomerInfo);

  public abstract void deleteAllCustomerInfo(List<CustomerInfo> paramList);

  public abstract List<CustomerInfo> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract void disabledAllCustomerInfo(List<CustomerInfo> paramList);

  public abstract void enabledAllCustomerInfo(List<CustomerInfo> paramList);

  public abstract List<CustomerInfo> getCustomerList(String paramString);
  /**
   * 根据客户名称进行准确查询
   * @param paramString
   * @return
   */
  public abstract List<CustomerInfo> getOneCustomerByName(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager
 * JD-Core Version:    0.6.2
 */