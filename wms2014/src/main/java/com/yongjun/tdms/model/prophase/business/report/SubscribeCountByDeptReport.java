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
 * <p>Title: com.yongjun.tdms.model.prophase.business.report.SubscribeCountByDeptReport.java</p>
 * <p>Description: 备件采购计划落实情况月报表</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:SubscribeCountByDeptReport.java 2011-3-11 zzb $</p>
 */
public class SubscribeCountByDeptReport extends BaseInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8331995464050456986L;
	/**
	 * 部门
	 */
	private String dept;
	/*
	 * 部门ID
	 */
	private Long deptId;
	/**
	 * 类别 低耗/备件
	 */
	private String categoryName;
	/**
	 * 计划数
	 */
	private Integer planNum;
	/**
	 * 采购数
	 */
	private Integer purNum;
	/**
	 * 取消数
	 */
	private Integer calNum;
	/**
	 * 待确认数
	 */
	private Integer conNum;
	/**
	 * 采购费用
	 */
	private Double sumPrice;
	/**
	 * 备注
	 */
	private String comment;
	
	/**
	 * 月份
	 */
	private String month;
	/**
	 * 低耗计划数合计
	 */
	private Integer dplanNum;
	/**
	 * 低耗采购数合计
	 */
	private Integer dpurNum;
	/**
	 * 低耗取消数合计
	 */
	private Integer dcalNum;
	/**
	 * 低耗待确认数合计
	 */
	private Integer dconNum;
	/**
	 * 低耗采购费用合计
	 */
	private Double dsumPrice;
	
	/**
	 * 备件计划数合计
	 */
	private Integer bplanNum;
	/**
	 * 备件采购数合计
	 */
	private Integer bpurNum;
	/**
	 * 备件取消数合计
	 */
	private Integer bcalNum;
	/**
	 * 备件待确认数合计
	 */
	private Integer bconNum;
	/**
	 * 备件采购费用合计
	 */
	private Double bsumPrice;
	
	
	

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public Integer getCalNum() {
		return calNum;
	}

	public void setCalNum(Integer calNum) {
		this.calNum = calNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getConNum() {
		return conNum;
	}

	public void setConNum(Integer conNum) {
		this.conNum = conNum;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Integer planNum) {
		this.planNum = planNum;
	}

	public Integer getPurNum() {
		return purNum;
	}

	public void setPurNum(Integer purNum) {
		this.purNum = purNum;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Integer getBcalNum() {
		return bcalNum;
	}

	public void setBcalNum(Integer bcalNum) {
		this.bcalNum = bcalNum;
	}

	public Integer getBconNum() {
		return bconNum;
	}

	public void setBconNum(Integer bconNum) {
		this.bconNum = bconNum;
	}

	public Integer getBplanNum() {
		return bplanNum;
	}

	public void setBplanNum(Integer bplanNum) {
		this.bplanNum = bplanNum;
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

	public Integer getDcalNum() {
		return dcalNum;
	}

	public void setDcalNum(Integer dcalNum) {
		this.dcalNum = dcalNum;
	}

	public Integer getDconNum() {
		return dconNum;
	}

	public void setDconNum(Integer dconNum) {
		this.dconNum = dconNum;
	}

	public Integer getDplanNum() {
		return dplanNum;
	}

	public void setDplanNum(Integer dplanNum) {
		this.dplanNum = dplanNum;
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

