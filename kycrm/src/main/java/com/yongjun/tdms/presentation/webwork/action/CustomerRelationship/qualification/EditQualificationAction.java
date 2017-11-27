package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.qualification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.qualification.CustomerRelationshipQualification;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.qualification.QualificationManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditQualificationAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final QualificationManager customerRelationshipQualificationManager;
	private final CustomerInfoManager customerInfoManager;
	private CustomerRelationshipQualification qualification;
	private final CodeValueManager codeValueManager;
	private CustomerInfo customer;
	private long customerInfoId;

	public EditQualificationAction(QualificationManager customerRelationshipQualificationManager,
			CustomerInfoManager customerInfoManager,CodeValueManager codeValueManager) {
		this.customerRelationshipQualificationManager = customerRelationshipQualificationManager;
		this.customerInfoManager = customerInfoManager;
		this.codeValueManager=codeValueManager;
	}

	public void prepare() throws Exception {
		if (null == this.qualification) {
			if (hasId("qualification.id")) {
				this.qualification = this.customerRelationshipQualificationManager.loadQualification(getId("qualification.id"));

			} else {
				this.qualification = new CustomerRelationshipQualification();
			}
		}

		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id");
		} 

	}

	public String save() {
		boolean isNew = this.qualification.isNew();
		if (hasId("customerInfo.id")&&isNew) {
			this.customer = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			this.qualification.setCustomerName(this.customer);
		} 
		if(hasId("level.id")){
			CodeValue codeValue = this.codeValueManager.loadCodeValue(getId("level.id"));
			this.qualification.setLevel(codeValue);
			
		}
		this.customerRelationshipQualificationManager.storeQualification(this.qualification);
		
		if (isNew) {
			addActionMessage(getText("qualification.add.success",
					Arrays.asList(new Object[] { this.qualification.getName() })));
			return "new";
		}else {
			addActionMessage(getText("qualification.update.success",
					Arrays.asList(new Object[] { this.qualification.getName() })));
			return "new";
		}
	}
	
	public List<CodeValue> getAllLevels() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "220");
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

	public CustomerRelationshipQualification getQualification() {
		return qualification;
	}

	public void setQualification(CustomerRelationshipQualification qualification) {
		this.qualification = qualification;
	}





}
