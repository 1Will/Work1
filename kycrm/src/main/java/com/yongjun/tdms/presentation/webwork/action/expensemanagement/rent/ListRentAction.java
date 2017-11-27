package com.yongjun.tdms.presentation.webwork.action.expensemanagement.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.expensemanagement.expense.Rent;
import com.yongjun.tdms.service.expensemanagement.expense.RentManager;

public class ListRentAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final RentManager rentManager;
	private List<Rent> rents = new ArrayList<Rent>();

	public ListRentAction(RentManager rentManager) {
		this.rentManager = rentManager;
	}

	public void prepare() throws Exception {
		if ((null == this.rents) && (hasIds("rentIds"))) {
			this.rents = this.rentManager.loadAllRent(getIds("rentIds"));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(!hasId("parentRent.id")){
			map.put("parent", "parent");
		}
		return map;
	}
	
	protected String getAdapterName() {
		return "rentHQL";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.rentManager.deleteAllRent(this.rents);
			addActionMessage(getText("rent.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			addActionMessage(getText("rent.delete.error"));
		}
		return ERROR;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

}
