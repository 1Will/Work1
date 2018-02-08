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
package com.yongjun.tdms.model.runmaintenance.discard;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
/**
 * <p>Title: DiscardBill
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DiscardBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DiscardBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 1L;
    //报废单编号
	private String billNo;
	//报废单名称
	private String discardBillName;
	//部门
	private Department department;
	//报废类别
	private CodeValue discardCategory;
	//标示[半报废][已报废]
	private boolean discardAgree = false;
	//申报人
	private User reportUser;
	//报废日期
	private Date discardDate=new Date();
	//申报日期 
	private Date reportDate =new Date();
	//报废原因
	private String discardReason;
	//设备报废单所关联的设备报废明细的集合
	private Set<DiscardBillDtl> discardBillDtl=new HashSet<DiscardBillDtl>();
	@Override
	public int hashCode() {
		
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		
		return false;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDiscardBillName() {
		return discardBillName;
	}

	public void setDiscardBillName(String discardBillName) {
		this.discardBillName = discardBillName;
	}

	public CodeValue getDiscardCategory() {
		return discardCategory;
	}

	public void setDiscardCategory(CodeValue discardCategory) {
		this.discardCategory = discardCategory;
	}

	public User getReportUser() {
		return reportUser;
	}

	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getDiscardReason() {
		return discardReason;
	}

	public void setDiscardReason(String discardReason) {
		this.discardReason = discardReason;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<DiscardBillDtl> getDiscardBillDtl() {
		return discardBillDtl;
	}

	public void setDiscardBillDtl(Set<DiscardBillDtl> discardBillDtl) {
		this.discardBillDtl = discardBillDtl;
	}

	public Date getDiscardDate() {
		return discardDate;
	}

	public void setDiscardDate(Date discardDate) {
		this.discardDate = discardDate;
	}

	public boolean isDiscardAgree() {
		return discardAgree;
	}

	public void setDiscardAgree(boolean discardAgree) {
		this.discardAgree = discardAgree;
	}

}
