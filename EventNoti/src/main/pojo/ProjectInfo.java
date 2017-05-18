package main.pojo;

import java.util.Date;


public class ProjectInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String projectName;// 项目名称
	private String code;// 项目编号
	private String outline;// 项目概要

	private Long customerinfoid;

	private String creatorName; //项目负责人
	private Date createdTime; //创建日期
	private String lastOperator; //最后跟进人
	private Date lastModifiedTime; //最后修改时间 
    private Long state; //项目状态代码
    private Long version; //项目版本
    private Integer disabled; //项目状态代码

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

	public Long getCustomerinfoid() {
		return customerinfoid;
	}
	public void setCustomerinfoid(Long customerinfoid) {
		this.customerinfoid = customerinfoid;
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
	
    

}