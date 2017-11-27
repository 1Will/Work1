package com.yongjun.tdms.service.CustomerRelationship.finance.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.finance.FinanceHistoryDao;
import com.yongjun.tdms.model.CustomerRelationship.finance.FinanceHistory;
import com.yongjun.tdms.service.CustomerRelationship.finance.FinanceHistoryManager;

public class DefaultFinanceHistoryManager extends BaseManager implements FinanceHistoryManager {
	
	private final FinanceHistoryDao financeHistoryDao;
	public DefaultFinanceHistoryManager(FinanceHistoryDao financeHistoryDao) {
		// TODO Auto-generated constructor stub
		this.financeHistoryDao = financeHistoryDao;
	}
	
	
	public void storeFinanceHistory(FinanceHistory financeHistory) {
		// TODO Auto-generated method stub
		financeHistoryDao.storeFinanceHistory(financeHistory);
	}

	public FinanceHistory loadFinanceHistory(Long id) {
		// TODO Auto-generated method stub
		return financeHistoryDao.loadFinanceHistory(id);
	}

	public List<FinanceHistory> loadAllFinanceHistory() {
		// TODO Auto-generated method stub
		return financeHistoryDao.loadAllFinanceHistory();
	}

	public List<FinanceHistory> loadAllFinanceHistory(Long[] params) {
		// TODO Auto-generated method stub
		return financeHistoryDao.loadAllFinanceHistory(params);
	}

	public void deleteFinanceHistory(FinanceHistory financeHistory) {
		// TODO Auto-generated method stub
		financeHistoryDao.deleteFinanceHistory(financeHistory);
	}

	public void deleteAllFinanceHistory(List<FinanceHistory> financeHistorys) {
		// TODO Auto-generated method stub
		financeHistoryDao.deleteAllFinanceHistory(financeHistorys);
	}

	public List<FinanceHistory> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return financeHistoryDao.loadByKey(key, value);
	}

	public List<FinanceHistory> loadByKeyArray(String[] keys, Object[] values) throws DaoException {
		// TODO Auto-generated method stub
		return financeHistoryDao.loadByKeyArray(keys, values);
	}

}
