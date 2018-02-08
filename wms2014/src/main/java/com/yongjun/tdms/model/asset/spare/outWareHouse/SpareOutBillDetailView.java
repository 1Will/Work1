package com.yongjun.tdms.model.asset.spare.outWareHouse;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

public class SpareOutBillDetailView extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	//出库单编号
    private String outBillCode;
    //出库单名称
    private String outBillName;
    //是否是出库单类型0出库1报废2维修
    private String outType;
    
    public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	//是否是旧件出库
    private String oldSpare;
    
    public String getOldSpare() {
		return oldSpare;
	}

	public void setOldSpare(String oldSpare) {
		this.oldSpare = oldSpare;
	}

	//出库日期
    private Date outBillDate;
    //出库人
    private String outBillPerson;
    //领料人
    private String borrower;
    //部门名称
    private String deptName;
    //编号
    private String code;
    //名称
    private String name;
    //规格
	private String specification;
	//型号
	private String model;
	//单价
	private Double unitPrice;
	//数量
	private Long number;
	//总价
	private Double allPrice;
    //种类名称
	private String categoryName;
	//单位
	private String unit;
	//出库前库存
	private Long stocksBeforeInOrOut;
	//出库后库存
	private Long stocksAfterInOrOut;
	//出库单id
	private Long outWareHouseBillId;
	//出库状态
	private String status;
	//出仓库
	private String outWareHouse;
	//库位
	private String locationCode;
	//明细种类
	private String detailCategoryName;
	//订货号
	private String orderNo;
	//生产厂家
	private String factoryName;
	/**
	 * 领料人 zzb
	 */
	private String borrowerPeople;
	/**
	 * 所属设备 zzb
	 */
	private String shebei;
	/**
	 * 所属班组 zzb
	 */
	private String banzu;
	/**
	 * 是否是以旧换新
	 */
	private String newOrOld;
	//出库人
	private String outPeople;
	//出库日期
	private Date outDate;
	private String useTypes;
	private String shebeino;
	/**
	 * 备注 zzb
	 */
	private String comment;
	private Long warehouse;
	public Long getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Long warehouse) {
		this.warehouse = warehouse;
	}

	public String getBanzu() {
		return banzu;
	}

	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}

	public String getBorrowerPeople() {
		return borrowerPeople;
	}

	public void setBorrowerPeople(String borrowerPeople) {
		this.borrowerPeople = borrowerPeople;
	}

	public String getNewOrOld() {
		return newOrOld;
	}

	public void setNewOrOld(String newOrOld) {
		this.newOrOld = newOrOld;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutPeople() {
		return outPeople;
	}

	public void setOutPeople(String outPeople) {
		this.outPeople = outPeople;
	}

	public String getShebei() {
		return shebei;
	}

	public void setShebei(String shebei) {
		this.shebei = shebei;
	}

	public String getDetailCategoryName() {
		return detailCategoryName;
	}

	public void setDetailCategoryName(String detailCategoryName) {
		this.detailCategoryName = detailCategoryName;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOutWareHouse() {
		return outWareHouse;
	}

	public void setOutWareHouse(String outWareHouse) {
		this.outWareHouse = outWareHouse;
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

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	
	public String getOutBillCode() {
		return outBillCode;
	}

	public void setOutBillCode(String outBillCode) {
		this.outBillCode = outBillCode;
	}

	public Date getOutBillDate() {
		return outBillDate;
	}

	public void setOutBillDate(Date outBillDate) {
		this.outBillDate = outBillDate;
	}

	public String getOutBillPerson() {
		return outBillPerson;
	}

	public void setOutBillPerson(String outBillPerson) {
		this.outBillPerson = outBillPerson;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public Long getOutWareHouseBillId() {
		return outWareHouseBillId;
	}

	public void setOutWareHouseBillId(Long outWareHouseBillId) {
		this.outWareHouseBillId = outWareHouseBillId;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
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

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getOutBillName() {
		return outBillName;
	}

	public void setOutBillName(String outBillName) {
		this.outBillName = outBillName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUseTypes() {
		return useTypes;
	}

	public void setUseTypes(String useTypes) {
		this.useTypes = useTypes;
	}

	public String getShebeino() {
		return shebeino;
	}

	public void setShebeino(String shebeino) {
		this.shebeino = shebeino;
	}
	
	

}
