package main.pojo;
import java.util.Date;

public class ReplyBackVisit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long visitid;
	private Long userid;
	private String content;
	private String username;
	private Date raplydate;
    public ReplyBackVisit() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVisitid() {
		return visitid;
	}

	public void setVisitid(Long visitid) {
		this.visitid = visitid;
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

	public Date getRaplydate() {
		return raplydate;
	}

	public void setRaplydate(Date raplydate) {
		this.raplydate = raplydate;
	}

	
	



}