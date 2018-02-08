/*     */ package com.yongjun.tdms.dao.activitiFlow.hibernate;
import java.sql.SQLException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.activitiFlow.RunTaskDao;
import com.yongjun.tdms.model.activitiFlow.RunTask;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
/*     */ 
/*     */ public class HibernateRunTaskDao extends BaseHibernateDao
/*     */   implements RunTaskDao
/*     */ {
/*     */   public void deleteAllRunTask(Collection<RunTask> points)
/*     */   {
/*  45 */     super.deleteAll(points);
/*     */   }
/*     */ 
/*     */   public void deleteRunTask(RunTask point)
/*     */   {
/*  55 */     super.delete(point);
/*     */   }
/*     */ 
/*     */   public List<RunTask> loadAllRunTask(Long[] pointIds)
/*     */   {
/*  66 */     return super.loadAll(RunTask.class, pointIds);
/*     */   }
/*     */ 
/*     */   public List<RunTask> loadAllRunTask()
/*     */   {
/*  75 */     return super.loadAll(RunTask.class);
/*     */   }
/*     */ 
/*     */   public List<RunTask> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/*  88 */     return super.loadByKey(RunTask.class, keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<RunTask> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 101 */     return super.loadByKeyArray(RunTask.class, keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public RunTask loadRunTask(Long pointId)
/*     */   {
/* 111 */     return (RunTask)super.load(RunTask.class, pointId);
/*     */   }
/*     */ 
/*     */   public void storeRunTask(RunTask point)
/*     */   {
/* 120 */     super.store(point);
/*     */   }
/*     */
			public void storeBussnessState(StartActiviti saActiviti) {
				
					String sql = "update "+saActiviti.getBussnessType()+" set status="+saActiviti.getBussnessCode().getId()+" where id ="+saActiviti.getBussnessId();
					this.getSession().createSQLQuery(sql).executeUpdate();
				
			}
			public List<String> loadCodySendPerson(final StartActiviti startActiviti) {
				
				
				return getHibernateTemplate().executeFind(new HibernateCallback() {
					
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						String sql = "select cast(p.CODE as varchar) from "+startActiviti.getFlow().getFlowCode().getCode()+"  l,t_CopySendPerson c ,t_personnelFiles p where l.ID = c.bussnessId  and c.person = p.Id and c.bussnessId = "+startActiviti.getBussnessId();
						
						return session.createSQLQuery(sql).list();
					}
				});
			}

}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.hibernate.HibernatePointDao
 * JD-Core Version:    0.6.2
 */