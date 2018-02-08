/**
 * 
 */
package com.yongjun.tdms.model.asset.spare.spareInAndOut;


import java.util.Date;


import com.yongjun.pluto.model.security.Organization;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;

import com.yongjun.pluto.model.security.Department;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.workflow.model.job.Job;
/**
 * 
 * <p>Title: SpareInAndOut
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: SpareInAndOut.java 2009-3-1 下午01:44:03Z yli$
 * @see com.yongjun.tdms.model.asset.spare.spareInAndOut
 */
public class SpareInAndOutAndStoreReport extends BaseInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Department department;
	//公司代号(暂时没有使用)
	private Integer orgId;
	//收发存报表所关联的库位
	private Location location;
	//收发存报表所关联的仓库
	private Warehouse warehouse;
	//仓库名
	private String warehouseName;
	//收发存报表所关联的库区
	private Regional regional;
	//库区名
	private String regionalName;
	
	//库位号
	private String locationCode;
	//保管员[冗余字段]
	private String custos;
	//保管员
	private User spareCustos;
//	public SpareInAndOutAndStoreReport(){
//		
//	}
//	public SpareInAndOutAndStoreReport(String spareNo,String spareName,String model,String specification,String unit,
//			Double unitPrice,Long beforeStocks,Long inStocks,Long outStocks,Long afterStocks,Double totalPrice,Date createTime,
//			String toolingDevFlag,Integer orgId){
//		
//		this.spareNo=spareNo;
//		this.spareName=spareName;
//		this.model=model;
//		this.specification=specification;
//		this.unit=unit;
//		this.unitPrice=unitPrice;
//		this.beforeStocks=beforeStocks;
//		this.inStocks=inStocks;
//		this.outStocks=outStocks;
//		this.afterStocks=afterStocks;
//		this.totalPrice=totalPrice;
//		this.createTime=createTime;
//		this.toolingDevFlag=toolingDevFlag;
//		this.orgId=orgId;
//		
//		
//	}
	
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

	public Department getDepartment() {
		return department;

	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
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

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public String getRegionalName() {
		return regionalName;
	}

	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
}
