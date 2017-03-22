/*    */ package com.yongjun.tdms.dao.workspace.leaveBill.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.workspace.leaveBill.LeaveBillDao;
/*    */ import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class HibernateLeaveBillDao extends BaseHibernateDao
/*    */   implements LeaveBillDao
/*    */ {
/*    */   public void deleteAllLeaveBill(Collection<LeaveBill> lbs)
/*    */   {
/* 17 */     deleteAll(lbs);
/*    */   }
/*    */ 
/*    */   public void deleteLeaveBill(LeaveBill lb) {
/* 21 */     delete(lb);
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadAllLeaveBill(Long[] lbIds) {
/* 25 */     return loadAll(LeaveBill.class, lbIds);
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadAllLeaveBill() {
/* 29 */     return loadAll(LeaveBill.class);
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 34 */     return loadByKey(LeaveBill.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<LeaveBill> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 39 */     return loadByKeyArray(LeaveBill.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public LeaveBill loadLeaveBill(Long lbId) {
/* 43 */     return (LeaveBill)get(LeaveBill.class, lbId);
/*    */   }
/*    */ 
/*    */   public void storeLeaveBill(LeaveBill lb) {
/* 47 */     store(lb);
/*    */   }
/*    */ 
/*    */   public String getMaxPFCode(String code, Long orgId)
/*    */   {
/* 57 */     String hql = "select lb.code from LeaveBill as lb where lb.organization.id=" + orgId + " and lb.code like '%" + code + "%'";
/*    */ 
/* 59 */     List codeList = getSession().createQuery(hql).list();
/* 60 */     if (codeList.size() > 0) {
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
 * Qualified Name:     com.yongjun.tdms.dao.workspace.leaveBill.hibernate.HibernateLeaveBillDao
 * JD-Core Version:    0.6.2
 */