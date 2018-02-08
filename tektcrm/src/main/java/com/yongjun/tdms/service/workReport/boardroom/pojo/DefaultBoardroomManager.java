package com.yongjun.tdms.service.workReport.boardroom.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.boardroom.BoardroomDao;
import com.yongjun.tdms.model.workReport.boardroom.Boardroom;
import com.yongjun.tdms.service.workReport.boardroom.BoardroomManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultBoardroomManager implements BoardroomManager{
	private final BoardroomDao boardroomDao;
	private final YongJunSequenceManager yongJunSequenceManager;

	public DefaultBoardroomManager(BoardroomDao boardroomDao,YongJunSequenceManager yongJunSequenceManager) {
		super();
		this.boardroomDao = boardroomDao;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void storeBoardroom(Boardroom boardroom) {
		if (boardroom.isNew()) {
			String toCode="HYS";
			String code =(String)this.yongJunSequenceManager.generateeCodeTypeReplacFormtter(YongJunSequenceConstant.CODE_PROJECT,toCode);
			boardroom.setCode(code);
		}
		this.boardroomDao.storeBoardroom(boardroom);
	}

	public void deleteBoardroom(Boardroom boardroom) {
		this.boardroomDao.deleteBoardroom(boardroom);
	}

	public void deleteAllBoardroom(Collection<Boardroom> boardroom) {
		this.boardroomDao.deleteAllBoardroom(boardroom);
	}

	public List<Boardroom> loadAllBoardroom(Long[] boardroomIds) {
		return this.boardroomDao.loadAllBoardroom(boardroomIds);
	}

	public Boardroom loadBoardroom(Long boardroomId) {
		return this.boardroomDao.loadBoardroom(boardroomId);
	}

	public List<Boardroom> loadAllBoardroom() {
		return this.boardroomDao.loadAllBoardroom();
	}

	public List<Boardroom> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.boardroomDao.loadByKey(keyName, keyValue);
	}

	public List<Boardroom> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.boardroomDao.loadByKeyArray(keyNames, keyValues);
	}
}
