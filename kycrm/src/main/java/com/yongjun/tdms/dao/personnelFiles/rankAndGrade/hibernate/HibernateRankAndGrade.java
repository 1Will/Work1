package com.yongjun.tdms.dao.personnelFiles.rankAndGrade.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.personnelFiles.rankAndGrade.RankAndGradeDao;
import com.yongjun.tdms.model.personnelFiles.rankAndGrade.RankAndGrade;

public class HibernateRankAndGrade extends BaseHibernateDao implements RankAndGradeDao {
	public void storeRankAndGrade(RankAndGrade rag) {
		super.store(rag);
	}

	public void deleteRankAndGrade(RankAndGrade rag) {
		super.delete(rag);
	}

	public void deleteAllRankAndGrade(Collection<RankAndGrade> rags) {
		super.deleteAll(rags);
	}

	public List<RankAndGrade> loadAllRankAndGrade(Long[] ragIds) {
		return super.loadAll(RankAndGrade.class, ragIds);
	}

	public RankAndGrade loadRankAndGrade(Long ragId) {
		return (RankAndGrade) super.load(RankAndGrade.class, ragId);
	}

	public List<RankAndGrade> loadAllRankAndGrade() {
		return super.loadAll(RankAndGrade.class);
	}

	public List<RankAndGrade> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(RankAndGrade.class, keyName, keyValue);
	}

	public List<RankAndGrade> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(RankAndGrade.class, keyNames, keyValues);
	}
}
