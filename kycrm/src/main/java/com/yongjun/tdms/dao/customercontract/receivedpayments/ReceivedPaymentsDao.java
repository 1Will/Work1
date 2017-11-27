package com.yongjun.tdms.dao.customercontract.receivedpayments;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.receivedpayments.ReceivedPayments;
import java.util.Collection;
import java.util.List;

public abstract interface ReceivedPaymentsDao
{
  public abstract void storeReceivedPayments(ReceivedPayments paramReceivedPayments);

  public abstract void deleteReceivedPayments(ReceivedPayments paramReceivedPayments);

  public abstract void deleteAllReceivedPayments(Collection<ReceivedPayments> paramCollection);

  public abstract List<ReceivedPayments> loadAllReceivedPayments(Long[] paramArrayOfLong);

  public abstract ReceivedPayments loadReceivedPayments(Long paramLong);

  public abstract List<ReceivedPayments> loadAllReceivedPayments();

  public abstract List<ReceivedPayments> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ReceivedPayments> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customercontract.receivedpayments.ReceivedPaymentsDao
 * JD-Core Version:    0.6.2
 */