package main.pojo;
import java.util.Date;

public class ReplyWeekly implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Weekly weeklyId;                          
	private UsersInfo userid;
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

	
	
	public Weekly getWeeklyId() {
		return weeklyId;
	}

	public void setWeeklyId(Weekly weeklyId) {
		this.weeklyId = weeklyId;
	}

	public UsersInfo getUserid() {
		return userid;
	}

	public void setUserid(UsersInfo userid) {
		this.userid = userid;
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

	
	
	



}