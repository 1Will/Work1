package main.pojo;

import java.util.Date;


public class Duty{
    private Long id; //not null
    private Long version; //not null
    private String code; //not null
    private String name; //not null 名称
    private Long jobName; //not null 工作名称
    private Long dept; //not null 部门编号 对应部门表 
    private Integer disabled; //not null
    private Date createdTime; 
    private String creator; 
    private Date lastModifiedTime; 
    private String lastOperator; 
    private Long organization; // 5永君科技 
    private Long perType; // 员工类型 
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getJobName() {
		return jobName;
	}
	public void setJobName(Long jobName) {
		this.jobName = jobName;
	}
	public Long getDept() {
		return dept;
	}
	public void setDept(Long dept) {
		this.dept = dept;
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
	public Long getPerType() {
		return perType;
	}
	public void setPerType(Long perType) {
		this.perType = perType;
	}
	
    
    
}