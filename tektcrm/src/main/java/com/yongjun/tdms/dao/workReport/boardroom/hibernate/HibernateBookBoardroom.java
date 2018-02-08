package com.yongjun.tdms.dao.workReport.boardroom.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.boardroom.BookBoardroomDao;
import com.yongjun.tdms.model.workReport.boardroom.BookBoardroom;

public class HibernateBookBoardroom extends BaseHibernateDao implements BookBoardroomDao {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm");
	public void storeBookBoardroom(BookBoardroom bookBoardroom) {
		super.store(bookBoardroom);
	}

	public void deleteBookBoardroom(BookBoardroom bookBoardroom) {
		super.delete(bookBoardroom);
	}

	public void deleteAllBookBoardroom(Collection<BookBoardroom> bookBoardrooms) {
		super.deleteAll(bookBoardrooms);
	}

	public List<BookBoardroom> loadAllBookBoardroom(Long[] bookBoardroomIds) {
		return super.loadAll(BookBoardroom.class, bookBoardroomIds);
	}

	public BookBoardroom loadBookBoardroom(Long bookBoardroomId) {
		return (BookBoardroom) super.load(BookBoardroom.class, bookBoardroomId);
	}

	public List<BookBoardroom> loadAllBookBoardroom() {
		return super.loadAll(BookBoardroom.class);
	}

	public List<BookBoardroom> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(BookBoardroom.class, keyName, keyValue);
	}

	public List<BookBoardroom> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(BookBoardroom.class, keyNames, keyValues);
	}

	@SuppressWarnings("rawtypes")
	public String hasConflict(String boardroomId,String start, String end) {
		try {
			format.parse(start);
			format.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
			return "0";
		}
		String hql_a = "from BookBoardroom b where b.startDate < '"+start +"' and b.endDate > '" +start+"' and b.boardroom.id = "+boardroomId;
		String hql_b = "from BookBoardroom b where b.startDate < '"+end +"' and b.endDate > '" + end +"' and b.boardroom.id = "+boardroomId;
		String hql_c = "from BookBoardroom b where b.startDate > '"+start +"' and b.endDate < '" + end +"' and b.boardroom.id = "+boardroomId;
		List bookBoardroom_a = getSession().createQuery(hql_a).list();
		List bookBoardroom_b = getSession().createQuery(hql_b).list();
		List bookBoardroom_c = getSession().createQuery(hql_c).list();
		if(bookBoardroom_a.size() == 0 && bookBoardroom_b.size() == 0 && bookBoardroom_c.size() == 0){
			return "1";
		}else {
			return "0";
		}
	}
}
