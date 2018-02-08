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
package com.yongjun.tdms.model.asset.spare.inWareHouse;


import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.asset.spare.BaseSpareInOutInfo;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;

/**
 * <p>Title: SpareInBillDetail
 * <p>Description: 入库单明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SpareInBillDetail.java 29144 2010-12-02 14:16:57Z qcshen $
 */
public class SpareInBillDetail extends BaseSpareInOutInfo implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 5171891197589520023L;
	//部门
	private Department department;
	//部门名称
	private String deptName;
	//备注
	private String comment;
	//关联的采购单明细
	private PurchaseBillDetail poDetail;
	//关联的入库单
	private SpareInBill spareInBill;
	//记录当前从采购单明细选择的记录的状态 【在此冗余一个字段】
    private String status;
    //关联备件领用单明细的对象
    private SpareBorrowDetail spareBorrowdtl;
    
    //-----新增字段----
    
    //关联的出库单明细
    private SpareOutBillDetail spareOutBillDtl;
    //关联的备件台账
    private SpareLocation spareLocation;
    //税前价格
    private Double taxPrice;
    //采购数量
    private Integer purchaseNum;
    private String  disableSpare;
    
    
    
    public String getDisableSpare() {
		return disableSpare;
	}

	public void setDisableSpare(String disableSpare) {
		this.disableSpare = disableSpare;
	}

	private SpareInBillDetailStatus instatus=SpareInBillDetailStatus.NEW;
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof SpareInBillDetail){return true;}
		return false;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public SpareInBill getSpareInBill() {
		return spareInBill;
	}

	public void setSpareInBill(SpareInBill spareInBill) {
		this.spareInBill = spareInBill;
	}

	public PurchaseBillDetail getPoDetail() {
		return poDetail;
	}

	public void setPoDetail(PurchaseBillDetail poDetail) {
		this.poDetail = poDetail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SpareBorrowDetail getSpareBorrowdtl() {
		return spareBorrowdtl;
	}

	public void setSpareBorrowdtl(SpareBorrowDetail spareBorrowdtl) {
		this.spareBorrowdtl = spareBorrowdtl;
	}

	public SpareInBillDetailStatus getInstatus() {
		return instatus;
	}

	public void setInstatus(SpareInBillDetailStatus instatus) {
		this.instatus = instatus;
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

	public SpareOutBillDetail getSpareOutBillDtl() {
		return spareOutBillDtl;
	}

	public void setSpareOutBillDtl(SpareOutBillDetail spareOutBillDtl) {
		this.spareOutBillDtl = spareOutBillDtl;
	}

	public SpareLocation getSpareLocation() {
		return spareLocation;
	}

	public void setSpareLocation(SpareLocation spareLocation) {
		this.spareLocation = spareLocation;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Integer getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
}
