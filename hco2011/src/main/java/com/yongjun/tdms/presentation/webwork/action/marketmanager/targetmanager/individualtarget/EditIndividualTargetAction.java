/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.individualtarget;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.individualtarget.IndividualTarget;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.individualtarget.IndividualTargetManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EditIndividualTargetAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final IndividualTargetManager individualTargetManager;
/*     */   private final ProductsManager productsManager;
/*     */   private final PersonnelFilesManager personnelFilesManager;
/*  63 */   private IndividualTarget targetManagement = null;
/*     */ 
/*  67 */   private Products product = null;
/*     */   private PersonnelFiles saleman;
/*     */ 
/*     */   public EditIndividualTargetAction(IndividualTargetManager individualTargetManager, ProductsManager productsManager, PersonnelFilesManager personnelFilesManager)
/*     */   {
/*  81 */     this.individualTargetManager = individualTargetManager;
/*  82 */     this.productsManager = productsManager;
/*  83 */     this.personnelFilesManager = personnelFilesManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  92 */     if (null == this.targetManagement) {
/*  93 */       if (hasId("targetManagement.id")) {
/*  94 */         this.targetManagement = this.individualTargetManager.loadIndividualTarget(getId("targetManagement.id"));
/*     */       }
/*     */       else {
/*  97 */         this.targetManagement = new IndividualTarget();
/*     */       }
/*     */     }
/* 100 */     if (null == this.product) {
/* 101 */       if (hasId("product.id")) {
/* 102 */         this.product = this.productsManager.loadProducts(getId("product.id"));
/*     */       }
/*     */       else {
/* 105 */         this.product = new Products();
/*     */       }
/*     */     }
/* 108 */     if (null == this.saleman)
/* 109 */       if (hasId("saleman.id")) {
/* 110 */         this.saleman = this.personnelFilesManager.loadPersonnel(getId("saleman.id"));
/*     */       }
/*     */       else
/* 113 */         this.saleman = null;
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 124 */     boolean isNew = this.targetManagement.isNew();
/* 125 */     if (isNew) {
/* 126 */       String code = autoCompleteCode();
/* 127 */       this.targetManagement.setCode(code);
/*     */     }
/* 129 */     if (null == this.product.getId())
/* 130 */       this.targetManagement.setProduct(null);
/*     */     else {
/* 132 */       this.targetManagement.setProduct(this.product);
/*     */     }
/* 134 */     this.targetManagement.setSaleman(this.saleman);
/*     */     try {
/* 136 */       this.individualTargetManager.storeIndividualTarget(this.targetManagement);
/* 137 */       if (isNew) {
/* 138 */         addActionMessage(getText("targetManagement.add.success"));
/* 139 */         return "new";
/*     */       }
/* 141 */       addActionMessage(getText("targetManagement.edit.success"));
/* 142 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 146 */       ex.printStackTrace();
/* 147 */       if (isNew) {
/* 148 */         addActionMessage(getText("targetManagement.add.error"));
/* 149 */         return "new";
/*     */       }
/* 151 */       addActionMessage(getText("targetManagement.edit.error"));
/* 152 */     }return "success";
/*     */   }
/*     */ 
/*     */   public Products getProduct()
/*     */   {
/* 163 */     return this.product;
/*     */   }
/*     */ 
/*     */   public void setProduct(Products product)
/*     */   {
/* 170 */     this.product = product;
/*     */   }
/*     */ 
/*     */   public IndividualTarget getTargetManagement()
/*     */   {
/* 178 */     return this.targetManagement;
/*     */   }
/*     */ 
/*     */   public void setTargetManagement(IndividualTarget targetManagement)
/*     */   {
/* 185 */     this.targetManagement = targetManagement;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getPlanType()
/*     */   {
/* 193 */     Map pt = new LinkedHashMap();
/* 194 */     pt.put("1", "年度计划");
/* 195 */     pt.put("2", "季度计划");
/* 196 */     pt.put("3", "月度计划");
/* 197 */     return pt;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getQuarterMap()
/*     */   {
/* 206 */     Map qu = new LinkedHashMap();
/* 207 */     qu.put("1", "第一季度");
/* 208 */     qu.put("2", "第二季度");
/* 209 */     qu.put("3", "第三季度");
/* 210 */     qu.put("4", "第四季度");
/* 211 */     return qu;
/*     */   }
/*     */ 
/*     */   public List<Integer> getMonth()
/*     */   {
/* 219 */     List ml = new ArrayList();
/* 220 */     ml.add(Integer.valueOf(0));
/* 221 */     int d = 1;
/* 222 */     while (d <= 12) {
/* 223 */       ml.add(Integer.valueOf(d));
/* 224 */       d++;
/*     */     }
/* 226 */     return ml;
/*     */   }
/*     */ 
/*     */   public List<String> getDepartment()
/*     */   {
/* 233 */     List depar = new ArrayList();
/* 234 */     depar.add("");
/* 235 */     depar.add("销售部");
/* 236 */     depar.add("研发部");
/* 237 */     depar.add("管理部");
/* 238 */     return depar;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 247 */     String maxCode = this.individualTargetManager.getMaxPFCode("GRMB");
/* 248 */     if (null != maxCode) {
/* 249 */       int num = Integer.parseInt(maxCode) + 1;
/* 250 */       if (num < 10)
/* 251 */         return "GRMB--000" + num;
/* 252 */       if (num < 100)
/* 253 */         return "GRMB--00" + num;
/* 254 */       if (num < 1000) {
/* 255 */         return "GRMB--0" + num;
/*     */       }
/* 257 */       return "GRMB" + num;
/*     */     }
/*     */ 
/* 260 */     return "GRMB--0001";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.individualtarget.EditIndividualTargetAction
 * JD-Core Version:    0.6.2
 */