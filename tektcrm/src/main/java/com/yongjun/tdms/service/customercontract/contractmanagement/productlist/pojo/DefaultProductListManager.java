/*     */ package com.yongjun.tdms.service.customercontract.contractmanagement.productlist.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customercontract.contractmanagement.productlist.ProductListDao;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultProductListManager extends BaseManager
/*     */   implements ProductListManager
/*     */ {
/*     */   private final ProductListDao dao;
/*     */ 
/*     */   public DefaultProductListManager(ProductListDao dao)
/*     */   {
/*  20 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeProductList(ProductList t)
/*     */   {
/*  27 */     this.dao.storeProductList(t);
/*     */   }
/*     */ 
/*     */   public ProductList loadProductList(Long id)
/*     */   {
/*  36 */     return this.dao.loadProductList(id);
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadProductList()
/*     */   {
/*  45 */     return this.dao.loadProductList();
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadAllProductList(Long[] tIds)
/*     */   {
/*  54 */     return this.dao.loadAllProductList(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteProductList(ProductList t)
/*     */   {
/*  62 */     this.dao.deleteProductList(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllProductList(List<ProductList> ts)
/*     */   {
/*  70 */     this.dao.deleteAllProductList(ts);
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  81 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  92 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 100 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllProductList(List<ProductList> ts)
/*     */   {
/* 107 */     for (ProductList productList : ts) {
/* 108 */       productList.setDisabled(true);
/* 109 */       this.dao.storeProductList(productList);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllProductList(List<ProductList> ts)
/*     */   {
/* 117 */     for (ProductList productList : ts) {
/* 118 */       productList.setDisabled(false);
/* 119 */       this.dao.storeProductList(productList);
/*     */     }
/*     */   }
/*     */ 
/*     */   public double getSumTotalPrice(long id)
/*     */     throws DaoException
/*     */   {
/* 130 */     return this.dao.getSumTotalPrice(id);
/*     */   }
/*     */
public boolean deliveryCountIsOrNot(int number ,long id,long iId) {
	return this.dao.deliveryCountIsOrNot(number,id,iId);
} }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.contractmanagement.productlist.pojo.DefaultProductListManager
 * JD-Core Version:    0.6.2
 */