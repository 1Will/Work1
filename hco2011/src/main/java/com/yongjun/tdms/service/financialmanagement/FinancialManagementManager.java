package com.yongjun.tdms.service.financialmanagement;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import java.util.List;

public abstract interface FinancialManagementManager
{
  public abstract void storeFinancialManagement(FinancialManagement paramFinancialManagement);

  public abstract FinancialManagement loadFinancialManagement(Long paramLong);

  public abstract List<FinancialManagement> loadAllFinancialManagements();

  public abstract List<FinancialManagement> loadAllFinancialManagement(Long[] paramArrayOfLong);

  public abstract void deleteFinancialManagement(FinancialManagement paramFinancialManagement);

  public abstract void deleteAllFinancialManagement(List<FinancialManagement> paramList);

  public abstract List<FinancialManagement> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<FinancialManagement> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllFinancialManagements(List<FinancialManagement> paramList);

  public abstract void enabledAllFinancialManagements(List<FinancialManagement> paramList);

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.financialmanagement.FinancialManagementManager
 * JD-Core Version:    0.6.2
 */