package com.yongjun.tdms.model.asset.spare.inventoryAndCleanHouse;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;

public class SpareInventoryAndCleanHouseReportView extends BaseInfoEntity{
	private static final long serialVersionUID = 8050191862938047326L;
	
	//备件编号
	private String spareNo;
	//备件名称
	private String spareName;
	//型号
	private String modelSpecs;
	//单位
	private String unit;
	//单价
	private Double unitPrice;
	//期初库存
	private Long beforeStocks;
	//月入库
	private Long inStocks;
	//月出库
	private Long outStocks;
	//期末库存
	private Long afterStocks;
	//库存资金
	private Double totalPrice;
	//生成时间
	private Date createTime;
	//工装设备标识
	private String toolingDevFlag;
    //部门名称
	private String deptName;
	//仓库,对应仓库id
	private Long warehouse;
	//仓库名称
	private String warehouseName;
	//库区，对应库区id
	private Long regional;
	//库区名称
	private String regionalName;
	//库位号
	private String locationCode;
    //月份
	private String month;
    //部门
	private Long department;
	//保管员[冗余字段]
	private String custos;
	//保管员
	private User spareCustos;
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public Long getAfterStocks() {
		return afterStocks;
	}

	public void setAfterStocks(Long afterStocks) {
		this.afterStocks = afterStocks;
	}

	public Long getBeforeStocks() {
		return beforeStocks;
	}

	public void setBeforeStocks(Long beforeStocks) {
		this.beforeStocks = beforeStocks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getInStocks() {
		return inStocks;
	}

	public void setInStocks(Long inStocks) {
		this.inStocks = inStocks;
	}

	public Long getOutStocks() {
		return outStocks;
	}

	public void setOutStocks(Long outStocks) {
		this.outStocks = outStocks;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getDepartment() {
		return department;
	}

	public void setDepartment(Long department) {
		this.department = department;
	}

	public String getModelSpecs() {
		return modelSpecs;
	}

	public void setModelSpecs(String modelSpecs) {
		this.modelSpecs = modelSpecs;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCustos() {
		return custos;
	}

	public void setCustos(String custos) {
		this.custos = custos;
	}

	public User getSpareCustos() {
		return spareCustos;
	}

	public void setSpareCustos(User spareCustos) {
		this.spareCustos = spareCustos;
	}

	public Long getRegional() {
		return regional;
	}

	public void setRegional(Long regional) {
		this.regional = regional;
	}

	public String getRegionalName() {
		return regionalName;
	}

	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}

	public Long getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Long warehouse) {
		this.warehouse = warehouse;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
}
