/*     */ package com.yongjun.tdms.service.CustomerRelationship.customerProfiles.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.sequence.service.SequenceManager;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.CustomerRelationship.customerProfiles.CustomerInfoDao;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultCustomerInfoManager extends BaseManager
/*     */   implements CustomerInfoManager
/*     */ {
/*     */   private final CustomerInfoDao customerInfoDao;
/*     */   private final SequenceManager ciSequenceManager;
/*     */ 
/*     */   public DefaultCustomerInfoManager(CustomerInfoDao customerInfoDao, SequenceManager ciSequenceManager)
/*     */   {
/*  50 */     this.customerInfoDao = customerInfoDao;
/*  51 */     this.ciSequenceManager = ciSequenceManager;
/*     */   }
/*     */ 
/*     */   public void storeCustomerInfo(CustomerInfo ci)
/*     */   {
/*  59 */     if (ci.isNew()) {
/*  60 */       ci.setCode((String)this.ciSequenceManager.generate("-"));
/*     */     }
/*     */ 
/*  63 */     setInfoIntegrity(ci);
/*  64 */     this.customerInfoDao.storeCustomerInfo(ci);
/*     */   }
/*     */ 
/*     */   public CustomerInfo loadCustomerInfo(Long ciId)
/*     */   {
/*  74 */     return this.customerInfoDao.loadCustomerInfo(ciId);
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> loadAllCustomerInfo()
/*     */   {
/*  83 */     return this.customerInfoDao.loadAllCustomerInfo();
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> loadAllCustomerInfo(Long[] ciIds)
/*     */   {
/*  93 */     return this.customerInfoDao.loadAllCustomerInfo(ciIds);
/*     */   }
/*     */ 
/*     */   public void deleteCustomerInfo(CustomerInfo ci)
/*     */   {
/* 102 */     this.customerInfoDao.deleteCustomerInfo(ci);
/*     */   }
/*     */ 
/*     */   public void deleteAllCustomerInfo(List<CustomerInfo> ciIds)
/*     */   {
/* 111 */     this.customerInfoDao.deleteAllCustomerInfo(ciIds);
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/* 122 */     return this.customerInfoDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public void disabledAllCustomerInfo(List<CustomerInfo> cis)
/*     */   {
/* 131 */     for (CustomerInfo customerInfo : cis) {
/* 132 */       customerInfo.setDisabled(true);
/* 133 */       this.customerInfoDao.storeCustomerInfo(customerInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllCustomerInfo(List<CustomerInfo> cis)
/*     */   {
/* 143 */     for (CustomerInfo customerInfo : cis) {
/* 144 */       customerInfo.setDisabled(false);
/* 145 */       this.customerInfoDao.storeCustomerInfo(customerInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<CustomerInfo> getCustomerList(String advisoryName)
/*     */   {
/* 155 */     List<CustomerInfo> allAdvisory = this.customerInfoDao.getCustomerByName(advisoryName);
/*     */ 
/* 157 */     List list = new ArrayList();
/* 158 */     if (allAdvisory.size() > 0) {
/* 159 */       for (CustomerInfo info : allAdvisory) {
/* 160 */         CustomerInfo c = new CustomerInfo();
/* 161 */         c.setId(info.getId());
/* 162 */         c.setCode(info.getCode());
/* 163 */         c.setName(info.getName());
/* 164 */         c.setAbbreviations(info.getAbbreviations());
/* 165 */         c.setLegalPerson(info.getLegalPerson());
/* 166 */         c.setKeyContacter(info.getKeyContacter());
/* 167 */         c.setTelphone(info.getTelphone());
/* 168 */         c.setMobilePhone(info.getMobilePhone());
/* 169 */         c.setFax(info.getFax());
/* 170 */         c.setEmail(info.getEmail());
/* 171 */         c.setQq(info.getQq());
/* 172 */         c.setSetupTime(info.getSetupTime());
/* 173 */         c.setRegisteredCapital(info.getRegisteredCapital());
/* 174 */         c.setPersonCount(info.getPersonCount());
/* 175 */         c.setSaleman(info.getSaleman());
/* 176 */         c.setPostCode(info.getPostCode());
/* 177 */         c.setAddress(info.getAddress());
/* 178 */         c.setBusinessScope(info.getBusinessScope());
/* 179 */         c.setStep(info.getStep());
/* 180 */         c.setCustomerType(info.getCustomerType());
/* 181 */         if (info.getLegalPerson() == null) {
/* 182 */           c.setLegalPerson("");
/*     */         }
/* 184 */         if (info.getStep() != null) {
/* 185 */           CodeValue ct = new CodeValue();
/* 186 */           ct.setId(info.getStep().getId());
/* 187 */           c.setStep(ct);
/*     */         }
/* 189 */         if (info.getCustomerType() != null) {
/* 190 */           CodeValue ct = new CodeValue();
/* 191 */           ct.setId(info.getCustomerType().getId());
/* 192 */           c.setCustomerType(ct);
/*     */         }
/* 194 */         if (info.getIndustry() != null) {
/* 195 */           CodeValue ind = new CodeValue();
/* 196 */           ind.setId(info.getIndustry().getId());
/* 197 */           c.setIndustry(ind);
/*     */         }
/* 199 */         if (info.getCompanyNature() != null) {
/* 200 */           CodeValue cn = new CodeValue();
/* 201 */           cn.setId(info.getCompanyNature().getId());
/* 202 */           c.setCompanyNature(cn);
/*     */         }
/* 204 */         if (info.getCountry() != null) {
/* 205 */           Area cou = new Area();
/* 206 */           cou.setId(info.getCountry().getId());
/* 207 */           c.setCountry(cou);
/*     */         }
/* 209 */         if (info.getProvince() != null) {
/* 210 */           Area pro = new Area();
/* 211 */           pro.setId(info.getProvince().getId());
/* 212 */           c.setProvince(pro);
/*     */         }
/* 214 */         if (info.getCity() != null) {
/* 215 */           Area city = new Area();
/* 216 */           city.setId(info.getCity().getId());
/* 217 */           c.setCity(city);
/*     */         }
/* 219 */         list.add(c);
/*     */       }
/*     */     }
/* 222 */     return list;
/*     */   }
/*     */ 
/*     */   public void setInfoIntegrity(CustomerInfo customerInfo)
/*     */   {
/* 230 */     float base = 60.0F;
/* 231 */     if ((null != customerInfo.getAbbreviations()) && (!"".equals(customerInfo.getAbbreviations()))) {
/* 232 */       base += 3.0F;
/*     */     }
/*     */ 
/* 235 */     if ((null != customerInfo.getLegalPerson()) && (!"".equals(customerInfo.getLegalPerson()))) {
/* 236 */       base += 3.0F;
/*     */     }
/*     */ 
/* 239 */     if ((null != customerInfo.getRegisteredCapital()) && (0.0D != customerInfo.getRegisteredCapital().doubleValue())) {
/* 240 */       base += 5.0F;
/*     */     }
/*     */ 
/* 243 */     if ((null != customerInfo.getPersonCount()) && (0 != customerInfo.getPersonCount().intValue())) {
/* 244 */       base += 3.0F;
/*     */     }
/* 246 */     if (null != customerInfo.getSetupTime()) {
/* 247 */       base += 3.0F;
/*     */     }
/* 249 */     if ((null != customerInfo.getPostCode()) && (!"".equals(customerInfo.getPostCode()))) {
/* 250 */       base += 3.0F;
/*     */     }
/* 252 */     if ((null != customerInfo.getAddress()) && (!"".equals(customerInfo.getAddress()))) {
/* 253 */       base += 3.0F;
/*     */     }
/* 255 */     if ((null != customerInfo.getMobilePhone()) && (!"".equals(customerInfo.getMobilePhone()))) {
/* 256 */       base += 4.0F;
/*     */     }
/* 258 */     if ((null != customerInfo.getFax()) && (!"".equals(customerInfo.getFax()))) {
/* 259 */       base += 3.0F;
/*     */     }
/*     */ 
/* 262 */     if ((null != customerInfo.getEmail()) && (!"".equals(customerInfo.getEmail()))) {
/* 263 */       base += 3.0F;
/*     */     }
/* 265 */     if ((null != customerInfo.getQq()) && (!"".equals(customerInfo.getQq()))) {
/* 266 */       base += 3.0F;
/*     */     }
/* 268 */     if ((null != customerInfo.getBusinessScope()) && (!"".equals(customerInfo.getBusinessScope()))) {
/* 269 */       base += 4.0F;
/*     */     }
/* 271 */     customerInfo.setCustomerInfoIntegrity(Float.valueOf(base));
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.CustomerRelationship.customerProfiles.pojo.DefaultCustomerInfoManager
 * JD-Core Version:    0.6.2
 */