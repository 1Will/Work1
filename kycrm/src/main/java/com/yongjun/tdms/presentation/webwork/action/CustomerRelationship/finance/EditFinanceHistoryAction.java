package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.finance;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.finance.FinanceHistory;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.finance.FinanceHistoryManager;

public class EditFinanceHistoryAction extends PrepareAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2204911060755693074L;
	
	
	private final CodeValueManager codeValueManager;
	private final CustomerInfoManager customerInfoManager;
	private final FinanceHistoryManager financeHistoryManager;
	private FinanceHistory financeHistory;
	private CustomerInfo customer;
	private long customerInfoId;
	
	public EditFinanceHistoryAction(CodeValueManager codeValueManager,CustomerInfoManager customerInfoManager,FinanceHistoryManager financeHistoryManager) {
		// TODO Auto-generated constructor stub
		this.codeValueManager = codeValueManager;
		this.customerInfoManager = customerInfoManager;
		this.financeHistoryManager = financeHistoryManager;
	}
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == this.financeHistory) {
			if (hasId("financeHistory.id")) {
				this.financeHistory = this.financeHistoryManager.loadFinanceHistory(getId("financeHistory.id"));
			} else {
				this.financeHistory = new FinanceHistory();
			}
		}
		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id");
		} 
	}
	public String save() {
		boolean isNew = this.financeHistory.isNew();
		if (hasId("customerInfo.id")&&isNew) {
			this.customer = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			this.financeHistory.setCustomerInfo(this.customer);
		} 
		if(hasId("financeWay.id")){
			CodeValue codeValue = this.codeValueManager.loadCodeValue(getId("financeWay.id"));
			this.financeHistory.setFinanceWay(codeValue);
			
		}
		this.financeHistoryManager.storeFinanceHistory(financeHistory);
		
		if (isNew) {
			addActionMessage(getText("finance.add.success"));
			return "new";
		}else {
			addActionMessage(getText("finance.update.success"));
			return "new";
		}
	}
	public List<CodeValue> getAllFinanceWay() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "222");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public FinanceHistory getFinanceHistory() {
		return financeHistory;
	}

	public void setFinanceHistory(FinanceHistory financeHistory) {
		this.financeHistory = financeHistory;
	}

	public CustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public long getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(long customerInfoId) {
		this.customerInfoId = customerInfoId;
	}
	
	
	
}
