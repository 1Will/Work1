package com.yongjun.tdms.presentation.webwork.action.expensemanagement.waterFloorFee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFloorFeeManager;

public class ListWaterFloorFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final WaterFloorFeeManager waterFloorFeeManager;
	private List<WaterFloorFee> waterFloorFees =new ArrayList<WaterFloorFee>();

	public ListWaterFloorFeeAction(WaterFloorFeeManager waterFloorFeeManager) {
		this.waterFloorFeeManager = waterFloorFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.waterFloorFees) && (hasIds("waterFeeIds")))
			this.waterFloorFees = this.waterFloorFeeManager.loadAllWaterFloorFee(getIds("waterFloorFeeIds"));
	}

	protected String getAdapterName() {
		return "waterFloorFeeHQL";
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
			this.waterFloorFeeManager.deleteAllWaterFloorFee(this.waterFloorFees);
			addActionMessage(getText("waterFee.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("waterFee.delete.error"));
		}
		return ERROR;
	}

	public List<WaterFloorFee> getWaterFloorFees() {
		return waterFloorFees;
	}

	public void setWaterFloorFees(List<WaterFloorFee> waterFloorFees) {
		this.waterFloorFees = waterFloorFees;
	}

}
