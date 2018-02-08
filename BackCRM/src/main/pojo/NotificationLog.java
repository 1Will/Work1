package main.pojo;


public class NotificationLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long notificationId;
	private String effectflag;
	
    public NotificationLog() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEffectflag() {
		return effectflag;
	}

	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	

}