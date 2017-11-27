package com.yongjun.tdms.service.base.house.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.house.FloorDao;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.service.base.house.FloorManager;

public class DefaultFloorManager extends BaseManager implements FloorManager {
	private final FloorDao floorDao;

	public DefaultFloorManager(FloorDao floorDao) {
		this.floorDao = floorDao;
	}

	public void deleteAllFloor(List<Floor> list) {
		this.floorDao.deleteAllFloor(list);
	}

	public void deleteFloor(Floor floor) {
		this.floorDao.deleteFloor(floor);
	}

	public List<Floor> loadAllFloor(Long[] ids) {
		return this.floorDao.loadAllFloor(ids);
	}

	public Floor loadFloor(Long id) {
		return this.floorDao.loadFloor(id);
	}

	public void storeFloor(Floor floor) {
		this.floorDao.storeFloor(floor);
	}

	public List<Floor> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.floorDao.loadByKey(keyName, keyValue);
	}

	public List<Floor> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject) throws DaoException {
		return this.floorDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

	public List<Floor> loadAllFloor() {
		return this.floorDao.loadAllFloor();
	}

}
