/*     */ package com.yongjun.tdms.service.base.jobs.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.base.jobs.JobsDao;
/*     */ import com.yongjun.tdms.model.base.jobs.Jobs;
/*     */ import com.yongjun.tdms.service.base.jobs.JobsManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultJobsManager extends BaseManager
/*     */   implements JobsManager
/*     */ {
/*     */   private JobsDao jobsDao;
private final YongJunSequenceManager yongJunSequenceManager;
/*     */ 
/*     */   public DefaultJobsManager(JobsDao jobsDao,YongJunSequenceManager yongJunSequenceManager)
/*     */   {
/*  50 */     this.jobsDao = jobsDao;
              this.yongJunSequenceManager=yongJunSequenceManager;
/*     */   }
/*     */ 
/*     */   public void storeJob(Jobs job)
/*     */   {
	if(job.isNew()){
		job.setCode((String)this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_JOB));
	    }
	
/*  55 */     this.jobsDao.storeJobs(job);
/*     */   }
/*     */ 
/*     */   public void disabledAllJobs(List<Jobs> jobsList)
/*     */   {
/*  60 */     for (Jobs job : jobsList)
/*     */     {
/*  62 */       job.setDisabled(true);
/*  63 */       this.jobsDao.storeJobs(job);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllJobs(List<Jobs> jobsList)
/*     */   {
/*  69 */     for (Jobs job : jobsList)
/*     */     {
/*  71 */       job.setDisabled(false);
/*  72 */       this.jobsDao.storeJobs(job);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadAllJobs()
/*     */   {
/*  78 */     return this.jobsDao.loadAllJobs();
/*     */   }
/*     */ 
/*     */   public Jobs loadJobs(Long jobsId)
/*     */   {
/*  83 */     return this.jobsDao.loadJobs(jobsId);
/*     */   }
/*     */ 
/*     */   public void delete(Jobs job)
/*     */   {
/*  88 */     this.jobsDao.deleteJobs(job);
/*     */   }
/*     */ 
/*     */   public void deleteAllJobs(List<Jobs> jobsList)
/*     */   {
/*  93 */     this.jobsDao.deleteAllJobs(jobsList);
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadByKey(String keyName, Object keyValue) throws DaoException
/*     */   {
/*  98 */     return this.jobsDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadAllJobs(Long[] jobIds)
/*     */   {
/* 104 */     return this.jobsDao.loadAllJobs(jobIds);
/*     */   }
/*     */ 
/*     */   public List<Jobs> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 116 */     return this.jobsDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.jobs.pojo.DefaultJobsManager
 * JD-Core Version:    0.6.2
 */