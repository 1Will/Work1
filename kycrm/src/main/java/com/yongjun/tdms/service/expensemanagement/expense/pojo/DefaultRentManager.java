package com.yongjun.tdms.service.expensemanagement.expense.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.expense.RentDao;
import com.yongjun.tdms.model.expensemanagement.expense.Rent;
import com.yongjun.tdms.service.expensemanagement.expense.RentManager;

public class DefaultRentManager extends BaseManager implements RentManager {
	private final RentDao dao;

	public DefaultRentManager(RentDao dao) {
		this.dao = dao;
	}

	public void storeRent(Rent t) {
		this.dao.storeRent(t);
	}

	public Rent loadRent(Long id) {
		return this.dao.loadRent(id);
	}

	public List<Rent> loadRent() {
		return this.dao.loadRent();
	}

	public List<Rent> loadAllRent(Long[] tIds) {
		return this.dao.loadAllRent(tIds);
	}

	public void deleteRent(Rent t) {
		this.dao.deleteRent(t);
	}

	public void deleteAllRent(List<Rent> ts) {
		this.dao.deleteAllRent(ts);
	}

	public List<Rent> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<Rent> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}
}
