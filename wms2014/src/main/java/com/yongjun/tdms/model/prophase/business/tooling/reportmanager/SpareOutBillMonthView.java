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
package com.yongjun.tdms.model.prophase.business.tooling.reportmanager;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * <p>Title: com.yongjun.tdms.model.prophase.business.tooling.reportmanager.SpareOutBillMonthView.java</p>
 * <p>Description: the SpareOutBillMonthView class</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:SpareOutBillMonthView.java 2011-3-23 zzb $</p>
 */
public class SpareOutBillMonthView extends BaseInfoEntity{

	/**
	 * 部门
	 */
	private String dept;
	/**
	 * 月份
	 */
	private String month;
	/**
	 * 出库数量
	 */
	private Integer amount;
	/**
	 * 单价
	 */
	private Double price;
	
	/**
	 * 数量(低耗)
	 */
	private Integer num;
	/**
	 * 数量(备件)
	 */
	private Integer bnum;
	
	/**
	 * 状态
	 */
	private String status ;
	/**
	 * 总价(低耗)
	 */
	private Double totalPrice;
	/**
	 * 总价(备件)
	 */
	private Double btotalPrice;
	
	/**
	 * 日期
	 */
	private Date createDate;
	/**
	 * 仓库级别
	 */
	private Integer storageGrade;
	
	
	
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

 

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getBnum() {
		return bnum;
	}

	public void setBnum(Integer bnum) {
		this.bnum = bnum;
	}

	public Double getBtotalPrice() {
		return btotalPrice;
	}

	public void setBtotalPrice(Double btotalPrice) {
		this.btotalPrice = btotalPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStorageGrade() {
		return storageGrade;
	}

	public void setStorageGrade(Integer storageGrade) {
		this.storageGrade = storageGrade;
	}

}

