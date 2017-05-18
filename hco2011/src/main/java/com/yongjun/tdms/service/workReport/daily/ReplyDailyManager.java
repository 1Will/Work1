package com.yongjun.tdms.service.workReport.daily;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.daily.ReplyDaily;

public interface ReplyDailyManager {

	public void storeReplyDaily(ReplyDaily replyDaily);

	public void deleteReplyDaily(ReplyDaily replyDaily);

	public void deleteAllReplyDaily(Collection<ReplyDaily> replyDaily);

	public List<ReplyDaily> loadAllReplyDaily(Long[] replyDailyIds);

	public ReplyDaily loadReplyDaily(Long replyDailyId);

	public List<ReplyDaily> loadAllReplyDaily();

	public List<ReplyDaily> loadByKey(String keyName, Object keyValue) throws DaoException;

	public List<ReplyDaily> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;

}
