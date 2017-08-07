/*    */ package com.yongjun.tdms.dao.workspace.ontheroadBill.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.workspace.ontheroadBill.OnTheRoadBillDao;
/*    */ import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class HibernateOnTheRoadBillDao extends BaseHibernateDao
/*    */   implements OnTheRoadBillDao
/*    */ {
/*    */   public void deleteAllOnTheRoadBill(Collection<OnTheRoadBill> otrbs)
/*    */   {
/* 21 */     deleteAll(otrbs);
/*    */   }
/*    */ 
/*    */   public void deleteOnTheRoadBill(OnTheRoadBill otrb) {
/* 25 */     delete(otrb);
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadAllOnTheRoadBill(Long[] otrbIds) {
/* 29 */     return loadAll(OnTheRoadBill.class, otrbIds);
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadAllOnTheRoadBill() {
/* 33 */     return loadAll(OnTheRoadBill.class);
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 38 */     return loadByKey(OnTheRoadBill.class, keyName, keyName);
/*    */   }
/*    */ 
/*    */   public List<OnTheRoadBill> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 43 */     return loadByKeyArray(OnTheRoadBill.class, keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public OnTheRoadBill loadOnTheRoadBill(Long otrbId) {
/* 47 */     return (OnTheRoadBill)load(OnTheRoadBill.class, otrbId);
/*    */   }
/*    */ 
/*    */   public void storeOnTheRoadBill(OnTheRoadBill otrb) {
/* 51 */     store(otrb);
/*    */   }
/*    */ 
/*    */   public String getMaxPFCode(String code, Long orgId)
/*    */   {
/* 61 */     String hql = "select otrb.code from OnTheRoadBill as otrb where otrb.organization.id=" + orgId + " and otrb.code like '%" + code + "%'";
/*    */ 
/* 63 */     List codeList = getSession().createQuery(hql).list();
/* 64 */     if (null!= codeList && codeList.size() > 0) {
/* 65 */       List items = new ArrayList();
/* 66 */       for (int i = 0; i < codeList.size(); i++) {
/* 67 */         String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("-") + 1);
/* 68 */         items.add(item);
/*    */       }
/* 70 */       Collections.sort(items);
/* 71 */       return (String)items.get(items.size() - 1);
/*    */     }
/* 73 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.ontheroadBill.hibernate.HibernateOnTheRoadBillDao
 * JD-Core Version:    0.6.2
 */