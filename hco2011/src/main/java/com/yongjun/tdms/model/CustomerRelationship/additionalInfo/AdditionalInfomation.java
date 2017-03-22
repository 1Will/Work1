/*
 * Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.model.CustomerRelationship.additionalInfo;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;

/**
 * <p>Title: AdditionalInfomation
 * <p>Description: 附加信息实体类�</p>
 * <p>Copyright: Copyright (c) 2009 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author wliu@yj-technology.com</p>
 * <p>@version $Id: AdditionalInfomation.java 2009-12-7 14:20:03Z wliu $</p>
 */
public class AdditionalInfomation extends BaseInfoEntity implements
CreatedTimeTracking ,CreatorTracking,LastOperatorTracking, LastModifiedTimeTracking{

	private static final long serialVersionUID = 1L;
	
	private String licenseNumber;//营业执照
	private String taxNumber;//税号
	private Double turnover;//营业额
	private String bank;//开户行
	private String bankAccount;//开户行账号
	private String website;//网址
	private String comment;//备注
	private CustomerInfo ci;//关联客户档案
	
	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the bankAccount
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount the bankAccount to set
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the ci
	 */
	public CustomerInfo getCi() {
		return ci;
	}

	/**
	 * @param ci the ci to set
	 */
	public void setCi(CustomerInfo ci) {
		this.ci = ci;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the licenseNumber
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/**
	 * @param licenseNumber the licenseNumber to set
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	/**
	 * @return the taxNumber
	 */
	public String getTaxNumber() {
		return taxNumber;
	}

	/**
	 * @param taxNumber the taxNumber to set
	 */
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	/**
	 * @return the turnover
	 */
	public Double getTurnover() {
		return turnover;
	}

	/**
	 * @param turnover the turnover to set
	 */
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0==this){
			return true;
		}
		if(!(arg0 instanceof AdditionalInfomation)){
			return false;
		}
		
		AdditionalInfomation additional = (AdditionalInfomation)arg0;
		
		if(!(additional.getId().equals(this.getId()))){
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
