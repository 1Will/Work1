package com.yongjun.tdms.service.expensemanagement.electricFee.pojo;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.electricFee.ElectricHouseFeeDao;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricHouseFee;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricHouseFeeManager;

public class DefaultElectricHouseFeeManager extends BaseManager implements ElectricHouseFeeManager {
	private final ElectricHouseFeeDao dao;

	public DefaultElectricHouseFeeManager(ElectricHouseFeeDao dao) {
		this.dao = dao;
	}

	public void storeElectricHouseFee(ElectricHouseFee t) {
		this.dao.storeElectricHouseFee(t);
	}

	public ElectricHouseFee loadElectricHouseFee(Long id) {
		return this.dao.loadElectricHouseFee(id);
	}

	public List<ElectricHouseFee> loadElectricHouseFee() {
		return this.dao.loadElectricHouseFee();
	}

	public List<ElectricHouseFee> loadAllElectricHouseFee(Long[] tIds) {
		return this.dao.loadAllElectricHouseFee(tIds);
	}

	public void deleteElectricHouseFee(ElectricHouseFee t) {
		this.dao.deleteElectricHouseFee(t);
	}

	public void deleteAllElectricHouseFee(List<ElectricHouseFee> ts) {
		this.dao.deleteAllElectricHouseFee(ts);
	}

	public List<ElectricHouseFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<ElectricHouseFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public Double loadSumFeeByCusId(Long id, Date date) {
		return this.dao.loadSumFeeByCusId(id, date);
	}
	
}
