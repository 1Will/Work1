package com.yongjun.tdms.service.workReport.daily.pojo;

import java.util.Collection;
import java.util.List;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.daily.ReplyDailyDao;
import com.yongjun.tdms.model.workReport.daily.ReplyDaily;
import com.yongjun.tdms.service.workReport.daily.ReplyDailyManager;

public class DefaultReplyDailyManager extends BaseManager implements ReplyDailyManager {
	private final ReplyDailyDao replyDailyDao;

	public DefaultReplyDailyManager(ReplyDailyDao replyDailyDao) {
		this.replyDailyDao = replyDailyDao;
	}

	public void storeReplyDaily(ReplyDaily replyDaily) {
		this.replyDailyDao.storeReplyDaily(replyDaily);
	}

	public void deleteReplyDaily(ReplyDaily replyDaily) {
		this.replyDailyDao.deleteReplyDaily(replyDaily);
	}

	public void deleteAllReplyDaily(Collection<ReplyDaily> replyDaily) {
		this.replyDailyDao.deleteAllReplyDaily(replyDaily);
	}

	public List<ReplyDaily> loadAllReplyDaily(Long[] replyDailyIds) {
		return this.replyDailyDao.loadAllReplyDaily(replyDailyIds);
	}

	public ReplyDaily loadReplyDaily(Long replyDailyId) {
		return this.replyDailyDao.loadReplyDaily(replyDailyId);
	}

	public List<ReplyDaily> loadAllReplyDaily() {
		return this.replyDailyDao.loadAllReplyDaily();
	}

	public List<ReplyDaily> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.replyDailyDao.loadByKey(keyName, keyValue);
	}

	public List<ReplyDaily> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.replyDailyDao.loadByKeyArray(keyNames, keyValues);
	}
}
