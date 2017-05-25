package main.pojo;

import java.util.Date;


public class ProjectInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String projectName;// ��Ŀ����
	private String code;// ��Ŀ���
	private String outline;// ��Ŀ��Ҫ
    
	private String creatorName;//��Ŀ������
	private Long creatorId;//������Id
	private Long customerId;//�ͻ�id
	private Long contactId;//��Ŀ��ϵ��id 
	private Long controllerId;//��Ŀ������id
	private String conOutline;//��ϵ�˽�ɫ˵��
	private Long businessType;//ҵ������:ʹ���� ������ ���� 521 522 524

	private Date createdTime; //��������
	private String lastOperator; //��������
	private Date lastModifiedTime; //����޸�ʱ�� 
    private Long state; //��Ŀ״̬����
    private Long version; //��Ŀ�汾
    private Integer disabled; //��Ŀ״̬����

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
    

	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public Long getControllerId() {
		return controllerId;
	}
	public void setControllerId(Long controllerId) {
		this.controllerId = controllerId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public String getConOutline() {
		return conOutline;
	}
	public void setConOutline(String conOutline) {
		this.conOutline = conOutline;
	}
	public Long getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Long businessType) {
		this.businessType = businessType;
	}
	
    

}