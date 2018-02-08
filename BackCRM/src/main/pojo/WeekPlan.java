package main.pojo;



public class WeekPlan implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long  id;
	private Long  weeklyId;  
	private Long  projectInfoId;  
	private Week weekId;
	private UsersInfo userid;
	private String  lastPlan;
	private String  thisPlan;
	private String  nextPlan;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWeeklyId() {
		return weeklyId;
	}
	public void setWeeklyId(Long weeklyId) {
		this.weeklyId = weeklyId;
	}
	public Long getProjectInfoId() {
		return projectInfoId;
	}
	public void setProjectInfoId(Long projectInfoId) {
		this.projectInfoId = projectInfoId;
	}
	public String getLastPlan() {
		return lastPlan;
	}
	public void setLastPlan(String lastPlan) {
		this.lastPlan = lastPlan;
	}
	public String getThisPlan() {
		return thisPlan;
	}
	public void setThisPlan(String thisPlan) {
		this.thisPlan = thisPlan;
	}
	public String getNextPlan() {
		return nextPlan;
	}
	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}
	public Week getWeekId() {
		return weekId;
	}
	public void setWeekId(Week weekId) {
		this.weekId = weekId;
	}
	public UsersInfo getUserid() {
		return userid;
	}
	public void setUserid(UsersInfo userid) {
		this.userid = userid;
	}
    
	
       
    
}