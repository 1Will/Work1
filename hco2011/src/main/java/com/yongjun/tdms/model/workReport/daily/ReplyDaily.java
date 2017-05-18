package com.yongjun.tdms.model.workReport.daily;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.security.User;

public class ReplyDaily extends BaseInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private User user;
	private String userName;
	private String content;
	private Date replyDate;
	private Daily daily;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public Daily getDaily() {
		return daily;
	}
	public void setDaily(Daily daily) {
		this.daily = daily;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean equals(Object paramObject) {
		// TODO Auto-generated method stub
		return false;
	}

	
}