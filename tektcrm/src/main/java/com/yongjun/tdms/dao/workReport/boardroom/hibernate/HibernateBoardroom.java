package com.yongjun.tdms.dao.workReport.boardroom.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.boardroom.BoardroomDao;
import com.yongjun.tdms.model.workReport.boardroom.Boardroom;

public class HibernateBoardroom extends BaseHibernateDao implements BoardroomDao {
	public void storeBoardroom(Boardroom boardroom) {
		super.store(boardroom);
	}

	public void deleteBoardroom(Boardroom boardroom) {
		super.delete(boardroom);
	}

	public void deleteAllBoardroom(Collection<Boardroom> boardrooms) {
		super.deleteAll(boardrooms);
	}

	public List<Boardroom> loadAllBoardroom(Long[] boardroomIds) {
		return super.loadAll(Boardroom.class, boardroomIds);
	}

	public Boardroom loadBoardroom(Long boardroomId) {
		return (Boardroom) super.load(Boardroom.class, boardroomId);
	}

	public List<Boardroom> loadAllBoardroom() {
		return super.loadAll(Boardroom.class);
	}

	public List<Boardroom> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(Boardroom.class, keyName, keyValue);
	}

	public List<Boardroom> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(Boardroom.class, keyNames, keyValues);
	}
}
