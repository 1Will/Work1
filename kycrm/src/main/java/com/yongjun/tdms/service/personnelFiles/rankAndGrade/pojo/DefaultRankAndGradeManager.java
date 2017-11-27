package com.yongjun.tdms.service.personnelFiles.rankAndGrade.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.personnelFiles.rankAndGrade.RankAndGradeDao;
import com.yongjun.tdms.model.personnelFiles.rankAndGrade.RankAndGrade;
import com.yongjun.tdms.service.personnelFiles.rankAndGrade.RankAndGradeManager;

public class DefaultRankAndGradeManager extends BaseManager implements RankAndGradeManager {
	private final RankAndGradeDao rankAndGradeDao;

	public DefaultRankAndGradeManager(RankAndGradeDao rankAndGradeDao) {
		this.rankAndGradeDao = rankAndGradeDao;
	}

	public void storeRankAndGrade(RankAndGrade pf) {
		this.rankAndGradeDao.storeRankAndGrade(pf);
	}

	public void deleteRankAndGrade(RankAndGrade pf) {
		this.rankAndGradeDao.deleteRankAndGrade(pf);
	}

	public void deleteAllRankAndGrade(Collection<RankAndGrade> pfs) {
		this.rankAndGradeDao.deleteAllRankAndGrade(pfs);
	}

	public List<RankAndGrade> loadAllRankAndGrade(Long[] pfIds) {
		return this.rankAndGradeDao.loadAllRankAndGrade(pfIds);
	}

	public RankAndGrade loadRankAndGrade(Long pfId) {
		return this.rankAndGradeDao.loadRankAndGrade(pfId);
	}

	public List<RankAndGrade> loadAllRankAndGrade() {
		return this.rankAndGradeDao.loadAllRankAndGrade();
	}

	public List<RankAndGrade> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.rankAndGradeDao.loadByKey(keyName, keyValue);
	}

	public List<RankAndGrade> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.rankAndGradeDao.loadByKeyArray(keyNames, keyValues);
	}

}
