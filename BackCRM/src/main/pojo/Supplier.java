package main.pojo;


import java.util.Date;

public class Supplier  implements java.io.Serializable {
	private static final long serialVersionUID = 4076912356110674435L;
    
	private Long id;
	private Long version;
	
	private String name; // 供应商名称（中文）
    private String enName; //供应商名称（英文
    private String supplierNo;
    private CodeValue supplierType; //供应商类别
    private CodeValue tradeType; // 行业
    private CodeValue companyType;  // 公司性质
    private String legalPerson; // 法人代表
    private Area country;  // 国家
    private Area province;
    private Area city;
    private Double registeredFunds = Double.valueOf(0.0D); // 注册资金（万元） 0
   private Integer employeeNum = Integer.valueOf(0); // 员工人数 0
   private String managingScope;  // 经营范围： bd
   private String maorContact;  // 主要联系人
   private String phone;
   private String mobile; // 手机
   private String fex;
   private String email;
   private String qq;
   private String homeSite;
   private Date createDate;  // 创立时间
   private String postCode;
   private String address;  // 地址
   private String businessLicense;
   private String taxNo;
   private String turnover;
   private String bank;
   private String bankName;
   private String bankAccount;
   private String afterSaleService;
   private String qos;
   
  	private boolean disabled; //失效
  	private String creator; 
  	private String lastOperator; 
  	private Date  createdTime; 
  	private Date  lastModifiedTime; 
	
    public boolean equals(Object o)
	  {
    if (o == this) return true;
	if (!(o instanceof Supplier)) return false;
	    Supplier supplier = (Supplier)o;
    if (!this.supplierNo.equals(supplier.getSupplierNo())) return false;
	    return true;
	  }
	 
    public int hashCode()
    {
    return this.supplierNo.hashCode();
    }
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public CodeValue getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(CodeValue supplierType) {
		this.supplierType = supplierType;
	}

	public CodeValue getTradeType() {
		return tradeType;
	}

	public void setTradeType(CodeValue tradeType) {
		this.tradeType = tradeType;
	}

	public CodeValue getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CodeValue companyType) {
		this.companyType = companyType;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public Area getCountry() {
		return country;
	}

	public void setCountry(Area country) {
		this.country = country;
	}

	public Area getProvince() {
		return province;
	}

	public void setProvince(Area province) {
		this.province = province;
	}

	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public Double getRegisteredFunds() {
		return registeredFunds;
	}

	public void setRegisteredFunds(Double registeredFunds) {
		this.registeredFunds = registeredFunds;
	}

	public Integer getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getManagingScope() {
		return managingScope;
	}

	public void setManagingScope(String managingScope) {
		this.managingScope = managingScope;
	}

	public String getMaorContact() {
		return maorContact;
	}

	public void setMaorContact(String maorContact) {
		this.maorContact = maorContact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFex() {
		return fex;
	}

	public void setFex(String fex) {
		this.fex = fex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getHomeSite() {
		return homeSite;
	}

	public void setHomeSite(String homeSite) {
		this.homeSite = homeSite;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAfterSaleService() {
		return afterSaleService;
	}

	public void setAfterSaleService(String afterSaleService) {
		this.afterSaleService = afterSaleService;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
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
	
	
	
	
	
}
