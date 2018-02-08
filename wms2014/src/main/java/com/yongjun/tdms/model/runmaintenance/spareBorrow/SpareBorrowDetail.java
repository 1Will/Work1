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

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;

/**
 * <p>Title: SpareBorrowDetail
 * <p>Description: 备件领用明细类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id: SpareBorrowDetail.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class SpareBorrowDetail extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	private static final long serialVersionUID = 1L;
    //备件编号
	private String spareNo;
	//备件名称
	private String spareName;
	//规格
	private String specification;
	//型号
	private String model;
	//单位
	private String unit;
	//单价
	private Double price;
	//领用数量
	private Long amount;
	//总价
	private Double totalPrice;
	//实际出库数量
	private Long totalStock;
	//备件领用明细状态
	private SpareBorrowDetailStatus status=SpareBorrowDetailStatus.UNBORROW;
	//备件领用明细所关联的领用单
	private SpareBorrow spareBorrow;
	//备件领用单所关联的备件
	private Spare spare;
	//备件领用单明细所关联的出库单明细
	
	public String getStatusTxt(){
		if("UNBORROW".equals(status.toString())){
			return "未领用";
		}else{
			return "已领用";
		}
		
	}
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof SpareBorrowDetail){return true;}
		return false;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Spare getSpare() {
		return spare;
	}
	public void setSpare(Spare spare) {
		this.spare = spare;
	}
	public SpareBorrow getSpareBorrow() {
		return spareBorrow;
	}
	public void setSpareBorrow(SpareBorrow spareBorrow) {
		this.spareBorrow = spareBorrow;
	}
	public String getSpareName() {
		return spareName;
	}
	public void setSpareName(String spareName) {
		this.spareName = spareName;
	}
	public String getSpareNo() {
		return spareNo;
	}
	public void setSpareNo(String spareNo) {
		this.spareNo = spareNo;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public SpareBorrowDetailStatus getStatus() {
		return status;
	}
	public void setStatus(SpareBorrowDetailStatus status) {
		this.status = status;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(Long totalStock) {
		this.totalStock = totalStock;
	}
}
