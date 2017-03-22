/*    */ package com.yongjun.tdms.service.project.projectInfoContract.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoContract.ProjectInfoContractDao;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProjectInfoContractManager extends BaseManager
/*    */   implements ProjectInfoContractManager
/*    */ {
/*    */   private ProjectInfoContractDao projectInfoContractDao;
			public DefaultProjectInfoContractManager(ProjectInfoContractDao projectInfoContractDao){
			this.projectInfoContractDao = projectInfoContractDao;
			}
/*    */  public void deleteAllProjectInfoContract(Collection<ProjectInfoContract> ProjectInfoContract)
/*    */   {
/* 28 */     this.projectInfoContractDao.deleteAllProjectInfoContract(ProjectInfoContract);
/*    */   }
/*    */ 
/*    */   public void deleteProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
/* 32 */     this.projectInfoContractDao.deleteProjectInfoContract(ProjectInfoContract);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoContract> loadAllProjectInfoContract(Long[] ProjectInfoContractIds) {
/* 36 */     return this.projectInfoContractDao.loadAllProjectInfoContract(ProjectInfoContractIds);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoContract> loadAllProjectInfoContracts() {
/* 40 */     return this.projectInfoContractDao.loadAllProjectInfoContracts();
/*    */   }
/*    */ 
/*    */   public ProjectInfoContract loadProjectInfoContract(Long ProjectInfoContractId) {
/* 44 */     return this.projectInfoContractDao.loadProjectInfoContract(ProjectInfoContractId);
/*    */   }
/*    */ 
/*    */   public void storeProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
/* 48 */     this.projectInfoContractDao.storeProjectInfoContract(ProjectInfoContract);
/*    */   }
/*    */ 
/*    */ 
/*    */   public void disabledProjectInfoContracts(List<ProjectInfoContract> ProjectInfoContracts) {
/* 56 */     for (ProjectInfoContract bv : ProjectInfoContracts) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.projectInfoContractDao.storeProjectInfoContract(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableProjectInfoContracts(List<ProjectInfoContract> ProjectInfoContracts) {
/* 63 */     for (ProjectInfoContract bv : ProjectInfoContracts) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.projectInfoContractDao.storeProjectInfoContract(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoContract> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.projectInfoContractDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoContract> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.projectInfoContractDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   
/*    */ }

