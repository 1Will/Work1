/*    */ package com.yongjun.tdms.service.project.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.codevalue.CodeValueDao;
import com.yongjun.tdms.dao.project.ProjectInfoDao;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.project.ProjectInfoManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProjectInfoManager extends BaseManager
/*    */   implements ProjectInfoManager
/*    */ {
/*    */   private ProjectInfoDao projectInfoDao;
			private final SequenceManager ciSequenceManager;
			private final UserManager userManager;
			public DefaultProjectInfoManager(ProjectInfoDao projectInfoDao, SequenceManager ciSequenceManager,UserManager userManager){
			this.projectInfoDao = projectInfoDao;
			this.ciSequenceManager =ciSequenceManager;
			this.userManager=userManager;
			}
/*    */  public void deleteAllProjectInfo(Collection<ProjectInfo> ProjectInfos)
/*    */   {
/* 28 */     this.projectInfoDao.deleteAllProjectInfo(ProjectInfos);
/*    */   }
/*    */ 
/*    */   public void deleteProjectInfo(ProjectInfo ProjectInfo) {
/* 32 */     this.projectInfoDao.deleteProjectInfo(ProjectInfo);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfo> loadAllProjectInfo(Long[] ProjectInfoIds) {
/* 36 */     return this.projectInfoDao.loadAllProjectInfo(ProjectInfoIds);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfo> loadAllProjectInfos() {
/* 40 */     return this.projectInfoDao.loadAllProjectInfos();
/*    */   }
/*    */ 
/*    */   public ProjectInfo loadProjectInfo(Long ProjectInfoId) {
/* 44 */     return this.projectInfoDao.loadProjectInfo(ProjectInfoId);
/*    */   }
/*    */ 
/*    */   public void storeProjectInfo(ProjectInfo ProjectInfo) {
				if (ProjectInfo.isNew()) {
					String code = (String)this.ciSequenceManager.generate("-");
					String temp= code.replace("KH", "XM");
		/*  60 */       ProjectInfo.setCode(temp);
		/*     */     }
/* 48 */     this.projectInfoDao.storeProjectInfo(ProjectInfo);
/*    */   }
/*    */ 
/*    */   public void setprojectInfoDao(ProjectInfoDao projectInfoDao) {
/* 52 */     this.projectInfoDao = projectInfoDao;
/*    */   }
/*    */ 
/*    */   public void disabledProjectInfos(List<ProjectInfo> ProjectInfos) {
/* 56 */     for (ProjectInfo bv : ProjectInfos) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.projectInfoDao.storeProjectInfo(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableProjectInfos(List<ProjectInfo> ProjectInfos) {
/* 63 */     for (ProjectInfo bv : ProjectInfos) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.projectInfoDao.storeProjectInfo(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<ProjectInfo> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.projectInfoDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfo> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.projectInfoDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
		   public ProjectInfoDao getProjectInfoDao() {
			   return projectInfoDao;
		   }
		   public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
			   this.projectInfoDao = projectInfoDao;
		   }
		   
		   //public List<ProjectInfo> loadProjectByDate(String date){
		   public int loadProjectByDate(String userId,String date){
			   User user = userManager.loadUser(Long.parseLong(userId));
			   List<ProjectInfo> plist =this.projectInfoDao.loadByDate(date,user.getName());
			   int size =plist.size();
			   return size;
		   }

/*    */ 
/*    */   
/*    */ }

