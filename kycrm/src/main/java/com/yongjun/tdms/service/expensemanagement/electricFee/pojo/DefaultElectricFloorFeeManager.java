package com.yongjun.tdms.service.expensemanagement.electricFee.pojo;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.electricFee.ElectricFloorFeeDao;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFloorFeeManager;

public class DefaultElectricFloorFeeManager extends BaseManager implements ElectricFloorFeeManager {
	private final ElectricFloorFeeDao dao;

	public DefaultElectricFloorFeeManager(ElectricFloorFeeDao dao) {
		this.dao = dao;
	}

	public void storeElectricFloorFee(ElectricFloorFee t) {
		this.dao.storeElectricFloorFee(t);
	}

	public ElectricFloorFee loadElectricFloorFee(Long id) {
		return this.dao.loadElectricFloorFee(id);
	}

	public List<ElectricFloorFee> loadElectricFloorFee() {
		return this.dao.loadElectricFloorFee();
	}

	public List<ElectricFloorFee> loadAllElectricFloorFee(Long[] tIds) {
		return this.dao.loadAllElectricFloorFee(tIds);
	}

	public void deleteElectricFloorFee(ElectricFloorFee t) {
		this.dao.deleteElectricFloorFee(t);
	}

	public void deleteAllElectricFloorFee(List<ElectricFloorFee> ts) {
		this.dao.deleteAllElectricFloorFee(ts);
	}

	public List<ElectricFloorFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<ElectricFloorFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

}
