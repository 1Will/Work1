package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.ReplyDailyDao;
import main.dao.ReplyWeeklyDao;
import main.pojo.ReplyDaily;
import main.pojo.ReplyWeekly;
import main.service.ReplyDailyService;
import main.service.ReplyWeeklyService;

public class ReplyWeeklyServiceImpl implements ReplyWeeklyService {
    private  ReplyWeeklyDao replyWeeklyDao;

	@Override
	public void saveReplyWeekly(ReplyWeekly replyWeekly) {
		replyWeeklyDao.saveReplyWeekly(replyWeekly);
	}

	@Override
	public List<ReplyWeekly> getReplyWeeklyById(Long id) {
		return replyWeeklyDao.getReplyWeeklyById(id);
	}

	@Override
	public Session getSuperSession() {
		return replyWeeklyDao.getSuperSession();
	}

	public ReplyWeeklyDao getReplyWeeklyDao() {
		return replyWeeklyDao;
	}

	public void setReplyWeeklyDao(ReplyWeeklyDao replyWeeklyDao) {
		this.replyWeeklyDao = replyWeeklyDao;
	}
    
	
	
	
}
