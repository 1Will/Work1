package main.pojo; 

import java.util.Date;
 
public class TleaveBill {
	
	private String title;
    private String personId; 
    private Date startDate; 
    private Date endDate; 
    private String type; 
    private String betreffzeile;
    private Float mainHour;
    private String shenhe;
    private String bohui;//
    private String deptId;
    private Date createDate;
    
    private Date createdTime;
    private String creator;
    private Date lastOperatorTime;
    private String lastOperator;
    private String status;
    private int oranaration; 


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

	public String getBetreffzeile() {
		return betreffzeile;
	}
	public void setBetreffzeile(String betreffzeile) {
		this.betreffzeile = betreffzeile;
	}
	public Float getMainHour() {
		return mainHour;
	}
	public void setMainHour(Float mainHour) {
		this.mainHour = mainHour;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShenhe() {
		return shenhe;
	}
	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBohui() {
		return bohui;
	}
	public void setBohui(String bohui) {
		this.bohui = bohui;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Date getLastOperatorTime() {
		return lastOperatorTime;
	}
	public void setLastOperatorTime(Date lastOperatorTime) {
		this.lastOperatorTime = lastOperatorTime;
	}
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public int getOranaration() {
		return oranaration;
	}
	public void setOranaration(int oranaration) {
		this.oranaration = oranaration;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
	
    
	
	
} 
