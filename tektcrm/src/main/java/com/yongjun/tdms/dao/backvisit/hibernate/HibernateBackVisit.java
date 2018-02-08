package com.yongjun.tdms.dao.backvisit.hibernate;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.backvisit.BackVisitDao;
import com.yongjun.tdms.model.backvisit.BackVisit;
import java.util.Collection;
import java.util.List;

public class HibernateBackVisit extends BaseHibernateDao implements BackVisitDao {
	public void deleteAllBackVisit(Collection<BackVisit> backVisits) {
		deleteAll(backVisits);
	}

	public void deleteBackVisit(BackVisit backVisit) {
		delete(backVisit);
	}

	public List<BackVisit> loadAllBackVisit(Long[] backVisitIds) {
		return loadAll(BackVisit.class, backVisitIds);
	}

	public List<BackVisit> loadAllBackVisits() {
		return loadAll(BackVisit.class);
	}

	public BackVisit loadBackVisit(Long backVisitId) {
		return (BackVisit) load(BackVisit.class, backVisitId);
	}

	public List<BackVisit> loadBackVisitByPj(String hqlWord) {
		return getHibernateTemplate().find("From BackVisit b where b.projectInfo"+ hqlWord );
	}
	
	public List<BackVisit> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(BackVisit.class, keyName, keyValue);
	}

	public List<BackVisit> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(BackVisit.class, keyNames, keyValues);
	}

	public void storeBackVisit(BackVisit backVisit) {
		store(backVisit);
	}
}
