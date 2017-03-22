/*    */ package com.yongjun.tdms.service.project.projectInfoPersonnels.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoPersonnels.ProjectInfoPersonnelDao;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProjectInfoPersonnelsManager extends BaseManager
/*    */   implements ProjectInfoPersonnelsManager
/*    */ {
/*    */   private ProjectInfoPersonnelDao projectInfoPersonnelDao;
			public DefaultProjectInfoPersonnelsManager(ProjectInfoPersonnelDao projectInfoPersonnelDao){
			this.projectInfoPersonnelDao = projectInfoPersonnelDao;
			}
/*    */  public void deleteAllProjectInfoPersonnels(Collection<ProjectInfoPersonnels> projectInfoPersonnels)
/*    */   {
/* 28 */     this.projectInfoPersonnelDao.deleteAllProjectInfoPersonnels(projectInfoPersonnels);
/*    */   }
/*    */ 
/*    */   public void deleteProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
/* 32 */     this.projectInfoPersonnelDao.deleteProjectInfoPersonnels(ProjectInfoPersonnels);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnels(Long[] ProjectInfoPersonnelsIds) {
/* 36 */     return this.projectInfoPersonnelDao.loadAllProjectInfoPersonnels(ProjectInfoPersonnelsIds);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnelss() {
/* 40 */     return this.projectInfoPersonnelDao.loadAllProjectInfoPersonnelss();
/*    */   }
/*    */ 
/*    */   public ProjectInfoPersonnels loadProjectInfoPersonnels(Long ProjectInfoPersonnelsId) {
/* 44 */     return this.projectInfoPersonnelDao.loadProjectInfoPersonnels(ProjectInfoPersonnelsId);
/*    */   }
/*    */ 
/*    */   public void storeProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
/* 48 */     this.projectInfoPersonnelDao.storeProjectInfoPersonnels(ProjectInfoPersonnels);
/*    */   }
/*    */ 
/*    */   public void setProjectInfoPersonnelDao(ProjectInfoPersonnelDao projectInfoPersonnelDao) {
/* 52 */     this.projectInfoPersonnelDao = projectInfoPersonnelDao;
/*    */   }
/*    */ 
/*    */   public void disabledProjectInfoPersonnelss(List<ProjectInfoPersonnels> ProjectInfoPersonnelss) {
/* 56 */     for (ProjectInfoPersonnels bv : ProjectInfoPersonnelss) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.projectInfoPersonnelDao.storeProjectInfoPersonnels(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableProjectInfoPersonnelss(List<ProjectInfoPersonnels> ProjectInfoPersonnelss) {
/* 63 */     for (ProjectInfoPersonnels bv : ProjectInfoPersonnelss) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.projectInfoPersonnelDao.storeProjectInfoPersonnels(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPersonnels> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.projectInfoPersonnelDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPersonnels> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.projectInfoPersonnelDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
		   public ProjectInfoPersonnelDao getprojectInfoPersonnelDao() {
			   return projectInfoPersonnelDao;
		   }
		   public void setprojectInfoPersonnelDao(ProjectInfoPersonnelDao projectInfoPersonnelDao) {
			   this.projectInfoPersonnelDao = projectInfoPersonnelDao;
		   }

/*    */ 
/*    */   
/*    */ }

