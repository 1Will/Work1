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

import com.yongjun.pluto.model.DomainModel;

/**
 * @author qs
 * @version $Id: SupplierExtInfo.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SupplierExtInfo extends DomainModel{
	private static final long serialVersionUID = 2975552216747178810L;
	private Long peopleScale;     //人员规模
	private String contact0;      //主要联系人
	private String fax;           //传真
	private String tel;           //联系电话
	private String site;          //网址
	private String email;         //电子邮件
	private String commercialInstrument;//营业执照内容
	private String businessVariety;	    //企业成产经营品种
 	private String saleSupport;         //售后服务
	private String supportQos;          //服务质量
	
	public SupplierExtInfo() {
		
	}

	public String getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(String businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getCommercialInstrument() {
		return commercialInstrument;
	}

	public void setCommercialInstrument(String commercialInstrument) {
		this.commercialInstrument = commercialInstrument;
	}

	public String getContact0() {
		return contact0;
	}

	public void setContact0(String contact0) {
		this.contact0 = contact0;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSaleSupport() {
		return saleSupport;
	}

	public void setSaleSupport(String saleSupport) {
		this.saleSupport = saleSupport;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSupportQos() {
		return supportQos;
	}

	public void setSupportQos(String supportQos) {
		this.supportQos = supportQos;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getPeopleScale() {
		return peopleScale;
	}

	public void setPeopleScale(Long peopleScale) {
		this.peopleScale = peopleScale;
	}
}
