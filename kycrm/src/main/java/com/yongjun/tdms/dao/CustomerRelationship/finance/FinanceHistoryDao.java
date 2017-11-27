package com.yongjun.tdms.dao.CustomerRelationship.finance;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.finance.FinanceHistory;

public interface FinanceHistoryDao {
	public abstract void storeFinanceHistory(FinanceHistory financeHistory);

	  public abstract FinanceHistory loadFinanceHistory(Long id);

	  public abstract List<FinanceHistory> loadAllFinanceHistory();

	  public abstract List<FinanceHistory> loadAllFinanceHistory(Long[] params);

	  public abstract void deleteFinanceHistory(FinanceHistory financeHistory);

	  public abstract void deleteAllFinanceHistory(List<FinanceHistory> financeHistorys);

	  public abstract List<FinanceHistory> loadByKey(String key, Object value)
	    throws DaoException;

	  public abstract List<FinanceHistory> loadByKeyArray(String[] keys, Object[] values)
	    throws DaoException;
}
