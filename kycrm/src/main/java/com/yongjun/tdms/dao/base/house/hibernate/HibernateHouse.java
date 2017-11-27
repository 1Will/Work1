package com.yongjun.tdms.dao.base.house.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.house.HouseDao;
import com.yongjun.tdms.model.base.house.House;

public class HibernateHouse extends BaseHibernateDao implements HouseDao {
	public void deleteAllHouse(Collection<House> productes) {
		deleteAll(productes);
	}

	public void deleteHouse(House house) {
		delete(house);
	}

	public List<House> loadAllHouse(Long[] houseIds) {
		return loadAll(House.class, houseIds);
	}

	public List<House> loadAllHouse() {
		return loadAll(House.class);
	}

	public House loadHouse(Long houseId) {
		return (House) load(House.class, houseId);
	}

	public void storeHouse(House house) {
		store(house);
	}

	public List<House> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(House.class, keyName, keyValue);
	}

	public List<House> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(House.class, keyNames, keyValues);
	}
}
