package com.yongjun.tdms.dao.expensemanagement.expenseForm.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.expenseForm.ExpenseFormDao;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;

public class HibernateExpenseForm extends BaseHibernateDao implements ExpenseFormDao {
	public void storeExpenseForm(ExpenseForm t) {
		store(t);
	}

	public ExpenseForm loadExpenseForm(Long id) {
		return (ExpenseForm) load(ExpenseForm.class, id);
	}

	public List<ExpenseForm> loadExpenseForm() {
		return loadAll(ExpenseForm.class);
	}

	public List<ExpenseForm> loadAllExpenseForm(Long[] tIds) {
		return loadAll(ExpenseForm.class, tIds);
	}

	public void deleteExpenseForm(ExpenseForm t) {
		delete(t);
	}

	public void deleteAllExpenseForm(List<ExpenseForm> ts) {
		deleteAll(ts);
	}

	public List<ExpenseForm> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(ExpenseForm.class, key, value);
	}

	public List<ExpenseForm> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ExpenseForm.class, keyNames, keyValues);
	}
}
