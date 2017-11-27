package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.additionalInfo;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.service.CustomerRelationship.additionalInfo.CusAdditionalInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;

public class EditAdditionalInfoAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CusAdditionalInfoManager cusAdditionalInfoManager;
	private final CustomerInfoManager customerInfoManager;
	private CusAdditionalInfo cusAdditionalInfo;
	private CustomerInfo customerInfo;

	public EditAdditionalInfoAction(CusAdditionalInfoManager cusAdditionalInfoManager,
			CustomerInfoManager customerInfoManager) {
		this.cusAdditionalInfoManager = cusAdditionalInfoManager;
		this.customerInfoManager = customerInfoManager;
	}

	public void prepare() throws Exception {
		if (hasId("customerInfo.id")) {
			this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
		}

		if ((null != this.customerInfo) && (null != this.customerInfo.getAdditional())) {
			this.cusAdditionalInfo = this.customerInfo.getAdditional();
		} else {
			this.cusAdditionalInfo = new CusAdditionalInfo();
			this.cusAdditionalInfo.setCi(this.customerInfo);
		}
	}

	public String save() {
		boolean isNew = this.cusAdditionalInfo.isNew();
		System.out.println(isNew);
		try {
			this.cusAdditionalInfoManager.storeCusAdditionalInfo(this.cusAdditionalInfo);
			if (null != this.customerInfo) {
				this.customerInfo.setAdditional(this.cusAdditionalInfo);
				this.customerInfoManager.storeCustomerInfo(this.customerInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("additionalInfo.add.error",
						Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));

				return "error";
			}
			addActionMessage(getText("additionalInfo.edit.error",
					Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));

			return "error";
		}

		if (isNew) {
			addActionMessage(getText("additionalInfo.add.success",
					Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));

			return "new";
		}
		addActionMessage(getText("additionalInfo.edit.success",
				Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));

		return "success";
	}

	public CusAdditionalInfo getCusAdditionalInfo() {
		return this.cusAdditionalInfo;
	}

	public void setCusAdditionalInfo(CusAdditionalInfo cusAdditionalInfo) {
		this.cusAdditionalInfo = cusAdditionalInfo;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
}
