package com.yongjun.tdms.dao.workReport.week.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.week.WeekDao;
import com.yongjun.tdms.model.workReport.week.Week;

public class HibernateWeek extends BaseHibernateDao implements WeekDao {

	public List<Week> loadAllWeek(Long[] weekIds) {
		return super.loadAll(Week.class, weekIds);
	}

	public Week loadWeek(Long weekId) {
		return (Week) super.load(Week.class, weekId);
	}

	public List<Week> loadAllWeek() {
		return super.loadAll(Week.class);
	}

	public List<Week> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(Week.class, keyName, keyValue);
	}

	public List<Week> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(Week.class, keyNames, keyValues);
	}

}
