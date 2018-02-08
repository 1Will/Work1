package com.yongjun.tdms.model.backvisit;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.workReport.daily.Daily;

public class BackVisit extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking,
		LastModifiedTimeTracking {
	private static final long serialVersionUID = 1L;
	private CodeValue backVisitType;
	private String customerName;
	private CustomerInfo customerInfo;
	private String caName;
	private ContactArchives contactArchive;
	private Date backVisitDate;
	private Double costTime = Double.valueOf(0.0D);
	private CodeValue backVisitWay;
	private String backVisiter;
	private PersonnelFiles employee;
	private String continueBackVisit;
	private Date nextBackVisitDate;
	private String backVisitContent;
	private String feedback;
	private String attention;
	private String remarks;

	private String gradeChang;
	private CodeValue customerSteping;
	private CodeValue customerSteped;
	private CodeValue customerStating;
	private CodeValue customerStated;
	private CodeValue importanceType;
	private String changStateReason;
	private String changReason;
	private String contactArchives;
	private String employees;
	private String isPublic;
	private String isSaved;// 存在并且等于0，，方可提交
	private long submitNum = 0;
	private ProjectInfo projectInfo;
	private String projectName;
	private Daily daily;
	private Long replyTime;
	private String employeesIds;
	private String contactArchiveIds;

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public CodeValue getCustomerSteped() {
		return this.customerSteped;
	}

	public void setCustomerSteped(CodeValue customerSteped) {
		this.customerSteped = customerSteped;
	}

	public CodeValue getCustomerSteping() {
		return this.customerSteping;
	}

	public void setCustomerSteping(CodeValue customerSteping) {
		this.customerSteping = customerSteping;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BackVisit))
			return false;
		BackVisit bv = (BackVisit) obj;
		if (!bv.getId().equals(getId()))
			return false;
		return true;
	}

	public int hashCode() {
		return getId().hashCode();
	}

	public String getAttention() {
		return this.attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getBackVisitContent() {
		return this.backVisitContent;
	}

	public void setBackVisitContent(String backVisitContent) {
		this.backVisitContent = backVisitContent;
	}

	public Date getBackVisitDate() {
		return this.backVisitDate;
	}

	public void setBackVisitDate(Date backVisitDate) {
		this.backVisitDate = backVisitDate;
	}

	public String getBackVisiter() {
		return this.backVisiter;
	}

	public void setBackVisiter(String backVisiter) {
		this.backVisiter = backVisiter;
	}

	public CodeValue getBackVisitWay() {
		return this.backVisitWay;
	}

	public void setBackVisitWay(CodeValue backVisitWay) {
		this.backVisitWay = backVisitWay;
	}

	public String getCaName() {
		return this.caName;
	}

	public void setCaName(String caName) {
		this.caName = caName;
	}

	public ContactArchives getContactArchive() {
		return this.contactArchive;
	}

	public void setContactArchive(ContactArchives contactArchive) {
		this.contactArchive = contactArchive;
	}

	public String getContinueBackVisit() {
		return this.continueBackVisit;
	}

	public void setContinueBackVisit(String continueBackVisit) {
		this.continueBackVisit = continueBackVisit;
	}

	public Double getCostTime() {
		return this.costTime;
	}

	public void setCostTime(Double costTime) {
		this.costTime = costTime;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public PersonnelFiles getEmployee() {
		return this.employee;
	}

	public void setEmployee(PersonnelFiles employee) {
		this.employee = employee;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getNextBackVisitDate() {
		return this.nextBackVisitDate;
	}

	public void setNextBackVisitDate(Date nextBackVisitDate) {
		this.nextBackVisitDate = nextBackVisitDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public CodeValue getBackVisitType() {
		return this.backVisitType;
	}

	public void setBackVisitType(CodeValue backVisitType) {
		this.backVisitType = backVisitType;
	}

	public String getGradeChang() {
		return this.gradeChang;
	}

	public void setGradeChang(String gradeChang) {
		this.gradeChang = gradeChang;
	}

	public String getChangReason() {
		return this.changReason;
	}

	public void setChangReason(String changReason) {
		this.changReason = changReason;
	}

	public String getContactArchives() {
		return contactArchives;
	}

	public void setContactArchives(String contactArchives) {
		this.contactArchives = contactArchives;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public CodeValue getCustomerStating() {
		return customerStating;
	}

	public void setCustomerStating(CodeValue customerStating) {
		this.customerStating = customerStating;
	}

	public CodeValue getCustomerStated() {
		return customerStated;
	}

	public void setCustomerStated(CodeValue customerStated) {
		this.customerStated = customerStated;
	}

	public String getChangStateReason() {
		return changStateReason;
	}

	public void setChangStateReason(String changStateReason) {
		this.changStateReason = changStateReason;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public Daily getDaily() {
		return daily;
	}

	public void setDaily(Daily daily) {
		this.daily = daily;
	}

	public Long getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Long replyTime) {
		this.replyTime = replyTime;
	}

	public CodeValue getImportanceType() {
		return importanceType;
	}

	public void setImportanceType(CodeValue importanceType) {
		this.importanceType = importanceType;
	}

	public long getSubmitNum() {
		return submitNum;
	}

	public void setSubmitNum(long submitNum) {
		this.submitNum = submitNum;
	}

	public String getEmployeesIds() {
		return employeesIds;
	}

	public void setEmployeesIds(String employeesIds) {
		this.employeesIds = employeesIds;
	}

	public String getContactArchiveIds() {
		return contactArchiveIds;
	}

	public void setContactArchiveIds(String contactArchiveIds) {
		this.contactArchiveIds = contactArchiveIds;
	}

}
