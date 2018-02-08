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
package com.yongjun.tdms.model.runmaintenance.tooling.record;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.pluto.model.security.Department;

/**
 * <p>Title: ToolingChangeBill
 * <p>Description: 工装变更单类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ToolingChangeBill.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class ToolingChangeBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	private static final long serialVersionUID = -477231680088031520L;
	private String billNo;                         //变更单编号          
	private String billName;                       //变更单名称
	//private String fileNo;                        //技术文件编号
	private String changeReason;                  //修改原因
	private String changeSolution;                //修改方案
	private String checkResult;                   //验证结果
	//private String comment;                       //备注
	private Date planCompleteTime;                //计划完成时间
	private Date actualCompleteTime;              //实际完成时间
    private boolean changeBillFlag = false;      //变更单状态 [false 变更中]|[true 已变更] 默认为变更中
	private DeviceCard tooling;                   //工装
	private Department acceptDepartment;          //承接单位
	private User acceptor;                       //承接人
	private User bailor;                         //委托人
	private Set<ApplicationDoc> changeDoc = new HashSet<ApplicationDoc>();
	
	private String createdPeople;               //编制人
	private Date createdDateTime = new Date();  //创建日期
	
	public ToolingChangeBill() {}
	public Department getAcceptDepartment() {
		return acceptDepartment;
	}

	public void setAcceptDepartment(Department acceptDepartment) {
		this.acceptDepartment = acceptDepartment;
	}

	public User getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(User acceptor) {
		this.acceptor = acceptor;
	}

	public Date getActualCompleteTime() {
		return actualCompleteTime;
	}

	public void setActualCompleteTime(Date actualCompleteTime) {
		this.actualCompleteTime = actualCompleteTime;
	}

	public User getBailor() {
		return bailor;
	}

	public void setBailor(User bailor) {
		this.bailor = bailor;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getChangeSolution() {
		return changeSolution;
	}

	public void setChangeSolution(String changeSolution) {
		this.changeSolution = changeSolution;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public Date getPlanCompleteTime() {
		return planCompleteTime;
	}

	public void setPlanCompleteTime(Date planCompleteTime) {
		this.planCompleteTime = planCompleteTime;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	@Override
	public int hashCode() {
		return billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof ToolingChangeBill)) { return false; }
		
		ToolingChangeBill changeBill = (ToolingChangeBill)o;
		
		if (!(billNo.equals(changeBill.getBillNo())))  { return false; }
		return true;
	}

	public boolean isChangeBillFlag() {
		return changeBillFlag;
	}

	public void setChangeBillFlag(boolean changeBillFlag) {
		this.changeBillFlag = changeBillFlag;
	}
	public Set<ApplicationDoc> getChangeDoc() {
		return changeDoc;
	}
	public void setChangeDoc(Set<ApplicationDoc> changeDoc) {
		this.changeDoc = changeDoc;
	}
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getCreatedPeople() {
		return createdPeople;
	}
	public void setCreatedPeople(String createdPeople) {
		this.createdPeople = createdPeople;
	}


}
