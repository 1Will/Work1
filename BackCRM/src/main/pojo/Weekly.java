package main.pojo;

import java.util.Date;


public class Weekly implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long version;  //not null
    private String code;  //编号  hjia_2011-03_01  not null
    private String weeklyName;  //几月第几周  not null
    private Date startDate;  //计划开始日期  not null
    private Date endDate;  //计划结束日期  not null
    private String leaderIdea;  //领导意见  
    private String summary;  //总结  
    private String comment;  //评论意见
    private Long rapporteurId;  //填写人 ID user表  not null
    private Long personId;  //personId来自人事表
    private Long instId;  //制度表 id 14  not null
    private Long deptId;  //部门ID 部门表  not null
    private Long dutyId;  //职位ID 职位表  not null
 //   private Long weekly;  //周报id 周报表   not null 
    private Integer disabled;  //失效  not null
    private Date createdTime;  //创建时间
    private String creator;  //创建人 user表
    private Date lastModifiedTime;  //最后修改时间
    private String lastOperator;  //最后修改人
    private Long organization;  //组织ID 5
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