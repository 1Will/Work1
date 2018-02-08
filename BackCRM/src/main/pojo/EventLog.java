package main.pojo;


public class EventLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long Id;
	private String name;
	private EventType eventType;
	private String moreinfo;
	
	private String userid;
	private String effectflag;
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public String getMoreinfo() {
		return moreinfo;
	}
	public void setMoreinfo(String moreinfo) {
		this.moreinfo = moreinfo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEffectflag() {
		return effectflag;
	}
	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}
	
  
}