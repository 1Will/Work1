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
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
/**
 * @author qs
 * @version $Id: PurchaseBill.java 30991 2011-03-09 02:02:19Z zbzhang $
 */
@SuppressWarnings("serial")
public class PurchaseBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	// 采购单编号
	private String billNo;

	// 采购单名称
	private String name;

	// 费用来源
	private CodeValue feeSource;

	// 预算编号
	private String budgetNo;

	// 采购人
	private User buyer;

	// 采购部门
	private Department department;

	// 采购日期
	private Date purchaseDate = new Date();

	// 总价格
	private Double totalPrice;

	// 总数量
	private Integer totalAmounts;

	// 单价
	private Integer unitPrice;

	// 已付金额
	private Double alreadyPayOut;

	// 合同金额
	private Double contractMoney;

	// 合同条款
	private String contractMainClause;

	// 付款方式
	private String paymentWay;
	
	private SysModel toolingDevFlag = SysModel.DEVICE;

	// 备注
	private String comment;

	// 采购单所关联的供应商
	private Supplier supplier;

	// 供应商联系人姓名
	private String supplierName;

	// 供应商联系人电话
	private String telphone;

	// 采购单所关联的申购单明细
	private SubscribeDetail subscribeDetail;
	
	//关联的预算详细
	private BudgetDetail budgetDetail;

	// 采购单状态
	private PurchaseBillStatus status = PurchaseBillStatus.NEWSTATUS;
	private Boolean submit = false;					//是否‘提交’标识
    //采购单所关联的验收单集合
	private Set<AcceptBill> acceptBill=new HashSet<AcceptBill>();
	// 采购单所关的上传文件
	private Set<ApplicationDoc> changeDoc = new HashSet<ApplicationDoc>();

	// 采购单所关联的中途检查
	private Set<MiddleCheck> middleCheck = new HashSet<MiddleCheck>();

	// 采购单所关联的付款明细
	private Set<PayDetail> payDetails = new HashSet<PayDetail>();

	// 采购单所关联的采购单明细
	private Set<PurchaseBillDetail> purchaseBillDetails = new HashSet<PurchaseBillDetail>();
	//采购单类型
	private PurchaseTypeStatus typeStatus;
	
	//收货地址
	private String consigneeAdd;
//	收货人
	private String consigneeName;
//	收货电话
	private String consigneeTel;
//	收货传真
	private String consigneeFax;
	
	/**
 	 * 税前总价 zzb
 	 */
 	private Double taxTotalPrice;
 	
    //明细的数量 zzb 2011-03-01
	private Integer sumDetail;
	//入库项
	private Integer insNum;
	
	
	

	public Integer getSumDetail() {
		return sumDetail;
	}

	public void setSumDetail(Integer sumDetail) {
		this.sumDetail = sumDetail;
	}

	public String getTypeStatusTxt() {
		if("SPARE".equals(typeStatus.toString())){
			return "备件";
		}else if("TOOLING".equals(typeStatus.toString())){
			return "工装";
		}else{
			return "设备";
		}
	}
	
	public PurchaseBill() {

	}

	public Double getAlreadyPayOut() {
		return alreadyPayOut;
	}

	public void setAlreadyPayOut(Double alreadyPayOut) {
		this.alreadyPayOut = alreadyPayOut;
	}

	public Double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public Set<PurchaseBillDetail> getPurchaseBillDetails() {
		return this.purchaseBillDetails;
	}

	public void setPurchaseBillDetails(
			Set<PurchaseBillDetail> purchaseBillDetails) {
		this.purchaseBillDetails = purchaseBillDetails;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	// public String getSubscribeNo() {
	// return subscribeNo;
	// }
	//
	// public void setSubscribeNo(String subscribeNo) {
	// this.subscribeNo = subscribeNo;
	// }

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getTotalAmounts() {
		return totalAmounts;
	}

	public void setTotalAmounts(Integer totalAmounts) {
		this.totalAmounts = totalAmounts;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public CodeValue getFeeSource() {
		return feeSource;
	}

	public void setFeeSource(CodeValue feeSource) {
		this.feeSource = feeSource;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public PurchaseBillStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseBillStatus status) {
		this.status = status;
	}

	public Set<PayDetail> getPayDetails() {
		return payDetails;
	}

	public void setPayDetails(Set<PayDetail> payDetails) {
		this.payDetails = payDetails;
	}

	public Set<MiddleCheck> getMiddleCheck() {
		return middleCheck;
	}

	public void setMiddleCheck(Set<MiddleCheck> middleCheck) {
		this.middleCheck = middleCheck;
	}

	public Set<ApplicationDoc> getChangeDoc() {
		return changeDoc;
	}

	public void setChangeDoc(Set<ApplicationDoc> changeDoc) {
		this.changeDoc = changeDoc;
	}

	public SubscribeDetail getSubscribeDetail() {
		return subscribeDetail;
	}

	public void setSubscribeDetail(SubscribeDetail subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}

	public String getContractMainClause() {
		return contractMainClause;
	}

	public void setContractMainClause(String contractMainClause) {
		this.contractMainClause = contractMainClause;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Set<AcceptBill> getAcceptBill() {
		return acceptBill;
	}

	public void setAcceptBill(Set<AcceptBill> acceptBill) {
		this.acceptBill = acceptBill;
	}

	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(BudgetDetail budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

	public PurchaseTypeStatus getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(PurchaseTypeStatus typeStatus) {
		this.typeStatus = typeStatus;
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

	public String getConsigneeAdd() {
		return consigneeAdd;
	}

	public void setConsigneeAdd(String consigneeAdd) {
		this.consigneeAdd = consigneeAdd;
	}

	public String getConsigneeFax() {
		return consigneeFax;
	}

	public void setConsigneeFax(String consigneeFax) {
		this.consigneeFax = consigneeFax;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public Double getTaxTotalPrice() {
		return taxTotalPrice;
	}

	public void setTaxTotalPrice(Double taxTotalPrice) {
		this.taxTotalPrice = taxTotalPrice;
	}

	public Integer getInsNum() {
		return insNum;
	}

	public void setInsNum(Integer insNum) {
		this.insNum = insNum;
	}




}
