package main.pojo;

import java.util.Date;


public class FinancialManagement implements java.io.Serializable {
	private static final long serialVersionUID = -6500861920079420052L;
	private Long id;  
	private Long version;  
	private String code; // 收款单编码
	private ContractManagement contractManagement; //	合同
	private CustomerInfo customerInfo; //客户名称
	private PersonnelFiles saleman;	// 业务员
	private CodeValue collectionType;	//收款类型 
	private CodeValue payment; // 付款方式
	private String accountNumber; //  银行账号 
	private String accountName; // 银行名称
	private CodeValue batch; //  批次 
	private Double sumReceivable = Double.valueOf(0.0D); // 应付金额 

	private Double trueSum = Double.valueOf(0.0D); //实收金额 

	private Double totalSum = Double.valueOf(0.0D); //累计已收金额 

	private Double withoutGotSum = Double.valueOf(0.0D); //累计未收金额
	private String invoice; //发票   0-已开  1-未开
	private String invoiceCode; // 发票编码
	private Date collectionDate; // 收款日期 
	private PersonnelFiles payee; //  收款人
	private Department dept; // 部门
	private String remark; // 备注 
	private String isSaved = "0"; //  是否提交
	
	private boolean disabled;
	private Date createdTime;
	private String creator;
	private Date lastModifiedTime;
	private String lastOperator;
	
	private Long submitNum;
	private Double lastSubmitMoney;
	

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public CodeValue getBatch() {
		return this.batch;
	}

	public void setBatch(CodeValue batch) {
		this.batch = batch;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCollectionDate() {
		return this.collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public CodeValue getCollectionType() {
		return this.collectionType;
	}

	public void setCollectionType(CodeValue collectionType) {
		this.collectionType = collectionType;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Department getDept() {
		return this.dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public PersonnelFiles getPayee() {
		return this.payee;
	}

	public void setPayee(PersonnelFiles payee) {
		this.payee = payee;
	}

	public CodeValue getPayment() {
		return this.payment;
	}

	public void setPayment(CodeValue payment) {
		this.payment = payment;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PersonnelFiles getSaleman() {
		return this.saleman;
	}

	public void setSaleman(PersonnelFiles saleman) {
		this.saleman = saleman;
	}

	public Double getSumReceivable() {
		return this.sumReceivable;
	}

	public void setSumReceivable(Double sumReceivable) {
		this.sumReceivable = sumReceivable;
	}

	public Double getTotalSum() {
		return this.totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	public Double getTrueSum() {
		return this.trueSum;
	}

	public void setTrueSum(Double trueSum) {
		this.trueSum = trueSum;
	}

	public Double getWithoutGotSum() {
		return this.withoutGotSum;
	}

	public void setWithoutGotSum(Double withoutGotSum) {
		this.withoutGotSum = withoutGotSum;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public ContractManagement getContractManagement() {
		return this.contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
