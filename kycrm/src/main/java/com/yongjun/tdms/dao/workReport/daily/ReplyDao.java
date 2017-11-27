package com.yongjun.tdms.dao.workReport.daily;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.daily.Reply;

public interface ReplyDao {

	void storeReply(Reply reply);

	void deleteReply(Reply reply);

	void deleteAllReply(Collection<Reply> reply);

	List<Reply> loadAllReply(Long[] replyIds);

	Reply loadReply(Long replyId);

	List<Reply> loadAllReply();

	List<Reply> loadByKey(String keyName, Object keyValue) throws DaoException;

	List<Reply> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;

}
