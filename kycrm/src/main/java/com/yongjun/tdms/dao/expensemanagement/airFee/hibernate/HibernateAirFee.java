package com.yongjun.tdms.dao.expensemanagement.airFee.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.airFee.AirFeeDao;
import com.yongjun.tdms.model.expensemanagement.airFee.AirFee;

public class HibernateAirFee extends BaseHibernateDao implements AirFeeDao {
	public void storeAirFee(AirFee t) {
		store(t);
	}

	public AirFee loadAirFee(Long id) {
		return (AirFee) load(AirFee.class, id);
	}

	public List<AirFee> loadAirFee() {
		return loadAll(AirFee.class);
	}

	public List<AirFee> loadAllAirFee(Long[] tIds) {
		return loadAll(AirFee.class, tIds);
	}

	public void deleteAirFee(AirFee t) {
		delete(t);
	}

	public void deleteAllAirFee(List<AirFee> ts) {
		deleteAll(ts);
	}

	public List<AirFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(AirFee.class, key, value);
	}

	public List<AirFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(AirFee.class, keyNames, keyValues);
	}
}
