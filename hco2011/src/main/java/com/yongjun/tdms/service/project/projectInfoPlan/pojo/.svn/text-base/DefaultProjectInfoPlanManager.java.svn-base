/*    */ package com.yongjun.tdms.service.project.projectInfoPlan.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoPlan.ProjectInfoPlanDao;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProjectInfoPlanManager extends BaseManager
/*    */   implements ProjectInfoPlanManager
/*    */ {
/*    */   private ProjectInfoPlanDao projectInfoPlanDao;
			public DefaultProjectInfoPlanManager(ProjectInfoPlanDao ProjectInfoPlanDao){
			this.projectInfoPlanDao = ProjectInfoPlanDao;
			}
/*    */  public void deleteAllProjectInfoPlan(Collection<ProjectInfoPlan> ProjectInfoPlan)
/*    */   {
/* 28 */     this.projectInfoPlanDao.deleteAllProjectInfoPlan(ProjectInfoPlan);
/*    */   }
/*    */ 
/*    */   public void deleteProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
/* 32 */     this.projectInfoPlanDao.deleteProjectInfoPlan(ProjectInfoPlan);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlan(Long[] ProjectInfoPlanIds) {
/* 36 */     return this.projectInfoPlanDao.loadAllProjectInfoPlan(ProjectInfoPlanIds);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlans() {
/* 40 */     return this.projectInfoPlanDao.loadAllProjectInfoPlans();
/*    */   }
/*    */ 
/*    */   public ProjectInfoPlan loadProjectInfoPlan(Long ProjectInfoPlanId) {
/* 44 */     return this.projectInfoPlanDao.loadProjectInfoPlan(ProjectInfoPlanId);
/*    */   }
/*    */ 
/*    */   public void storeProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
/* 48 */     this.projectInfoPlanDao.storeProjectInfoPlan(ProjectInfoPlan);
/*    */   }
/*    */ 
/*    */ 
/*    */   public void disabledProjectInfoPlans(List<ProjectInfoPlan> ProjectInfoPlans) {
/* 56 */     for (ProjectInfoPlan bv : ProjectInfoPlans) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.projectInfoPlanDao.storeProjectInfoPlan(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableProjectInfoPlans(List<ProjectInfoPlan> ProjectInfoPlans) {
/* 63 */     for (ProjectInfoPlan bv : ProjectInfoPlans) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.projectInfoPlanDao.storeProjectInfoPlan(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.projectInfoPlanDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.projectInfoPlanDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   
/*    */ }

