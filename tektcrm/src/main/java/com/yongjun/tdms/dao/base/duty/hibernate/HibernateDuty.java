/*     */ package com.yongjun.tdms.dao.base.duty.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.base.duty.DutyDao;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateDuty extends BaseHibernateDao
/*     */   implements DutyDao
/*     */ {
/*     */   public void storeDuty(Duty duty)
/*     */   {
/*  44 */     super.store(duty);
/*     */   }
/*     */ 
/*     */   public void deleteDuty(Duty duty)
/*     */   {
/*  53 */     super.delete(duty);
/*     */   }
/*     */ 
/*     */   public void deleteAllDuty(Collection<Duty> duty)
/*     */   {
/*  62 */     super.deleteAll(duty);
/*     */   }
/*     */ 
/*     */   public List<Duty> loadAllDuty(Long[] dutyIds)
/*     */   {
/*  72 */     return super.loadAll(Duty.class, dutyIds);
/*     */   }
/*     */ 
/*     */   public Duty loadDuty(Long dutyId)
/*     */   {
/*  82 */     return (Duty)super.load(Duty.class, dutyId);
/*     */   }
/*     */ 
/*     */   public List<Duty> loadAllDuty()
/*     */   {
/*  91 */     return super.loadAll(Duty.class);
/*     */   }
/*     */ 
/*     */   public List<Duty> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(Duty.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Duty> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(Duty.class, keyNames, keyValues);
/*     */   }
			public  List<Duty> loadByDeptId(String deptid)
/*     */     throws DaoException
/*     */   {
/* 113 */      String hql = " from Duty a where a.dept.id = " + deptid;
/* 87 */     return getSession().createQuery(hql).list();
/*     */   }
/*     */
			public List<Duty> loadByDutyId(String name1, Long code2) {
//				String hql = "select p from PersonnelFiles p  where p.dept.id = "+name1+" and p.duty.id = "+code2+"";
				String hql = "select d from Duty d where d.name = '"+name1+"' and d.dept.id = "+code2;
			    return  getSession().createQuery(hql).list();
			} }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.duty.hibernate.HibernateDuty
 * JD-Core Version:    0.6.2
 */