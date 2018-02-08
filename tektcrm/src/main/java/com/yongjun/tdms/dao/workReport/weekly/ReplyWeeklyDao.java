package com.yongjun.tdms.dao.workReport.weekly;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.weekly.ReplyWeekly;

public interface ReplyWeeklyDao {
	void storeReplyWeekly(ReplyWeekly replyWeekly);

	void deleteReplyWeekly(ReplyWeekly replyWeekly);

	void deleteAllReplyWeekly(Collection<ReplyWeekly> replyWeekly);

	List<ReplyWeekly> loadAllReplyWeekly(Long[] replyWeeklyIds);

	ReplyWeekly loadReplyWeekly(Long replyWeeklyId);

	List<ReplyWeekly> loadAllReplyWeekly();

	List<ReplyWeekly> loadByKey(String keyName, Object keyValue) throws DaoException;

	List<ReplyWeekly> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
}
