/*     */ package com.yongjun.tdms.presentation.webwork.action.supplier.supplierAptitudes;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.model.supplier.supplierAptitudes.SupplierAptitudes;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import com.yongjun.tdms.service.supplier.supplierAptitudes.SupplierAptitudesManager;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class EditSupplierAptitudesAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 6319367396099686081L;
/*     */   private final SupplierAptitudesManager supplierAptitudesManager;
/*     */   private final SupplierManager supplierManager;
/*     */   private SupplierAptitudes supplierAptitudes;
/*     */   private Supplier supplier;
/*     */ 
/*     */   public EditSupplierAptitudesAction(SupplierAptitudesManager supplierAptitudesManager, SupplierManager supplierManager)
/*     */   {
/*  56 */     this.supplierAptitudesManager = supplierAptitudesManager;
/*  57 */     this.supplierManager = supplierManager;
/*     */   }
/*     */ 
/*     */   public void prepare() throws Exception {
/*  61 */     if ((this.supplier == null) && (hasId("supplier.id"))) {
/*  62 */       this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
/*     */     }
/*  64 */     if ((this.supplierAptitudes == null) && (hasId("supplierAptitudes.id"))) {
/*  65 */       this.supplierAptitudes = this.supplierAptitudesManager.loadSupplierAptitudes(getId("supplierAptitudes.id"));
/*     */     }
/*     */     else
/*  68 */       this.supplierAptitudes = new SupplierAptitudes();
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*  73 */     boolean isNew = this.supplierAptitudes.isNew();
/*     */     try {
/*  75 */       if (isNew)
/*     */       {
/*  77 */         if (null == this.supplierAptitudesManager.loadByKey("name", this.supplierAptitudes.getName()))
/*     */         {
/*  79 */           this.supplierAptitudes.setSupplier(this.supplier);
/*  80 */           this.supplierAptitudesManager.storeSupplierAptitudes(this.supplierAptitudes);
/*     */         } else {
/*  82 */           addActionMessage(getText("supplierAptitudesName.add.exist", Arrays.asList(new Object[] { this.supplierAptitudes.getName() })));
/*     */ 
/*  85 */           return "error";
/*     */         }
/*     */       }
/*  88 */       else this.supplierAptitudesManager.storeSupplierAptitudes(this.supplierAptitudes); 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  91 */       e.printStackTrace();
/*  92 */       addActionMessage(getText("supplierAptitudesName.add.error", Arrays.asList(new Object[] { this.supplierAptitudes.getName() })));
/*     */ 
/*  95 */       return "error";
/*     */     }
/*     */ 
/*  98 */     if (isNew) {
/*  99 */       addActionMessage(getText("supplierAptitudesName.add.success", Arrays.asList(new Object[] { this.supplierAptitudes.getName() })));
/*     */ 
/* 102 */       return "new";
/*     */     }
/* 104 */     addActionMessage(getText("supplierAptitudesName.edit.success", Arrays.asList(new Object[] { this.supplierAptitudes.getName() })));
/*     */ 
/* 107 */     return "success";
/*     */   }
/*     */ 
/*     */   public SupplierAptitudes getSupplierAptitudes()
/*     */   {
/* 112 */     return this.supplierAptitudes;
/*     */   }
/*     */ 
/*     */   public void setSupplierAptitudes(SupplierAptitudes supplierAptitudes) {
/* 116 */     this.supplierAptitudes = supplierAptitudes;
/*     */   }
/*     */ 
/*     */   public Supplier getSupplier() {
/* 120 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplier(Supplier supplier) {
/* 124 */     this.supplier = supplier;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.supplier.supplierAptitudes.EditSupplierAptitudesAction
 * JD-Core Version:    0.6.2
 */