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
package com.yongjun.tdms.model.prophase.business;

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
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;

/**
 * @author qs
 * @version $Id: Subscribe.java 32090 2011-05-23 09:39:48Z wclin $
 */
@SuppressWarnings("serial")
public class Subscribe extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	//申购单编号
	private String billNo;
	//申购单名称
	private String name;
	
	private String subscriber;
	//申购日期
	private Date subscribeDate = new Date();
	//费用来源
	private CodeValue feeSource;
	//预算项目号
	private String budgetNo;
    //申购人
	private User buyingPerson;
	//申购总价
	private Double totalPrice = 0.0;
	//申购数量
	private Integer totalAmounts = 0;
	//申购部门
	private Department department;
	//关联的预算详细
	private BudgetDetail budgetDetail;
	//申购原因
	private String reason;
	//申购单备注
	private String comment;
	//表示申购单是否可以汇总
	private String summaryable;
	//申购单状态
	private SubscribeStatus status;
	//资产标识[设备][工装]
	private SysModel toolingDevFlag = SysModel.DEVICE;
	//申购单所关联的申购明细集合
	private Set<SubscribeDetail> subscribeDetails = new HashSet<SubscribeDetail>();
	//关联的工装制作明细
	private Set<ToolingMakeDetail> toolingMakeDetails = new HashSet<ToolingMakeDetail>();
	//关联的备件采购明细
	private Set<SparePurchaseDetail> sparePurchaseDetails = new HashSet<SparePurchaseDetail>();
    //关联的维修保养明细
	private Set<RepairMaintenanceDetail> repairMaintenanceDetails = new HashSet<RepairMaintenanceDetail>();
	//关联的技术改造明细
	private Set<TechAlterDetail> techAlterDetails = new HashSet<TechAlterDetail>();
	//申购单类型
	private SubscribeTypeStatus typeStatus;
	//单据类型
	private CodeValue detailKind;
	
	//明细的数量 zzb 2011-03-01
	private Integer sumDetail;
   //申购的数量 zzb 2011-03-01
	private Integer subNum;
    //采购的数量 zzb 2011-03-01
	private Integer purNum;
    //入库数量 zzb 2011-03-01
	private Integer insNum;
	//标记是否汇总
	private Integer remark=0;
 
 
	
	

	
	public String getTypeStatusTxt(){
		if("SPARE".equals(typeStatus.toString())){
			return "备件";
		}else if("TOOLING".equals(typeStatus.toString())){
			return "工装";
		}else{
			return "设备";
		}
	}
	public Subscribe() {
		
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Integer getTotalAmounts() {
		return this.totalAmounts;
	}
	
	public void setTotalAmounts(Integer totalAmounts) {
		this.totalAmounts = totalAmounts;
	}
	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBudgetNo() {
		return budgetNo;
	}

	public void setBudgetNo(String budgetNo) {
		this.budgetNo = budgetNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getSubscribeDate() {
		return subscribeDate;
	}

	public void setSubscribeDate(Date subscribeDate) {
		this.subscribeDate = subscribeDate;
	}

	public Set<SubscribeDetail> getSubscribeDetails() {
		return subscribeDetails;
	}

	public void setSubscribeDetails(Set<SubscribeDetail> subscribeDetails) {
		this.subscribeDetails = subscribeDetails;
	}

	public String getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
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

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public User getBuyingPerson() {
		return buyingPerson;
	}

	public void setBuyingPerson(User buyingPerson) {
		this.buyingPerson = buyingPerson;
	}

	public CodeValue getFeeSource() {
		return feeSource;
	}

	public void setFeeSource(CodeValue feeSource) {
		this.feeSource = feeSource;
	}

	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

	public SubscribeStatus getStatus() {
		return status;
	}

	public void setStatus(SubscribeStatus status) {
		this.status = status;
	}

	public SubscribeTypeStatus getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(SubscribeTypeStatus typeStatus) {
		this.typeStatus = typeStatus;
	}
	public Set<RepairMaintenanceDetail> getRepairMaintenanceDetails() {
		return repairMaintenanceDetails;
	}
	public void setRepairMaintenanceDetails(
			Set<RepairMaintenanceDetail> repairMaintenanceDetails) {
		this.repairMaintenanceDetails = repairMaintenanceDetails;
	}
	public Set<SparePurchaseDetail> getSparePurchaseDetails() {
		return sparePurchaseDetails;
	}
	public void setSparePurchaseDetails(
			Set<SparePurchaseDetail> sparePurchaseDetails) {
		this.sparePurchaseDetails = sparePurchaseDetails;
	}
	public Set<TechAlterDetail> getTechAlterDetails() {
		return techAlterDetails;
	}
	public void setTechAlterDetails(Set<TechAlterDetail> techAlterDetails) {
		this.techAlterDetails = techAlterDetails;
	}
	public Set<ToolingMakeDetail> getToolingMakeDetails() {
		return toolingMakeDetails;
	}
	public void setToolingMakeDetails(Set<ToolingMakeDetail> toolingMakeDetails) {
		this.toolingMakeDetails = toolingMakeDetails;
	}
	public CodeValue getDetailKind() {
		return detailKind;
	}
	public void setDetailKind(CodeValue detailKind) {
		this.detailKind = detailKind;
	}
	public Integer getSumDetail() {
		return sumDetail;
	}
	public void setSumDetail(Integer sumDetail) {
		this.sumDetail = sumDetail;
	}
	public Integer getInsNum() {
		return insNum;
	}
	public void setInsNum(Integer insNum) {
		this.insNum = insNum;
	}
	public Integer getPurNum() {
		return purNum;
	}
	public void setPurNum(Integer purNum) {
		this.purNum = purNum;
	}
	public Integer getSubNum() {
		return subNum;
	}
	public void setSubNum(Integer subNum) {
		this.subNum = subNum;
	}
	public String getSummaryable() {
		return summaryable;
	}
	public void setSummaryable(String summaryable) {
		this.summaryable = summaryable;
	}
	/**
	 * @return the remark
	 */
	public Integer getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(Integer remark) {
		this.remark = remark;
	}
 



}
