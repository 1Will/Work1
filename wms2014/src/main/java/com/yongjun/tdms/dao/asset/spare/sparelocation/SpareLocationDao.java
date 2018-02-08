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
package com.yongjun.tdms.dao.asset.spare.sparelocation;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;

/**
 * <p>Title: SpareLocationDao
 * <p>Description: 备件明细数据库访问控制接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public interface SpareLocationDao {
	
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
	void storeSpareLocation(SpareLocation spareLocation);
	
	/**
	 * 删除备件明细实体
	 * @param spareLocation 备件明细实体
	 */
	void deleteSpareLocation(SpareLocation spareLocation);
	
	/**
	 * 删除备件明细实体集合
	 * @param spareLocations 备件明细实体集合
	 */
	void deleteAllSpareLocation(Collection<SpareLocation> spareLocations);
	
	/**
	 * 根据部门ID，库位ID,备件ID 获取spareLocation记录数
	 * @param deptId 部门ID
	 * @param locationId 库位ID
	 * @param spareId 备件ID
	 * @return spareLocation记录数
	 */
	Integer getNumByDeptAndLocationAndSpare(Long deptId, Long locationId, Long spareId);
	
	/**
	 * 根据部门ID，库位ID,备件ID 获取明细台帐实体,如果该条件下有多条明细台帐实体,则取最顶一个
	 * @param deptId 部门ID
	 * @param locationId 库位ID
	 * @param spareId 备件ID
	 * @return
	 */
	SpareLocation getTop1SpareLocationByDeptAndLocationAndSpare(Long deptId, Long locationId, Long spareId, boolean isIncludeStocksZeroStatus);
	
	SpareLocation getTop1SpareLocationByLocationAndSpare(Long locationId, Long spareId);
	
	Long getSumBySpare(Long spareId);
	
	Long getSumByLocation(Long locationId);
	
	Long getSumStocksBySpareAndWareHouse(Long spareId,Long wareHouseId);
	 

	
	/**
	 * 根据部门ID,备件ID 获取明细台帐实体,如果该条件下有多条明细台帐实体,则取最顶一个
	 * @param deptId 部门ID
	 * @param spareId 备件ID
	 * @return
	 */
	SpareLocation getTop1SpareLocationByDeptAndSpare(Long deptId, String SpareNo);
	
	SpareLocation getTop1SpareLocationByLocationIdAndSpareId(
			Long locationId, Long spareId);
	/**
	 * 根据备件id和库位id进入t_spare_location表中查询该库位备件上有没有stocks
	 * @param spareId
	 * @param locationId
	 * @return
	 */
	Long getStocksBySpareIdAndLocationId(Long spareId, Long locationId);
	
	public List<SpareLocation> loadByKey(String keyName, Object keyValue) throws DaoException;

}
