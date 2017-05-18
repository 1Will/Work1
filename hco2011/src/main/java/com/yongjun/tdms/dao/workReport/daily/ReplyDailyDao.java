package com.yongjun.tdms.dao.workReport.daily;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.daily.ReplyDaily;

public interface ReplyDailyDao {

	void storeReplyDaily(ReplyDaily replyDaily);

	void deleteReplyDaily(ReplyDaily replyDaily);

	void deleteAllReplyDaily(Collection<ReplyDaily> replyDaily);

	List<ReplyDaily> loadAllReplyDaily(Long[] replyDailyIds);

	ReplyDaily loadReplyDaily(Long replyDailyId);

	List<ReplyDaily> loadAllReplyDaily();

	List<ReplyDaily> loadByKey(String keyName, Object keyValue) throws DaoException;

	List<ReplyDaily> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;

}
