/*     */ package com.yongjun.tdms.model.marketmanager.targetmanager.individualtarget;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.util.DateUtil;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class IndividualTarget extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private PersonnelFiles saleman;
/*     */   private String targetName;
/*     */   private String departmentName;
/*  43 */   private int customerCount = 0;
/*     */ 
/*  47 */   private int visitCount = 0;
/*     */ 
/*  51 */   private int newCustomerNum = 0;
/*     */   private Products product;
/*  59 */   private int year = DateUtil.getYear(new Date());
/*     */ 
/*  64 */   private int quarter = 1;
/*     */ 
/*  68 */   private int month = 0;
/*     */ 
/*  73 */   private int planType = 1;
/*     */ 
/*  77 */   private String remark = "";
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  94 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 102 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 109 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public int getCustomerCount()
/*     */   {
/* 117 */     return this.customerCount;
/*     */   }
/*     */ 
/*     */   public void setCustomerCount(int customerCount)
/*     */   {
/* 124 */     this.customerCount = customerCount;
/*     */   }
/*     */ 
/*     */   public String getDepartmentName()
/*     */   {
/* 132 */     return this.departmentName;
/*     */   }
/*     */ 
/*     */   public void setDepartmentName(String departmentName)
/*     */   {
/* 139 */     this.departmentName = departmentName;
/*     */   }
/*     */ 
/*     */   public PersonnelFiles getSaleman()
/*     */   {
/* 147 */     return this.saleman;
/*     */   }
/*     */ 
/*     */   public void setSaleman(PersonnelFiles saleman)
/*     */   {
/* 154 */     this.saleman = saleman;
/*     */   }
/*     */ 
/*     */   public int getMonth()
/*     */   {
/* 162 */     return this.month;
/*     */   }
/*     */ 
/*     */   public void setMonth(int month)
/*     */   {
/* 169 */     this.month = month;
/*     */   }
/*     */ 
/*     */   public int getNewCustomerNum()
/*     */   {
/* 177 */     return this.newCustomerNum;
/*     */   }
/*     */ 
/*     */   public void setNewCustomerNum(int newCustomerNum)
/*     */   {
/* 184 */     this.newCustomerNum = newCustomerNum;
/*     */   }
/*     */ 
/*     */   public int getPlanType()
/*     */   {
/* 192 */     return this.planType;
/*     */   }
/*     */ 
/*     */   public void setPlanType(int planType)
/*     */   {
/* 199 */     this.planType = planType;
/*     */   }
/*     */ 
/*     */   public Products getProduct()
/*     */   {
/* 207 */     return this.product;
/*     */   }
/*     */ 
/*     */   public void setProduct(Products product)
/*     */   {
/* 214 */     this.product = product;
/*     */   }
/*     */ 
/*     */   public int getQuarter()
/*     */   {
/* 222 */     return this.quarter;
/*     */   }
/*     */ 
/*     */   public void setQuarter(int quarter)
/*     */   {
/* 229 */     this.quarter = quarter;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 237 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 244 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public String getTargetName()
/*     */   {
/* 252 */     return this.targetName;
/*     */   }
/*     */ 
/*     */   public void setTargetName(String targetName)
/*     */   {
/* 259 */     this.targetName = targetName;
/*     */   }
/*     */ 
/*     */   public int getVisitCount()
/*     */   {
/* 267 */     return this.visitCount;
/*     */   }
/*     */ 
/*     */   public void setVisitCount(int visitCount)
/*     */   {
/* 274 */     this.visitCount = visitCount;
/*     */   }
/*     */ 
/*     */   public int getYear()
/*     */   {
/* 282 */     return this.year;
/*     */   }
/*     */ 
/*     */   public void setYear(int year)
/*     */   {
/* 289 */     this.year = year;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.marketmanager.targetmanager.individualtarget.IndividualTarget
 * JD-Core Version:    0.6.2
 */