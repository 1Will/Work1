package com.yongjun.tdms.service.expensemanagement.expense.pojo;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.expense.ExpenseDao;
import com.yongjun.tdms.model.expensemanagement.expense.Expense;
import com.yongjun.tdms.service.expensemanagement.expense.ExpenseManager;

public class DefaultExpenseManager extends BaseManager implements ExpenseManager {
	private final ExpenseDao dao;

	public DefaultExpenseManager(ExpenseDao dao) {
		this.dao = dao;
	}

	public void storeExpense(Expense t) {
		this.dao.storeExpense(t);
	}

	public Expense loadExpense(Long id) {
		return this.dao.loadExpense(id);
	}

	public List<Expense> loadExpense() {
		return this.dao.loadExpense();
	}

	public List<Expense> loadAllExpense(Long[] tIds) {
		return this.dao.loadAllExpense(tIds);
	}

	public void deleteExpense(Expense t) {
		this.dao.deleteExpense(t);
	}

	public void deleteAllExpense(List<Expense> ts) {
		this.dao.deleteAllExpense(ts);
	}

	public List<Expense> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<Expense> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public Date loadLastEndTime() {
		return dao.loadLastEndTime();
	}

	public Expense loadExpensebyMaxId() {
		return dao.loadExpensebyMaxId();
	}
}
