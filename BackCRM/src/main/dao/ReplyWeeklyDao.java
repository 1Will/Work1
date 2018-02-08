package main.dao;

import java.util.List;

import main.pojo.ReplyWeekly;

import org.hibernate.Session;


public interface ReplyWeeklyDao {

	public void saveReplyWeekly(ReplyWeekly replyWeekly);
	public  List<ReplyWeekly> getReplyWeeklyById(Long id);
	public Session getSuperSession();

}
