/*    */ package com.yongjun.tdms.dao.supplier.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.supplier.SupplierDao;
/*    */ import com.yongjun.tdms.model.supplier.Supplier;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HiberanteSupplier extends BaseHibernateDao
/*    */   implements SupplierDao
/*    */ {
/*    */   public void deleteAllSuppliers(Collection<Supplier> supplierIds)
/*    */   {
/* 39 */     deleteAll(supplierIds);
/*    */   }
/*    */ 
/*    */   public void deleteSupplier(Supplier supplier) {
/* 43 */     delete(supplier);
/*    */   }
/*    */ 
/*    */   public List<Supplier> loadAllSupplier(Long[] supplierIds) {
/* 47 */     return loadAll(Supplier.class, supplierIds);
/*    */   }
/*    */ 
/*    */   public List<Supplier> loadAllSupplier() {
/* 51 */     return loadAll(Supplier.class);
/*    */   }
/*    */ 
/*    */   public Supplier loadSupplier(Long supplierId) {
/* 55 */     return (Supplier)load(Supplier.class, supplierId);
/*    */   }
/*    */ 
/*    */   public void storeSupplier(Supplier supplier) {
/* 59 */     store(supplier);
/*    */   }
/*    */ 
/*    */   public List<Supplier> loadByKey(String keyName, Object supplier) throws DaoException {
/* 63 */     return loadByKey(Supplier.class, keyName, supplier);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.supplier.hibernate.HiberanteSupplier
 * JD-Core Version:    0.6.2
 */