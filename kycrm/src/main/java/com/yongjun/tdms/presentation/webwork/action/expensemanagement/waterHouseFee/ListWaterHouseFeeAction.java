package com.yongjun.tdms.presentation.webwork.action.expensemanagement.waterHouseFee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterHouseFee;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterHouseFeeManager;

public class ListWaterHouseFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final WaterHouseFeeManager waterHouseFeeManager;
	private List<WaterHouseFee> waterHouseFees =new ArrayList<WaterHouseFee>();

	public ListWaterHouseFeeAction(WaterHouseFeeManager waterHouseFeeManager) {
		this.waterHouseFeeManager = waterHouseFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.waterHouseFees) && (hasIds("waterFeeIds")))
			this.waterHouseFees = this.waterHouseFeeManager.loadAllWaterHouseFee(getIds("waterHouseFeeIds"));
	}
	
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	protected String getAdapterName() {
		return "waterHouseFeeHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.waterHouseFeeManager.deleteAllWaterHouseFee(this.waterHouseFees);
			addActionMessage(getText("waterFee.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("waterFee.delete.error"));
		}
		return ERROR;
	}

	public List<WaterHouseFee> getWaterHouseFees() {
		return waterHouseFees;
	}

	public void setWaterHouseFees(List<WaterHouseFee> waterHouseFees) {
		this.waterHouseFees = waterHouseFees;
	}

}
