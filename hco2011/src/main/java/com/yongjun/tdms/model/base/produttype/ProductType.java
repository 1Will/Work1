/*     */ package com.yongjun.tdms.model.base.produttype;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ProductType extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = -6303446246448716640L;
/*     */   private String code;
/*     */   private String name;
/*     */   private Integer step;
/*     */   private ProductType parentPT;
/*  36 */   private Set<ProductType> childPT = new HashSet();
/*     */ 
/*     */   public boolean equals(Object arg0) {
/*  39 */     if (arg0 == this) {
/*  40 */       return true;
/*     */     }
/*  42 */     if (!(arg0 instanceof ProductType)) {
/*  43 */       return false;
/*     */     }
/*     */ 
/*  46 */     ProductType pt = (ProductType)arg0;
/*     */ 
/*  48 */     if (!pt.getId().equals(getId())) {
/*  49 */       return false;
/*     */     }
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  58 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public Set<ProductType> getChildPT()
/*     */   {
/*  63 */     return this.childPT;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  68 */     return this.code;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  73 */     return this.name;
/*     */   }
/*     */ 
/*     */   public ProductType getParentPT()
/*     */   {
/*  78 */     return this.parentPT;
/*     */   }
/*     */ 
/*     */   public void setChildPT(Set<ProductType> childPT)
/*     */   {
/*  83 */     this.childPT = childPT;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  88 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  93 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setParentPT(ProductType parentPT)
/*     */   {
/*  98 */     this.parentPT = parentPT;
/*     */   }
/*     */ 
/*     */   public Integer getStep()
/*     */   {
/* 103 */     return this.step;
/*     */   }
/*     */ 
/*     */   public void setStep(Integer step)
/*     */   {
/* 108 */     this.step = step;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.produttype.ProductType
 * JD-Core Version:    0.6.2
 */