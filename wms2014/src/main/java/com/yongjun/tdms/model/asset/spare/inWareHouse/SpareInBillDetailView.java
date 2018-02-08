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

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * <p>Title:SpareInBillDetailView
 * <p>Description:备件入库明细视图类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class SpareInBillDetailView extends BaseInfoEntity {
	private static final long serialVersionUID = 2770679886170574687L;
	//仓库
	private String warehouse;
	//备件编号
	private String code;
	//备件名称
	private String name;
	//备件类型
	private String model;
	//备件规格
	private String specification;
	//备件类别
	private String category;
	//备件单位
	private String unit;
	//入库数量
	private Long number;
	//单价
	private Double unitPrice;
	//总价
	private Double totalPrice;
	//入库前库存
	private Long stocksBeforeInOrOut;
	//入库后库存
	private Long stocksAfterInOrOut;
	//备注
	private String comment;
	//入库人
	private String inpeople;
	//验收人
	private String checkpeople;
	//入库单编号
	private String spareinbill_code;
	//入库单名称
	private String spareinbill_name;
	//入库日期
	private Date spareinbill_indate;
	//入库单ID
	private Long spareInBillId;
	//供应商名称
	private String supplier_name;
	//入库状态
	private String status;
	//部门
	private String deptName;
	
	//库位
	private String locationCode;
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	public String getSpareinbill_name() {
		return spareinbill_name;
	}

	public void setSpareinbill_name(String spareinbill_name) {
		this.spareinbill_name = spareinbill_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getInpeople() {
		return inpeople;
	}

	public void setInpeople(String inpeople) {
		this.inpeople = inpeople;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getSpareinbill_indate() {
		return spareinbill_indate;
	}

	public void setSpareinbill_indate(Date spareinbill_indate) {
		this.spareinbill_indate = spareinbill_indate;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Long getStocksAfterInOrOut() {
		return stocksAfterInOrOut;
	}

	public void setStocksAfterInOrOut(Long stocksAfterInOrOut) {
		this.stocksAfterInOrOut = stocksAfterInOrOut;
	}

	public Long getStocksBeforeInOrOut() {
		return stocksBeforeInOrOut;
	}

	public void setStocksBeforeInOrOut(Long stocksBeforeInOrOut) {
		this.stocksBeforeInOrOut = stocksBeforeInOrOut;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getSpareInBillId() {
		return spareInBillId;
	}

	public void setSpareInBillId(Long spareInBillId) {
		this.spareInBillId = spareInBillId;
	}

	public String getSpareinbill_code() {
		return spareinbill_code;
	}

	public void setSpareinbill_code(String spareinbill_code) {
		this.spareinbill_code = spareinbill_code;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getCheckpeople() {
		return checkpeople;
	}

	public void setCheckpeople(String checkpeople) {
		this.checkpeople = checkpeople;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

}
