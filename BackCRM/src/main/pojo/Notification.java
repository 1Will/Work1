package main.pojo;

import java.util.Date;


public class Notification implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long receiver;
	private Long sender;
	private String haveRead;
	private Long eventType_id;
	private Date sendTime;
	private String effectflag;
	
	public Notification() {
			
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public String getHaveRead() {
		return haveRead;
	}

	public void setHaveRead(String haveRead) {
		this.haveRead = haveRead;
	}

	public Long getEventType_id() {
		return eventType_id;
	}

	public void setEventType_id(Long eventType_id) {
		this.eventType_id = eventType_id;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getEffectflag() {
		return effectflag;
	}

	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}

}