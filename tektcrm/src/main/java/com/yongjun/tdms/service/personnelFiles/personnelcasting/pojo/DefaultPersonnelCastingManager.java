/*     */ package com.yongjun.tdms.service.personnelFiles.personnelcasting.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.personnelFiles.personnelcasting.PersonnelCastingDao;
/*     */ import com.yongjun.tdms.model.personnelFiles.personnelcasting.PersonnelCasting;
/*     */ import com.yongjun.tdms.service.personnelFiles.personnelcasting.PersonnelCastingManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultPersonnelCastingManager extends BaseManager
/*     */   implements PersonnelCastingManager
/*     */ {
/*     */   private final PersonnelCastingDao dao;
/*     */ 
/*     */   public DefaultPersonnelCastingManager(PersonnelCastingDao dao)
/*     */   {
/*  19 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void storePersonnelCasting(PersonnelCasting t)
/*     */   {
/*  26 */     this.dao.storePersonnelCasting(t);
/*     */   }
/*     */ 
/*     */   public PersonnelCasting loadPersonnelCasting(Long id)
/*     */   {
/*  35 */     return this.dao.loadPersonnelCasting(id);
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadPersonnelCasting()
/*     */   {
/*  43 */     return this.dao.loadPersonnelCasting();
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadAllPersonnelCasting(Long[] tIds)
/*     */   {
/*  53 */     return this.dao.loadAllPersonnelCasting(tIds);
/*     */   }
/*     */ 
/*     */   public void deletePersonnelCasting(PersonnelCasting t)
/*     */   {
/*  61 */     this.dao.deletePersonnelCasting(t);
/*     */   }
/*     */ 
/*     */   public void deleteAllPersonnelCasting(List<PersonnelCasting> ts)
/*     */   {
/*  69 */     this.dao.deleteAllPersonnelCasting(ts);
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  80 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<PersonnelCasting> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  90 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/*  99 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ 
/*     */   public void disabledAllPersonnelCasting(List<PersonnelCasting> ts)
/*     */   {
/* 106 */     for (PersonnelCasting jm : ts) {
/* 107 */       jm.setDisabled(true);
/* 108 */       this.dao.storePersonnelCasting(jm);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllPersonnelCasting(List<PersonnelCasting> ts)
/*     */   {
/* 116 */     for (PersonnelCasting jm : ts) {
/* 117 */       jm.setDisabled(false);
/* 118 */       this.dao.storePersonnelCasting(jm);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.personnelcasting.pojo.DefaultPersonnelCastingManager
 * JD-Core Version:    0.6.2
 */