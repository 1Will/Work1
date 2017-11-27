package com.yongjun.tdms.dao.expensemanagement.expense;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.expense.Expense;

public interface ExpenseDao {
	 public abstract void storeExpense(Expense paramExpense);

	  public abstract Expense loadExpense(Long paramLong);

	  public abstract List<Expense> loadExpense();

	  public abstract List<Expense> loadAllExpense(Long[] paramArrayOfLong);

	  public abstract void deleteExpense(Expense paramExpense);

	  public abstract void deleteAllExpense(List<Expense> paramList);

	  public abstract List<Expense> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<Expense> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
	  public abstract Date loadLastEndTime();


	public abstract Expense loadExpensebyMaxId();
}
