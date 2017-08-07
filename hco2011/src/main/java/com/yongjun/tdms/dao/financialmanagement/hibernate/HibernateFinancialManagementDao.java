/*     */ package com.yongjun.tdms.dao.financialmanagement.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.financialmanagement.FinancialManagementDao;
/*     */ import com.yongjun.tdms.model.financialmanagement.FinancialManagement;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
import java.util.HashMap;
/*     */ import java.util.List;

/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class HibernateFinancialManagementDao extends BaseHibernateDao
/*     */   implements FinancialManagementDao
/*     */ {
/*     */   public void storeFinancialManagement(FinancialManagement financialManagement)
/*     */   {
/*  24 */     store(financialManagement);
/*     */   }
/*     */ 
/*     */   public FinancialManagement loadFinancialManagement(Long financialManagementId)
/*     */   {
/*  33 */     return (FinancialManagement)load(FinancialManagement.class, financialManagementId);
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadAllFinancialManagements()
/*     */   {
/*  41 */     return loadAll(FinancialManagement.class);
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadAllFinancialManagement(Long[] financialManagementIds)
/*     */   {
/*  50 */     return loadAll(FinancialManagement.class, financialManagementIds);
/*     */   }
/*     */ 
/*     */   public void deleteFinancialManagement(FinancialManagement financialManagement)
/*     */   {
/*  58 */     delete(financialManagement);
/*     */   }
/*     */ 
/*     */   public void deleteAllFinancialManagement(List<FinancialManagement> financialManagements)
/*     */   {
/*  66 */     deleteAll(financialManagements);
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  77 */     return loadByKey(FinancialManagement.class, key, value);
/*     */   }
/*     */ 
/*     */   public List<FinancialManagement> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  87 */     return loadByKeyArray(FinancialManagement.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  95 */     String hql = "select c.code from FinancialManagement as c where  c.code like '%" + code + "%'";
/*  96 */     List codeList = getSession().createQuery(hql).list();
/*  97 */     if (null!= codeList && codeList.size() > 0) {
/*  98 */       List items = new ArrayList();
/*  99 */       for (int i = 0; i < codeList.size(); i++) {
/* 100 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 101 */         items.add(item);
/*     */       }
/* 103 */       Collections.sort(items);
/* 104 */       return (String)items.get(items.size() - 1);
/*     */     }
/* 106 */     return null;
/*     */   }
/*     */
			public HashMap getDataMap(String staDate,String endDate){
			 HashMap map =new HashMap();
			 Session session =getSession();
			 String hqlCount ="select count(*) from FinancialManagement c where  c.collectionDate >= CAST('"+staDate+"' AS DATETIME )  AND c.collectionDate <= CAST('"+endDate+"' AS DATETIME )  ";
			 List countList =session.createQuery(hqlCount).list();
			 if ((null != countList) && (countList.size() > 0)) {
				 Integer object  =  (Integer) countList.get(0);
				 map.put("count",object);
			 }
			 String hqlMoney ="select isnull(sum(c.trueSum),0) from FinancialManagement c where  c.collectionDate >= CAST('"+staDate+"' AS DATETIME )  AND c.collectionDate <= CAST('"+endDate+"' AS DATETIME )  ";
			 List moneytList =session.createQuery(hqlMoney).list();
			 if ((null != moneytList) && (moneytList.size() > 0)) {
				 Double object  =  (Double) moneytList.get(0);
				 map.put("money", object);
			 }
			 return map;
	
}
		}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.financialmanagement.hibernate.HibernateFinancialManagementDao
 * JD-Core Version:    0.6.2
 */