package com.yongjun.tdms.dao.financeManagement.paymentorder;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.financeManagement.paymentorder.Paymentorder;
import java.util.List;

public abstract interface PaymentorderDao
{
  public abstract void storePaymentorder(Paymentorder paramPaymentorder);

  public abstract Paymentorder loadPaymentorder(Long paramLong);

  public abstract List<Paymentorder> loadPaymentorder();

  public abstract List<Paymentorder> loadAllPaymentorder(Long[] paramArrayOfLong);

  public abstract void deletePaymentorder(Paymentorder paramPaymentorder);

  public abstract void deleteAllPaymentorder(List<Paymentorder> paramList);

  public abstract List<Paymentorder> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Paymentorder> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.financeManagement.paymentorder.PaymentorderDao
 * JD-Core Version:    0.6.2
 */