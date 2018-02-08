package com.yongjun.tdms.dao.notice.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.notice.NoticeDao;
import com.yongjun.tdms.model.notice.Notice;

public class HibernateNotice extends BaseHibernateDao implements NoticeDao{

	public Notice loadNotice(Long NoticeId) {
		
		return this.load(Notice.class,NoticeId);
	}

	public List<Notice> loadNotices(Long[] NoticeIds) {
		
		return this.loadAll(Notice.class,NoticeIds);
	}

	public void storeNotice(Notice notice) {
		this.store(notice);
		
	}



}
