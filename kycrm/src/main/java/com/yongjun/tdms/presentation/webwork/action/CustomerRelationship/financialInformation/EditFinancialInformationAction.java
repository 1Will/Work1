package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.financialInformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.financialInformation.FinancialInformation;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.financialInformation.FinancialInformationManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditFinancialInformationAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final FinancialInformationManager financialInformationManager;
	private final CustomerInfoManager customerInfoManager;
	private FinancialInformation financialInformation;
	private CustomerInfo customer;
	private long customerInfoId;

	public EditFinancialInformationAction(FinancialInformationManager financialInformationManager,
			CustomerInfoManager customerInfoManager) {
		this.financialInformationManager = financialInformationManager;
		this.customerInfoManager = customerInfoManager;
	}

	public void prepare() throws Exception {
		if (null == this.financialInformation) {
			if (hasId("financialInformation.id")) {
				this.financialInformation = this.financialInformationManager.loadFinancialInformation(getId("financialInformation.id"));

			} else {
				this.financialInformation = new FinancialInformation();
			}
		}

		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id");
		} 

	}

	public String save() {
		boolean isNew = this.financialInformation.isNew();
		if (hasId("customerInfo.id")&&isNew) {
			this.customer = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			this.financialInformation.setCustomerName(this.customer);
		} 
//		if(request.getParameter("financialInformation.year")!=null&&!request.getParameter("financialInformation.year").equals("")){
//		System.out.println(request.getParameter("financialInformation.year"));
//		}
		this.financialInformationManager.storeFinancialInformation(this.financialInformation);
		
		if (isNew) {
			addActionMessage(getText("financialInformation.add.success",
					Arrays.asList(new Object[] { this.financialInformation.getYear() })));
			return "new";
		}else {
			addActionMessage(getText("financialInformation.update.success",
					Arrays.asList(new Object[] { this.financialInformation.getYear() })));
			return "new";
		}
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

	public FinancialInformation getFinancialInformation() {
		return financialInformation;
	}

	public void setFinancialInformation(FinancialInformation financialInformation) {
		this.financialInformation = financialInformation;
	}





}
