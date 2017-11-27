package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.finance;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.finance.FinanceHistory;
import com.yongjun.tdms.service.CustomerRelationship.finance.FinanceHistoryManager;

@SuppressWarnings("serial")
public class ListFinanceHistoryAction extends ValueListAction {
	
	private final FinanceHistoryManager financeHistoryManager;
	private List<FinanceHistory> financeHistories;
	private Long customerId;
	
	
	public ListFinanceHistoryAction(FinanceHistoryManager financeHistoryManager) {
		// TODO Auto-generated constructor stub
		this.financeHistoryManager = financeHistoryManager;
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (hasIds("financeHistoryIds")) {
			this.financeHistories = this.financeHistoryManager.loadAllFinanceHistory(getIds("financeHistoryIds"));
		}
		
		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
			setFirst(false);
		}
	}
	
	
	
	@Override
	protected String getAdapterName() {
		// TODO Auto-generated method stub
		return "financeHistoryHQL";
	}
	
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}
	public String execute() throws Exception {
		if (isDelete()) {
			delete();
		}
		return "success";
	}
	
	
	private String delete() {
		try {
			this.financeHistoryManager.deleteAllFinanceHistory(financeHistories);;
			addActionMessage(getText("financeHistory.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("financeHistory.delete.error"));
		}
		return "error";
	}

	public List<FinanceHistory> getFinanceHistories() {
		return financeHistories;
	}

	public void setFinanceHistories(List<FinanceHistory> financeHistories) {
		this.financeHistories = financeHistories;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
