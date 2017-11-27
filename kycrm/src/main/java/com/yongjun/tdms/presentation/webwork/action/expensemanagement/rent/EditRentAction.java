package com.yongjun.tdms.presentation.webwork.action.expensemanagement.rent;

import java.text.DecimalFormat;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.expensemanagement.expense.Rent;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.expensemanagement.expense.RentManager;

public class EditRentAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final RentManager rentManager;
	private final CodeValueManager codeValueManager;
	private final HouseListManager houseListManager;

	public DecimalFormat format = new DecimalFormat("0.00");
	private Rent rent;

	public EditRentAction(RentManager rentManager,CodeValueManager codeValueManager, HouseListManager houseListManager) {
		this.rentManager = rentManager;
		this.codeValueManager = codeValueManager;
		this.houseListManager = houseListManager;
	}

	public void prepare() throws Exception {
		if (hasId("rent.id")) {
			this.rent = this.rentManager.loadRent(getId("rent.id"));
		} else {
			this.rent = new Rent();
		}
	}

	public String save() {
		boolean isNew = this.rent.isNew();
		try {
			this.rentManager.storeRent(this.rent);
			if (isNew) {
				addActionMessage(getText("rent.add.success"));
				return NEW;
			}
			addActionMessage(getText("rent.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage(getText("rent.edit.error"));
			return ERROR;
		}
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

}
