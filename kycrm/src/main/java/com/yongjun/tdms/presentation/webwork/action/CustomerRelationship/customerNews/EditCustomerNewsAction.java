package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerNews;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.CustomerRelationship.customerNews.CustomerNews;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.service.CustomerRelationship.customerNews.CustomerNewsManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;

public class EditCustomerNewsAction extends FileTransportAction{
    /**
 * 
 */
private static final long serialVersionUID = 1L;
	private final CustomerNewsManager customerNewsManager;
	private final CustomerInfoManager customerInfoManager;
    private final FileTransportManager fileTransportManager;
    private CustomerNews customerNews;
    private CustomerInfo customer;
    private long customerInfoId;
    
public EditCustomerNewsAction(CustomerNewsManager customerNewsManager,
		FileTransportManager fileTransportManager,CustomerInfoManager customerInfoManager) {
	this.customerNewsManager = customerNewsManager;
	this.fileTransportManager = fileTransportManager;
	this.customerInfoManager=customerInfoManager;
}
public void prepare() throws Exception {
	if (null == this.customerNews) {
		if (hasId("customerNews.id")) {
			this.customerNews = this.customerNewsManager.loadCustomerNews(getId("customerNews.id"));

		} else {
			this.customerNews = new CustomerNews();
		}
	}

	if (hasId("customerInfo.id")) {
		this.customerInfoId=getId("customerInfo.id");
	} 
}
public String save() {
	boolean isNew = this.customerNews.isNew();
	if ((isNew) || (getFile() != null)) {
		if (!isNew) {
			this.fileTransportManager.delete(this.request, this.customerNews.getPosition());
		}
		String location;
		try {
			location = this.fileTransportManager.upload2(this.request, getFile(), "origFileName",request.getParameter("savePicName"));
			this.customerNews.setPosition(location);
			String orgFileName = this.request.getParameter("origFileName");
			this.customerNews.setPictureName(orgFileName);
			this.customerNews.setFileNo(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	try {
		if (hasId("customerInfo.id")) {
			this.customer = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			this.customerNews.setCustomerInfo(customer);
		} 
		if (!StringUtils.isEmpty("customerNews.pictureName")) {
			String pictureName = this.request.getParameter("customerNews.pictureName");
			this.customerNews.setPictureName(pictureName);
		}
		if (!StringUtils.isEmpty("customerNews。content")) {
			String content = this.request.getParameter("customerNews。content");
			this.customerNews.setContent(content);
		}
		this.customerNewsManager.storeCustomerNews(customerNews);
	} catch (Exception e) {
		e.printStackTrace();
		if (isNew) {
		addActionMessage(getText("new.customerNews.error",Arrays.asList(new Object[] { this.customerNews.getPictureName()})));
		return "error";
		}
		addActionMessage(getText("alert.customerNews.error",Arrays.asList(new Object[] { this.customerNews.getPictureName() })));
		return "error";
	}

	if (isNew) {
		addActionMessage(getText("new.customerNews.success",Arrays.asList(new Object[] { this.customerNews.getPictureName() })));
		return "success";
	}
	  addActionMessage(getText("alert.customerNews.success",Arrays.asList(new Object[] { this.customerNews.getPictureName() })));
	  return "success";
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
public CustomerNews getCustomerNews() {
	return customerNews;
}
public void setCustomerNews(CustomerNews customerNews) {
	this.customerNews = customerNews;
}

}
