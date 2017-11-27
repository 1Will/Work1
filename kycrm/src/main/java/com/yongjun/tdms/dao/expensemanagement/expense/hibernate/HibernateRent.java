package com.yongjun.tdms.dao.expensemanagement.expense.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.expense.RentDao;
import com.yongjun.tdms.model.expensemanagement.expense.Rent;

public class HibernateRent extends BaseHibernateDao implements RentDao {
	public void storeRent(Rent t) {
		store(t);
	}

	public Rent loadRent(Long id) {
		return (Rent) load(Rent.class, id);
	}

	public List<Rent> loadRent() {
		return loadAll(Rent.class);
	}

	public List<Rent> loadAllRent(Long[] tIds) {
		return loadAll(Rent.class, tIds);
	}

	public void deleteRent(Rent t) {
		delete(t);
	}

	public void deleteAllRent(List<Rent> ts) {
		deleteAll(ts);
	}

	public List<Rent> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(Rent.class, key, value);
	}

	public List<Rent> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(Rent.class, keyNames, keyValues);
	}
}
