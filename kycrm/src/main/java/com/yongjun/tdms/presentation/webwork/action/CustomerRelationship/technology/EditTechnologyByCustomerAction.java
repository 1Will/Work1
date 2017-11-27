package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.technology;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.technology.Technology;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.technology.TechnologyManager;

public class EditTechnologyByCustomerAction   extends PrepareAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
    private final CustomerInfoManager customerInfoManager;
    private final TechnologyManager technologyManager;
    private Technology technology;
    private CustomerInfo customer;
	public EditTechnologyByCustomerAction(CodeValueManager codeValueManager,CustomerInfoManager customerInfoManager,TechnologyManager technologyManager) {
	    this.codeValueManager=codeValueManager;
	    this.customerInfoManager=customerInfoManager;
	    this.technologyManager=technologyManager;
	}

	public void prepare() throws Exception {
		if (null == this.technology) {
			if (hasId("technology.id")) {
				this.technology = this.technologyManager.loadTechnology(getId("technology.id"));
			} else {
				this.technology = new Technology();
			}
		}

		if (hasId("customer.id")) {
			this.customer = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));
			this.technology.setCustomerInfo(this.customer);
		} 
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}
	
	public List<CodeValue> getAllgenre() {
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("219"));
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
	public List<CodeValue> getAllStyleTypes() {
		
		try {
			List<CodeValue> codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("226"));
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
		boolean isNew = this.technology.isNew();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			if (this.request.getParameter("technology.type").length()>0) {
				String type = this.request.getParameter("technology.type");
				this.technology.setType(codeValueManager.loadByKey("id", Long.parseLong(type)).get(0));
			}
			if (this.request.getParameter("technology.genre").length()>0) {
				String genre = this.request.getParameter("technology.genre");
				this.technology.setGenre(codeValueManager.loadByKey("id", Long.parseLong(genre)).get(0));
			}
			if(this.request.getParameter("customer.id").length()>0){
				this.technology.setCustomerInfo(customerInfoManager.loadCustomerInfo(Long.parseLong(this.request.getParameter("customer.id"))));
			}
			this.technologyManager.storeTechnology(technology);

		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
			addActionMessage(getText("new.technology.error",Arrays.asList(new Object[] { this.technology.getName() })));
			return "error";
			}
			addActionMessage(getText("alert.technology.error",Arrays.asList(new Object[] { this.technology.getName() })));
			return "error";
		}

		if (isNew) {
			addActionMessage(getText("new.technology.success",Arrays.asList(new Object[] { this.technology.getName() })));
			return "success";
		}
		  addActionMessage(getText("alert.technology.success",Arrays.asList(new Object[] { this.technology.getName() })));
		  return "success";
	}

	
}
