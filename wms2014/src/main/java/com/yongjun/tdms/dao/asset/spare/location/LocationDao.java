package com.yongjun.tdms.dao.asset.spare.location;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.spare.Location;

/**
 * <p>
 * Title: LocationDao
 * <p>
 * Description: 库位数据库访问接口类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author zbzhang@yj-technology.com
 * @version $
 */
public interface LocationDao {
	/**
	 * 根据备件明细ID集合，获取List备件明细实体集合
	 * 
	 * @param spareLocationId表示备件明细ID集合
	 * @return List备件明细实体集合
	 */
	List<Location> loadAllLocations(Long[] locationIds);

	/**
	 * 根据备件明细ID，获取备件明细实体
	 * 
	 * @param spareLocationId表示备件明细ID
	 * @return SpareLocation备件明细实体
	 */
	Location loadLocation(Long locationId);

	/**
	 * 保存备件明细实体
	 * 
	 * @param Location表示备件明细实体
	 */
	void storeLocation(Location location);

	/**
	 * 删除备件明细实体
	 * 
	 * @param Location表示备件明细实体
	 */
	void deleteLocation(Location location);

	/**
	 * 删除备件明细实体集合
	 * 
	 * @param Locations表示备件明细实体集合
	 */
	void deleteAllLocation(Collection<Location> locations);

	/**
	 * 根据库位号获取有效的库位实体
	 * 
	 * @param  code表示库位号
	 * @return 有效的库位实体
	 */
	Location getLocationByCodeOnlyValid(String code);
	
	
	/**
	 * 根据库位号获取有效的库位实体
	 * 
	 * @param  code表示库位号
	 * @param warehoustId 仓库ID
	 * @return 有效的库位实体
	 */
	Location getLocationByCodeOnlyValid(String code,Long warehouseId);


	/**
	 * 根据库位号获取失效的List库位实体集合
	 * 
	 * @param   code表示库位号
	 * @returnd 失效的List库位实体集合
	 */
	List<Location> getLocationByCodeOnlyInvalid(String code);
	
	/**
	 * 根据库区查找库位
	 * @param regionalId
	 * @return
	 */
	public List<Location> findLocationByRegional(String regionalId)throws Exception;
	
	/**
	 * 根据库位名称和仓库查找库位
	 * @param regionalId
	 * @return
	 * @throws Exception 
	 */
	public Location findLocationByWarehouse(String locationName,long warehouseId);
}
