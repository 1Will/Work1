package com.yongjun.tdms.dao.expensemanagement.expense.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.expense.ExpenseDao;
import com.yongjun.tdms.model.expensemanagement.expense.Expense;

public class HibernateExpense extends BaseHibernateDao implements ExpenseDao {
	public void storeExpense(Expense t) {
		store(t);
	}

	public Expense loadExpense(Long id) {
		return (Expense) load(Expense.class, id);
	}

	public List<Expense> loadExpense() {
		return loadAll(Expense.class);
	}

	public List<Expense> loadAllExpense(Long[] tIds) {
		return loadAll(Expense.class, tIds);
	}

	public void deleteExpense(Expense t) {
		delete(t);
	}

	public void deleteAllExpense(List<Expense> ts) {
		deleteAll(ts);
	}

	public List<Expense> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(Expense.class, key, value);
	}

	public List<Expense> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(Expense.class, keyNames, keyValues);
	}

	public Date loadLastEndTime() {
		Date date=null;
		String hql  = "select e from Expense e where e.id=(select max(ex.id)  from Expense ex)";
		Session session = getSession();
		List<Expense> expenses=session.createQuery(hql).list();
		if(expenses!=null&& expenses.size()>0){
			date=expenses.get(0).getEndTime();
		}
		return date;
	}

	public Expense loadExpensebyMaxId() {
		String hql  = "select e from Expense e where e.id=(select max(ex.id)  from Expense ex) ";
		Session session = getSession();
		List <Expense> expenses=session.createQuery(hql).list();
		if(expenses!=null&& expenses.size()>0){
			return expenses.get(0);
		}
		return null;
	}
}
