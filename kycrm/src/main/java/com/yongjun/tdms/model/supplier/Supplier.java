/*     */ package com.yongjun.tdms.model.supplier;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Supplier extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = -3456371158887795021L;
/*     */   private String name;
/*     */   private String enName;
/*     */   private String supplierNo;
/*     */   private CodeValue supplierType;
/*     */   private CodeValue tradeType;
/*     */   private CodeValue companyType;
/*     */   private String legalPerson;
/*     */   private Area country;
/*     */   private Area province;
/*     */   private Area city;
/*  56 */   private Double registeredFunds = Double.valueOf(0.0D);
/*     */ 
/*  59 */   private Integer employeeNum = Integer.valueOf(0);
/*     */   private String managingScope;
/*     */   private String maorContact;
/*     */   private String phone;
/*     */   private String mobile;
/*     */   private String fex;
/*     */   private String email;
/*     */   private String qq;
/*     */   private String homeSite;
/*     */   private Date createDate;
/*     */   private String postCode;
/*     */   private String address;
/*     */   private String businessLicense;
/*     */   private String taxNo;
/*     */   private String turnover;
/*     */   private String bank;
/*     */   private String bankName;
/*     */   private String bankAccount;
/*     */   private String afterSaleService;
/*     */   private String qos;
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 104 */     if (o == this) return true;
/* 105 */     if (!(o instanceof Supplier)) return false;
/* 106 */     Supplier supplier = (Supplier)o;
/* 107 */     if (!this.supplierNo.equals(supplier.getSupplierNo())) return false;
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 113 */     return this.supplierNo.hashCode();
/*     */   }
/*     */ 
/*     */   public Area getCity() {
/* 117 */     return this.city;
/*     */   }
/*     */ 
/*     */   public void setCity(Area city) {
/* 121 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public String getManagingScope() {
/* 125 */     return this.managingScope;
/*     */   }
/*     */ 
/*     */   public void setManagingScope(String managingScope) {
/* 129 */     this.managingScope = managingScope;
/*     */   }
/*     */ 
/*     */   public CodeValue getCompanyType() {
/* 133 */     return this.companyType;
/*     */   }
/*     */ 
/*     */   public void setCompanyType(CodeValue companyType) {
/* 137 */     this.companyType = companyType;
/*     */   }
/*     */ 
/*     */   public Area getCountry() {
/* 141 */     return this.country;
/*     */   }
/*     */ 
/*     */   public void setCountry(Area country) {
/* 145 */     this.country = country;
/*     */   }
/*     */ 
/*     */   public Integer getEmployeeNum()
/*     */   {
/* 150 */     return this.employeeNum;
/*     */   }
/*     */ 
/*     */   public void setEmployeeNum(Integer employeeNum) {
/* 154 */     this.employeeNum = employeeNum;
/*     */   }
/*     */ 
/*     */   public String getEnName() {
/* 158 */     return this.enName;
/*     */   }
/*     */ 
/*     */   public void setEnName(String enName) {
/* 162 */     this.enName = enName;
/*     */   }
/*     */ 
/*     */   public String getLegalPerson() {
/* 166 */     return this.legalPerson;
/*     */   }
/*     */ 
/*     */   public void setLegalPerson(String legalPerson) {
/* 170 */     this.legalPerson = legalPerson;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 174 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 178 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Area getProvince() {
/* 182 */     return this.province;
/*     */   }
/*     */ 
/*     */   public void setProvince(Area province) {
/* 186 */     this.province = province;
/*     */   }
/*     */ 
/*     */   public Double getRegisteredFunds()
/*     */   {
/* 191 */     return this.registeredFunds;
/*     */   }
/*     */ 
/*     */   public void setRegisteredFunds(Double registeredFunds) {
/* 195 */     this.registeredFunds = registeredFunds;
/*     */   }
/*     */ 
/*     */   public String getSupplierNo() {
/* 199 */     return this.supplierNo;
/*     */   }
/*     */ 
/*     */   public void setSupplierNo(String supplierNo) {
/* 203 */     this.supplierNo = supplierNo;
/*     */   }
/*     */ 
/*     */   public CodeValue getSupplierType() {
/* 207 */     return this.supplierType;
/*     */   }
/*     */ 
/*     */   public void setSupplierType(CodeValue supplierType) {
/* 211 */     this.supplierType = supplierType;
/*     */   }
/*     */ 
/*     */   public CodeValue getTradeType() {
/* 215 */     return this.tradeType;
/*     */   }
/*     */ 
/*     */   public void setTradeType(CodeValue tradeType) {
/* 219 */     this.tradeType = tradeType;
/*     */   }
/*     */ 
/*     */   public String getAddress() {
/* 223 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/* 227 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public String getAfterSaleService() {
/* 231 */     return this.afterSaleService;
/*     */   }
/*     */ 
/*     */   public void setAfterSaleService(String afterSaleService) {
/* 235 */     this.afterSaleService = afterSaleService;
/*     */   }
/*     */ 
/*     */   public String getBank() {
/* 239 */     return this.bank;
/*     */   }
/*     */ 
/*     */   public void setBank(String bank) {
/* 243 */     this.bank = bank;
/*     */   }
/*     */ 
/*     */   public String getBankAccount() {
/* 247 */     return this.bankAccount;
/*     */   }
/*     */ 
/*     */   public void setBankAccount(String bankAccount) {
/* 251 */     this.bankAccount = bankAccount;
/*     */   }
/*     */ 
/*     */   public String getBankName() {
/* 255 */     return this.bankName;
/*     */   }
/*     */ 
/*     */   public void setBankName(String bankName) {
/* 259 */     this.bankName = bankName;
/*     */   }
/*     */ 
/*     */   public String getBusinessLicense() {
/* 263 */     return this.businessLicense;
/*     */   }
/*     */ 
/*     */   public void setBusinessLicense(String businessLicense) {
/* 267 */     this.businessLicense = businessLicense;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate() {
/* 271 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate) {
/* 275 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getEmail() {
/* 279 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/* 283 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getFex() {
/* 287 */     return this.fex;
/*     */   }
/*     */ 
/*     */   public void setFex(String fex) {
/* 291 */     this.fex = fex;
/*     */   }
/*     */ 
/*     */   public String getHomeSite() {
/* 295 */     return this.homeSite;
/*     */   }
/*     */ 
/*     */   public void setHomeSite(String homeSite) {
/* 299 */     this.homeSite = homeSite;
/*     */   }
/*     */ 
/*     */   public String getMaorContact() {
/* 303 */     return this.maorContact;
/*     */   }
/*     */ 
/*     */   public void setMaorContact(String maorContact) {
/* 307 */     this.maorContact = maorContact;
/*     */   }
/*     */ 
/*     */   public String getMobile() {
/* 311 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile) {
/* 315 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   public String getPhone() {
/* 319 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone) {
/* 323 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public String getPostCode() {
/* 327 */     return this.postCode;
/*     */   }
/*     */ 
/*     */   public void setPostCode(String postCode) {
/* 331 */     this.postCode = postCode;
/*     */   }
/*     */ 
/*     */   public String getQos() {
/* 335 */     return this.qos;
/*     */   }
/*     */ 
/*     */   public void setQos(String qos) {
/* 339 */     this.qos = qos;
/*     */   }
/*     */ 
/*     */   public String getQq() {
/* 343 */     return this.qq;
/*     */   }
/*     */ 
/*     */   public void setQq(String qq) {
/* 347 */     this.qq = qq;
/*     */   }
/*     */ 
/*     */   public String getTaxNo() {
/* 351 */     return this.taxNo;
/*     */   }
/*     */ 
/*     */   public void setTaxNo(String taxNo) {
/* 355 */     this.taxNo = taxNo;
/*     */   }
/*     */ 
/*     */   public String getTurnover() {
/* 359 */     return this.turnover;
/*     */   }
/*     */ 
/*     */   public void setTurnover(String turnover) {
/* 363 */     this.turnover = turnover;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.supplier.Supplier
 * JD-Core Version:    0.6.2
 */