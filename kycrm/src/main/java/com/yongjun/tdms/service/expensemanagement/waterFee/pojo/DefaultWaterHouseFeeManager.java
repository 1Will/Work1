package com.yongjun.tdms.service.expensemanagement.waterFee.pojo;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.waterFee.WaterHouseFeeDao;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterHouseFee;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterHouseFeeManager;

public class DefaultWaterHouseFeeManager extends BaseManager implements WaterHouseFeeManager {
	private final WaterHouseFeeDao dao;

	public DefaultWaterHouseFeeManager(WaterHouseFeeDao dao) {
		this.dao = dao;
	}

	public void storeWaterHouseFee(WaterHouseFee t) {
		this.dao.storeWaterHouseFee(t);
	}

	public WaterHouseFee loadWaterHouseFee(Long id) {
		return this.dao.loadWaterHouseFee(id);
	}

	public List<WaterHouseFee> loadWaterHouseFee() {
		return this.dao.loadWaterHouseFee();
	}

	public List<WaterHouseFee> loadAllWaterHouseFee(Long[] tIds) {
		return this.dao.loadAllWaterHouseFee(tIds);
	}

	public void deleteWaterHouseFee(WaterHouseFee t) {
		this.dao.deleteWaterHouseFee(t);
	}

	public void deleteAllWaterHouseFee(List<WaterHouseFee> ts) {
		this.dao.deleteAllWaterHouseFee(ts);
	}

	public List<WaterHouseFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<WaterHouseFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public Double loadSumFeeByCusId(Long id, Date date) {
		return this.dao.loadSumFeeByCusId(id,date);
	}
}
