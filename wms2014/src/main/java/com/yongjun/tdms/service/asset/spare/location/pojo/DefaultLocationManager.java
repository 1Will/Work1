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
package com.yongjun.tdms.service.asset.spare.location.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.asset.spare.location.LocationDao;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.LocationStatus;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;

/**
 * <p>
 * Title: DefaultLocationManager
 * <p>
 * Description: 库位业务逻辑实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class DefaultLocationManager implements LocationManager {

	private final LocationDao locationDao;

	public DefaultLocationManager(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public List<Location> loadAllLocations(Long[] locationIds) {
		return this.locationDao.loadAllLocations(locationIds);
	}

	public Location loadLocation(Long locationId) {
		return this.locationDao.loadLocation(locationId);
	}

	public void storeLocation(Location location) throws Exception {
		Location old = null;
		//old = this.locationDao.getLocationByCodeOnlyValid(location.getCode());// 根据库位号获取有效的locaiton对象
		old = this.locationDao.getLocationByCodeOnlyValid(location.getCode(),location.getWarehouse().getId());
		if (old != null&&location.isNew()) {// 如果存在相同的库位号，则抛出异常
			//bxchen,time 2010-03-19,make sure the operating is "add"
			//while the user update the entity then skip the judgement
			throw new Exception();
		} else {//有效的库位对象不存在，可以保存location对象
			this.locationDao.storeLocation(location);
		}
	}

	public void deleteLocation(Location location) {
		this.locationDao.deleteLocation(location);
	}

	public void deleteAllLocation(Collection<Location> locations) {
		this.locationDao.deleteAllLocation(locations);
	}

	public Location getLocationByCode(String code) {
		return this.locationDao.getLocationByCodeOnlyValid(code);
	}

	public void disabledAllLocation(List<Location> locations) throws Exception {
		for (Location location : locations) {
			if (location.getStatus().equals(LocationStatus.USED)) {
				throw new Exception();
			}
		}
		for (Location location : locations) {
			location.setDisabled(true);
			this.locationDao.storeLocation(location);
		}
	}

	public void enabledAllLocation(List<Location> locations) throws Exception {
		System.out.println(locations.size());
		for (Location location : locations) {
			if ((this.locationDao.getLocationByCodeOnlyInvalid(
					location.getCode()).size() >1)||(this.locationDao.getLocationByCodeOnlyValid(location.getCode())!=null)) {
				throw new Exception();
			}
		}
		for (Location location : locations) {
			location.setDisabled(false);
			this.locationDao.storeLocation(location);
		}
	}

	/*
	 * 如果库位上的备件已全部出库，则将其状态置为 “未用”
	 */
	public void updateLocationStatusToNoUse(Location location) {

		long locationStock = 0;
		// 计算该库位的总库存
		if (null == location) {
			return ;
		}
		for (SpareLocation spareLocation : location.getSpareLoc()) {
			locationStock = +spareLocation.getStocks();
		}
		// 库位库存为0时则状态为未用
		if (locationStock == 0) {
			location.setStatus(LocationStatus.NON_USE);
		}
	}
	
//	/**
//	 * @function 根据仓库标识查询其包含的库区
//	 * @author wliu
//	 * @param warehouseId 仓库标识
//	 * @param flag 标识查询/维护
//	 * @return
//	 */
//	public List<Location> loadLocationByRegionalId(String regionalId,String flag) throws DaoException{
//		
//		Long regionalIdTemp = Long.valueOf(regionalId);
//		List<Location> locationList = new ArrayList<Location>();
//		if(flag.equals("search")){
//			String[] keyNames = new String[]{"regional.id"};
//			Object[] keyValues = new Object[]{regionalIdTemp};
//			locationList = this.locationDao.loadByKeyArray(keyNames, keyValues);
//		}else if(flag.equals("edit")){
//			String[] keyNames = new String[]{"warehouse.id","disabled"};
//			Object[] keyValues = new Object[]{regionalIdTemp,false};
//			locationList = this.locationDao.loadByKeyArray(keyNames, keyValues);
//		}
//		for (Location location : locationList) {
//			location.setWarehouse(null);
//			location.setJob(null);
//			location.setOrganization(null);
//		}
//		return locationList;
//	}
	
	/**
	 * 根据库区查找库位
	 * @param regionalId
	 * @return
	 */
	public List<Location> findLocationByRegional(String regionalId)throws Exception{
		return this.locationDao.findLocationByRegional(regionalId);
	}
}
