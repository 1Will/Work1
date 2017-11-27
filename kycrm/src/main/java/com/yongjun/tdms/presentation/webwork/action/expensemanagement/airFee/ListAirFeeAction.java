package com.yongjun.tdms.presentation.webwork.action.expensemanagement.airFee;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.airFee.AirFee;
import com.yongjun.tdms.model.expensemanagement.airFee.AirHouseFee;
import com.yongjun.tdms.service.expensemanagement.airFee.AirFeeManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirHouseFeeManager;

public class ListAirFeeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final AirFeeManager airFeeManager;
	private final AirHouseFeeManager airHouseFeeManager;
	private List<AirFee> airFees;

	public ListAirFeeAction(AirFeeManager airFeeManager,AirHouseFeeManager airHouseFeeManager) {
		this.airFeeManager = airFeeManager;
		this.airHouseFeeManager = airHouseFeeManager;
	}

	public void prepare() throws Exception {
		if ((null == this.airFees) && (hasIds("airFeeIds"))) {
			this.airFees = this.airFeeManager.loadAllAirFee(getIds("airFeeIds"));
		}
	}

	protected String getAdapterName() {
		return "airFeeHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			if(this.airFees != null && this.airFees.size()>0){
				for(int i = 0; i< airFees.size();i++){
					List<AirHouseFee> aFees = this.airHouseFeeManager.loadByKey("airFee.id", this.airFees.get(i).getId());
					if(aFees!=null){
						airHouseFeeManager.deleteAllAirHouseFee(aFees);
					}
				}
				this.airFeeManager.deleteAllAirFee(this.airFees);
				addActionMessage(getText("airFee.delete.success"));
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("airFee.delete.error"));
		}
		return ERROR;
	}

	public List<AirFee> getAirFees() {
		return airFees;
	}

	public void setAirFees(List<AirFee> airFees) {
		this.airFees = airFees;
	}

}
