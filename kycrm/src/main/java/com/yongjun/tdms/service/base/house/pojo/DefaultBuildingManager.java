package com.yongjun.tdms.service.base.house.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.house.BuildingDao;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.service.base.house.BuildingManager;

public class DefaultBuildingManager extends BaseManager implements BuildingManager {
	private final BuildingDao buildingDao;

	public DefaultBuildingManager(BuildingDao buildingDao) {
		this.buildingDao = buildingDao;
	}

	public void deleteAllBuilding(List<Building> list) {
		this.buildingDao.deleteAllBuilding(list);
	}

	public void deleteBuilding(Building building) {
		this.buildingDao.deleteBuilding(building);
	}

	public List<Building> loadAllBuilding(Long[] ids) {
		return this.buildingDao.loadAllBuilding(ids);
	}
	
	public List<Building> loadAllBuilding() {
		return this.buildingDao.loadAllBuilding();
	}

	public Building loadBuilding(Long id) {
		return this.buildingDao.loadBuilding(id);
	}

	public void storeBuilding(Building building) {
		this.buildingDao.storeBuilding(building);
	}

	public List<Building> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.buildingDao.loadByKey(keyName, keyValue);
	}

	public List<Building> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject) throws DaoException {
		return this.buildingDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}
	
	

}
