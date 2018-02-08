/*    */ package com.yongjun.tdms.dao.workspace.overTimeBill.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.workspace.overTimeBill.OverTimeBillDao;
/*    */ import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class HibernateOverTimeBillDao extends BaseHibernateDao
/*    */   implements OverTimeBillDao
/*    */ {
/*    */   public void deleteAllOverTimeBill(Collection<OverTimeBill> otbs)
/*    */   {
/* 17 */     deleteAll(otbs);
/*    */   }
/*    */ 
/*    */   public void deleteOverTimeBill(OverTimeBill otb) {
/* 21 */     delete(otb);
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadAllOverTimeBill(Long[] otbIds) {
/* 25 */     return loadAll(OverTimeBill.class, otbIds);
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadAllOverTimeBill() {
/* 29 */     return loadAll(OverTimeBill.class);
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 34 */     return loadByKey(OverTimeBill.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<OverTimeBill> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 39 */     return loadByKeyArray(OverTimeBill.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public OverTimeBill loadOverTimeBill(Long otbId) {
/* 43 */     return (OverTimeBill)load(OverTimeBill.class, otbId);
/*    */   }
/*    */ 
/*    */   public void storeOverTimeBill(OverTimeBill otb) {
/* 47 */     store(otb);
/*    */   }
/*    */ 
/*    */   public String getMaxPFCode(String code, Long orgId)
/*    */   {
/* 57 */     String hql = "select otb.code from OverTimeBill as otb where otb.organization.id=" + orgId + " and otb.code like '%" + code + "%'";
/*    */ 
/* 59 */     List codeList = getSession().createQuery(hql).list();
/* 60 */     if (null!= codeList && codeList.size() > 0) {
/* 61 */       List items = new ArrayList();
/* 62 */       for (int i = 0; i < codeList.size(); i++) {
/* 63 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 64 */         items.add(item);
/*    */       }
/* 66 */       Collections.sort(items);
/* 67 */       return (String)items.get(items.size() - 1);
/*    */     }
/* 69 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.overTimeBill.hibernate.HibernateOverTimeBillDao
 * JD-Core Version:    0.6.2
 */