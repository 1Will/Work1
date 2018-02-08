package com.yongjun.tdms.service.notice.pojo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.yongjun.pluto.dao.notice.ReceviceNoticeDao;
import com.yongjun.pluto.model.notice.ReadStatus;
import com.yongjun.pluto.model.notice.ReceviceNotice;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;


public class DefaultReceviceNoticeManager extends BaseManager implements ReceviceNoticeManager{
	private final ReceviceNoticeDao receviceNoticeDao;
	private final FileTransportManager fileTransportManager;
    public DefaultReceviceNoticeManager(ReceviceNoticeDao receviceNoticeDao,
    		FileTransportManager fileTransportManager){
    	this.receviceNoticeDao=receviceNoticeDao;
    	this.fileTransportManager=fileTransportManager;
    }
	public ReceviceNotice loadReceviceNotice(Long receviceNoticeId) {
		
		return receviceNoticeDao.loadReceviceNotice(receviceNoticeId);
	}

	public List<ReceviceNotice> loadReceviceNotices(Long[] receviceNoticeIds) {
		
		return receviceNoticeDao.loadReceviceNotices(receviceNoticeIds);
	}

	public void storeReceviceNotice(ReceviceNotice notice) {
		receviceNoticeDao.storeReceviceNotice(notice);
		
	}

	public void deleteReceviceNotice(ReceviceNotice notice) {
		receviceNoticeDao.deleteReceviceNotice(notice);
		
	}

	public void deleteReceviceNotices(Collection<ReceviceNotice> notices) {
		receviceNoticeDao.deleteReceviceNotices(notices);
		
	}

	public void disabledAllReceviceNotices(Collection<ReceviceNotice> receviceNotices) {
		for(ReceviceNotice oil:receviceNotices){
			oil.setDisabled(true);
			receviceNoticeDao.storeReceviceNotice(oil);
		}
		
	}

	public void enabledAllReceviceNotices(Collection<ReceviceNotice> receviceNotices) {
		for(ReceviceNotice oil:receviceNotices){
			oil.setDisabled(false);
			receviceNoticeDao.storeReceviceNotice(oil);
		}
		
	}
	public Integer getNumberOfUnReadNoticByUserID(Long userId) {
		return this.receviceNoticeDao.getNumberOfUnReadNoticByUserID(userId);
	}
	public List<ReceviceNotice> getAllUnReadNoticByUserID(Long userId, Date validityDate) {
		return this.receviceNoticeDao.getAllUnReadNoticByUserID(userId, validityDate);
	}
	public void unreadAllReceviceNotices(Collection<ReceviceNotice> receviceNotices) {
		for(ReceviceNotice oil:receviceNotices){
			oil.setReadStatus(ReadStatus.UNREAD);
			
			receviceNoticeDao.storeReceviceNotice(oil);
		}
		
	}
	public List LoadAllNoticeByNoticeTilteAndContent(String title, String content) {
		
		//return this.receviceNoticeDao.LoadAllNoticeByNoticeTilteAndContent(title,content);
		return null;
	}

}
