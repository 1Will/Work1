package com.yongjun.tdms.service.workReport.boardroom;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.boardroom.BookBoardroom;

public interface BookBoardroomManager {
	  public abstract void storeBookBoardroom(BookBoardroom paramBookBoardroom);

	  public abstract void deleteBookBoardroom(BookBoardroom paramBookBoardroom);

	  public abstract void deleteAllBookBoardroom(Collection<BookBoardroom> paramCollection);

	  public abstract List<BookBoardroom> loadAllBookBoardroom(Long[] paramArrayOfLong);

	  public abstract BookBoardroom loadBookBoardroom(Long paramLong);

	  public abstract List<BookBoardroom> loadAllBookBoardroom();

	  public abstract List<BookBoardroom> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<BookBoardroom> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
	  
	  public String hasConflict(String boardroomId,String start, String end);
}
