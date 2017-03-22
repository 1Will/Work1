/*     */ package com.yongjun.tdms.service.marketmanager.targetmanager.departmenttarget.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.marketmanager.targetmanager.departmenttarget.DepartmentTargetDao;
/*     */ import com.yongjun.tdms.model.marketmanager.targetmanager.departmenttarget.DepartmentTarget;
/*     */ import com.yongjun.tdms.service.marketmanager.targetmanager.departmenttarget.DepartmentTargetManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultDepartmentTarget extends BaseManager
/*     */   implements DepartmentTargetManager
/*     */ {
/*     */   private final DepartmentTargetDao dao;
/*     */ 
/*     */   public DefaultDepartmentTarget(DepartmentTargetDao dao)
/*     */   {
/*  28 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public void deleteAllDepartmentTarget(List<DepartmentTarget> ts)
/*     */   {
/*  35 */     this.dao.deleteAllDepartmentTarget(ts);
/*     */   }
/*     */ 
/*     */   public void deleteDepartmentTarget(DepartmentTarget t)
/*     */   {
/*  42 */     this.dao.deleteDepartmentTarget(t);
/*     */   }
/*     */ 
/*     */   public void disabledAllDepartmentTarget(List<DepartmentTarget> ts)
/*     */   {
/*  49 */     for (DepartmentTarget departmentTarget : ts) {
/*  50 */       departmentTarget.setDisabled(true);
/*  51 */       this.dao.storeDepartmentTarget(departmentTarget);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllDepartmentTarget(List<DepartmentTarget> ts)
/*     */   {
/*  59 */     for (DepartmentTarget departmentTarget : ts) {
/*  60 */       departmentTarget.setDisabled(false);
/*  61 */       this.dao.storeDepartmentTarget(departmentTarget);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadAllDepartmentTarget(Long[] tIds)
/*     */   {
/*  70 */     return this.dao.loadAllDepartmentTarget(tIds);
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/*  81 */     return this.dao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/*  92 */     return this.dao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public DepartmentTarget loadDepartmentTarget(Long id)
/*     */   {
/* 100 */     return this.dao.loadDepartmentTarget(id);
/*     */   }
/*     */ 
/*     */   public List<DepartmentTarget> loadDepartmentTarget()
/*     */   {
/* 107 */     return this.dao.loadDepartmentTarget();
/*     */   }
/*     */ 
/*     */   public void storeDepartmentTarget(DepartmentTarget t)
/*     */   {
/* 114 */     this.dao.storeDepartmentTarget(t);
/*     */   }
/*     */ 
/*     */   public String getMaxPFCode(String code)
/*     */   {
/* 122 */     return this.dao.getMaxPFCode(code);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.targetmanager.departmenttarget.pojo.DefaultDepartmentTarget
 * JD-Core Version:    0.6.2
 */