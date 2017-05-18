package souvc.pojo; 

import java.util.Date;
 
/** 
 * 璇峰亣琛╩odel 
 */
public class TleaveBill {
	
	private String title;
    private String personId; 
    private Date startDate; 
    private Date endDate; 
    private String type; 
    private String betreffzeile;
    private Float mainHour;
    private String shenhe;//审核人
    private String status;//
    private String bohui;//
    private String deptId;
    private Date createDate;


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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
} 
