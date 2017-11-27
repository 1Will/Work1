package com.yongjun.tdms.service.expensemanagement.expenseForm.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.expenseForm.ExpenseFormDao;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;
import com.yongjun.tdms.service.expensemanagement.expenseForm.ExpenseFormManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultExpenseFormManager extends BaseManager implements ExpenseFormManager {
	private final ExpenseFormDao dao;
	private final YongJunSequenceManager yongJunSequenceManager;

	public DefaultExpenseFormManager(ExpenseFormDao dao,YongJunSequenceManager yongJunSequenceManager) {
		this.dao = dao;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void storeExpenseForm(ExpenseForm t) {
		if(t.isNew()){
			t.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_EXPENSEFORM));
		}
		this.dao.storeExpenseForm(t);
	}

	public ExpenseForm loadExpenseForm(Long id) {
		return this.dao.loadExpenseForm(id);
	}

	public List<ExpenseForm> loadExpenseForm() {
		return this.dao.loadExpenseForm();
	}

	public List<ExpenseForm> loadAllExpenseForm(Long[] tIds) {
		return this.dao.loadAllExpenseForm(tIds);
	}

	public void deleteExpenseForm(ExpenseForm t) {
		this.dao.deleteExpenseForm(t);
	}

	public void deleteAllExpenseForm(List<ExpenseForm> ts) {
		this.dao.deleteAllExpenseForm(ts);
	}

	public List<ExpenseForm> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<ExpenseForm> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public void disabledAllExpenseForm(List<ExpenseForm> ts) {
		for (ExpenseForm e : ts) {
			e.setDisabled(true);
			this.dao.storeExpenseForm(e);
		}
	}

	public void enabledAllExpenseForm(List<ExpenseForm> ts) {
		for (ExpenseForm e : ts) {
			e.setDisabled(false);
			this.dao.storeExpenseForm(e);
		}
	}
}
