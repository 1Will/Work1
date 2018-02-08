/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.companytarget;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.companytarget.CompanyTarget;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.companytarget.CompanyTargetManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EditCompanyTargetAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final CompanyTargetManager targetManagementManager;
/*     */   private final ProductsManager productsManager;
/*  57 */   private CompanyTarget targetManagement = null;
/*     */ 
/*  61 */   private Products product = null;
/*     */ 
/*     */   public EditCompanyTargetAction(CompanyTargetManager targetManagementManager, ProductsManager productsManager)
/*     */   {
/*  69 */     this.targetManagementManager = targetManagementManager;
/*  70 */     this.productsManager = productsManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  79 */     if (null == this.targetManagement) {
/*  80 */       if (hasId("targetManagement.id")) {
/*  81 */         this.targetManagement = this.targetManagementManager.loadCompanyTarget(getId("targetManagement.id"));
/*     */       }
/*     */       else {
/*  84 */         this.targetManagement = new CompanyTarget();
/*     */       }
/*     */     }
/*  87 */     if (null == this.product)
/*  88 */       if (hasId("product.id")) {
/*  89 */         this.product = this.productsManager.loadProducts(getId("product.id"));
/*     */       }
/*     */       else
/*  92 */         this.product = new Products();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 102 */     boolean isNew = this.targetManagement.isNew();
/* 103 */     if (null == this.product.getId())
/* 104 */       this.targetManagement.setProduct(null);
/*     */     else {
/* 106 */       this.targetManagement.setProduct(this.product);
/*     */     }
/* 108 */     if (isNew) {
/* 109 */       String code = autoCompleteCode();
/* 110 */       this.targetManagement.setCode(code);
/*     */     }
/*     */     try {
/* 113 */       this.targetManagementManager.storeCompanyTarget(this.targetManagement);
/* 114 */       if (isNew) {
/* 115 */         addActionMessage(getText("targetManagement.add.success"));
/* 116 */         return "new";
/*     */       }
/* 118 */       addActionMessage(getText("targetManagement.edit.success"));
/* 119 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 123 */       ex.printStackTrace();
/* 124 */       if (isNew) {
/* 125 */         addActionMessage(getText("targetManagement.add.error"));
/* 126 */         return "new";
/*     */       }
/* 128 */       addActionMessage(getText("targetManagement.edit.error"));
/* 129 */     }return "success";
/*     */   }
/*     */ 
/*     */   public Products getProduct()
/*     */   {
/* 140 */     return this.product;
/*     */   }
/*     */ 
/*     */   public void setProduct(Products product)
/*     */   {
/* 147 */     this.product = product;
/*     */   }
/*     */ 
/*     */   public CompanyTarget getTargetManagement()
/*     */   {
/* 155 */     return this.targetManagement;
/*     */   }
/*     */ 
/*     */   public void setTargetManagement(CompanyTarget targetManagement)
/*     */   {
/* 162 */     this.targetManagement = targetManagement;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getPlanType()
/*     */   {
/* 170 */     Map pt = new LinkedHashMap();
/* 171 */     pt.put("1", "年度计划");
/* 172 */     pt.put("2", "季度计划");
/* 173 */     pt.put("3", "月度计划");
/* 174 */     return pt;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getQuarterMap()
/*     */   {
/* 183 */     Map qu = new LinkedHashMap();
/* 184 */     qu.put("1", "第一季度");
/* 185 */     qu.put("2", "第二季度");
/* 186 */     qu.put("3", "第三季度");
/* 187 */     qu.put("4", "第四季度");
/* 188 */     return qu;
/*     */   }
/*     */ 
/*     */   public List<Integer> getMonth()
/*     */   {
/* 196 */     List ml = new ArrayList();
/* 197 */     ml.add(Integer.valueOf(0));
/* 198 */     int d = 1;
/* 199 */     while (d <= 12) {
/* 200 */       ml.add(Integer.valueOf(d));
/* 201 */       d++;
/*     */     }
/* 203 */     return ml;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 211 */     String maxCode = this.targetManagementManager.getMaxPFCode("GSMB");
/* 212 */     if (null != maxCode) {
/* 213 */       int num = Integer.parseInt(maxCode) + 1;
/* 214 */       if (num < 10)
/* 215 */         return "GSMB--000" + num;
/* 216 */       if (num < 100)
/* 217 */         return "GSMB--00" + num;
/* 218 */       if (num < 1000) {
/* 219 */         return "GSMB--0" + num;
/*     */       }
/* 221 */       return "GSMB--" + num;
/*     */     }
/*     */ 
/* 224 */     return "GSMB--0001";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.companytarget.EditCompanyTargetAction
 * JD-Core Version:    0.6.2
 */