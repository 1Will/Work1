package com.yongjun.tdms.service.workReport.daily.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.daily.ReplyDao;
import com.yongjun.tdms.model.workReport.daily.Reply;
import com.yongjun.tdms.service.workReport.daily.ReplyManager;

public class DefaultReplyManager extends BaseManager implements ReplyManager {
	private final ReplyDao replyDao;

	public DefaultReplyManager(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void storeReply(Reply reply) {
		this.replyDao.storeReply(reply);
	}

	public void deleteReply(Reply reply) {
		this.replyDao.deleteReply(reply);
	}

	public void deleteAllReply(Collection<Reply> reply) {
		this.replyDao.deleteAllReply(reply);
	}

	public List<Reply> loadAllReply(Long[] replyIds) {
		return this.replyDao.loadAllReply(replyIds);
	}

	public Reply loadReply(Long replyId) {
		return this.replyDao.loadReply(replyId);
	}

	public List<Reply> loadAllReply() {
		return this.replyDao.loadAllReply();
	}

	public List<Reply> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.replyDao.loadByKey(keyName, keyValue);
	}

	public List<Reply> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.replyDao.loadByKeyArray(keyNames, keyValues);
	}
}
