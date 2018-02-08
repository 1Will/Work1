/*     */ package com.yongjun.tdms.service.customerservicemanagement.repairs.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customerservicemanagement.repairs.RepairsDao;
/*     */ import com.yongjun.tdms.model.customerservicemanagement.repairs.Repairs;
/*     */ import com.yongjun.tdms.service.customerservicemanagement.repairs.RepairsManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultRepairsManager extends BaseManager
/*     */   implements RepairsManager
/*     */ {
/*     */   private final RepairsDao dao;
/*     */ 
/*     */   public DefaultRepairsManager(RepairsDao dao)
/*     */   {
/*  23 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storeRepairs(Repairs t)
/*     */   {
/*  33 */     this.dao.storeRepairs(t);
/*     */   }
/*     */ 
/*     */   public Repairs loadRepairs(Long id)
/*     */   {
/*  42 */     return this.dao.loadRepairs(id);
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadRepairs()
/*     */   {
/*  50 */     return this.dao.loadRepairs();
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadAllRepairs(Long[] tIds)
/*     */   {
/*  60 */     return this.dao.loadAllRepairs(tIds);
/*     */   }
/*     */ 
/*     */   public void deleteRepairs(Repairs t)
/*     */   {
/*  68 */     this.dao.deleteRepairs(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllRepairs(List<Repairs> ts)
/*     */   {
/*  76 */     this.dao.deleteAllRepairs(ts);
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  87 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<Repairs> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  98 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 106 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllRepairs(List<Repairs> ts)
/*     */   {
/* 113 */     for (Repairs contractManagement : ts) {
/* 114 */       contractManagement.setDisabled(true);
/* 115 */       this.dao.storeRepairs(contractManagement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllRepairs(List<Repairs> ts)
/*     */   {
/* 124 */     for (Repairs contractManagement : ts) {
/* 125 */       contractManagement.setDisabled(false);
/* 126 */       this.dao.storeRepairs(contractManagement);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customerservicemanagement.repairs.pojo.DefaultRepairsManager
 * JD-Core Version:    0.6.2
 */