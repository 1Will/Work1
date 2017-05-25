package main.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Daily implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long version;  //not null
    private Date currentDate;  //��ǰ����  not null
    private String weekDate;  //��¼����  not null
    private Date startTime;  //�ƻ���ʼʱ��  not null
    private Date endTime;  //�ƻ�����ʱ��  not null
    private String backvisitContext;//�ݷù�������
    private String workContext;  //������������  not null
    private String leaderIdea;  //�쵼���  
    private String questions;  //ԭ��
    private String solutions;  //�������
    private String tomorrowPlan;  //���հ���
    private String comment;  //��ע
    private Long rapporteurId;  //��д�� ID user��  not null
    private Long personId;  //personId�������±�
    private Long instId;  //�ƶȱ� id 14  not null
    private Long deptId;  //����ID ���ű�  not null
    private Long dutyId;  //ְλID ְλ��  not null
    private Long weeklyId;  //�ܱ�id �ܱ���   
    private Integer disabled;  //ʧЧ  not null
    private Date createdTime;  //����ʱ��
    private String creator;  //������ user��
    private Date lastModifiedTime;  //����޸�ʱ��
    private String lastOperator;  //����޸���
    private Long organization;  //��֯ID 5
    public Set bvtList =new HashSet();
    
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
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getWeekDate() {
		return weekDate;
	}
	public void setWeekDate(String weekDate) {
		this.weekDate = weekDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getLeaderIdea() {
		return leaderIdea;
	}
	public void setLeaderIdea(String leaderIdea) {
		this.leaderIdea = leaderIdea;
	}
	public String getWorkContext() {
		return workContext;
	}
	public void setWorkContext(String workContext) {
		this.workContext = workContext;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getSolutions() {
		return solutions;
	}
	public void setSolutions(String solutions) {
		this.solutions = solutions;
	}
	public String getTomorrowPlan() {
		return tomorrowPlan;
	}
	public void setTomorrowPlan(String tomorrowPlan) {
		this.tomorrowPlan = tomorrowPlan;
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
	public Long getWeeklyId() {
		return weeklyId;
	}
	public void setWeeklyId(Long weeklyId) {
		this.weeklyId = weeklyId;
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
	public String getBackvisitContext() {
		return backvisitContext;
	}
	public void setBackvisitContext(String backvisitContext) {
		this.backvisitContext = backvisitContext;
	}
	public Set getBvtList() {
		return bvtList;
	}
	public void setBvtList(Set bvtList) {
		this.bvtList = bvtList;
	}
    
	
    
    
    
}