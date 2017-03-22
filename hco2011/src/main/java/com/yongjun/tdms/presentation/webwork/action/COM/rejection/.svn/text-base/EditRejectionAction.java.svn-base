/*     */ package com.yongjun.tdms.presentation.webwork.action.COM.rejection;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.COM.co.Co;
/*     */ import com.yongjun.tdms.model.COM.rejection.Rejection;
/*     */ import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.COM.co.CoManager;
/*     */ import com.yongjun.tdms.service.COM.rejection.RejectionManager;
/*     */ import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class EditRejectionAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 612315316215110285L;
/*     */   private final RejectionManager rejectionManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*     */   private final CustomerInfoManager customerInfoManager;
/*     */   private final CoManager coManager;
/*     */   private final ProductsManager productsManager;
/*     */   private Rejection rejection;
/*     */ 
/*     */   public EditRejectionAction(RejectionManager rejectionManager, CustomerInfoManager customerInfoManager, PersonnelFilesManager personnelFilesManager, CoManager coManager, ProductsManager productsManager)
/*     */   {
/*  66 */     this.rejectionManager = rejectionManager;
/*  67 */     this.customerInfoManager = customerInfoManager;
/*  68 */     this.personnelFilesManager = personnelFilesManager;
/*  69 */     this.coManager = coManager;
/*  70 */     this.productsManager = productsManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  78 */     if (hasId("rejection.id"))
/*  79 */       this.rejection = this.rejectionManager.loadRejection(getId("rejection.id"));
/*     */     else
/*  81 */       this.rejection = new Rejection();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
/*  91 */     boolean isNew = this.rejection.isNew();
/*     */ 
/*  93 */     if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
/*  94 */       CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request.getParameter("customer.id")));
/*     */ 
/*  96 */       if (null != customer) {
/*  97 */         this.rejection.setCustomerInfo(customer);
/*     */       }
/*     */     }
/*     */ 
/* 101 */     if (!StringUtils.isEmpty(this.request.getParameter("saleMan.id"))) {
/* 102 */       PersonnelFiles salesman = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("saleMan.id")));
/*     */ 
/* 104 */       if (null != salesman) {
/* 105 */         this.rejection.setSaleMan(salesman);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 110 */     if (!StringUtils.isEmpty(this.request.getParameter("co.id"))) {
/* 111 */       Co co = this.coManager.loadCo(Long.valueOf(this.request.getParameter("co.id")));
/*     */ 
/* 113 */       if (null != co) {
/* 114 */         this.rejection.setCo(co);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 119 */     if (!StringUtils.isEmpty(this.request.getParameter("products.id"))) {
/* 120 */       Products products = this.productsManager.loadProducts(Long.valueOf(this.request.getParameter("products.id")));
/*     */ 
/* 122 */       if (null != products) {
/* 123 */         this.rejection.setProducts(products);
/*     */       }
/*     */     }
/*     */ 
/* 127 */     if (isNew) {
/* 128 */       String code = autoCompleteCode();
/* 129 */       this.rejection.setCode(code);
/* 130 */       this.rejectionManager.storeRejection(this.rejection);
/* 131 */       addActionMessage(getText("rejection.save.success"));
/* 132 */       return "new";
/*     */     }
/* 134 */     this.rejectionManager.storeRejection(this.rejection);
/* 135 */     addActionMessage(getText("rejection.edit.success"));
/* 136 */     return "success";
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 145 */     String maxCode = this.rejectionManager.getMaxPFCode("HKGL");
/* 146 */     if (null != maxCode) {
/* 147 */       int num = Integer.parseInt(maxCode) + 1;
/* 148 */       if (num < 10)
/* 149 */         return "HKGL-000" + num;
/* 150 */       if (num < 100)
/* 151 */         return "HKGL-00" + num;
/* 152 */       if (num < 1000) {
/* 153 */         return "HKGL-0" + num;
/*     */       }
/* 155 */       return "HKGL-" + num;
/*     */     }
/*     */ 
/* 158 */     return "HKGL-0001";
/*     */   }
/*     */ 
/*     */   public Rejection getRejection()
/*     */   {
/* 167 */     return this.rejection;
/*     */   }
/*     */ 
/*     */   public void setRejection(Rejection rejection)
/*     */   {
/* 176 */     this.rejection = rejection;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.COM.rejection.EditRejectionAction
 * JD-Core Version:    0.6.2
 */