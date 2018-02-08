package com.yongjun.tdms.dao.expensemanagement.expenseForm;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;

public interface ExpenseFormDao {
	 public abstract void storeExpenseForm(ExpenseForm paramExpenseForm);

	  public abstract ExpenseForm loadExpenseForm(Long paramLong);

	  public abstract List<ExpenseForm> loadExpenseForm();

	  public abstract List<ExpenseForm> loadAllExpenseForm(Long[] paramArrayOfLong);

	  public abstract void deleteExpenseForm(ExpenseForm paramExpenseForm);

	  public abstract void deleteAllExpenseForm(List<ExpenseForm> paramList);

	  public abstract List<ExpenseForm> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ExpenseForm> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
