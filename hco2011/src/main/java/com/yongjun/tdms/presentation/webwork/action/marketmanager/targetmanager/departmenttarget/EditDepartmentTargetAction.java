/*     */ package com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.departmenttarget;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.departmenttarget.DepartmentTarget;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.departmenttarget.DepartmentTargetManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EditDepartmentTargetAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final DepartmentTargetManager departmentTargetManager;
/*     */   private final ProductsManager productsManager;
/*  57 */   private DepartmentTarget targetManagement = null;
/*     */ 
/*  61 */   private Products product = null;
/*     */ 
/*     */   public EditDepartmentTargetAction(DepartmentTargetManager departmentTargetManager, ProductsManager productsManager)
/*     */   {
/*  67 */     this.departmentTargetManager = departmentTargetManager;
/*  68 */     this.productsManager = productsManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  77 */     if (null == this.targetManagement) {
/*  78 */       if (hasId("targetManagement.id")) {
/*  79 */         this.targetManagement = this.departmentTargetManager.loadDepartmentTarget(getId("targetManagement.id"));
/*     */       }
/*     */       else {
/*  82 */         this.targetManagement = new DepartmentTarget();
/*     */       }
/*     */     }
/*  85 */     if (null == this.product)
/*  86 */       if (hasId("product.id")) {
/*  87 */         this.product = this.productsManager.loadProducts(getId("product.id"));
/*     */       }
/*     */       else
/*  90 */         this.product = new Products();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 101 */     boolean isNew = this.targetManagement.isNew();
/* 102 */     if (null == this.product.getId())
/* 103 */       this.targetManagement.setProduct(null);
/*     */     else {
/* 105 */       this.targetManagement.setProduct(this.product);
/*     */     }
/* 107 */     if (isNew) {
/* 108 */       String code = autoCompleteCode();
/* 109 */       this.targetManagement.setCode(code);
/*     */     }
/*     */     try {
/* 112 */       this.departmentTargetManager.storeDepartmentTarget(this.targetManagement);
/* 113 */       if (isNew) {
/* 114 */         addActionMessage(getText("targetManagement.add.success"));
/* 115 */         return "new";
/*     */       }
/* 117 */       addActionMessage(getText("targetManagement.edit.success"));
/* 118 */       return "success";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 122 */       ex.printStackTrace();
/* 123 */       if (isNew) {
/* 124 */         addActionMessage(getText("targetManagement.add.error"));
/* 125 */         return "new";
/*     */       }
/* 127 */       addActionMessage(getText("targetManagement.edit.error"));
/* 128 */     }return "success";
/*     */   }
/*     */ 
/*     */   public DepartmentTarget getTargetManagement()
/*     */   {
/* 139 */     return this.targetManagement;
/*     */   }
/*     */ 
/*     */   public void setTargetManagement(DepartmentTarget targetManagement)
/*     */   {
/* 146 */     this.targetManagement = targetManagement;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getPlanType()
/*     */   {
/* 154 */     Map pt = new LinkedHashMap();
/* 155 */     pt.put("1", "年度计划");
/* 156 */     pt.put("2", "季度计划");
/* 157 */     pt.put("3", "月度计划");
/* 158 */     return pt;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getQuarterMap()
/*     */   {
/* 167 */     Map qu = new LinkedHashMap();
/* 168 */     qu.put("1", "第一季度");
/* 169 */     qu.put("2", "第二季度");
/* 170 */     qu.put("3", "第三季度");
/* 171 */     qu.put("4", "第四季度");
/* 172 */     return qu;
/*     */   }
/*     */ 
/*     */   public List<Integer> getMonth()
/*     */   {
/* 180 */     List ml = new ArrayList();
/* 181 */     ml.add(Integer.valueOf(0));
/* 182 */     int d = 1;
/* 183 */     while (d <= 12) {
/* 184 */       ml.add(Integer.valueOf(d));
/* 185 */       d++;
/*     */     }
/* 187 */     return ml;
/*     */   }
/*     */ 
/*     */   public List<String> getDepartment()
/*     */   {
/* 194 */     List depar = new ArrayList();
/* 195 */     depar.add("");
/* 196 */     depar.add("销售部");
/* 197 */     depar.add("研发部");
/* 198 */     depar.add("管理部");
/* 199 */     return depar;
/*     */   }
/*     */ 
/*     */   public Products getProduct()
/*     */   {
/* 208 */     return this.product;
/*     */   }
/*     */ 
/*     */   public void setProduct(Products product)
/*     */   {
/* 215 */     this.product = product;
/*     */   }
/*     */ 
/*     */   public String autoCompleteCode()
/*     */   {
/* 223 */     String maxCode = this.departmentTargetManager.getMaxPFCode("DMMB");
/* 224 */     if (null != maxCode) {
/* 225 */       int num = Integer.parseInt(maxCode) + 1;
/* 226 */       if (num < 10)
/* 227 */         return "DMMB--000" + num;
/* 228 */       if (num < 100)
/* 229 */         return "DMMB--00" + num;
/* 230 */       if (num < 1000) {
/* 231 */         return "DMMB--0" + num;
/*     */       }
/* 233 */       return "DMMB--" + num;
/*     */     }
/*     */ 
/* 236 */     return "DMMB--0001";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.marketmanager.targetmanager.departmenttarget.EditDepartmentTargetAction
 * JD-Core Version:    0.6.2
 */