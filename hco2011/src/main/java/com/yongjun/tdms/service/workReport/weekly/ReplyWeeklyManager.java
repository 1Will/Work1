package com.yongjun.tdms.service.workReport.weekly;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workReport.weekly.ReplyWeekly;

public abstract interface ReplyWeeklyManager
{
	public void storeReplyWeekly(ReplyWeekly replyWeekly);

	public void deleteReplyWeekly(ReplyWeekly replyWeekly);

	public void deleteAllReplyWeekly(Collection<ReplyWeekly> replyWeekly);

	public List<ReplyWeekly> loadAllReplyWeekly(Long[] replyWeeklyIds);

	public ReplyWeekly loadReplyWeekly(Long replyWeeklyId);

	public List<ReplyWeekly> loadAllReplyWeekly();

	public List<ReplyWeekly> loadByKey(String keyName, Object keyValue) throws DaoException;

	public List<ReplyWeekly> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
}