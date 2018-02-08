package com.yongjun.tdms.service.workReport.boardroom.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.boardroom.BookBoardroomDao;
import com.yongjun.tdms.model.workReport.boardroom.BookBoardroom;
import com.yongjun.tdms.service.workReport.boardroom.BookBoardroomManager;

public class DefaultBookBoardroomManager implements BookBoardroomManager{
	private final BookBoardroomDao bookBoardroomDao;
	public DefaultBookBoardroomManager(BookBoardroomDao bookBoardroomDao) {
		this.bookBoardroomDao = bookBoardroomDao;
	}

	public void storeBookBoardroom(BookBoardroom bookBoardroom) {
		this.bookBoardroomDao.storeBookBoardroom(bookBoardroom);
	}

	public void deleteBookBoardroom(BookBoardroom bookBoardroom) {
		this.bookBoardroomDao.deleteBookBoardroom(bookBoardroom);
	}

	public void deleteAllBookBoardroom(Collection<BookBoardroom> bookBoardroom) {
		this.bookBoardroomDao.deleteAllBookBoardroom(bookBoardroom);
	}

	public List<BookBoardroom> loadAllBookBoardroom(Long[] bookBoardroomIds) {
		return this.bookBoardroomDao.loadAllBookBoardroom(bookBoardroomIds);
	}

	public BookBoardroom loadBookBoardroom(Long bookBoardroomId) {
		return this.bookBoardroomDao.loadBookBoardroom(bookBoardroomId);
	}

	public List<BookBoardroom> loadAllBookBoardroom() {
		return this.bookBoardroomDao.loadAllBookBoardroom();
	}

	public List<BookBoardroom> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.bookBoardroomDao.loadByKey(keyName, keyValue);
	}

	public List<BookBoardroom> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.bookBoardroomDao.loadByKeyArray(keyNames, keyValues);
	}
	
	public String hasConflict(String boardroomId,String start, String end){
		return this.bookBoardroomDao.hasConflict(boardroomId,start,end);
	}
}
