package com.yongjun.tdms.dao.workReport.daily.hibernate;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.daily.DailyDao;
import com.yongjun.tdms.model.workReport.daily.Daily;
import java.util.Collection;
import java.util.List;

public class HibernateDaily extends BaseHibernateDao implements DailyDao {
	public void storeDaily(Daily daily) {
		super.store(daily);
	}

	public void deleteDaily(Daily daily) {
		super.delete(daily);
	}

	public void deleteAllDaily(Collection<Daily> dailys) {
		super.deleteAll(dailys);
	}

	public List<Daily> loadAllDaily(Long[] dailyIds) {
		return super.loadAll(Daily.class, dailyIds);
	}

	public Daily loadDaily(Long dailyId) {
		return (Daily) super.load(Daily.class, dailyId);
	}

	public List<Daily> loadAllDaily() {
		return super.loadAll(Daily.class);
	}

	public List<Daily> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(Daily.class, keyName, keyValue);
	}

	public List<Daily> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(Daily.class, keyNames, keyValues);
	}
}
