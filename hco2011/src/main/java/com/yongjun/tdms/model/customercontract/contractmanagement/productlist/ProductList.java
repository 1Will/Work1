/*     */ package com.yongjun.tdms.model.customercontract.contractmanagement.productlist;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.tdms.model.base.products.Products;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ 
/*     */ public class ProductList extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ContractManagement contractManagement;
/*     */   private Products product;
/*     */   private CodeValue unit;
/*  31 */   private int count = 1;
/*     */   private double unitPrice;
/*  39 */   private double discount = 100.0D;
/*     */   private double totalPrice;
/*     */   private String remark;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  64 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getCount()
/*     */   {
/*  72 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(int count)
/*     */   {
/*  79 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public double getDiscount()
/*     */   {
/*  87 */     return this.discount;
/*     */   }
/*     */ 
/*     */   public void setDiscount(double discount)
/*     */   {
/*  94 */     this.discount = discount;
/*     */   }
/*     */ 
/*     */   public Products getProduct()
/*     */   {
/* 102 */     return this.product;
/*     */   }
/*     */ 
/*     */   public void setProduct(Products product)
/*     */   {
/* 109 */     this.product = product;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 117 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 124 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public double getTotalPrice()
/*     */   {
/* 132 */     return this.totalPrice;
/*     */   }
/*     */ 
/*     */   public void setTotalPrice(double totalPrice)
/*     */   {
/* 139 */     this.totalPrice = totalPrice;
/*     */   }
/*     */ 
/*     */   public CodeValue getUnit()
/*     */   {
/* 147 */     return this.unit;
/*     */   }
/*     */ 
/*     */   public void setUnit(CodeValue unit)
/*     */   {
/* 154 */     this.unit = unit;
/*     */   }
/*     */ 
/*     */   public double getUnitPrice()
/*     */   {
/* 162 */     return this.unitPrice;
/*     */   }
/*     */ 
/*     */   public void setUnitPrice(double unitPrice)
/*     */   {
/* 169 */     this.unitPrice = unitPrice;
/*     */   }
/*     */ 
/*     */   public ContractManagement getContractManagement()
/*     */   {
/* 177 */     return this.contractManagement;
/*     */   }
/*     */ 
/*     */   public void setContractManagement(ContractManagement contractManagement)
/*     */   {
/* 184 */     this.contractManagement = contractManagement;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList
 * JD-Core Version:    0.6.2
 */