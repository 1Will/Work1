/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.model.prophase.business.report;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * <p>Title: PurchaseCountBySupplierReport
 * <p>Description: 供应商采购情况统计月报表</p>
 * <p>Copyright: Copyright (c) 2011 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class PurchaseCountBySupplierReport extends BaseInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1421697454486457311L;
	
	//供应商名称
	private String supplierName;
	//供应商ID
	private Long supplierId;
	//种类
	private String categoryName;
	//采购数量
	private Integer purNum;
	//采购费用
	private Double sumPrice;
	//月份
	private String month;
	
	/**
	 * 低耗采购数合计
	 */
	private Integer dpurNum;
	/**
	 * 低耗采购费用合计
	 */
	private Double dsumPrice;
	/**
	 * 备件采购数合计
	 */
	private Integer bpurNum;
	/**
	 * 备件采购费用合计
	 */
	private Double bsumPrice;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getPurNum() {
		return purNum;
	}

	public void setPurNum(Integer purNum) {
		this.purNum = purNum;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/* (non-Javadoc)
	 * @see com.yongjun.pluto.model.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yongjun.pluto.model.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Integer getBpurNum() {
		return bpurNum;
	}

	public void setBpurNum(Integer bpurNum) {
		this.bpurNum = bpurNum;
	}

	public Double getBsumPrice() {
		return bsumPrice;
	}

	public void setBsumPrice(Double bsumPrice) {
		this.bsumPrice = bsumPrice;
	}

	public Integer getDpurNum() {
		return dpurNum;
	}

	public void setDpurNum(Integer dpurNum) {
		this.dpurNum = dpurNum;
	}

	public Double getDsumPrice() {
		return dsumPrice;
	}

	public void setDsumPrice(Double dsumPrice) {
		this.dsumPrice = dsumPrice;
	}

}
