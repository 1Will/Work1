/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.model.prophase.supplier;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.country.Country;

/**
 * @author qs
 * @version $Id: Supplier.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class Supplier extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = -5138303029036644184L;
	//供应商名称(中文)
	private String name;
	
	//供应商名称(英文)
	private String enName;
	
	//供应商编号
	private String supplierNo;
	
	//注册资金
	private String registeredFunds;
	
	//地区
	private String zone;
	
	//备注
	private String comment;
	
	//等级
	private CodeValue level;
	
	//法人代表
	private String legalPerson;
	
	//国家
	private Country country;
	
	//供应商类别
	private CodeValue supplierType;
	
	//公司性质
	private CodeValue companyType;
	
	//行业
	private CodeValue tradeType;
	
    //资产标识[设备][工装]
	private SysModel toolingDevFlag = SysModel.DEVICE;
	
	//供应商附加信息
	private SupplierExtInfo supplierExtInfo;
	
	//关联的供应商资质信息
	private Set<SupplierCertification> certification = new HashSet<SupplierCertification>();
	
	//关联的供应商产品信息
	private Set<SupplierProduct> product = new HashSet<SupplierProduct>();
	//关联的供应商级别变更历史
	private Set<SupplierLevelHistory> supplierLevelHistory =new HashSet<SupplierLevelHistory>();
	//关联的供应商的历史信息
	private Set<SupplierBusinessHistory> supplierBusinessHistory =new HashSet<SupplierBusinessHistory>();
	
	//类别(FACTORY:生产厂家 SUPPLIER:供应商)
	private String category;
	
	public Supplier() {
		
	}
	
	public CodeValue getSupplierType() {
		return supplierType;
	}
	
	public void setSupplierType(CodeValue supplierType) {
		this.supplierType = supplierType;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public CodeValue getTradeType() {
		return this.tradeType;
	}
	
	public void setTradeType(CodeValue tradeType) {
		this.tradeType = tradeType;
	}
	

	public SupplierExtInfo getSupplierExtInfo() {
		return supplierExtInfo;
	}
	
	public void setSupplierExtInfo(SupplierExtInfo supplierExtInfo) {
		this.supplierExtInfo = supplierExtInfo;
	}
	
	public CodeValue getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CodeValue companyType) {
		this.companyType = companyType;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	@Override
	public int hashCode() {
		return supplierNo.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof Supplier)) { return false; }
		
		Supplier supplier = (Supplier)o;
		
		if (!(supplierNo.equals(supplier.getSupplierNo())))  { return false; }
		return true;
	}

	public Set<SupplierProduct> getProduct() {
		return product;
	}

	public void setProduct(Set<SupplierProduct> product) {
		this.product = product;
	}

	public Set<SupplierLevelHistory> getSupplierLevelHistory() {
		return supplierLevelHistory;
	}

	public void setSupplierLevelHistory(
			Set<SupplierLevelHistory> supplierLevelHistory) {
		this.supplierLevelHistory = supplierLevelHistory;
	}

	public Set<SupplierBusinessHistory> getSupplierBusinessHistory() {
		return supplierBusinessHistory;
	}

	public void setSupplierBusinessHistory(
			Set<SupplierBusinessHistory> supplierBusinessHistory) {
		this.supplierBusinessHistory = supplierBusinessHistory;
	}



	public Set<SupplierCertification> getCertification() {
		return certification;
	}

	public void setCertification(Set<SupplierCertification> certification) {
		this.certification = certification;
	}

	public CodeValue getLevel() {
		return level;
	}

	public void setLevel(CodeValue level) {
		this.level = level;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getRegisteredFunds() {
		return registeredFunds;
	}

	public void setRegisteredFunds(String registeredFunds) {
		this.registeredFunds = registeredFunds;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

}
