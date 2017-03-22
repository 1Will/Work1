/*     */ package com.yongjun.tdms.service.workspace.warnning.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.security.User;
/*     */ import com.yongjun.pluto.util.DateUtil;
/*     */ import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDao;
/*     */ import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
/*     */ import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class DefaultWorkWarnningManager
/*     */   implements WorkWarnningManager
/*     */ {
/*  45 */   private final Log logger = LogFactory.getLog(getClass());
/*     */   private final WorkWarnningDao workWarnningDao;
/*     */ 
/*     */   public DefaultWorkWarnningManager(WorkWarnningDao workWarnningDao)
/*     */   {
/*  50 */     this.workWarnningDao = workWarnningDao;
/*     */   }
/*     */ 
/*     */   public WorkWarnning loadWorkWarnning(Long workWarnningId) {
/*  54 */     return this.workWarnningDao.loadWorkWarnning(workWarnningId);
/*     */   }
/*     */ 
/*     */   public List<WorkWarnning> loadAllWorkWarnnings(Long[] workWarnningIds) {
/*  58 */     return this.workWarnningDao.loadAllWorkWarnnings(workWarnningIds);
/*     */   }
/*     */ 
/*     */   public List<WorkWarnning> loadAllWorkWarnnings() {
/*  62 */     return this.workWarnningDao.loadAllWorkWarnnings();
/*     */   }
/*     */ 
/*     */   public void storeWorkWarnning(WorkWarnning workWarnning) {
/*  66 */     this.workWarnningDao.storeWorkWarnning(workWarnning);
/*     */   }
/*     */ 
/*     */   public void deleteWorkWarnning(WorkWarnning workWarnning) {
/*  70 */     this.workWarnningDao.deleteWorkWarnning(workWarnning);
/*     */   }
/*     */ 
/*     */   public void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
/*  74 */     this.workWarnningDao.deleteAllWorkWarnnings(workWarnnings);
/*     */   }
/*     */ 
/*     */   public Integer GetNumberOfUnReadWarnningByUserID(Long userId) {
/*  78 */     return this.workWarnningDao.GetNumberOfUnReadWarnningByUserID(userId);
/*     */   }
/*     */ 
/*     */   public void readAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
/*  82 */     for (WorkWarnning warnning : workWarnnings)
/*     */     {
/*  84 */       warnning.setReadFlag(true);
/*  85 */       this.workWarnningDao.storeWorkWarnning(warnning);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void unReadAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
/*  90 */     for (WorkWarnning warnning : workWarnnings)
/*     */     {
/*  92 */       warnning.setReadFlag(false);
/*  93 */       this.workWarnningDao.storeWorkWarnning(warnning);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendWarnningMessage(List<User> warnningPeoples, String warnningType, String warnningContent) {
/*  98 */     if (null != warnningPeoples)
/*     */     {
/* 100 */       for (User warnningPeople : warnningPeoples)
/* 101 */         if ((null != warnningType) && (null != warnningContent)) {
/* 102 */           this.logger.info("start send warnning message for " + warnningPeople.getName());
/* 103 */           WorkWarnning warnning = new WorkWarnning();
/*     */ 
/* 105 */           warnning.setType(warnningType);
/*     */ 
/* 107 */           warnning.setWarnedPeople(warnningPeople);
/*     */ 
/* 109 */           warnning.setContent(warnningContent);
/*     */ 
/* 111 */           warnning.setWarnningDate(DateUtil.getSystemDate());
/*     */           try {
/* 113 */             this.workWarnningDao.storeWorkWarnning(warnning);
/*     */           } catch (Exception e) {
/* 115 */             this.logger.info("end send warnning message for " + warnningPeople.getName() + " send failure!");
/*     */           }
/* 117 */           this.logger.info("end send warnning message for " + warnningPeople.getName() + " send successful!");
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<WorkWarnning> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 123 */     return this.workWarnningDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public List<WorkWarnning> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 127 */     return this.workWarnningDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.warnning.pojo.DefaultWorkWarnningManager
 * JD-Core Version:    0.6.2
 */