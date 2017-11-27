package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.newStandard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.newStandard.NewStandard;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.newStandard.NewStandardManager;

public class EditNewStandardByCustomerAction extends PrepareAction{

	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final CodeValueManager codeValueManager;
	    private final CustomerInfoManager customerInfoManager;
	    private final NewStandardManager newStandardManager;
	    private NewStandard newStandard;
	    private CustomerInfo customer;
	    private long customerInfoId;
		public EditNewStandardByCustomerAction(CodeValueManager codeValueManager,CustomerInfoManager customerInfoManager,NewStandardManager newStandardManager) {
		    this.codeValueManager=codeValueManager;
		    this.customerInfoManager=customerInfoManager;
		    this.newStandardManager=newStandardManager;
		}
		
		public long getCustomerInfoId() {
			return customerInfoId;
		}

		public void setCustomerInfoId(long customerInfoId) {
			this.customerInfoId = customerInfoId;
		}

		public void prepare() throws Exception {
			customer=null;
			if (null == this.newStandard) {
				if (hasId("newStandard.id")) {
					this.newStandard = this.newStandardManager.loadNewStandard(getId("newStandard.id"));

				} else {
					this.newStandard = new NewStandard();
				}
			}

			if (hasId("customerInfo.id")) {
				this.customerInfoId=getId("customerInfo.id");
			} 
		}

		
		public List<CodeValue> getAllgenre() {
			try {
				List<CodeValue> codes = new ArrayList<CodeValue>();
				List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("221"));
				if ((null != one) && (one.size() > 0)) {
					List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
					if ((null != list) && (list.size() > 0)) {
						codes.addAll(list);
					}
				}
				return codes;
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList();
			}
		}
		public List<CodeValue> getAllpartakeGenre() {
			
			try {
				List<CodeValue> codes = new ArrayList<CodeValue>();
				List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("228"));
				if ((null != one) && (one.size() > 0)) {
					List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
					if ((null != list) && (list.size() > 0)) {
						codes.addAll(list);
					}
				}
				return codes;
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList();
			}
			
		}
		
		public String save() {
			boolean isNew = this.newStandard.isNew();
			try {
				if (hasId("customerInfo.id")&&isNew) {
					this.customer = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
					newStandard.setCustomerInfo(customer);
				} 
				if (!StringUtils.isEmpty("newStandard.genre")) {
					String genre = this.request.getParameter("newStandard.genre");
					this.newStandard.setGenre(codeValueManager.loadByKey("id", Long.parseLong(genre)).get(0));
				}
				if (!StringUtils.isEmpty("newStandard.partakeGenre")) {
					String partakeGenre = this.request.getParameter("newStandard.partakeGenre");
					this.newStandard.setPartakeGenre(codeValueManager.loadByKey("id", Long.parseLong(partakeGenre)).get(0));
				}
				this.newStandardManager.storeNewStandard(newStandard);;
			} catch (Exception e) {
				e.printStackTrace();
				if (isNew) {
				addActionMessage(getText("new.technology.error",Arrays.asList(new Object[] { this.newStandard.getName() })));
				return "error";
				}
				addActionMessage(getText("alert.technology.error",Arrays.asList(new Object[] { this.newStandard.getName() })));
				return "error";
			}

			if (isNew) {
				addActionMessage(getText("new.technology.success",Arrays.asList(new Object[] { this.newStandard.getName() })));
				return "success";
			}
			  addActionMessage(getText("alert.technology.success",Arrays.asList(new Object[] { this.newStandard.getName() })));
			  return "success";
		}

		public NewStandard getNewStandard() {
			return newStandard;
		}

		public void setNewStandard(NewStandard newStandard) {
			this.newStandard = newStandard;
		}

		public CustomerInfo getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerInfo customer) {
			this.customer = customer;
		}


		public NewStandardManager getNewStandardManager() {
			return newStandardManager;
		}

	}

