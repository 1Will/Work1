/*    */ package com.yongjun.tdms.model.personnelFiles.salarymanager.salarydetail;
/*    */ 
/*    */ import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems.SalaryItems;
/*    */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarystandard.SalaryStandard;
/*    */ 
/*    */ public class SalaryDetail extends BaseInfoEntity
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private SalaryStandard salaryStandard;
/*    */   private SalaryItems salaryItems;
/*    */   private double basicMoney;
/*    */ 
/*    */   public boolean equals(Object arg0)
/*    */   {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */   public double getBasicMoney()
/*    */   {
/* 60 */     return this.basicMoney;
/*    */   }
/*    */ 
/*    */   public void setBasicMoney(double basicMoney)
/*    */   {
/* 67 */     this.basicMoney = basicMoney;
/*    */   }
/*    */ 
/*    */   public SalaryItems getSalaryItems()
/*    */   {
/* 75 */     return this.salaryItems;
/*    */   }
/*    */ 
/*    */   public void setSalaryItems(SalaryItems salaryItems)
/*    */   {
/* 82 */     this.salaryItems = salaryItems;
/*    */   }
/*    */ 
/*    */   public SalaryStandard getSalaryStandard()
/*    */   {
/* 90 */     return this.salaryStandard;
/*    */   }
/*    */ 
/*    */   public void setSalaryStandard(SalaryStandard salaryStandard)
/*    */   {
/* 97 */     this.salaryStandard = salaryStandard;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.salarymanager.salarydetail.SalaryDetail
 * JD-Core Version:    0.6.2
 */