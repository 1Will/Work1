package main.pojo;

import java.util.Date;

public class BackVisit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long version;
	private int disabled;
	private String customerName;
	private String contactname;
	private Long customerid;
	private Long contactid;
	private Date visitDate;
	private String visitor; //�ط���
	private Long visiterid;
	private String visitContent;
	private String feedback;
	private String attention;
	private String remarks;
	private Date createtime;
	private String projectName;
	private Long projectinfoid;
	
	private CodeValue backvisittype;
	
	private Long visittype;
	private Long customerStating;
	
	private String isPublic;
	private String contactArchives;
	private String creator;
	private String continuebackvisit;
	private CodeValue importanceType;
	private Daily daily;
	
	private Date nextVisitDate; //�´λط�����
	private Date lastModifiedTime; //����޶�ʱ��
	private String lastOperator; //����޶���
	
	private Long customerSteping; //�ͻ��ȼ�    339 һ��  340  342
	private Long customerSteped; //�ͻ��ȼ�   ���Կͻ��� step
	private String employees; //�ط���ͬ����
	private String isSaved; //0  �Ƿ�ʧЧ
	private Long expendTime; //��ʱ���֣�
	private Long replyTime; //��¼�ظ�����
	private Long submitNum;
	
	public Long getCustomerStating() {
		return customerStating;
	}
	public void setCustomerStating(Long customerStating) {
		this.customerStating = customerStating;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getContactArchives() {
		return contactArchives;
	}
	public void setContactArchives(String contactArchives) {
		this.contactArchives = contactArchives;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	public Long getContactid() {
		return contactid;
	}
	public void setContactid(Long contactid) {
		this.contactid = contactid;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	
	public String getVisitor() {
		return visitor;
	}
	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}
	public Long getVisiterid() {
		return visiterid;
	}
	public void setVisiterid(Long visiterid) {
		this.visiterid = visiterid;
	}
	public String getVisitContent() {
		return visitContent;
	}
	public void setVisitContent(String visitContent) {
		this.visitContent = visitContent;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Long getProjectinfoid() {
		return projectinfoid;
	}
	public void setProjectinfoid(Long projectinfoid) {
		this.projectinfoid = projectinfoid;
	}
	
	public CodeValue getBackvisittype() {
		return backvisittype;
	}
	public void setBackvisittype(CodeValue backvisittype) {
		this.backvisittype = backvisittype;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	public Long getVisittype() {
		return visittype;
	}
	public void setVisittype(Long visittype) {
		this.visittype = visittype;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getContinuebackvisit() {
		return continuebackvisit;
	}
	public void setContinuebackvisit(String continuebackvisit) {
		this.continuebackvisit = continuebackvisit;
	}
	public Daily getDaily() {
		return daily;
	}
	public void setDaily(Daily daily) {
		this.daily = daily;
	}
	public Date getNextVisitDate() {
		return nextVisitDate;
	}
	public void setNextVisitDate(Date nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
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
	public Long getCustomerSteping() {
		return customerSteping;
	}
	public void setCustomerSteping(Long customerSteping) {
		this.customerSteping = customerSteping;
	}
	public String getEmployees() {
		return employees;
	}
	public void setEmployees(String employees) {
		this.employees = employees;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	
	public Long getExpendTime() {
		return expendTime;
	}
	public void setExpendTime(Long expendTime) {
		this.expendTime = expendTime;
	}
	public Long getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Long replyTime) {
		this.replyTime = replyTime;
	}
	public Long getCustomerSteped() {
		return customerSteped;
	}
	public void setCustomerSteped(Long customerSteped) {
		this.customerSteped = customerSteped;
	}
	public CodeValue getImportanceType() {
		return importanceType;
	}
	public void setImportanceType(CodeValue importanceType) {
		this.importanceType = importanceType;
	}
	public Long getSubmitNum() {
		return submitNum;
	}
	public void setSubmitNum(Long submitNum) {
		this.submitNum = submitNum;
	}
	
	
    
	
	
	
}