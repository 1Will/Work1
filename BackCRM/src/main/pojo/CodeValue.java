package main.pojo;

import java.util.Date;


public class CodeValue{
	private static final long serialVersionUID = 1L;
    private Long id; 
    private String name; //��Ӧ�ֶ�
    private Long cvId; //��Ӧ��ϢId
    private Long version; //�汾
    private String code; //���
    private Integer disabled ; //ʧЧ
    private Date createdTime ; //����ʱ��
    private String creator; //������
    private Date lastModifiedTime; //����޸�ʱ��
	
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public Long getCvId() {
		return cvId;
	}
	public void setCvId(Long cvId) {
		this.cvId = cvId;
	}
    
	
    
}