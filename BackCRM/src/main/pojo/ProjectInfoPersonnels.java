package main.pojo;

import java.util.Date;


public class ProjectInfoPersonnels implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long projectInfo_id;
	private Long proPerson_id; //��Ŀ��Աid ���±�
	private Long version; //�汾version
	private String outline; // ��Ŀ��ҵ������˵��
	private Date createdTime; //����ʱ��
	private String creator; //������
	private Date lastModifiedTime; //����޸�ʱ��
	private String lastOperator; //����޸���
	private Long businessType; //��Ŀҵ��
	private Integer disabled; 
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectInfo_id() {
		return projectInfo_id;
	}
	public void setProjectInfo_id(Long projectInfo_id) {
		this.projectInfo_id = projectInfo_id;
	}
	public Long getProPerson_id() {
		return proPerson_id;
	}
	public void setProPerson_id(Long proPerson_id) {
		this.proPerson_id = proPerson_id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
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
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public Long getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Long businessType) {
		this.businessType = businessType;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	
	
	
	
}