package com.yongjun.tdms.model.workReport.weekly;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.security.User;

public class ReplyWeekly extends BaseInfoEntity {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Weekly weekly;                          
	private User user;
	private String content;
	private String username;
	private Date replydate;
   
	public ReplyWeekly() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Weekly getWeekly() {
		return weekly;
	}
	
	public void setWeekly(Weekly weekly) {
		this.weekly = weekly;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getReplydate() {
		return replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}

	@Override
	public boolean equals(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	



}