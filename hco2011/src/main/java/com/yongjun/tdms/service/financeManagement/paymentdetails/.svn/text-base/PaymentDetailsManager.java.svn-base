package com.yongjun.tdms.service.financeManagement.paymentdetails;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.financeManagement.paymentdetails.PaymentDetails;
import java.util.List;

public abstract interface PaymentDetailsManager
{
  public abstract void storePaymentDetails(PaymentDetails paramPaymentDetails);

  public abstract PaymentDetails loadPaymentDetails(Long paramLong);

  public abstract List<PaymentDetails> loadPaymentDetails();

  public abstract List<PaymentDetails> loadAllPaymentDetails(Long[] paramArrayOfLong);

  public abstract void deletePaymentDetails(PaymentDetails paramPaymentDetails);

  public abstract void deleteAllPaymentDetails(List<PaymentDetails> paramList);

  public abstract List<PaymentDetails> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<PaymentDetails> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);

  public abstract void disabledAllPaymentDetails(List<PaymentDetails> paramList);

  public abstract void enabledAllPaymentDetails(List<PaymentDetails> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.financeManagement.paymentdetails.PaymentDetailsManager
 * JD-Core Version:    0.6.2
 */