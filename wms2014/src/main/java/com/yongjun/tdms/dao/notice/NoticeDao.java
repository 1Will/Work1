package com.yongjun.tdms.dao.notice;

import java.util.List;

import com.yongjun.tdms.model.notice.Notice;

public interface NoticeDao {
	Notice loadNotice(Long NoticeId);
	List<Notice> loadNotices(Long[] NoticeIds);
	void storeNotice(Notice notice);


}
