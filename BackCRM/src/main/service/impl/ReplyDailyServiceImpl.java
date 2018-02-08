package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.ReplyDailyDao;
import main.pojo.ReplyDaily;
import main.service.ReplyDailyService;

public class ReplyDailyServiceImpl implements ReplyDailyService {
    private ReplyDailyDao replyDailyDao;
	@Override
	public void saveReplyDaily(ReplyDaily replyDaily) {
        this.replyDailyDao.saveReplyDaily(replyDaily);		
	}

	@Override
	public List<ReplyDaily> getReplyDailyById(Long id) {
		return replyDailyDao.getReplyDailyById(id);
	}

	@Override
	public Session getSuperSession() {
		return replyDailyDao.getSuperSession();
	}

	
	public ReplyDailyDao getReplyDailyDao() {
		return replyDailyDao;
	}

	public void setReplyDailyDao(ReplyDailyDao replyDailyDao) {
		this.replyDailyDao = replyDailyDao;
	}

	
	
}
