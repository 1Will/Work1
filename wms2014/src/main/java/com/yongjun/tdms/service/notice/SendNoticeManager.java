package com.yongjun.tdms.service.notice;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.yongjun.pluto.model.notice.SendNotice;
import com.yongjun.pluto.model.security.Group;
import com.yongjun.tdms.model.notice.Notice;
public interface SendNoticeManager {
	SendNotice loadSendNotice(Long sendNoticeId);
	List<SendNotice> loadSendNotices(Long[] sendNoticeIds);
	@Transactional
	void storeSendNotice(SendNotice notice);
	@Transactional
	void deleteSendNotice(SendNotice notice);
	@Transactional
	void deleteSendNotices(Collection <SendNotice> notices);
	@Transactional
	void disabledAllSendNotices(Collection<SendNotice> receviceNotices);
	
	@Transactional
	void enabledAllSendNotices(Collection<SendNotice> receviceNotices);
	@Transactional
    void joinUsersForSend(String [] userIds, SendNotice notice);
	SendNotice getNoticeByNoticeTilteAndContent(String title,String content);
}
