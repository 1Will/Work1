package main.service;

import java.util.List;

import main.pojo.ReplyBackVisit;

import org.hibernate.Session;

public interface ReplyBackVisitService {

	public void saveReplyBackVisit(ReplyBackVisit replyBackVisit);

	public  List<ReplyBackVisit> getReplyBackVisitById(int id);

	public Session getSuperSession();
	
	

}
