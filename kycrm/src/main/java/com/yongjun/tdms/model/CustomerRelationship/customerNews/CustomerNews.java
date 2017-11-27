package com.yongjun.tdms.model.CustomerRelationship.customerNews;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

public class CustomerNews extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;//企业新闻标题
	private String content;//企业新闻内容
	private String pictureName;//照片名称
	private String position;
	private String fileNo;
	private CustomerInfo customerInfo;
	
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof CustomerNews)) {
			return false;
		}

		CustomerNews customerNews = (CustomerNews) arg0;

		if (!customerNews.getId().equals(getId())) {
			return false;
		}
		return true;
		
	}

	@Override
	public int hashCode() {
		return 0;
	}

}
