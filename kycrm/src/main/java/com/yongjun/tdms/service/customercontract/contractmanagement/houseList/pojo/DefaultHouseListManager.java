package com.yongjun.tdms.service.customercontract.contractmanagement.houseList.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.houseList.HouseListDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;

public class DefaultHouseListManager extends BaseManager implements HouseListManager {
	private final HouseListDao dao;

	public DefaultHouseListManager(HouseListDao dao) {
		this.dao = dao;
	}

	public void storeHouseList(HouseList t) {
		this.dao.storeHouseList(t);
	}

	public HouseList loadHouseList(Long id) {
		return this.dao.loadHouseList(id);
	}

	public List<HouseList> loadHouseList() {
		return this.dao.loadHouseList();
	}

	public List<HouseList> loadAllHouseList(Long[] tIds) {
		return this.dao.loadAllHouseList(tIds);
	}

	public void deleteHouseList(HouseList t) {
		this.dao.deleteHouseList(t);
	}

	public void deleteAllHouseList(List<HouseList> ts) {
		this.dao.deleteAllHouseList(ts);
	}

	public List<HouseList> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<HouseList> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public double getSum(long id) throws DaoException {
		return this.dao.getSum(id);
	}
	
	public double getAllSquare(long id) throws DaoException {
		return this.dao.getAllSquare(id);
	}

	public String getHouseListEndTime(long id) throws DaoException {
		return this.dao.getHouseListEndTime(id);
	}
}
