package com.yongjun.tdms.model.asset.spare.spareInAndOut;

import java.util.Date;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * 
 * <p>Title: SpareInAndOutStoreMonthReport
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: SpareInAndOut.java 2009-3-1 下午01:44:03Z yli$
 * @see com.yongjun.tdms.model.asset.spare.spareInAndOut
 */
public class SpareInAndOutStoreMonthReportView extends BaseInfoEntity{

	private static final long serialVersionUID = 1L;
	//备件编号
	private String spareNo;
    //备件名称 
	private String spareName;
	//型号
	private String modelSpecs;
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
	//单位
	private String unit;
	//单价
	private Double unitPrice;
	//期初库存
	private Long beforeStocks;
	//月入库数量
	private Long inStocks;
	//月出库数量
	private Long outStocks;
	//期末库存
	private Long afterStocks;
	//实际发生时间
	private Date createTime;
	//月份
	private String month;
    //总价
	private Double totalPrice;
	//标示工装和设备的类别
	private String toolingDevFlag;
	//保管员[冗余字段]
	private String custos;
	//保管员
	private User spareCustos;
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSpareName(){
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getModelSpecs() {
		return modelSpecs;
	}

	public void setModelSpecs(String modelSpecs) {
		this.modelSpecs = modelSpecs;
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

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
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
