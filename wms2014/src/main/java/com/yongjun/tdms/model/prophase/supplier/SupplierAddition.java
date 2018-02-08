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

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: SupplierAddition.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
//TODO: 修改这个类
public class SupplierAddition extends BaseInfoEntity{
	private static final long serialVersionUID = -2927931149713799145L;
	private Long id;    
	private Long supplier_id;
	private String person_scale;
	private String contactor;
	private String tel;
	//private Long category;
	private String fax;
	private String site;
	private String business_variety;
	private String commercial_instrument;
	private String service;
	/*
	private Long level_id;
		*/
	private String Qos;
	
	public String getBusiness_variety() {
		return business_variety;
	}
	public void setBusiness_variety(String business_variety) {
		this.business_variety = business_variety;
	}
	public String getCommercial_instrument() {
		return commercial_instrument;
	}
	public void setCommercial_instrument(String commercial_instrument) {
		this.commercial_instrument = commercial_instrument;
	}
	public String getContactor() {
		return contactor;
	}
	public void setContactor(String contactor) {
		this.contactor = contactor;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPerson_scale() {
		return person_scale;
	}
	public void setPerson_scale(String person_scale) {
		this.person_scale = person_scale;
	}
	public String getQos() {
		return Qos;
	}
	public void setQos(String qos) {
		Qos = qos;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Long getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
