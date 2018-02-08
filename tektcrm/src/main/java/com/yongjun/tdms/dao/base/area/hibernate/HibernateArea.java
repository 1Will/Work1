/*    */ package com.yongjun.tdms.dao.base.area.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.tdms.dao.base.area.AreaDao;
/*    */ import com.yongjun.tdms.model.base.area.Area;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class HibernateArea extends BaseHibernateDao
/*    */   implements AreaDao
/*    */ {
/*    */   public void deleteAllArea(Collection<Area> areas)
/*    */   {
/* 22 */     super.deleteAll(areas);
/*    */   }
/*    */ 
/*    */   public void deleteArea(Area area)
/*    */   {
/* 27 */     super.delete(area);
/*    */   }
/*    */ 
/*    */   public List<Area> loadAllArea(Long[] areaIds) {
/* 31 */     return super.loadAll(Area.class, areaIds);
/*    */   }
/*    */ 
/*    */   public List<Area> loadAllAreas()
/*    */   {
/* 36 */     return super.loadAll(Area.class);
/*    */   }
/*    */ 
/*    */   public Area loadArea(Long areaId)
/*    */   {
/* 41 */     return (Area)super.load(Area.class, areaId);
/*    */   }
/*    */ 
/*    */   public void storeArea(Area area)
/*    */   {
/* 46 */     super.store(area);
/*    */   }
/*    */ 
/*    */   public List<Area> loadByKey(String key, Object value)
/*    */     throws DaoException
/*    */   {
/* 57 */     if (value.equals("province")) {
/* 58 */       String hql = "select area from Area as area where area." + key + " = '" + value + "' order by area.parentArea.id asc";
/* 59 */       return getSession().createQuery(hql).list();
/*    */     }
/* 61 */     return super.loadByKey(Area.class, key, value);
/*    */   }
/*    */ 
/*    */   public Long getLatestLogo()
/*    */   {
/* 73 */     String hql = "select max(a.id) from Area a";
/* 74 */     return (Long)getSession().createQuery(hql).uniqueResult();
/*    */   }
/*    */ 
/*    */   public List<Area> loadAreaKeyProperty(Long value)
/*    */   {
/* 86 */     String hql = "select a.id,a.name from Area as a where a.parentArea.id = " + value;
/* 87 */     return getSession().createQuery(hql).list();
/*    */   }
/*    */ 
/*    */   public List<Area> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 91 */     return loadByKeyArray(Area.class, keyNames, keyValues);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.area.hibernate.HibernateArea
 * JD-Core Version:    0.6.2
 */