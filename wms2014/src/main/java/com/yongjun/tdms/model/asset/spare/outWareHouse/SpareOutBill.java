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
package com.yongjun.tdms.model.asset.spare.outWareHouse;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * <p>Title: SpareOutBill
 * <p>Description: 备件出库单类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SpareOutBill.java 28459 2010-11-04 10:42:10Z qcshen $
 */
public class SpareOutBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking,OrganizationTracking{
	private static final long serialVersionUID = -4358732229455698439L;
	//领料单编号
	private String borrowBillNo;
	//出库单号
	private String code;
	//出库单名称
	private String name;
	//出库日期
	private Date outDate;
	//备注
	private String comment;
	//是否‘发送提醒’标识
	private boolean submit = false;
	//领料部门
	private Department department;
	//领料人
	private String borrower;
	//出库人
	private User outPeople;
	//出仓库
	private Warehouse warehouse;
	//入仓库
	private Warehouse inWarehouse;
	//已出库总金额
	private Double totalPrice=0.0;
	//已入库总金额
	private Double inTotalPrice=0.0;
	//关联的出库单明细
	private Set<SpareOutBillDetail> detail = new HashSet<SpareOutBillDetail>();
    //出库单的状态 默认为“新建”
	private SpareOutBillStatus status=SpareOutBillStatus.NEWSTATUS;
	//仓库级别
	private CodeValue storageGrade;
	
	private SpareInBill spareInBill;
	
	private String oldSpare;
	
	private String outType;
	
	private SpareInBill oldForNew;
	
	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (o instanceof SpareOutBill){return true;}
		SpareOutBill bill = (SpareOutBill)o;
		if (bill.getCode().equals(this.code)) {
			return true;
		}
		return false;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public User getOutPeople() {
		return outPeople;
	}

	public void setOutPeople(User outPeople) {
		this.outPeople = outPeople;
	}

	public boolean isSubmit() {
		return submit;
	}

	public void setSubmit(boolean submit) {
		this.submit = submit;
	}

	public Set<SpareOutBillDetail> getDetail() {
		return detail;
	}

	public void setDetail(Set<SpareOutBillDetail> detail) {
		this.detail = detail;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getBorrowBillNo() {
		return borrowBillNo;
	}

	public void setBorrowBillNo(String borrowBillNo) {
		this.borrowBillNo = borrowBillNo;
	}

	public SpareOutBillStatus getStatus() {
		return status;
	}

	public void setStatus(SpareOutBillStatus status) {
		this.status = status;
	}

	@Override
	public String getDomainModelProperty() {
		// TODO Auto-generated method stub
		return this.getProperties().getProperty("spare_out_bill");
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public CodeValue getStorageGrade() {
		return storageGrade;
	}

	public void setStorageGrade(CodeValue storageGrade) {
		this.storageGrade = storageGrade;
	}

	public Warehouse getInWarehouse() {
		return inWarehouse;
	}

	public void setInWarehouse(Warehouse inWarehouse) {
		this.inWarehouse = inWarehouse;
	}

	public SpareInBill getSpareInBill() {
		return spareInBill;
	}

	public void setSpareInBill(SpareInBill spareInBill) {
		this.spareInBill = spareInBill;
	}

	public Double getInTotalPrice() {
		return inTotalPrice;
	}

	public void setInTotalPrice(Double inTotalPrice) {
		this.inTotalPrice = inTotalPrice;
	}

	public String getOldSpare() {
		return oldSpare;
	}

	public void setOldSpare(String oldSpare) {
		this.oldSpare = oldSpare;
	}

	public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	public SpareInBill getOldForNew() {
		return oldForNew;
	}

	public void setOldForNew(SpareInBill oldForNew) {
		this.oldForNew = oldForNew;
	}

	
}
