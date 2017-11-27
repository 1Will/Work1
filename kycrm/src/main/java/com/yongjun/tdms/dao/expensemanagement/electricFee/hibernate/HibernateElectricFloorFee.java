package com.yongjun.tdms.dao.expensemanagement.electricFee.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.electricFee.ElectricFloorFeeDao;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;

public class HibernateElectricFloorFee extends BaseHibernateDao implements ElectricFloorFeeDao {
	public void storeElectricFloorFee(ElectricFloorFee t) {
		store(t);
	}

	public ElectricFloorFee loadElectricFloorFee(Long id) {
		return (ElectricFloorFee) load(ElectricFloorFee.class, id);
	}

	public List<ElectricFloorFee> loadElectricFloorFee() {
		return loadAll(ElectricFloorFee.class);
	}

	public List<ElectricFloorFee> loadAllElectricFloorFee(Long[] tIds) {
		return loadAll(ElectricFloorFee.class, tIds);
	}

	public void deleteElectricFloorFee(ElectricFloorFee t) {
		delete(t);
	}

	public void deleteAllElectricFloorFee(List<ElectricFloorFee> ts) {
		deleteAll(ts);
	}

	public List<ElectricFloorFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(ElectricFloorFee.class, key, value);
	}

	public List<ElectricFloorFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ElectricFloorFee.class, keyNames, keyValues);
	}
}
