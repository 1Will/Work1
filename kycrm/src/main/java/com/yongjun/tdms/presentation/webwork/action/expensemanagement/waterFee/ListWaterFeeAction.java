package com.yongjun.tdms.presentation.webwork.action.expensemanagement.waterFee;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterHouseFee;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFeeManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFloorFeeManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterHouseFeeManager;

public class ListWaterFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final WaterFeeManager waterFeeManager;
	private final WaterFloorFeeManager waterFloorFeeManager;
	private final WaterHouseFeeManager waterHouseFeeManager;
	private List<WaterFee> waterFees;

	public ListWaterFeeAction(WaterFeeManager waterFeeManager,WaterFloorFeeManager waterFloorFeeManager,
			WaterHouseFeeManager waterHouseFeeManager) {
		this.waterFeeManager = waterFeeManager;
		this.waterFloorFeeManager = waterFloorFeeManager;
		this.waterHouseFeeManager = waterHouseFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.waterFees) && (hasIds("waterFeeIds")))
			this.waterFees = this.waterFeeManager.loadAllWaterFee(getIds("waterFeeIds"));
	}

	protected String getAdapterName() {
		return "waterFeeHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			if(this.waterFees!=null && this.waterFees.size()>0){
				for(int i = 0; i< waterFees.size();i++){
					List<WaterHouseFee> whFees = this.waterHouseFeeManager.loadByKey("waterFee.id", this.waterFees.get(i).getId());
					if(whFees!=null){
						waterHouseFeeManager.deleteAllWaterHouseFee(whFees);
					}
					List<WaterFloorFee> wfFees = this.waterFloorFeeManager.loadByKey("waterFee.id", this.waterFees.get(i).getId());
					if(wfFees!=null){
						waterFloorFeeManager.deleteAllWaterFloorFee(wfFees);
					}
				}
				this.waterFeeManager.deleteAllWaterFee(this.waterFees);
				addActionMessage(getText("waterFee.delete.success"));
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("waterFee.delete.error"));
		}
		return ERROR;
	}

	public List<WaterFee> getWaterFees() {
		return waterFees;
	}

	public void setWaterFees(List<WaterFee> waterFees) {
		this.waterFees = waterFees;
	}

}
