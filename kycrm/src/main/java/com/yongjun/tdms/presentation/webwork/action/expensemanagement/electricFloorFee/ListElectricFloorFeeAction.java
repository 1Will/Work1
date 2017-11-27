package com.yongjun.tdms.presentation.webwork.action.expensemanagement.electricFloorFee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFloorFeeManager;

public class ListElectricFloorFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ElectricFloorFeeManager electricFloorFeeManager;
	private List<ElectricFloorFee> electricFloorFees =new ArrayList<ElectricFloorFee>();

	public ListElectricFloorFeeAction(ElectricFloorFeeManager electricFloorFeeManager) {
		this.electricFloorFeeManager = electricFloorFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.electricFloorFees) && (hasIds("electricFeeIds")))
			this.electricFloorFees = this.electricFloorFeeManager.loadAllElectricFloorFee(getIds("electricFloorFeeIds"));
	}

	protected String getAdapterName() {
		return "electricFloorFeeHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}
	
	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.electricFloorFeeManager.deleteAllElectricFloorFee(this.electricFloorFees);
			addActionMessage(getText("electricFee.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("electricFee.delete.error"));
		}
		return ERROR;
	}

	public List<ElectricFloorFee> getElectricFloorFees() {
		return electricFloorFees;
	}

	public void setElectricFloorFees(List<ElectricFloorFee> electricFloorFees) {
		this.electricFloorFees = electricFloorFees;
	}

}
