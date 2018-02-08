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

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.asset.spare.BaseSpareInOutInfo;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;

/**
 * <p>Title: SpareOutBillDetail
 * <p>Description: 运维计划的日常维修维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class SpareOutBillDetail extends BaseSpareInOutInfo implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 29199800768489834L;
	//关联的出库单
	private SpareOutBill spareOutBill;
	//出库单所关联的备件领用单明细
	private SpareBorrowDetail dtl;
	//出库单明细的状态 默认为“未出库”
	private SpareOutBillDetailStatus status= SpareOutBillDetailStatus.NEW;
	
	//部门
	private Department department;
	//部门名称
	private String deptName;
	//标识 是否被 二级入库单选择 zzb 2010-11-12
	private boolean haveInbill=false;
	/**
	 * 领料人 zzb
	 */
	private String borrowerPeople;
	/**
	 * 出库人 zzb
	 */
	private User outPeople;
	/**
	 * 出库日期 zzb
	 */
	private Date outDate = new Date();
	/**
	 * 备注 zzb
	 */
	private String comment;
	/**
	 * 备件库台账 zzb
	 */
	private SpareLocation spareLocation;
	/**
	 * 是否是以旧换新 true -> 以旧换新  false
	 */
	private boolean newOrOld;
	/**
	 * 所属设备 zzb
	 */
	private String shebei;
	private String shebeiNo;
	private String useTypes;
	/**
	 * 所属班组 zzb
	 */
	private String banzu;
	
	//已入数量
	private Long inNumber=0l;
	

	public Long getInNumber() {
		return inNumber;
	}

	public void setInNumber(Long inNumber) {
		this.inNumber = inNumber;
	}

	public boolean isNewOrOld() {
		return newOrOld;
	}

	public void setNewOrOld(boolean newOrOld) {
		this.newOrOld = newOrOld;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof SpareOutBillDetail)) { return false; }
		return false;
	}

	public SpareOutBill getSpareOutBill() {
		return spareOutBill;
	}

	public void setSpareOutBill(SpareOutBill spareOutBill) {
		this.spareOutBill = spareOutBill;
	}

	public SpareBorrowDetail getDtl() {
		return dtl;
	}

	public void setDtl(SpareBorrowDetail dtl) {
		this.dtl = dtl;
	}

	public SpareOutBillDetailStatus getStatus() {
		return status;
	}

	public void setStatus(SpareOutBillDetailStatus status) {
		this.status = status;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public boolean isHaveInbill() {
		return haveInbill;
	}

	public void setHaveInbill(boolean haveInbill) {
		this.haveInbill = haveInbill;
	}

 

	public String getBorrowerPeople() {
		return borrowerPeople;
	}

	public void setBorrowerPeople(String borrowerPeople) {
		this.borrowerPeople = borrowerPeople;
	}

	public User getOutPeople() {
		return outPeople;
	}

	public void setOutPeople(User outPeople) {
		this.outPeople = outPeople;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public SpareLocation getSpareLocation() {
		return spareLocation;
	}

	public void setSpareLocation(SpareLocation spareLocation) {
		this.spareLocation = spareLocation;
	}

	public String getBanzu() {
		return banzu;
	}

	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}

	public String getShebei() {
		return shebei;
	}

	public void setShebei(String shebei) {
		this.shebei = shebei;
	}

	public String getShebeiNo() {
		return shebeiNo;
	}

	public void setShebeiNo(String shebeiNo) {
		this.shebeiNo = shebeiNo;
	}

	public String getUseTypes() {
		return useTypes;
	}

	public void setUseTypes(String useTypes) {
		this.useTypes = useTypes;
	}



//	public SpareBorrowDetail getSpareBorrowDtl() {
//		return spareBorrowDtl;
//	}
//
//	public void setSpareBorrowDtl(SpareBorrowDetail spareBorrowDtl) {
//		this.spareBorrowDtl = spareBorrowDtl;
//	}
	

	
}
