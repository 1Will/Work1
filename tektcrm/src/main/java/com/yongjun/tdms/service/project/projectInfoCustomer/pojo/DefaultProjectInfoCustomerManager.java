/*    */ package com.yongjun.tdms.service.project.projectInfoCustomer.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoContract.ProjectInfoContractDao;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;
import com.yongjun.tdms.dao.project.projectInfoCustomer.ProjectInfoCustomerDao;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProjectInfoCustomerManager extends BaseManager
/*    */   implements ProjectInfoCustomerManager
/*    */ {
/*    */   private ProjectInfoCustomerDao projectInfoCustomerDao;
			public DefaultProjectInfoCustomerManager(ProjectInfoCustomerDao projectInfoCustomerDao){
			this.projectInfoCustomerDao = projectInfoCustomerDao;
			}
/*    */  public void deleteAllProjectInfoCustomer(Collection<ProjectInfoCustomer> projectInfoCustomer)
/*    */   {
/* 28 */     this.projectInfoCustomerDao.deleteAllProjectInfoCustomer(projectInfoCustomer);
/*    */   }
/*    */ 
/*    */   public void deleteProjectInfoCustomer(ProjectInfoCustomer projectInfoCustomer) {
/* 32 */     this.projectInfoCustomerDao.deleteProjectInfoCustomer(projectInfoCustomer);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoCustomer> loadAllProjectInfoCustomer(Long[] ProjectInfoContractIds) {
/* 36 */     return this.projectInfoCustomerDao.loadAllProjectInfoCustomer(ProjectInfoContractIds);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoCustomer> loadAllProjectInfoCustomers() {
/* 40 */     return this.projectInfoCustomerDao.loadAllProjectInfoCustomers();
/*    */   }
/*    */ 
/*    */   public ProjectInfoCustomer loadProjectInfoCustomer(Long projectInfoCustomerId) {
/* 44 */     return this.projectInfoCustomerDao.loadProjectInfoCustomer(projectInfoCustomerId);
/*    */   }
/*    */ 
/*    */   public void storeProjectInfoCustomer(ProjectInfoCustomer projectInfoCustomer) {
/* 48 */     this.projectInfoCustomerDao.storeProjectInfoCustomer(projectInfoCustomer);
/*    */   }
/*    */ 
/*    */ 
/*    */   public void disabledProjectInfoCustomers(List<ProjectInfoCustomer> projectInfoCustomers) {
/* 56 */     for (ProjectInfoCustomer bv : projectInfoCustomers) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.projectInfoCustomerDao.storeProjectInfoCustomer(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableProjectInfoCustomers(List<ProjectInfoCustomer> projectInfoCustomers) {
/* 63 */     for (ProjectInfoCustomer bv : projectInfoCustomers) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.projectInfoCustomerDao.storeProjectInfoCustomer(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoCustomer> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.projectInfoCustomerDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoCustomer> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.projectInfoCustomerDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   
/*    */ }

