/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.model.asset.spare;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.pluto.model.security.Warehouse;

/**
 * <p>Title: SpareLocation
 * <p>Description: 备件库明细台账</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SpareLocation.java 28207 2010-10-28 10:48:20Z zbzhang $
 */
public class SpareLocation extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	
	/*
	 * YONGJUN-TEACHNOLOGY
	 */
	private static final long serialVersionUID = 2429000086340256709L;
	//关联spare表
	private Spare spare;
	//单价
	private Double unitPrice;
	//库存
	private Long stocks;
	//库存
	private Long safeStocks;
	//被占用库存
	private Long lockedStocks = 0l;
	//关联部门
	private Department department;
	//关联仓库
	private Warehouse warehouse;
	//关联库区
	private Regional regional;
	//关联库位
	private Location location;
	//库位号
	private String locationCode;
	//期初库存
	private Long preStocks;
    //金额	
	private Double money;
	
	//
	private String spareState;
	
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

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}


	public void setPreStocks(Long preStocks) {
		this.preStocks = preStocks;
	}

	public Long getStocks() {
		return stocks;
	}

	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Long getPreStocks() {
		return preStocks;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Long getLockedStocks() {
		return lockedStocks;
	}

	public void setLockedStocks(Long lockedStocks) {
		this.lockedStocks = lockedStocks;
	}

	public String getSpareState() {
		return spareState;
	}

	public void setSpareState(String spareState) {
		this.spareState = spareState;
	}

	public Long getSafeStocks() {
		return safeStocks;
	}

	public void setSafeStocks(Long safeStocks) {
		this.safeStocks = safeStocks;
	}

	
	
}
