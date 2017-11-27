/*     */ package com.yongjun.tdms.service.base.institution.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.base.institution.InstitutionDao;
/*     */ import com.yongjun.tdms.service.base.institution.InstitutionManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultInstitutionManager extends BaseManager
/*     */   implements InstitutionManager
/*     */ {
/*     */   public final InstitutionDao institutionDao;
/*     */ 
/*     */   public DefaultInstitutionManager(InstitutionDao institutionDao)
/*     */   {
/*  49 */     this.institutionDao = institutionDao;
/*     */   }
/*     */ 
/*     */   public void storeInstitution(Institution institution)
/*     */   {
/*  58 */     this.institutionDao.storeInstitution(institution);
/*     */   }
/*     */ 
/*     */   public void deleteInstitution(Institution institution)
/*     */   {
/*  67 */     this.institutionDao.deleteInstitution(institution);
/*     */   }
/*     */ 
/*     */   public void deleteAllInstitution(Collection<Institution> institutions)
/*     */   {
/*  76 */     this.institutionDao.deleteAllInstitution(institutions);
/*     */   }
/*     */ 
/*     */   public List<Institution> loadAllInstitution(Long[] institutionIds)
/*     */   {
/*  86 */     return this.institutionDao.loadAllInstitution(institutionIds);
/*     */   }
/*     */ 
/*     */   public Institution loadInstitution(Long institutionId)
/*     */   {
/*  96 */     return this.institutionDao.loadInstitution(institutionId);
/*     */   }
/*     */ 
/*     */   public List<Institution> loadAllInstitution()
/*     */   {
/* 105 */     return this.institutionDao.loadAllInstitution();
/*     */   }
/*     */ 
/*     */   public void disabledAllInstitution(Collection<Institution> institutions)
/*     */   {
/* 113 */     for (Institution inst : institutions) {
/* 114 */       inst.setDisabled(true);
/* 115 */       this.institutionDao.storeInstitution(inst);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllInstitution(Collection<Institution> institutions)
/*     */   {
/* 124 */     for (Institution inst : institutions) {
/* 125 */       inst.setDisabled(false);
/* 126 */       this.institutionDao.storeInstitution(inst);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Institution> loadAllInstitution(String mark)
/*     */   {
/* 134 */     List list = this.institutionDao.loadAllInstitution();
/* 135 */     Institution inst = new Institution();
/* 136 */     inst.setId(Long.valueOf(-1L));
/* 137 */     inst.setName(mark);
/* 138 */     list.add(0, inst);
/* 139 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Institution> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 150 */     return this.institutionDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public int getInstMaxId()
/*     */   {
/* 159 */     return this.institutionDao.getInstMaxId();
/*     */   }
/*     */ 
/*     */   public int getInstSteps()
/*     */   {
/* 168 */     return this.institutionDao.getInstSteps();
/*     */   }
/*     */ 
/*     */   public List<Institution> getInstsByStep(int step)
/*     */   {
/* 178 */     return this.institutionDao.getInstsByStep(step);
/*     */   }
/*     */ 
/*     */   public List<Institution> getInstitutions() {
/* 182 */     List<Institution> list = loadAllInstitution("");
/* 183 */     List enableInst = new ArrayList();
/*     */ 
/* 185 */     for (Institution inst : list) {
/* 186 */       if (!inst.getDisabled()) {
/* 187 */         enableInst.add(inst);
/*     */       }
/*     */     }
/* 190 */     return enableInst;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.institution.pojo.DefaultInstitutionManager
 * JD-Core Version:    0.6.2
 */