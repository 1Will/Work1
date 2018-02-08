package main.pojo;


import java.util.Date;

public class PaymentOrder  implements java.io.Serializable {
	private static final long serialVersionUID = 4076912356110674435L;
	private Long id;
	private Long version;
	private String code; //编码
	private Supplier supplier; //供应商 (供应商信息展示)  实体
	private CodeValue produceType; // 产生类型
	private PersonnelFiles paymentPersion; // 付款人

	private ContractManagement contractManagement; //合同名称
	private ProjectInfo projectInfo; // 项目
    private ContactArchives contactArchives; // 相关联系人
    private Department department ; // 部门
    private Double totalMoney = Double.valueOf(0.0D); //总金额 元
    private String fileName; // 附件名称
    private String remark ; // 备注
    
    private String position; // 附件位置
    private String isSaved; // 是否保存
	private boolean disabled; //失效
	private String creator; 
	private String lastOperator; 
	private Date  createdTime; 
	private Date  lastModifiedTime; 
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

	public ContractManagement getContractManagement() {
		return contractManagement;
	}
	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}
	public ContactArchives getContactArchives() {
		return contactArchives;
	}
	public void setContactArchives(ContactArchives contactArchives) {
		this.contactArchives = contactArchives;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public CodeValue getProduceType() {
		return produceType;
	}
	public void setProduceType(CodeValue produceType) {
		this.produceType = produceType;
	}
	public PersonnelFiles getPaymentPersion() {
		return paymentPersion;
	}
	public void setPaymentPersion(PersonnelFiles paymentPersion) {
		this.paymentPersion = paymentPersion;
	}
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
	
	
}
