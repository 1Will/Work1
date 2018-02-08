package main.service.impl;

import java.util.List;

import main.dao.ReplyBackVisitDao;
import main.pojo.ReplyBackVisit;
import main.service.ReplyBackVisitService;

import org.hibernate.Session;

public class ReplyBackVisitServiceImpl implements ReplyBackVisitService {
	
	private ReplyBackVisitDao replyBackVisitDao;

	public ReplyBackVisitDao getReplyBackVisitDao() {
		return replyBackVisitDao;
	}

	public void setReplyBackVisitDao(ReplyBackVisitDao replyBackVisitDao) {
		this.replyBackVisitDao = replyBackVisitDao;
	}

	public void saveReplyBackVisit(ReplyBackVisit replyBackVisit) {
		replyBackVisitDao.saveReplyBackVisit(replyBackVisit);
		
	}

	public  List<ReplyBackVisit> getReplyBackVisitById(int id){
		return replyBackVisitDao.getReplyBackVisitById(id);
	}

	
	public Session getSuperSession() {
		return replyBackVisitDao.getSuperSession();
	}

}
