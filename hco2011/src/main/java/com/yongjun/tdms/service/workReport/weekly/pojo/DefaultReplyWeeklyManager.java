package com.yongjun.tdms.service.workReport.weekly.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.weekly.ReplyWeeklyDao;
import com.yongjun.tdms.model.workReport.weekly.ReplyWeekly;
import com.yongjun.tdms.service.workReport.weekly.ReplyWeeklyManager;

public class DefaultReplyWeeklyManager extends BaseManager implements ReplyWeeklyManager{
	private final ReplyWeeklyDao replyWeeklyDao;

	public DefaultReplyWeeklyManager(ReplyWeeklyDao replyWeeklyDao) {
		this.replyWeeklyDao = replyWeeklyDao;
	}

	public void storeReplyWeekly(ReplyWeekly replyWeekly) {
		this.replyWeeklyDao.storeReplyWeekly(replyWeekly);
	}

	public void deleteReplyWeekly(ReplyWeekly replyWeekly) {
		this.replyWeeklyDao.deleteReplyWeekly(replyWeekly);
	}

	public void deleteAllReplyWeekly(Collection<ReplyWeekly> replyWeekly) {
		this.replyWeeklyDao.deleteAllReplyWeekly(replyWeekly);
	}

	public List<ReplyWeekly> loadAllReplyWeekly(Long[] replyWeeklyIds) {
		return this.replyWeeklyDao.loadAllReplyWeekly(replyWeeklyIds);
	}

	public ReplyWeekly loadReplyWeekly(Long replyWeeklyId) {
		return this.replyWeeklyDao.loadReplyWeekly(replyWeeklyId);
	}

	public List<ReplyWeekly> loadAllReplyWeekly() {
		return this.replyWeeklyDao.loadAllReplyWeekly();
	}

	public List<ReplyWeekly> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.replyWeeklyDao.loadByKey(keyName, keyValue);
	}

	public List<ReplyWeekly> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.replyWeeklyDao.loadByKeyArray(keyNames, keyValues);
	}
}
