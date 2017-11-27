package com.yongjun.tdms.service.base.house.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.house.HouseDao;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.service.base.house.HouseManager;

public class DefaultHouseManager extends BaseManager implements HouseManager {
	private final HouseDao houseDao;

	public DefaultHouseManager(HouseDao houseDao) {
		this.houseDao = houseDao;
	}

	public void deleteAllHouse(List<House> list) {
		this.houseDao.deleteAllHouse(list);
	}

	public void deleteHouse(House house) {
		this.houseDao.deleteHouse(house);
	}

	public List<House> loadAllHouse(Long[] ids) {
		return this.houseDao.loadAllHouse(ids);
	}

	public House loadHouse(Long id) {
		return this.houseDao.loadHouse(id);
	}

	public void storeHouse(House house) {
		this.houseDao.storeHouse(house);
	}

	public List<House> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.houseDao.loadByKey(keyName, keyValue);
	}

	public List<House> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject) throws DaoException {
		return this.houseDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

	public List<House> loadAllHouse() {
		return this.houseDao.loadAllHouse();
	}

	public void saveAllHouse(List<House> paramList) {
		for (int i = 0; i < paramList.size(); i++) {
			this.houseDao.storeHouse(paramList.get(i));
		}
	}

}
