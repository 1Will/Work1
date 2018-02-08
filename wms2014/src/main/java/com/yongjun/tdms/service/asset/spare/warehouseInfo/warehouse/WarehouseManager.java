/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Warehouse;

/**
 * <p>
 * Title:WarehouseManager
 * <p>
 * Description:WarehouseManager
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author bxchen@yj-technology.com
 * @version
 */
@Transactional(readOnly = true)
public interface WarehouseManager {
	/**
	 * 根据仓库的ID集合，获得仓库的集合
	 * 
	 * @param warehouseIds
	 * @return
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	List<Warehouse> loadAllWarehouse(Long[] warehouseIds);

	/**
	 * 根据仓库的ID，获得仓库实体
	 * 
	 * @param warehouseId
	 * @return
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	Warehouse loadWarehouse(Long warehouseId);

	/**
	 * 保存仓库实体
	 * 
	 * @param warehouse
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void storeWarehouse(Warehouse warehouse);

	/**
	 * 删除仓库实体
	 * 
	 * @param warehouse
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void deleteWarehouse(Warehouse warehouse);

	/**
	 * 删除仓库实体集合
	 * 
	 * @param warehouses
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void deleteAllWarehouse(Collection<Warehouse> warehouses);

	/**
	 * 失效仓库实体集合
	 * 
	 * @param warehouse
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void disabledAllWarehouse(Collection<Warehouse> warehouse);

	/**
	 * 有效仓库实体集合
	 * 
	 * @param warehouse
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void enabledAllWarehouse(Collection<Warehouse> warehouse);

	List<Warehouse> loadAllValidWarehouse();

	List<Warehouse> loadAllWarehouse();

	/**
	 * 根据非主键查询实体类
	 * @param keyName 非主键标识
	 * @param keyValue 非主键值
	 * @return 实体类集合
	 */
	public List<Warehouse> loadByKey(String keyName, Object keyValue) throws DaoException;
	
	/**
	 * 根据非主键集合查询实体类
	 * @param keyNamess 非主键标识集合
	 * @param keyValues 非主键值集合
	 * @return 实体类集合
	 */
	public List<Warehouse> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
	/**
	 * 根据仓库级别查找仓库
	 * @param id
	 * @return
	 */
	@Transactional
	public List loadWareHouseByStorageGradeId(String id);
	@Transactional
	public List loadWareHouseByUser(String id, String userId) ;
	@Transactional
	public Warehouse loadJJWareHouse(String code) ;
	public List<Warehouse> loadByUser(long userid) throws DaoException;
	public List<Warehouse> loadByUserAndNew(long userid) throws DaoException;
	public List<Warehouse> loadByAber(String wids);
	
	
	
}
