package com.yongjun.tdms.dao.CustomerRelationship.finance.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.finance.FinanceHistoryDao;
import com.yongjun.tdms.model.CustomerRelationship.finance.FinanceHistory;

public class HibernateFinanceHistory extends BaseHibernateDao implements FinanceHistoryDao{

	public void storeFinanceHistory(FinanceHistory financeHistory) {
		// TODO Auto-generated method stub
		super.store(financeHistory);
	}

	public FinanceHistory loadFinanceHistory(Long id) {
		// TODO Auto-generated method stub
		return super.load(FinanceHistory.class, id);
	}

	public List<FinanceHistory> loadAllFinanceHistory() {
		// TODO Auto-generated method stub
		return super.loadAll(FinanceHistory.class);
	}

	public List<FinanceHistory> loadAllFinanceHistory(Long[] params) {
		// TODO Auto-generated method stub
		return super.loadAll(FinanceHistory.class, params);
	}

	public void deleteFinanceHistory(FinanceHistory financeHistory) {
		// TODO Auto-generated method stub
		super.delete(financeHistory);
	}

	public void deleteAllFinanceHistory(List<FinanceHistory> financeHistorys) {
		// TODO Auto-generated method stub
		super.deleteAll(financeHistorys);
	}

	public List<FinanceHistory> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return super.loadByKey(FinanceHistory.class, key, value);
	}

	public List<FinanceHistory> loadByKeyArray(String[] keys, Object[] values) throws DaoException {
		// TODO Auto-generated method stub
		return super.loadByKeyArray(FinanceHistory.class, keys, values);
	}

}
