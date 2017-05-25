package main.pojo;
import java.util.Date;

public class ReplyDaily implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long dailyid;                          
	private Long userid;
	private String content;
	private String username;
	private Date replydate;
   
	public ReplyDaily() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getDailyid() {
		return dailyid;
	}

	public void setDailyid(Long dailyid) {
		this.dailyid = dailyid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
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