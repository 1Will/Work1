/*     */ package com.yongjun.tdms.model.marketmanager.targetmanager.companytarget;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.util.DateUtil;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CompanyTarget extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  21 */   private String code = "";
/*     */ 
/*  25 */   private String targetName = "";
/*     */ 
/*  29 */   private double orderMoney = 0.0D;
/*     */ 
/*  33 */   private double sellReceivedPayments = 0.0D;
/*     */ 
/*  37 */   private float loseRate = 0.0F;
/*     */ 
/*  41 */   private int visitCount = 0;
/*     */ 
/*  45 */   private float successRate = 0.0F;
/*     */ 
/*  49 */   private int newCustomerNum = 0;
/*     */ 
/*  53 */   private String area = "";
/*     */   private Products product;
/*  62 */   private int year = DateUtil.getYear(new Date());
/*     */ 
/*  66 */   private int quarter = 1;
/*     */ 
/*  70 */   private int month = 0;
/*     */ 
/*  75 */   private int planType = 1;
/*     */ 
/*  79 */   private String remark = "";
/*     */ 
/*     */   public Products getProduct()
/*     */   {
/*  88 */     return this.product;
/*     */   }
/*     */ 
/*     */   public void setProduct(Products product)
/*     */   {
/*  95 */     this.product = product;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 112 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getArea()
/*     */   {
/* 119 */     return this.area;
/*     */   }
/*     */ 
/*     */   public void setArea(String area)
/*     */   {
/* 126 */     this.area = area;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 133 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 140 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public float getLoseRate()
/*     */   {
/* 147 */     return this.loseRate;
/*     */   }
/*     */ 
/*     */   public void setLoseRate(float loseRate)
/*     */   {
/* 154 */     this.loseRate = loseRate;
/*     */   }
/*     */ 
/*     */   public int getMonth()
/*     */   {
/* 161 */     return this.month;
/*     */   }
/*     */ 
/*     */   public void setMonth(int month)
/*     */   {
/* 168 */     this.month = month;
/*     */   }
/*     */ 
/*     */   public int getNewCustomerNum()
/*     */   {
/* 175 */     return this.newCustomerNum;
/*     */   }
/*     */ 
/*     */   public void setNewCustomerNum(int newCustomerNum)
/*     */   {
/* 182 */     this.newCustomerNum = newCustomerNum;
/*     */   }
/*     */ 
/*     */   public double getOrderMoney()
/*     */   {
/* 189 */     return this.orderMoney;
/*     */   }
/*     */ 
/*     */   public void setOrderMoney(double orderMoney)
/*     */   {
/* 196 */     this.orderMoney = orderMoney;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 203 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 210 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public double getSellReceivedPayments()
/*     */   {
/* 217 */     return this.sellReceivedPayments;
/*     */   }
/*     */ 
/*     */   public void setSellReceivedPayments(double sellReceivedPayments)
/*     */   {
/* 224 */     this.sellReceivedPayments = sellReceivedPayments;
/*     */   }
/*     */ 
/*     */   public float getSuccessRate()
/*     */   {
/* 231 */     return this.successRate;
/*     */   }
/*     */ 
/*     */   public void setSuccessRate(float successRate)
/*     */   {
/* 238 */     this.successRate = successRate;
/*     */   }
/*     */ 
/*     */   public String getTargetName()
/*     */   {
/* 245 */     return this.targetName;
/*     */   }
/*     */ 
/*     */   public void setTargetName(String targetName)
/*     */   {
/* 252 */     this.targetName = targetName;
/*     */   }
/*     */ 
/*     */   public int getVisitCount()
/*     */   {
/* 259 */     return this.visitCount;
/*     */   }
/*     */ 
/*     */   public void setVisitCount(int visitCount)
/*     */   {
/* 266 */     this.visitCount = visitCount;
/*     */   }
/*     */ 
/*     */   public int getPlanType()
/*     */   {
/* 274 */     return this.planType;
/*     */   }
/*     */ 
/*     */   public void setPlanType(int planType)
/*     */   {
/* 281 */     this.planType = planType;
/*     */   }
/*     */ 
/*     */   public int getQuarter()
/*     */   {
/* 289 */     return this.quarter;
/*     */   }
/*     */ 
/*     */   public void setQuarter(int quarter)
/*     */   {
/* 296 */     this.quarter = quarter;
/*     */   }
/*     */ 
/*     */   public int getYear()
/*     */   {
/* 304 */     return this.year;
/*     */   }
/*     */ 
/*     */   public void setYear(int year)
/*     */   {
/* 311 */     this.year = year;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.marketmanager.targetmanager.companytarget.CompanyTarget
 * JD-Core Version:    0.6.2
 */