package com.yongjun.tdms.presentation.webwork.action.expensemanagement.electricHouseFee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricHouseFee;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricHouseFeeManager;

public class ListElectricHouseFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ElectricHouseFeeManager electricHouseFeeManager;
	private List<ElectricHouseFee> electricHouseFees = new ArrayList<ElectricHouseFee>();

	public ListElectricHouseFeeAction(ElectricHouseFeeManager electricHouseFeeManager) {
		this.electricHouseFeeManager = electricHouseFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.electricHouseFees) && (hasIds("electricFeeIds")))
			this.electricHouseFees = this.electricHouseFeeManager
					.loadAllElectricHouseFee(getIds("electricHouseFeeIds"));
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	protected String getAdapterName() {
		return "electricHouseFeeHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.electricHouseFeeManager.deleteAllElectricHouseFee(this.electricHouseFees);
			addActionMessage(getText("electricFee.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("electricFee.delete.error"));
		}
		return ERROR;
	}

	public List<ElectricHouseFee> getElectricHouseFees() {
		return electricHouseFees;
	}

	public void setElectricHouseFees(List<ElectricHouseFee> electricHouseFees) {
		this.electricHouseFees = electricHouseFees;
	}

}
