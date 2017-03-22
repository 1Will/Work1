package com.yongjun.tdms.service.customercontract.billingrecord;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import java.util.List;

public abstract interface BillingRecordManager
{
  public abstract void storeBillingRecord(BillingRecord paramBillingRecord);

  public abstract BillingRecord loadBillingRecord(Long paramLong);

  public abstract List<BillingRecord> loadBillingRecord();

  public abstract List<BillingRecord> loadAllBillingRecord(Long[] paramArrayOfLong);

  public abstract void deleteBillingRecord(BillingRecord paramBillingRecord);

  public abstract void deleteAllBillingRecord(List<BillingRecord> paramList);

  public abstract List<BillingRecord> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<BillingRecord> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllBillingRecord(List<BillingRecord> paramList);

  public abstract void enabledAllBillingRecord(List<BillingRecord> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager
 * JD-Core Version:    0.6.2
 */