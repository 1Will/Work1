package com.yongjun.tdms.service.customerservicemanagement.customercomplaint;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customerservicemanagement.customercomplaint.CustomerComplaint;
import java.util.List;

public abstract interface CustomerComplaintManager
{
  public abstract void storeCustomerComplaint(CustomerComplaint paramCustomerComplaint);

  public abstract CustomerComplaint loadCustomerComplaint(Long paramLong);

  public abstract List<CustomerComplaint> loadCustomerComplaint();

  public abstract List<CustomerComplaint> loadAllCustomerComplaint(Long[] paramArrayOfLong);

  public abstract void deleteCustomerComplaint(CustomerComplaint paramCustomerComplaint);

  public abstract void deleteAllCustomerComplaint(List<CustomerComplaint> paramList);

  public abstract List<CustomerComplaint> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CustomerComplaint> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllCustomerComplaint(List<CustomerComplaint> paramList);

  public abstract void enabledAllCustomerComplaint(List<CustomerComplaint> paramList);

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customerservicemanagement.customercomplaint.CustomerComplaintManager
 * JD-Core Version:    0.6.2
 */