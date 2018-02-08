package main.pojo;


import java.util.Date;

public class PaymentOrder  implements java.io.Serializable {
	private static final long serialVersionUID = 4076912356110674435L;
	private Long id;
	private Long version;
	private String code; //����
	private Supplier supplier; //��Ӧ�� (��Ӧ����Ϣչʾ)  ʵ��
	private CodeValue produceType; // ��������
	private PersonnelFiles paymentPersion; // ������

	private ContractManagement contractManagement; //��ͬ����
	private ProjectInfo projectInfo; // ��Ŀ
    private ContactArchives contactArchives; // �����ϵ��
    private Department department ; // ����
    private Double totalMoney = Double.valueOf(0.0D); //�ܽ�� Ԫ
    private String fileName; // ��������
    private String remark ; // ��ע
    
    private String position; // ����λ��
    private String isSaved; // �Ƿ񱣴�
	private boolean disabled; //ʧЧ
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
