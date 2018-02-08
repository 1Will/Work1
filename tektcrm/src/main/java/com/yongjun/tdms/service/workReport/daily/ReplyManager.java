package com.yongjun.tdms.service.workReport.daily;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.daily.Reply;

public interface ReplyManager {

	public void storeReply(Reply reply);

	public void deleteReply(Reply reply);

	public void deleteAllReply(Collection<Reply> reply);

	public List<Reply> loadAllReply(Long[] replyIds);

	public Reply loadReply(Long replyId);

	public List<Reply> loadAllReply();

	public List<Reply> loadByKey(String keyName, Object keyValue) throws DaoException;

	public List<Reply> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;

}
