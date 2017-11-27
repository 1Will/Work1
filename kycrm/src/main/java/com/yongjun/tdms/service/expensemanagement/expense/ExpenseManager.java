package com.yongjun.tdms.service.expensemanagement.expense;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.expense.Expense;

public interface ExpenseManager {

	public abstract void storeExpense(Expense paramExpense);

	public abstract Expense loadExpense(Long paramLong);

	public abstract List<Expense> loadExpense();

	public abstract List<Expense> loadAllExpense(Long[] paramArrayOfLong);

	public abstract void deleteExpense(Expense paramExpense);

	public abstract void deleteAllExpense(List<Expense> paramList);

	public abstract List<Expense> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<Expense> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
    //查询最后一条结束时间
	public abstract Date loadLastEndTime();
	//查询最大id
	public abstract Expense loadExpensebyMaxId();
}
