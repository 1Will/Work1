package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.additionalInfo;

import java.io.File;
import java.util.Arrays;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.service.CustomerRelationship.additionalInfo.CusAdditionalInfoManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;

public class EditAdditionalUploadPicAction extends FileTransportAction {
	
	private static final long serialVersionUID = 1L;
	private final CusAdditionalInfoManager cusAdditionalInfoManager;
	private final CustomerInfoManager customerInfoManager;
	private final FileTransportManager fileTransportManager;
	private CusAdditionalInfo cusAdditionalInfo;
	private CustomerInfo customerInfo;
	
	public EditAdditionalUploadPicAction(CusAdditionalInfoManager cusAdditionalInfoManager,
			CustomerInfoManager customerInfoManager,FileTransportManager fileTransportManager) {
		this.cusAdditionalInfoManager = cusAdditionalInfoManager;
		this.customerInfoManager = customerInfoManager;
		this.fileTransportManager = fileTransportManager;
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
		try {
			System.out.println(getFile().getName());
			System.out.println(getFile_1().getName());
			// 处理上传文件
			if ((isNew) || (getFile()!= null)) {
				if (!isNew) {
					this.fileTransportManager.delete(this.request, this.cusAdditionalInfo.getLogoPath());
					this.fileTransportManager.delete(this.request, this.cusAdditionalInfo.getBackgroundPath());
				}
				String location1 = this.fileTransportManager.upload2(this.request, getFile(), "origFileName",request.getParameter("savePicName"));
				String location2 = this.fileTransportManager.upload2(this.request, getFile_1(), "origFileName",request.getParameter("savePicName_1"));
				
				this.cusAdditionalInfo.setLogoPath(location1);
				this.cusAdditionalInfo.setBackgroundPath(location2);
				
			}
			
			
			this.cusAdditionalInfoManager.storeCusAdditionalInfo(this.cusAdditionalInfo);
			if (null != this.customerInfo) {
				this.customerInfo.setAdditional(this.cusAdditionalInfo);
				this.customerInfoManager.storeCustomerInfo(this.customerInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("additionalInfo.upload.success",
					Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));

			return "error";
		}

		addActionMessage(getText("additionalInfo.upload.success",
				Arrays.asList(new Object[] { this.cusAdditionalInfo.getLicenseNumber() })));

		return "success";
	}


	public CusAdditionalInfo getCusAdditionalInfo() {
		return cusAdditionalInfo;
	}


	public void setCusAdditionalInfo(CusAdditionalInfo cusAdditionalInfo) {
		this.cusAdditionalInfo = cusAdditionalInfo;
	}


	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}


	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	
	
}
