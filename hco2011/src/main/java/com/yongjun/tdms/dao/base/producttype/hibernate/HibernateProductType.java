/*     */ package com.yongjun.tdms.dao.base.producttype.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.base.producttype.ProductTypeDao;
/*     */ import com.yongjun.tdms.model.base.produttype.ProductType;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateProductType extends BaseHibernateDao
/*     */   implements ProductTypeDao
/*     */ {
/*     */   public void deleteAllProductType(Collection<ProductType> productTypes)
/*     */   {
/*  27 */     deleteAll(productTypes);
/*     */   }
/*     */   public void deleteProductType(ProductType productType) {
/*  30 */     delete(productType);
/*     */   }
/*     */ 
/*     */   public List<ProductType> loadAllProductType(Long[] productTypeIds) {
/*  34 */     return loadAll(ProductType.class, productTypeIds);
/*     */   }
/*     */ 
/*     */   public List<ProductType> loadAllProductType() {
/*  38 */     return loadAll(ProductType.class);
/*     */   }
/*     */ 
/*     */   public void storeProductType(ProductType productType) {
/*  42 */     store(productType);
/*     */   }
/*     */ 
/*     */   public ProductType loadProductType(Long productTypeId) {
/*  46 */     return (ProductType)load(ProductType.class, productTypeId);
/*     */   }
/*     */ 
/*     */   public List<ProductType> loadByKey(String keyName, Object keyValue) throws DaoException {
/*  50 */     return loadByKey(ProductType.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Integer> getStepAfterGroupingByStep() {
/*  54 */     Session session = getSession();
/*  55 */     String hql = "select pt.step from productType as pt group by pt.step order by pt.step";
/*     */     try {
/*  57 */       Query query = session.createQuery(hql);
/*  58 */       List list = query.list();
/*  59 */       return list;
/*     */     } finally {
/*  61 */       releaseSession(session);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getMaxPTId() {
/*  66 */     ResultSet rs = null;
/*  67 */     Session session = getSession();
/*  68 */     String sql = "select max(ID) from t_productType";
/*     */     try {
/*  70 */       rs = session.connection().createStatement().executeQuery(sql);
/*     */       int i;
/*  71 */       if (rs.next()) {
/*  72 */         return Integer.valueOf(rs.getString(1)).intValue();
/*     */       }
/*  74 */       return 0;
/*     */     } catch (SQLException e) {
/*  76 */       e.printStackTrace();
/*  77 */       return 0;
/*     */     } finally {
/*  79 */       releaseSession(session);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<ProductType> loadAllProductTypes()
/*     */   {
/* 105 */     return loadAll(ProductType.class);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.producttype.hibernate.HibernateProductType
 * JD-Core Version:    0.6.2
 */