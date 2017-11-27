package com.yongjun.tdms.presentation.webwork.action.expensemanagement.electricFee;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricHouseFee;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFloorFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricHouseFeeManager;

public class ListElectricFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ElectricFeeManager electricFeeManager;
	private final ElectricFloorFeeManager electricFloorFeeManager;
	private final ElectricHouseFeeManager electricHouseFeeManager;
	private List<ElectricFee> electricFees;

	public ListElectricFeeAction(ElectricFeeManager electricFeeManager,ElectricFloorFeeManager electricFloorFeeManager,
			ElectricHouseFeeManager electricHouseFeeManager) {
		this.electricFeeManager = electricFeeManager;
		this.electricFloorFeeManager = electricFloorFeeManager;
		this.electricHouseFeeManager = electricHouseFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.electricFees) && (hasIds("electricFeeIds")))
			this.electricFees = this.electricFeeManager.loadAllElectricFee(getIds("electricFeeIds"));
	}

	protected String getAdapterName() {
		return "electricFeeHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			if(this.electricFees!=null && this.electricFees.size()>0){
				for(int i = 0; i< electricFees.size();i++){
					List<ElectricHouseFee> ehFees = this.electricHouseFeeManager.loadByKey("electricFee.id", this.electricFees.get(i).getId());
					if(ehFees!=null){
						electricHouseFeeManager.deleteAllElectricHouseFee(ehFees);
					}
					List<ElectricFloorFee> wfFees = this.electricFloorFeeManager.loadByKey("electricFee.id", this.electricFees.get(i).getId());
					if(wfFees!=null){
						electricFloorFeeManager.deleteAllElectricFloorFee(wfFees);
					}
				}
				this.electricFeeManager.deleteAllElectricFee(this.electricFees);
				addActionMessage(getText("electricFee.delete.success"));
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("electricFee.delete.error"));
		}
		return ERROR;
	}

	public List<ElectricFee> getElectricFees() {
		return electricFees;
	}

	public void setElectricFees(List<ElectricFee> electricFees) {
		this.electricFees = electricFees;
	}

}
