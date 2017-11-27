package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProduct;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.CustomerRelationship.customerProduct.CustomerProduct;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.service.CustomerRelationship.customerProduct.CustomerProductManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;

public class EditCustomerProductAction extends FileTransportAction{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private final CustomerProductManager customerProductManager;
		private final CustomerInfoManager customerInfoManager;
	    private final FileTransportManager fileTransportManager;
	    private CustomerProduct customerProduct;
	    private CustomerInfo customer;
	    private long customerInfoId;
	    
	public EditCustomerProductAction(CustomerProductManager customerProductManager,
			FileTransportManager fileTransportManager,CustomerInfoManager customerInfoManager) {
		this.customerProductManager = customerProductManager;
		this.fileTransportManager = fileTransportManager;
		this.customerInfoManager=customerInfoManager;
	}
   public void prepare() throws Exception {
		if (null == this.customerProduct) {
			if (hasId("customerProdust.id")) {
				this.customerProduct = this.customerProductManager.loadCustomerProduct(getId("customerProdust.id"));

			} else {
				this.customerProduct = new CustomerProduct();
			}
		}

		if (hasId("customerInfo.id")) {
			this.customerInfoId=getId("customerInfo.id");
		} 
	}
   public String save() {
		boolean isNew = this.customerProduct.isNew();
		if ((isNew) || (getFile() != null)) {
			if (!isNew) {
				this.fileTransportManager.delete(this.request, this.customerProduct.getPosition());
			}
			String location;
			try {
				location = this.fileTransportManager.upload2(this.request, getFile(), "origFileName",request.getParameter("savePicName"));
				this.customerProduct.setPosition(location);
				String orgFileName = this.request.getParameter("origFileName");
				this.customerProduct.setPictureName(orgFileName);
				this.customerProduct.setFileNo(location);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			if (hasId("customerInfo.id")) {
				this.customer = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
				this.customerProduct.setCustomerInfo(customer);
			} 
			if (!StringUtils.isEmpty("customerProduct.pictureName")) {
				String pictureName = this.request.getParameter("customerProduct.pictureName");
				this.customerProduct.setPictureName(pictureName);
			}
			if (!StringUtils.isEmpty("customerProduct。productDescribe")) {
				String description = this.request.getParameter("customerProduct。productDescribe");
				System.out.println(description);
				this.customerProduct.setProductDescribe(description);
			}
			this.customerProductManager.storeCustomerProduct(customerProduct);;
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
			addActionMessage(getText("new.CustomerProduct.error",Arrays.asList(new Object[] { this.customerProduct.getProductName() })));
			return "error";
			}
			addActionMessage(getText("alert.CustomerProduct.error",Arrays.asList(new Object[] { this.customerProduct.getProductName() })));
			return "error";
		}

		if (isNew) {
			addActionMessage(getText("new.CustomerProduct.success",Arrays.asList(new Object[] { this.customerProduct.getProductName() })));
			return "success";
		}
		  addActionMessage(getText("alert.CustomerProduct.success",Arrays.asList(new Object[] { this.customerProduct.getProductName() })));
		  return "success";
	}
public CustomerProduct getCustomerProduct() {
	return customerProduct;
}
public void setCustomerProduct(CustomerProduct customerProduct) {
	this.customerProduct = customerProduct;
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
