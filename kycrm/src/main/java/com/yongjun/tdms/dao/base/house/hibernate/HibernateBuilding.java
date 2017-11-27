package com.yongjun.tdms.dao.base.house.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.house.BuildingDao;
import com.yongjun.tdms.model.base.house.Building;

public class HibernateBuilding extends BaseHibernateDao implements BuildingDao {
	public void deleteAllBuilding(Collection<Building> productes) {
		deleteAll(productes);
	}

	public void deleteBuilding(Building building) {
		delete(building);
	}

	public List<Building> loadAllBuilding(Long[] buildingIds) {
		return loadAll(Building.class, buildingIds);
	}

	public List<Building> loadAllBuilding() {
		return loadAll(Building.class);
	}

	public Building loadBuilding(Long buildingId) {
		return (Building) load(Building.class, buildingId);
	}

	public void storeBuilding(Building building) {
		store(building);
	}

	public List<Building> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(Building.class, keyName, keyValue);
	}

	public List<Building> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(Building.class, keyNames, keyValues);
	}
}
