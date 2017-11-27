package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryset;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset.SalarySet;
import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryset.SalarySetManager;
import java.util.ArrayList;
import java.util.List;

public class ListSalarySetAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final SalarySetManager salarySetManager;
	private final CodeValueManager codeValueManager;
	private List<SalarySet> salarySets;

	public ListSalarySetAction(SalarySetManager salarySetManager, CodeValueManager codeValueManager) {
		this.salarySetManager = salarySetManager;
		this.codeValueManager = codeValueManager;
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			return disable();
		}
		if (isEnable()) {
			return enable();
		}
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public void prepare() throws Exception {
		if (hasIds("salarySetIds"))
			this.salarySets = this.salarySetManager.loadAllSalarySet(getIds("salarySetIds"));
	}

	public String disable() {
		this.salarySetManager.disabledSalarySets(this.salarySets);
		addActionMessage(getText("salarySet.disable.success"));
		return "success";
	}

	public String delete() {
		try {
			this.salarySetManager.deleteAllSalarySet(this.salarySets);
			addActionMessage(getText("salarySet.delete.success"));
		} catch (Exception e) {
			addActionMessage(getText("salarySet.delete.error"));
		}
		return "success";
	}

	public String enable() {
		this.salarySetManager.enabledSalarySets(this.salarySets);
		addActionMessage(getText("salarySet.enable.success"));
		return "success";
	}

	protected String getAdapterName() {
		return "salarySetes";
	}

	public List<CodeValue> getAllStatus() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("055558"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<SalarySet> getSalarySets() {
		return this.salarySets;
	}

	public void setSalarySets(List<SalarySet> salarySets) {
		this.salarySets = salarySets;
	}
}
