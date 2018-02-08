/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.competitor;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Organization;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.security.UserManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.marketmanager.competitor.Competitor;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.marketmanager.competitor.CompetitorManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditCompetitorAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CompetitorManager competitorManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final UserManager userManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final ProductsManager productsManager;
/*     */   private Competitor competitor;
/*     */ 
/*     */   public EditCompetitorAction(CompetitorManager competitorManager, CodeValueManager codeValueManager, UserManager userManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, ProductsManager productsManager)
/*     */   {
/* 101 */     this.competitorManager = competitorManager;
/* 102 */     this.codeValueManager = codeValueManager;
/* 103 */     this.userManager = userManager;
/* 104 */     this.personnelFilesManager = personnelFilesManager;
/* 105 */     this.customerInfoManager = customerInfoManager;
/* 106 */     this.contactArchivesManager = contactArchivesManager;
/* 107 */     this.productsManager = productsManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/* 116 */     if (hasId("competitor.id"))
/* 117 */       this.competitor = this.competitorManager.loadCompetitor(getId("competitor.id"));
/*     */     else
/* 119 */       this.competitor = new Competitor();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 128 */     boolean isNew = this.competitor.isNew();
/*     */ 
/* 130 */     if (hasId("industry.id")) {
/* 131 */       CodeValue industry = new CodeValue();
/* 132 */       industry = this.codeValueManager.loadCodeValue(getId("industry.id"));
/* 133 */       this.competitor.setIndustry(industry);
/*     */     }
/*     */ 
/* 136 */     if (hasId("nature.id")) {
/* 137 */       CodeValue nature = new CodeValue();
/* 138 */       nature = this.codeValueManager.loadCodeValue(getId("nature.id"));
/* 139 */       this.competitor.setNature(nature);
/*     */     }
/*     */ 
/* 142 */     if (hasId("ability.id")) {
/* 143 */       CodeValue ability = new CodeValue();
/* 144 */       ability = this.codeValueManager.loadCodeValue(getId("ability.id"));
/* 145 */       this.competitor.setAbility(ability);
/*     */     }
/*     */ 
/* 148 */     if (hasId("customerInfo.id")) {
/* 149 */       CustomerInfo customerInfo = new CustomerInfo();
/* 150 */       customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
/* 151 */       this.competitor.setCustomerInfo(customerInfo);
/*     */     }
/*     */ 
/* 154 */     if (hasId("products.id")) {
/* 155 */       Products products = new Products();
/* 156 */       products = this.productsManager.loadProducts(getId("products.id"));
/* 157 */       this.competitor.setProducts(products);
/*     */     }
/*     */ 
/* 160 */     if (!StringUtils.isEmpty(this.request.getParameter("competitor.price"))) {
/* 161 */       String priceStr = String.valueOf(this.request.getParameter("competitor.price"));
/* 162 */       Double price = Double.valueOf(priceStr);
/* 163 */       this.competitor.setPrice(price);
/*     */     }
/*     */ 
/* 167 */     this.competitor.setOrganization(this.userManager.getOrganization());
/*     */     try
/*     */     {
/* 170 */       if (isNew) {
/* 171 */         String newCode = autoCompleteCode();
/* 172 */         this.competitor.setCode(newCode);
/* 173 */         this.competitorManager.storeCompetitor(this.competitor);
/* 174 */         addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.competitor.getCode() })));
/*     */ 
/* 176 */         return "new";
/*     */       }
/* 178 */       this.competitorManager.storeCompetitor(this.competitor);
/* 179 */       addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.competitor.getCode() })));
/*     */ 
/* 181 */       return "success";
/*     */     }
/*     */     catch (Exception e) {
/* 184 */       e.printStackTrace();
/* 185 */       if (isNew) {
/* 186 */         addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.competitor.getCode() })));
/*     */       }
/*     */       else
/*     */       {
/* 190 */         addActionMessage(getText("edit.error", Arrays.asList(new Object[] { this.competitor.getCode() })));
/*     */       }
/*     */     }
/* 193 */     return "error";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 203 */     String maxCode = this.competitorManager.getMaxPFCode("JZDS", this.userManager.getOrganization().getId());
/*     */ 
/* 205 */     if (null != maxCode) {
/* 206 */       int num = Integer.parseInt(maxCode) + 1;
/* 207 */       if (num < 10)
/* 208 */         return "JZDS-000" + num;
/* 209 */       if (num < 100)
/* 210 */         return "JZDS00" + num;
/* 211 */       if (num < 1000) {
/* 212 */         return "JZDS-0" + num;
/*     */       }
/* 214 */       return "JZDS-" + num;
/*     */     }
/*     */ 
/* 218 */     return "JZDS-0001";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllIndustry()
/*     */   {
/* 226 */     List codes = null;
/*     */     try {
/* 228 */       codes = new ArrayList();
/* 229 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("002"));
/*     */ 
/* 231 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 233 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 235 */         if ((null != list) && (list.size() > 0)) {
/* 236 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 239 */       return codes;
/*     */     } catch (DaoException e) {
/* 241 */       e.printStackTrace();
/*     */     }
/* 243 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllNature()
/*     */   {
/* 250 */     List codes = null;
/*     */     try {
/* 252 */       codes = new ArrayList();
/* 253 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("003"));
/*     */ 
/* 255 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 257 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 259 */         if ((null != list) && (list.size() > 0)) {
/* 260 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 263 */       return codes;
/*     */     } catch (DaoException e) {
/* 265 */       e.printStackTrace();
/*     */     }
/* 267 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllAbility()
/*     */   {
/* 274 */     List codes = null;
/*     */     try {
/* 276 */       codes = new ArrayList();
/* 277 */       List one = this.codeValueManager.loadByKey("code", String.valueOf("045"));
/*     */ 
/* 279 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 281 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 283 */         if ((null != list) && (list.size() > 0)) {
/* 284 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 287 */       return codes;
/*     */     } catch (DaoException e) {
/* 289 */       e.printStackTrace();
/*     */     }
/* 291 */     return codes;
/*     */   }
/*     */ 
/*     */   public Competitor getCompetitor()
/*     */   {
/* 298 */     return this.competitor;
/*     */   }
/*     */ 
/*     */   public void setCompetitor(Competitor competitor)
/*     */   {
/* 306 */     this.competitor = competitor;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.competitor.EditCompetitorAction
 * JD-Core Version:    0.6.2
 */