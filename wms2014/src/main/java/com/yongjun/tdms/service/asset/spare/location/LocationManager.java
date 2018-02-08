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
package com.yongjun.tdms.service.asset.spare.location;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.service.Manager;
import com.yongjun.tdms.model.asset.spare.Location;

/**
 * <p>Title: LocationManager
 * <p>Description: 库位业务逻辑接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
@Transactional(readOnly=true)
public interface LocationManager extends Manager{
	/**
	 * 根据备件明细ID集合，获取备件明细集合
	 * @param spareLocationId  备件明细ID集合
	 * @return List 备件明细集合
	 */
	List<Location> loadAllLocations(Long [] locationIds);
	
	/**
	 * 根据备件明细ID，获取备件明细
	 * @param spareLocationId 备件明细ID
	 * @return SpareLocation 备件明细实体
	 */
	Location loadLocation(Long locationId);
	
	/**
	 * 保存备件明细实体
	 * @param Location 备件明细实体
	 */
	@Transactional
	void storeLocation(Location location) throws Exception ;
	
	/**
	 * 删除备件明细实体
	 * @param Location 备件明细实体
	 */
	@Transactional
	void deleteLocation(Location location);
	
	/**
	 * 删除备件明细实体集合
	 * @param Locations 备件明细实体集合
	 */
	@Transactional
	void deleteAllLocation(Collection<Location> locations);
	
	/**
	 * 根据库位号获取库位实体
	 * @param code 库位号
	 * @return
	 */
	Location getLocationByCode(String code);
	
	/**
	 * 失效
	 * @param locations
	 */
	@Transactional
	void disabledAllLocation(List<Location> locations) throws Exception ;
	
	/**
	 * 有效
	 * @param spareInBills
	 */
	@Transactional
	void enabledAllLocation(List<Location> locations) throws Exception ;
	
	public void updateLocationStatusToNoUse(Location location);
	/**
	 * 根据库区查找库位
	 * @param regionalId
	 * @return
	 */
	public List<Location> findLocationByRegional(String regionalId)throws Exception;
}
