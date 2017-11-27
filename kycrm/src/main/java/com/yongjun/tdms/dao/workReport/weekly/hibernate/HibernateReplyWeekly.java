package com.yongjun.tdms.dao.workReport.weekly.hibernate;

import java.util.Collection;
import java.util.List;
import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.weekly.ReplyWeeklyDao;
import com.yongjun.tdms.model.workReport.weekly.ReplyWeekly;

public class HibernateReplyWeekly extends BaseHibernateDao implements ReplyWeeklyDao {

	public void storeReplyWeekly(ReplyWeekly replyWeekly) {
		super.store(replyWeekly);
	}

	public void deleteReplyWeekly(ReplyWeekly replyWeekly) {
		super.delete(replyWeekly);
	}

	public void deleteAllReplyWeekly(Collection<ReplyWeekly> replyWeekly) {
		super.deleteAll(replyWeekly);
	}

	public List<ReplyWeekly> loadAllReplyWeekly(Long[] replyWeeklyIds) {
		return super.loadAll(ReplyWeekly.class, replyWeeklyIds);
	}

	public ReplyWeekly loadReplyWeekly(Long replyWeeklyId) {
		return (ReplyWeekly) super.load(ReplyWeekly.class, replyWeeklyId);
	}

	public List<ReplyWeekly> loadAllReplyWeekly() {
		return super.loadAll(ReplyWeekly.class);
	}

	public List<ReplyWeekly> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(ReplyWeekly.class, keyName, keyValue);
	}

	public List<ReplyWeekly> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(ReplyWeekly.class, keyNames, keyValues);
	}
}
