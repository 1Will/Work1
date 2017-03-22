/*    */ package com.yongjun.tdms.dao.project.projectInfoPlan.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoPlan.ProjectInfoPlanDao;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProjectInfoPlan extends BaseHibernateDao
/*    */   implements ProjectInfoPlanDao
/*    */ {
	 			public void deleteAllProjectInfoPlan(Collection<ProjectInfoPlan> ProjectInfoPlans)
	 /*    */   {
	 /* 22 */     deleteAll(ProjectInfoPlans);
	 /*    */   }
	 /*    */ 
	 /*    */   public void deleteProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
	 /* 26 */     delete(ProjectInfoPlan);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlan(Long[] ProjectInfoPlanIds) {
	 /* 30 */     return loadAll(ProjectInfoPlan.class, ProjectInfoPlanIds);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlans() {
	 /* 34 */     return loadAll(ProjectInfoPlan.class);
	 /*    */   }
	 /*    */ 
	 /*    */   public ProjectInfoPlan loadProjectInfoPlan(Long ProjectInfoPlanId) {
	 /* 38 */     return (ProjectInfoPlan)load(ProjectInfoPlan.class, ProjectInfoPlanId);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadByKey(String keyName, Object keyValue) throws DaoException {
	 /* 42 */     return loadByKey(ProjectInfoPlan.class, keyName, keyValue);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
	 /* 46 */     return loadByKeyArray(ProjectInfoPlan.class, keyNames, keyValues);
	 /*    */   }
	 /*    */ 
	 /*    */   public void storeProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
	 /* 50 */     store(ProjectInfoPlan);
	 /*    */   }
/*    */   
/*    */ }

