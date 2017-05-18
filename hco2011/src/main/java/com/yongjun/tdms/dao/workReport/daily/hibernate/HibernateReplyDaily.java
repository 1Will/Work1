/*     */ package com.yongjun.tdms.dao.workReport.daily.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.daily.ReplyDailyDao;
/*     */ import com.yongjun.tdms.model.workReport.daily.ReplyDaily;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateReplyDaily extends BaseHibernateDao
/*     */   implements ReplyDailyDao
/*     */ {
/*     */   public void storeReplyDaily(ReplyDaily replyDaily)
/*     */   {
/*  44 */     super.store(replyDaily);
/*     */   }
/*     */ 	
/*     */   public void deleteReplyDaily(ReplyDaily replyDaily)
/*     */   {
/*  53 */     super.delete(replyDaily);
/*     */   }
/*     */ 	
/*     */   public void deleteAllReplyDaily(Collection<ReplyDaily> replyDaily)
/*     */   {
/*  62 */     super.deleteAll(replyDaily);
/*     */   }
/*     */ 	
/*     */   public List<ReplyDaily> loadAllReplyDaily(Long[] replyDailyIds)
/*     */   {
/*  72 */     return super.loadAll(ReplyDaily.class, replyDailyIds);
/*     */   }
/*     */ 	
/*     */   public ReplyDaily loadReplyDaily(Long replyDailyId)
/*     */   {
/*  82 */     return (ReplyDaily)super.load(ReplyDaily.class, replyDailyId);
/*     */   }
/*     */ 	
/*     */   public List<ReplyDaily> loadAllReplyDaily()
/*     */   {
/*  91 */     return super.loadAll(ReplyDaily.class);
/*     */   }
/*     */ 	
/*     */   public List<ReplyDaily> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 102 */     return super.loadByKey(ReplyDaily.class, keyName, keyValue);
/*     */   }
/*     */ 	
/*     */   public List<ReplyDaily> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 113 */     return super.loadByKeyArray(ReplyDaily.class, keyNames, keyValues);
/*     */   }
/*     */ }

