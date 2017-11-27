/*    */ package com.yongjun.tdms.dao.project.projectInfoContract.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoContract.ProjectInfoContractDao;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProjectInfoContract extends BaseHibernateDao
/*    */   implements ProjectInfoContractDao
/*    */ {
	 			public void deleteAllProjectInfoContract(Collection<ProjectInfoContract> ProjectInfoContracts)
	 /*    */   {
	 /* 22 */     deleteAll(ProjectInfoContracts);
	 /*    */   }
	 /*    */ 
	 /*    */   public void deleteProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
	 /* 26 */     delete(ProjectInfoContract);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoContract> loadAllProjectInfoContract(Long[] ProjectInfoContractIds) {
	 /* 30 */     return loadAll(ProjectInfoContract.class, ProjectInfoContractIds);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoContract> loadAllProjectInfoContracts() {
	 /* 34 */     return loadAll(ProjectInfoContract.class);
	 /*    */   }
	 /*    */ 
	 /*    */   public ProjectInfoContract loadProjectInfoContract(Long ProjectInfoContractId) {
	 /* 38 */     return (ProjectInfoContract)load(ProjectInfoContract.class, ProjectInfoContractId);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoContract> loadByKey(String keyName, Object keyValue) throws DaoException {
	 /* 42 */     return loadByKey(ProjectInfoContract.class, keyName, keyValue);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoContract> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
	 /* 46 */     return loadByKeyArray(ProjectInfoContract.class, keyNames, keyValues);
	 /*    */   }
	 /*    */ 
	 /*    */   public void storeProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
	 /* 50 */     store(ProjectInfoContract);
	 /*    */   }
/*    */   
/*    */ }

