package main.pojo;


import java.util.Date;

public class BillingRecord  implements java.io.Serializable {
	private static final long serialVersionUID = 4076912356110674435L;
	private Long id;
	private Long version;
	private String code; //����
	private PersonnelFiles payee; // ��Ʊ��
	private CustomerInfo customerInfo; // �ͻ�
    private ContractManagement contractManagement; //��ͬ����
    private ContactArchives contactArchives; // �����ϵ��
    private String invoiceTitle; // ��Ʊ̧ͷ 
    private Date billingTime; // ��Ʊʱ��
    private Double sum = Double.valueOf(0.0D); //���
	private String currency; // ��ע
    private String content; // ��Ʊ���� 
	private boolean disabled; //ʧЧ
	private String creator; 
	private String lastOperator; 
	private Date  createdTime; 
	private Date  lastModifiedTime; 
	private Long submitNum;
	private Double lastSubmitMoney;
	
	
	
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
	public PersonnelFiles getPayee() {
		return payee;
	}
	public void setPayee(PersonnelFiles payee) {
		this.payee = payee;
	}
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
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
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public Date getBillingTime() {
		return billingTime;
	}
	public void setBillingTime(Date billingTime) {
		this.billingTime = billingTime;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Long getSubmitNum() {
		return submitNum;
	}
	public void setSubmitNum(Long submitNum) {
		this.submitNum = submitNum;
	}
	public Double getLastSubmitMoney() {
		return lastSubmitMoney;
	}
	public void setLastSubmitMoney(Double lastSubmitMoney) {
		this.lastSubmitMoney = lastSubmitMoney;
	}
	
	
	
	
	
	
}
