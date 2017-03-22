package com.yongjun.tdms.dao.expensemanagement.expenseuse;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.expenseuse.ExpenseUse;
import java.util.List;

public abstract interface ExpenseUseDao
{
  public abstract void storeExpenseUse(ExpenseUse paramExpenseUse);

  public abstract ExpenseUse loadExpenseUse(Long paramLong);

  public abstract List<ExpenseUse> loadExpenseUse();

  public abstract List<ExpenseUse> loadAllExpenseUse(Long[] paramArrayOfLong);

  public abstract void deleteExpenseUse(ExpenseUse paramExpenseUse);

  public abstract void deleteAllExpenseUse(List<ExpenseUse> paramList);

  public abstract List<ExpenseUse> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ExpenseUse> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.expensemanagement.expenseuse.ExpenseUseDao
 * JD-Core Version:    0.6.2
 */