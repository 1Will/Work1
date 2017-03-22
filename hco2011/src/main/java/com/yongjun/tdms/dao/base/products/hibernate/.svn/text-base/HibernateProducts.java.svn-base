/*    */ package com.yongjun.tdms.dao.base.products.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.base.products.ProductsDao;
/*    */ import com.yongjun.tdms.model.base.products.Products;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProducts extends BaseHibernateDao
/*    */   implements ProductsDao
/*    */ {
/*    */   public void deleteAllProducts(Collection<Products> productes)
/*    */   {
/* 23 */     deleteAll(productes);
/*    */   }
/*    */ 
/*    */   public void deleteProducts(Products products) {
/* 27 */     delete(products);
/*    */   }
/*    */ 
/*    */   public List<Products> loadAllProducts(Long[] productsIds) {
/* 31 */     return loadAll(Products.class, productsIds);
/*    */   }
/*    */ 
/*    */   public List<Products> loadAllProducts() {
/* 35 */     return loadAll(Products.class);
/*    */   }
/*    */ 
/*    */   public Products loadProductType(Long productsId) {
/* 39 */     return (Products)load(Products.class, productsId);
/*    */   }
/*    */ 
/*    */   public void storeProducts(Products products) {
/* 43 */     store(products);
/*    */   }
/*    */ 
/*    */   public List<Products> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 47 */     return loadByKey(Products.class, keyName, keyValue);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.products.hibernate.HibernateProducts
 * JD-Core Version:    0.6.2
 */