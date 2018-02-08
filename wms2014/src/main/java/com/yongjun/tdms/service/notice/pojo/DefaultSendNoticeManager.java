package com.yongjun.tdms.service.notice.pojo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.dao.notice.ReceviceNoticeDao;
import com.yongjun.pluto.dao.notice.SendNoticeDao;
import com.yongjun.pluto.dao.security.UserDao;
import com.yongjun.pluto.model.notice.SendNotice;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.model.notice.Notice;
import com.yongjun.tdms.service.notice.SendNoticeManager;


public class DefaultSendNoticeManager extends BaseManager implements SendNoticeManager{
   private final SendNoticeDao sendNoticeDao;
   private final UserDao  userDao;
   private final ReceviceNoticeDao receviceNoticeDao;
  public  DefaultSendNoticeManager(SendNoticeDao sendNoticeDao,
		  UserDao userDao, ReceviceNoticeDao receviceNoticeDao){
	  this.sendNoticeDao=sendNoticeDao;
	  this.userDao=userDao;
	  this.receviceNoticeDao = receviceNoticeDao;
  }
	public List<SendNotice> loadSendNotices(Long[] sendNoticeIds) {
		
		return sendNoticeDao.loadSendNotices(sendNoticeIds);
	}

	public void storeSendNotice(SendNotice notice) {
		sendNoticeDao.storeSendNotice(notice);
		
	}

	public void deleteSendNotice(SendNotice notice) {
		sendNoticeDao.deleteSendNotice(notice);
		
	}

	public void deleteSendNotices(Collection<SendNotice> notices) {
		for (SendNotice notice : notices) {
			//sendNoticeDao.deleteSendNotice(notice);
			receviceNoticeDao.deleteReceviceNotices(notice.getReceviceNotices());
			
		}
		sendNoticeDao.deleteSendNotices(notices);
	}
    public SendNotice loadSendNotice(Long sendNoticeId) {
		
		return sendNoticeDao.loadSendNotice(sendNoticeId);
	}
	public void joinUsersForSend(String[] userIds, SendNotice notice) {

		Set<User> deleteUsers = new HashSet<User>(); 
		deleteUsers = notice.getUsers();
//		for (User user : deleteUsers) {
//			notice.getUsers().remove(user);
//		}
		notice.getUsers().removeAll(deleteUsers);

		for (int i=0; i<userIds.length; i++) {
			User user = this.userDao.loadUser(Long.valueOf(userIds[i]));
			notice.getUsers().add(user);
		}
		this.sendNoticeDao.storeSendNotice(notice);
	}
	public void disabledAllSendNotices(Collection<SendNotice> sendNotices) {
		for(SendNotice oil:sendNotices){
			oil.setDisabled(true);
			sendNoticeDao.storeSendNotice(oil);
		}
	}

	public void enabledAllSendNotices(Collection<SendNotice> sendNotices) {
		for(SendNotice oil:sendNotices){
			oil.setDisabled(false);
			sendNoticeDao.storeSendNotice(oil);
		}
		
	}
	public SendNotice getNoticeByNoticeTilteAndContent(String title, String content) {
		return null;
		
	}
	
	

}
