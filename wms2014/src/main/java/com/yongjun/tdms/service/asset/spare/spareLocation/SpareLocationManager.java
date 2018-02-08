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
package com.yongjun.tdms.service.asset.spare.spareLocation;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;

/**
 * <p>Title: SpareLocationManager
 * <p>Description: 备件明细业务逻辑接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
@Transactional(readOnly=true)
public interface SpareLocationManager {
	/**
	 * 根据备件明细ID集合，获取备件明细集合
	 * @param spareLocationId  备件明细ID集合
	 * @return List 备件明细集合
	 */
	List<SpareLocation> loadAllSpareLocations(Long [] spareLocationIds);
	
	/**
	 * 根据备件明细ID，获取备件明细
	 * @param spareLocationId 备件明细ID
	 * @return SpareLocation 备件明细实体
	 */
	SpareLocation loadSpareLocation(Long spareLocationId);
	
	/**
	 * 保存备件明细实体
	 * @param spareLocation 备件明细实体
	 */
	@Transactional
	void storeSpareLocation(SpareLocation spareLocation);
	
	/**
	 * 删除备件明细实体
	 * @param spareLocation 备件明细实体
	 */
	@Transactional
	void deleteSpareLocation(SpareLocation spareLocation);
	
	/**
	 * 删除备件明细实体集合
	 * @param spareLocations 备件明细实体集合
	 */
	@Transactional
	void deleteAllSpareLocation(Collection<SpareLocation> spareLocations);
	
	/**
	 * 修改备件明细中部分信息(如单价等)
	 * @param spareLocation  备件明细实体
	 * @param allSpareLocationIdValue spareLocatin id 用","分割开的字符串
	 * @param allUnitPriceValue unitPrice 用","分割开的字符串
	 * @param allLocationCodeValue 库位号 用","分割开的字符串
	 */
	@Transactional
	void storePartInfoOfSpareLocation(String allSpareLocationIdValue,
			                        String allUnitPriceValue,String allLocationCodeValue);
	
	/**
	 * 修改备件明细中部分信息(如单价等)
	 * @param spareLocation  备件明细实体
	 * @param allSpareLocationIdValue spareLocatin id 用","分割开的字符串
	 * @param allUnitPriceValue unitPrice 用","分割开的字符串
	 * @param allLocationCodeValue 库位号 用","分割开的字符串
	 */
	@Transactional
	void storePartInfoOfSpareLocation(String allSpareLocationIdValue,
			                        String allUnitPriceValue,String allLocationCodeValue,String allWareHouseIdValue);
	public SpareLocation getTop1SpareLocationByDeptAndSpare(Long deptId, String spareNo);
	/**
	 * 通过备件入库明细来更新或新增备件明细台帐
	 * @param detail 入库明细
	 */
	@Transactional
	void storeOrUpdateSpareLocationByInBillDetail(SpareInBillDetail detail);
	/**
	 * 通过备件入库明细来更新或新增备件明细台帐  包含是否可用
	 * @param detail 入库明细
	 */
	@Transactional
	void storeOrUpdateSpareLocationByInBillDetailHaveDisabe(SpareInBillDetail detail);
	@Transactional
	void updateInventoryAfterSpareStocks(SpareLocation spareLocation);
	
	
	SpareLocation getTop1SpareLocationByDeptAndLocationAndSpare(
			Long deptId, Long locationId, Long spareId, boolean isIncludeStocksZeroStatus);
	
	SpareLocation getTop1SpareLocationByLocationIdAndSpareId(
			Long locationId, Long spareId);
	
	SpareLocation getTop1SpareLocationByLocationAndSpare(Long locationId, Long spareId);
	
	 public List<SpareLocation> loadByKey(String key, Object value) throws DaoException;
	 /**
	  * 获取 备件库台账的库存
	  * @param str
	  * @return
	  */
	 public String getSpareLocationStock(String[] ary);
		
}
