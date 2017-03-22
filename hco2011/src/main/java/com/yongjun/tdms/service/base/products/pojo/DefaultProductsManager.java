/*    */ package com.yongjun.tdms.service.base.products.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.base.products.ProductsDao;
/*    */ import com.yongjun.tdms.model.base.products.Products;
/*    */ import com.yongjun.tdms.service.base.products.ProductsManager;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProductsManager extends BaseManager
/*    */   implements ProductsManager
/*    */ {
/*    */   private final ProductsDao productsDao;
/*    */ 
/*    */   public DefaultProductsManager(ProductsDao productsDao)
/*    */   {
/* 20 */     this.productsDao = productsDao;
/*    */   }
/*    */ 
/*    */   public void deleteAllProducts(List<Products> list)
/*    */   {
/* 27 */     this.productsDao.deleteAllProducts(list);
/*    */   }
/*    */ 
/*    */   public void deleteProducts(Products products)
/*    */   {
/* 34 */     this.productsDao.deleteProducts(products);
/*    */   }
/*    */ 
/*    */   public List<Products> loadAllProducts(Long[] ids)
/*    */   {
/* 42 */     return this.productsDao.loadAllProducts(ids);
/*    */   }
/*    */ 
/*    */   public Products loadProducts(Long id)
/*    */   {
/* 50 */     return this.productsDao.loadProductType(id);
/*    */   }
/*    */ 
/*    */   public void storeProducts(Products products)
/*    */   {
/* 57 */     this.productsDao.storeProducts(products);
/*    */   }
/*    */ 
/*    */   public void disabledAllProducts(Collection<Products> products)
/*    */   {
/* 64 */     for (Products product : products) {
/* 65 */       product.setDisabled(true);
/* 66 */       this.productsDao.storeProducts(product);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enabledAllProducts(Collection<Products> products)
/*    */   {
/* 74 */     for (Products product : products) {
/* 75 */       product.setDisabled(false);
/* 76 */       this.productsDao.storeProducts(product);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<Products> getAllProductsByNull(String name)
/*    */   {
/* 84 */     List list = this.productsDao.loadAllProducts();
/* 85 */     Products p = new Products();
/* 86 */     p.setId(Long.valueOf(-1L));
/* 87 */     p.setName(name);
/* 88 */     list.add(0, p);
/* 89 */     return list;
/*    */   }
/*    */ 
/*    */   public List<Products> loadByKey(String keyName, Object keyValue)
/*    */     throws DaoException
/*    */   {
/* 95 */     return this.productsDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.products.pojo.DefaultProductsManager
 * JD-Core Version:    0.6.2
 */