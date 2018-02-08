package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseuse;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.expenseuse.ExpenseUse;
import com.yongjun.tdms.service.expensemanagement.expenseuse.ExpenseUseManager;

public class ListExpenseUseAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ExpenseUseManager expenseUseManager;
	private final CodeValueManager codeValueManager;
	private List<ExpenseUse> expenseUses = null;

	public ListExpenseUseAction(ExpenseUseManager expenseUseManager, CodeValueManager codeValueManager) {
		this.expenseUseManager = expenseUseManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if ((null == this.expenseUses) && (hasIds("expenseUseIds")))
			this.expenseUses = this.expenseUseManager.loadAllExpenseUse(getIds("expenseUseIds"));
	}

	protected String getAdapterName() {
		return "expenseUseHQL";
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
		return "success";
	}

	public String delete() {
		try {
			this.expenseUseManager.deleteAllExpenseUse(this.expenseUses);
			addActionMessage(getText("expenseUse.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("expenseUse.delete.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.expenseUseManager.disabledAllExpenseUse(this.expenseUses);
			addActionMessage(getText("expenseUse.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("expenseUse.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.expenseUseManager.enabledAllExpenseUse(this.expenseUses);
			addActionMessage(getText("expenseApply.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("expenseApply.enabled.error"));
		}
		return "error";
	}

	public List<CodeValue> getAllExpenseType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("069"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();

			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
		}
		return codes;
	}
}
