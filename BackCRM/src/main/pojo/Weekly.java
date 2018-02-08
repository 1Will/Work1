package main.pojo;

import java.util.Date;


public class Weekly implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long version;  //not null
    private String code;  //���  hjia_2011-03_01  not null
    private String weeklyName;  //���µڼ���  not null
    private Date startDate;  //�ƻ���ʼ����  not null
    private Date endDate;  //�ƻ���������  not null
    private String leaderIdea;  //�쵼���  
    private String summary;  //�ܽ�  
    private String comment;  //�������
    private Long rapporteurId;  //��д�� ID user��  not null
    private Long personId;  //personId�������±�
    private Long instId;  //�ƶȱ� id 14  not null
    private Long deptId;  //����ID ���ű�  not null
    private Long dutyId;  //ְλID ְλ��  not null
 //   private Long weekly;  //�ܱ�id �ܱ���   not null 
    private Integer disabled;  //ʧЧ  not null
    private Date createdTime;  //����ʱ��
    private String creator;  //������ user��
    private Date lastModifiedTime;  //����޸�ʱ��
    private String lastOperator;  //����޸���
    private Long organization;  //��֯ID 5
    private String isSaved;
    private Long submitNum; 
    private Week weekId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLeaderIdea() {
		return leaderIdea;
	}
	public void setLeaderIdea(String leaderIdea) {
		this.leaderIdea = leaderIdea;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
    
	
	public Long getRapporteurId() {
		return rapporteurId;
	}
	public void setRapporteurId(Long rapporteurId) {
		this.rapporteurId = rapporteurId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getInstId() {
		return instId;
	}
	public void setInstId(Long instId) {
		this.instId = instId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getDutyId() {
		return dutyId;
	}
	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
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
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public Long getOrganization() {
		return organization;
	}
	public void setOrganization(Long organization) {
		this.organization = organization;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getWeeklyName() {
		return weeklyName;
	}
	public void setWeeklyName(String weeklyName) {
		this.weeklyName = weeklyName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public Long getSubmitNum() {
		return submitNum;
	}
	public void setSubmitNum(Long submitNum) {
		this.submitNum = submitNum;
	}
	public Week getWeekId() {
		return weekId;
	}
	public void setWeekId(Week weekId) {
		this.weekId = weekId;
	}
    
	
    
    
    
}