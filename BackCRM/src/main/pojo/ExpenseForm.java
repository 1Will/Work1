package main.pojo;


import java.util.Date;



public class ExpenseForm  implements java.io.Serializable {
	private static final long serialVersionUID = 4076912356110674435L;
	private Long id;
	private Long version;
	
	private String code;
	private ProjectInfo projectInfo;
	private PersonnelFiles applyPeople;
	private ContractManagement contractManagement;
	private Double money = Double.valueOf(0.0D);// 报销金额
	private Date applyDate;
	private String remark;  //备注
	private Integer formNum;//单据数量
	private Integer attachmentNum;//附件数量
	private String isSaved ;//提交判断
	private Date createdTime;
	private String creator;
	private Date lastModifiedTime;
	private String lastOperator;
	private boolean disabled;
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	public PersonnelFiles getApplyPeople() {
		return applyPeople;
	}
	public void setApplyPeople(PersonnelFiles applyPeople) {
		this.applyPeople = applyPeople;
	}
	public ContractManagement getContractManagement() {
		return contractManagement;
	}
	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Integer getFormNum() {
		return formNum;
	}
	public void setFormNum(Integer formNum) {
		this.formNum = formNum;
	}
	public Integer getAttachmentNum() {
		return attachmentNum;
	}
	public void setAttachmentNum(Integer attachmentNum) {
		this.attachmentNum = attachmentNum;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
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
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

   
	
	
	/*	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof Products)) {
			return false;
		}

		Products p = (Products) arg0;

		if (!p.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	
	public int hashCode() {
		return getId().hashCode();
	}    */

	
	
	
	
	
	
	
}
