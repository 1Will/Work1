package com.yongjun.tdms.presentation.webwork.action.expensemanagement.airHouseFee;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.airFee.AirFee;
import com.yongjun.tdms.model.expensemanagement.airFee.AirHouseFee;
import com.yongjun.tdms.service.expensemanagement.airFee.AirFeeManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirHouseFeeManager;

public class ListAirHouseFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final AirHouseFeeManager airHouseFeeManager;
	private final AirFeeManager airFeeManager;
	private List<AirHouseFee> airHouseFees = new ArrayList<AirHouseFee>();
	private AirFee airFee;

	public ListAirHouseFeeAction(AirHouseFeeManager airHouseFeeManager,AirFeeManager airFeeManager) {
		this.airHouseFeeManager = airHouseFeeManager;
		this.airFeeManager = airFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.airHouseFees) && (hasIds("airHouseFeeIds"))) {
			this.airHouseFees = this.airHouseFeeManager.loadAllAirHouseFee(getIds("airHouseFeeIds"));
		}
		if (hasId("airFee.id")) {
			this.airFee = this.airFeeManager.loadAirFee(getId("airFee.id"));
		}
	}

	protected String getAdapterName() {
		return "airHouseFeeHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.airHouseFeeManager.deleteAllAirHouseFee(this.airHouseFees);
			addActionMessage(getText("airHouseFee.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("airHouseFee.delete.error"));
		}
		return ERROR;
	}

	public List<AirHouseFee> getAirHouseFees() {
		return airHouseFees;
	}

	public void setAirHouseFees(List<AirHouseFee> airHouseFees) {
		this.airHouseFees = airHouseFees;
	}

	public AirFee getAirFee() {
		return airFee;
	}

	public void setAirFee(AirFee airFee) {
		this.airFee = airFee;
	}

}
