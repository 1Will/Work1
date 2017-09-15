package com.yongjun.tdms.dao.workReport.daily.hibernate;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.daily.ReplyDao;
import com.yongjun.tdms.model.workReport.daily.Reply;

import java.util.Collection;
import java.util.List;

public class HibernateReply extends BaseHibernateDao implements ReplyDao {
	public void storeReply(Reply reply) {
		super.store(reply);
	}

	public void deleteReply(Reply reply) {
		super.delete(reply);
	}

	public void deleteAllReply(Collection<Reply> reply) {
		super.deleteAll(reply);
	}

	public List<Reply> loadAllReply(Long[] replyIds) {
		return super.loadAll(Reply.class, replyIds);
	}

	public Reply loadReply(Long replyId) {
		return (Reply) super.load(Reply.class, replyId);
	}

	public List<Reply> loadAllReply() {
		return super.loadAll(Reply.class);
	}

	public List<Reply> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(Reply.class, keyName, keyValue);
	}

	public List<Reply> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(Reply.class, keyNames, keyValues);
	}
}
