package com.yongjun.tdms.service.expensemanagement.expenseapply;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.expenseapply.ExpenseApply;
import java.util.List;

public abstract interface ExpenseApplyManager
{
  public abstract void storeExpenseApply(ExpenseApply paramExpenseApply);

  public abstract ExpenseApply loadExpenseApply(Long paramLong);

  public abstract List<ExpenseApply> loadExpenseApply();

  public abstract List<ExpenseApply> loadAllExpenseApply(Long[] paramArrayOfLong);

  public abstract void deleteExpenseApply(ExpenseApply paramExpenseApply);

  public abstract void deleteAllExpenseApply(List<ExpenseApply> paramList);

  public abstract List<ExpenseApply> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ExpenseApply> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);

  public abstract void disabledAllExpenseApply(List<ExpenseApply> paramList);

  public abstract void enabledAllExpenseApply(List<ExpenseApply> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.expensemanagement.expenseapply.ExpenseApplyManager
 * JD-Core Version:    0.6.2
 */