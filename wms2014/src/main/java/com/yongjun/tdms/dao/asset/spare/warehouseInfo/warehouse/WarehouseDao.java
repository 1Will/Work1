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
package com.yongjun.tdms.dao.asset.spare.warehouseInfo.warehouse;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Warehouse;

/**
 * <p>Title: WarehouseDao</p>
 * <p>Description: 仓库信息数据访问层接口</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author wliu@yj-technology.com</p>
 * <p>@version $Id: WarehouseDao.java 2010-03-10 wliu $</p>
 */
@Deprecated
public interface WarehouseDao {

	/**
	 * 保存当前仓库信息
	 * @param wh 仓库信息
	 */
	public void storeWarehouse(Warehouse wh);
	
	/**
	 * 删除当前选定的仓库信息
	 * @param wh 仓库信息
	 */
	public void deleteWarehouse(Warehouse wh);

	/**
	 * 删除多个选定的仓库信息
	 * @param whs 仓库信息集合
	 */
	public void deleteAllWarehouse(Collection<Warehouse> whs);

	/**
	 * 根据多个标识查询多个仓库信息
	 * @param whIds 仓库信息集合
	 * @return 仓库信息集合
	 */
	public List<Warehouse> loadAllWarehouse(Long[] whIds);

	/**
	 * 根据标识查询指定的仓库信息
	 * @param whId 仓库信息标识
	 * @return 仓库信息
	 */
	public Warehouse loadWarehouse(Long whId);

	/**
	 * 查询所有仓库信息
	 * @return 仓库信息集合
	 */
	public List<Warehouse> loadAllWarehouse();

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
	public List loadWareHouseByStorageGradeId(String id);
	/**
	 * 根据参考编码获取参考
	 * @param code
	 * @return
	 */
	public Warehouse loadJJWareHouse(String code) ;
	/**
	 * 根据用户id获取仓库
	 * @param userid
	 * @return
	 */
	public List<Integer> loadWareHouseByUser(long userid);
	public List<Integer> loadWareHouseByUserAndNew(long userid);
	public List<Warehouse> loadByAber(String wids);
	
}
