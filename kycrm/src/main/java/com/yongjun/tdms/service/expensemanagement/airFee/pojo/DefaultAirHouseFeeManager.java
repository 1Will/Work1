package com.yongjun.tdms.service.expensemanagement.airFee.pojo;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.airFee.AirHouseFeeDao;
import com.yongjun.tdms.model.expensemanagement.airFee.AirHouseFee;
import com.yongjun.tdms.service.expensemanagement.airFee.AirHouseFeeManager;

public class DefaultAirHouseFeeManager extends BaseManager implements AirHouseFeeManager {
	private final AirHouseFeeDao dao;

	public DefaultAirHouseFeeManager(AirHouseFeeDao dao) {
		this.dao = dao;
	}

	public void storeAirHouseFee(AirHouseFee t) {
		this.dao.storeAirHouseFee(t);
	}

	public AirHouseFee loadAirHouseFee(Long id) {
		return this.dao.loadAirHouseFee(id);
	}

	public List<AirHouseFee> loadAirHouseFee() {
		return this.dao.loadAirHouseFee();
	}

	public List<AirHouseFee> loadAllAirHouseFee(Long[] tIds) {
		return this.dao.loadAllAirHouseFee(tIds);
	}

	public void deleteAirHouseFee(AirHouseFee t) {
		this.dao.deleteAirHouseFee(t);
	}

	public void deleteAllAirHouseFee(List<AirHouseFee> ts) {
		this.dao.deleteAllAirHouseFee(ts);
	}

	public List<AirHouseFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<AirHouseFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}
	public Double loadSumFeeByCusId(Long id, Date date) {
		return this.dao.loadSumFeeByCusId(id,date);
	}
}
