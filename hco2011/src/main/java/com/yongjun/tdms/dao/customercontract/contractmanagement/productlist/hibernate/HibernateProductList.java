/*     */ package com.yongjun.tdms.dao.customercontract.contractmanagement.productlist.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.customercontract.contractmanagement.productlist.ProductListDao;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateProductList extends BaseHibernateDao
/*     */   implements ProductListDao
/*     */ {
/*     */   public void storeProductList(ProductList t)
/*     */   {
/*  25 */     store(t);
/*     */   }
/*     */ 
/*     */   public ProductList loadProductList(Long id)
/*     */   {
/*  34 */     return (ProductList)load(ProductList.class, id);
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadProductList()
/*     */   {
/*  42 */     return loadAll(ProductList.class);
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadAllProductList(Long[] tIds)
/*     */   {
/*  51 */     return loadAll(ProductList.class, tIds);
/*     */   }
/*     */ 
/*     */   public void deleteProductList(ProductList t)
/*     */   {
/*  59 */     delete(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllProductList(List<ProductList> ts)
/*     */   {
/*  67 */     deleteAll(ts);
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  78 */     return loadByKey(ProductList.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<ProductList> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  88 */     return loadByKeyArray(ProductList.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  97 */     String hql = "select c.code from ProductList as c where  c.code like '%" + code + "%'";
/*  98 */     List codeList = getSession().createQuery(hql).list();
/*  99 */     if (null!=codeList && codeList.size() > 0) {
/* 100 */       List items = new ArrayList();
/* 101 */       for (int i = 0; i < codeList.size(); i++) {
/* 102 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 103 */         items.add(item);
/*     */       }
/* 105 */       Collections.sort(items);
/* 106 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */   public double getSumTotalPrice(long id)
/*     */     throws DaoException
/*     */   {
/* 118 */     String hql = "select sum(c.totalPrice) from ProductList as c where c.contractManagement.id=" + id;
/* 119 */     List codeList = getSession().createQuery(hql).list();
/* 120 */     if ((null != codeList) && (null!= codeList && codeList.size() > 0)) {
/* 121 */       Double item = (Double)codeList.get(0);
/* 122 */       if (null == item) {
/* 123 */         return 0.0D;
/*     */       }
/* 125 */       return item.doubleValue();
/*     */     }
/* 127 */     return 0.0D;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customercontract.contractmanagement.productlist.hibernate.HibernateProductList
 * JD-Core Version:    0.6.2
 */