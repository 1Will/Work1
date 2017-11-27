package com.yongjun.tdms.dao.base.house.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.house.FloorDao;
import com.yongjun.tdms.model.base.house.Floor;

public class HibernateFloor extends BaseHibernateDao implements FloorDao {
	public void deleteAllFloor(Collection<Floor> productes) {
		deleteAll(productes);
	}

	public void deleteFloor(Floor floor) {
		delete(floor);
	}

	public List<Floor> loadAllFloor(Long[] floorIds) {
		return loadAll(Floor.class, floorIds);
	}

	public List<Floor> loadAllFloor() {
		return loadAll(Floor.class);
	}

	public Floor loadFloor(Long floorId) {
		return (Floor) load(Floor.class, floorId);
	}

	public void storeFloor(Floor floor) {
		store(floor);
	}

	public List<Floor> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(Floor.class, keyName, keyValue);
	}

	public List<Floor> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(Floor.class, keyNames, keyValues);
	}
}
