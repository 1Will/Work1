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
package com.yongjun.tdms.model.runmaintenance.spareBorrow;

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
import com.yongjun.tdms.model.SysModel;

/**
 * <p>Title: SpareBorrow
 * <p>Description: 备件领用类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SpareBorrow.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SpareBorrow extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	private static final long serialVersionUID = 1L;
	//领用单编号
	private String billNo;
	//领用单名称
	private String name;
	//领用部门
	private Department department;
	//领用日期
	private Date borrowDate;
	//领用人
	private User borrowUser;
	//审批人
	private User approvalUser;
	//资产标识[工装]|[设备]
	private SysModel toolingDevFlag=SysModel.DEVICE;
	//用途说明
	private String description; 
    //领用单状态
	private SpareBorrowStatus status=SpareBorrowStatus.NEWSTATUS;
	//领用单所关联的领用单明细
	private Set<SpareBorrowDetail> detail=new HashSet<SpareBorrowDetail>();
	@Override
	public int hashCode() {
		return billNo.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SpareBorrow)) {return false;}
		SpareBorrow spareBorrow = (SpareBorrow)o;
		if (this.billNo.equals(spareBorrow.getBillNo())) {return true;}
		return false;
	}
	public User getApprovalUser() {
		return approvalUser;
	}
	public void setApprovalUser(User approvalUser) {
		this.approvalUser = approvalUser;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public User getBorrowUser() {
		return borrowUser;
	}
	public void setBorrowUser(User borrowUser) {
		this.borrowUser = borrowUser;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SpareBorrowStatus getStatus() {
		return status;
	}
	public void setStatus(SpareBorrowStatus status) {
		this.status = status;
	}
	public Set<SpareBorrowDetail> getDetail() {
		return detail;
	}
	public void setDetail(Set<SpareBorrowDetail> detail) {
		this.detail = detail;
	}
	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String getDomainModelProperty() {
		// TODO Auto-generated method stub
		return this.getProperties().getProperty("spare_borrow");
	}
	
}
