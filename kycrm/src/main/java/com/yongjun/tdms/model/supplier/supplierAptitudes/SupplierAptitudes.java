/*    */ package com.yongjun.tdms.model.supplier.supplierAptitudes;
/*    */ 
/*    */ import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*    */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*    */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*    */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*    */ import com.yongjun.tdms.model.supplier.Supplier;
/*    */ 
/*    */ public class SupplierAptitudes extends BaseInfoEntity
/*    */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*    */ {
/*    */   private static final long serialVersionUID = 4171106622796721521L;
/*    */   private String name;
/*    */   private Supplier supplier;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 50 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 54 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public Supplier getSupplier() {
/* 58 */     return this.supplier;
/*    */   }
/*    */ 
/*    */   public void setSupplier(Supplier supplier) {
/* 62 */     this.supplier = supplier;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object arg0)
/*    */   {
/* 68 */     return false;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 74 */     return 0;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.supplier.supplierAptitudes.SupplierAptitudes
 * JD-Core Version:    0.6.2
 */