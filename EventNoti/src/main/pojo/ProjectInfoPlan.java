package main.pojo;

import java.util.Date;

public class ProjectInfoPlan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private ProjectInfo projectInfo;//项目
	private String name;//任务名称
	private Date startDate;//预计开始时间
	private Date endDate;//预计结束时间
	private PersonnelFiles personnelFiles;//责任人
	private String assist;//协助者
	private String outline;//主要说明
	private int percentt;//百分比
	private int priority;//优先级

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProjectInfoPlan)) {
			return false;
		}

		ProjectInfoPlan contact = (ProjectInfoPlan) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return 0;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public String getAssist() {
		return assist;
	}

	public void setAssist(String assist) {
		this.assist = assist;
	}

	public int getPercentt() {
		return percentt;
	}

	public void setPercentt(int percentt) {
		this.percentt = percentt;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
