package main.pojo;


public class News implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String eventName; //�¼�����
	private String url; // �¼�����
    private Long time; // �鿴����
    private Long userid; 
    private String currentDate; //��ǰ����
    private String isRead; // 0 ��ʾδ�Ķ�  1 ��ʾ���Ķ�
    private String mainContent; //��Ҫ����ժҪ
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getMainContent() {
		return mainContent;
	}
	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}
	
	
	
    
    
}