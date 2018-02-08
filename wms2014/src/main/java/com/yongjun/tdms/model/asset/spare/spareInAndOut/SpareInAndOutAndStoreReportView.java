package com.yongjun.tdms.model.asset.spare.spareInAndOut;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.Location;

public class SpareInAndOutAndStoreReportView extends BaseInfoEntity{
	private static final long serialVersionUID = 8050191862938047326L;
	
	//备件编号
	private String spareNo;
	//备件名称
	private String spareName;
	//型号
	private String model;
	//规格
	private String specification;
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
    //部门
	private String department;
	//公司代号(暂时没有使用)
	private Integer orgId;
	//库位号
	private String locationCode;
	//仓库名称
	private String warehouseName;
	//库区名称
	private String regionalName;
	//仓库,对应仓库id
	private Long warehouse;
	//库区，对应库区id
	private Long regional;

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getRegionalName() {
		return regionalName;
	}

	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Long getRegional() {
		return regional;
	}

	public void setRegional(Long regional) {
		this.regional = regional;
	}

	public Long getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Long warehouse) {
		this.warehouse = warehouse;
	}



}
