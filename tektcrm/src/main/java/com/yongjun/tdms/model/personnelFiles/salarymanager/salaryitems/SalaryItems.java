/*     */ package com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ 
/*     */ public class SalaryItems extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = -366175309059628074L;
/*     */   private String code;
/*     */   private String name;
/*     */   private CodeValue type;
/*     */   private CodeValue orders;
/*     */   private String introduction;
/*     */   private String remark;
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  46 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  55 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getIntroduction()
/*     */   {
/*  63 */     return this.introduction;
/*     */   }
/*     */ 
/*     */   public void setIntroduction(String introduction)
/*     */   {
/*  72 */     this.introduction = introduction;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  80 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  89 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public CodeValue getOrders()
/*     */   {
/*  98 */     return this.orders;
/*     */   }
/*     */ 
/*     */   public void setOrders(CodeValue orders)
/*     */   {
/* 107 */     this.orders = orders;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 115 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 124 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public CodeValue getType()
/*     */   {
/* 132 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(CodeValue type)
/*     */   {
/* 141 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 160 */     return 0;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems.SalaryItems
 * JD-Core Version:    0.6.2
 */