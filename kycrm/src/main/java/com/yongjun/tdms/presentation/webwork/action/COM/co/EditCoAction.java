/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.co;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.COM.co.CoManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditCoAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final CoManager coManager;
/*     */   private final CodeValueManager codeValueManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final ContactArchivesManager contactArchivesManager;
/*     */   private final ProductsManager productsManager;
/*     */   private Co co;
/*     */ 
/*     */   public EditCoAction(CoManager coManager, CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager, ContactArchivesManager contactArchivesManager, ProductsManager productsManager)
/*     */   {
/*  83 */     this.coManager = coManager;
/*  84 */     this.codeValueManager = codeValueManager;
/*  85 */     this.personnelFilesManager = personnelFilesManager;
/*  86 */     this.customerInfoManager = customerInfoManager;
/*  87 */     this.contactArchivesManager = contactArchivesManager;
/*  88 */     this.productsManager = productsManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  96 */     if (hasId("co.id"))
/*  97 */       this.co = this.coManager.loadCo(getId("co.id"));
/*     */     else
/*  99 */       this.co = new Co();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/* 109 */     boolean isNew = this.co.isNew();
/*     */ 
/* 111 */     if (!StringUtils.isEmpty(this.request.getParameter("deliveryWay.id"))) {
/* 112 */       this.co.setDeliveryWay(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("deliveryWay.id"))));
/*     */     }
/*     */ 
/* 117 */     if (!StringUtils.isEmpty(this.request.getParameter("type.id"))) {
/* 118 */       this.co.setType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("type.id"))));
/*     */     }
/*     */ 
/* 122 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/* 123 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/* 125 */       if (null != customer) {
/* 126 */         this.co.setCustomerInfo(customer);
/*     */       }
/*     */     }
/*     */ 
/* 130 */     if (!StringUtils.isEmpty(this.request.getParameter("salesman.id"))) {
/* 131 */       PersonnelFiles salesman = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("salesman.id")));
/*     */ 
/* 133 */       if (null != salesman) {
/* 134 */         this.co.setSaleman(salesman);
/*     */       }
/*     */     }
/*     */ 
/* 138 */     if (!StringUtils.isEmpty(this.request.getParameter("products.id"))) {
/* 139 */       Products products = this.productsManager.loadProducts(Long.valueOf(this.request.getParameter("products.id")));
/*     */ 
/* 141 */       if (null != products) {
/* 142 */         this.co.setProducts(products);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 147 */     if (!StringUtils.isEmpty(this.request.getParameter("linkman.id"))) {
/* 148 */       ContactArchives linkman = this.contactArchivesManager.loadContactArchives(Long.valueOf(this.request.getParameter("linkman.id")));
/*     */ 
/* 150 */       if (null != linkman) {
/* 151 */         this.co.setLinkman(linkman);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 156 */     if (isNew) {
/* 157 */       String code = autoCompleteCode();
/* 158 */       this.co.setCode(code);
/* 159 */       this.coManager.storeCo(this.co);
/* 160 */       addActionMessage(getText("co.save.success"));
/* 161 */       return "new";
/*     */     }
/* 163 */     this.coManager.storeCo(this.co);
/* 164 */     addActionMessage(getText("co.edit.success"));
/* 165 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllDeliveryWays()
/*     */   {
/* 175 */     List codes = null;
/*     */     try {
/* 177 */       codes = new ArrayList();
/* 178 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("070"));
/*     */ 
/* 180 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 182 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 184 */         if ((null != list) && (list.size() > 0)) {
/* 185 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 188 */       return codes;
/*     */     } catch (DaoException e) {
/* 190 */       e.printStackTrace();
/*     */     }
/* 192 */     return codes;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> getAllTypes()
/*     */   {
/* 200 */     List codes = null;
/*     */     try {
/* 202 */       codes = new ArrayList();
/* 203 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("060"));
/*     */ 
/* 205 */       if ((null != one) && (one.size() > 0))
/*     */       {
/* 207 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
/*     */ 
/* 209 */         if ((null != list) && (list.size() > 0)) {
/* 210 */           codes.addAll(list);
/*     */         }
/*     */       }
/* 213 */       return codes;
/*     */     } catch (DaoException e) {
/* 215 */       e.printStackTrace();
/*     */     }
/* 217 */     return codes;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 226 */     String maxCode = this.coManager.getMaxPFCode("KHDD");
/* 227 */     if (null != maxCode) {
/* 228 */       int num = Integer.parseInt(maxCode) + 1;
/* 229 */       if (num < 10)
/* 230 */         return "KHDD--000" + num;
/* 231 */       if (num < 100)
/* 232 */         return "KHDD--00" + num;
/* 233 */       if (num < 1000) {
/* 234 */         return "KHDD--0" + num;
/*     */       }
/* 236 */       return "KHDD--" + num;
/*     */     }
/*     */ 
/* 239 */     return "KHDD--0001";
/*     */   }
/*     */ 
/*     */   public Co getCo()
/*     */   {
/* 248 */     return this.co;
/*     */   }
/*     */ 
/*     */   public void setCo(Co co)
/*     */   {
/* 257 */     this.co = co;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.co.EditCoAction
 * JD-Core Version:    0.6.2
 */