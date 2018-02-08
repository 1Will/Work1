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
package com.yongjun.tdms.model.asset.spare;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.pluto.model.security.Warehouse;

/**
 * <p>Title: BaseSpareInOutInfo
 * <p>Description: 备件出入库共同属性类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class BaseSpareInOutInfo extends BaseInfoEntity {

	private static final long serialVersionUID = 1L;
	
	//备件编号
	private String code;
	//备件名称
	private String name;
	//单价
	private Double unitPrice=0.0;
	//总价
	private Double totalPrice=0.0;
	//入库/出库数量
	private Long number;
	//入库/出库前库存
	private Long stocksBeforeInOrOut=Long.valueOf(0);
	//入库/出库后库存
	private Long stocksAfterInOrOut;
	//库位号  xschen/2009/04/12
	private String locationCode;
	//入库单明细所关联的仓库
	private Warehouse warehouse;
	//入库单明细所关联的库区
	private Regional regional;
	//入库单明细所关联的库位
	private Location location;
	//关联的备件
	private Spare spare;
	//标识出入库的备件或设备是否发送了提醒
	//private boolean callFlag=false;

	/* (non-Javadoc)
	 * @see com.yongjun.pluto.model.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.yongjun.pluto.model.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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



//	public boolean isCallFlag() {
//		return callFlag;
//	}
//
//	public void setCallFlag(boolean callFlag) {
//		this.callFlag = callFlag;
//	}

}
