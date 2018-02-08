package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseForm;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;
import com.yongjun.tdms.service.expensemanagement.expenseForm.ExpenseFormManager;

public class ListExpenseFormAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ExpenseFormManager expenseFormManager;
	private List<ExpenseForm> expenseForms = null;

	public ListExpenseFormAction(ExpenseFormManager expenseFormManager) {
		this.expenseFormManager = expenseFormManager;
	}

	public void prepare() throws Exception {
		if ((null == this.expenseForms) && (hasIds("expenseFormIds")))
			this.expenseForms = this.expenseFormManager.loadAllExpenseForm(getIds("expenseFormIds"));
	}

	protected String getAdapterName() {
		return "expenseFormHQL";
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.expenseFormManager.deleteAllExpenseForm(this.expenseForms);
			addActionMessage(getText("expenseForm.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("expenseForm.delete.error"));
		}
		return ERROR;
	}

	private String disabled() {
		try {
			this.expenseFormManager.disabledAllExpenseForm(this.expenseForms);
			addActionMessage(getText("expenseForm.disabled.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("expenseForm.disabled.error"));
		}
		return ERROR;
	}

	private String enabled() {
		try {
			this.expenseFormManager.enabledAllExpenseForm(this.expenseForms);
			addActionMessage(getText("expenseApply.enabled.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("expenseApply.enabled.error"));
		}
		return ERROR;
	}

	public List<ExpenseForm> getExpenseForms() {
		return expenseForms;
	}

	public void setExpenseForms(List<ExpenseForm> expenseForms) {
		this.expenseForms = expenseForms;
	}
}
