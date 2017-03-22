/*     */ package com.yongjun.tdms.service.supplier.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.sequence.service.SequenceManager;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.supplier.SupplierDao;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*     */ import com.yongjun.tdms.service.supplier.SupplierManager;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSupplierManager extends BaseManager
/*     */   implements SupplierManager
/*     */ {
/*     */   private final SupplierDao supplierDao;
/*     */   private final SequenceManager supplierNoSequenceManager;
/*     */   private final ProductsManager productsManager;
/*     */ 
/*     */   public DefaultSupplierManager(SupplierDao supplierDao, SequenceManager supplierNoSequenceManager, ProductsManager productsManager)
/*     */   {
/*  49 */     this.supplierDao = supplierDao;
/*  50 */     this.supplierNoSequenceManager = supplierNoSequenceManager;
/*  51 */     this.productsManager = productsManager;
/*     */   }
/*     */ 
/*     */   public void deleteAllSuppliers(Collection<Supplier> supplierIds) {
/*  55 */     this.supplierDao.deleteAllSuppliers(supplierIds);
/*     */   }
/*     */ 
/*     */   public void deleteSupplier(Supplier supplier) {
/*  59 */     this.supplierDao.deleteSupplier(supplier);
/*     */   }
/*     */ 
/*     */   public List<Supplier> loadAllSupplier(Long[] supplierIds) {
/*  63 */     return this.supplierDao.loadAllSupplier(supplierIds);
/*     */   }
/*     */ 
/*     */   public List<Supplier> loadAllSupplier() {
/*  67 */     return this.supplierDao.loadAllSupplier();
/*     */   }
/*     */ 
/*     */   public Supplier loadSupplier(Long supplierId) {
/*  71 */     return this.supplierDao.loadSupplier(supplierId);
/*     */   }
/*     */ 
/*     */   public void storeSupplier(Supplier supplier) {
/*  75 */     if (supplier.isNew()) {
/*  76 */       supplier.setSupplierNo((String)this.supplierNoSequenceManager.generate("-"));
/*     */     }
/*  78 */     this.supplierDao.storeSupplier(supplier);
/*     */   }
/*     */ 
/*     */   public boolean disabledAllSupplier(Collection<Supplier> suppliers) {
/*  82 */     Iterator i$ = suppliers.iterator(); if (i$.hasNext()) { Supplier supplier = (Supplier)i$.next();
/*  83 */       List list = null;
/*     */       try {
/*  85 */         list = this.productsManager.loadByKey("supplier", supplier.getId());
/*     */       } catch (DaoException e) {
/*  87 */         e.printStackTrace();
/*     */       }
/*  89 */       if (null != list) {
/*  90 */         return false;
/*     */       }
/*  92 */       supplier.setDisabled(true);
/*  93 */       this.supplierDao.storeSupplier(supplier);
/*  94 */       return true;
/*     */     }
/*     */ 
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   public void enabledAllSupplier(Collection<Supplier> suppliers) {
/* 101 */     for (Supplier supplier : suppliers) {
/* 102 */       supplier.setDisabled(false);
/* 103 */       this.supplierDao.storeSupplier(supplier);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Supplier> loadByKey(String keyName, Object supplier) throws DaoException {
/* 108 */     return this.supplierDao.loadByKey(keyName, supplier);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.supplier.pojo.DefaultSupplierManager
 * JD-Core Version:    0.6.2
 */