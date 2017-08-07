/*    */ package com.yongjun.tdms.dao.project.projectInfoCustomer.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoCustomer.ProjectInfoCustomerDao;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProjectInfoCustomer extends BaseHibernateDao
/*    */   implements ProjectInfoCustomerDao
/*    */ {
	 			public void deleteAllProjectInfoCustomer(Collection<ProjectInfoCustomer> projectInfoCustomers)
	 /*    */   {
	 /* 22 */     deleteAll(projectInfoCustomers);
	 /*    */   }
	 /*    */ 
	 /*    */   public void deleteProjectInfoCustomer(ProjectInfoCustomer projectInfoCustomer) {
	 /* 26 */     delete(projectInfoCustomer);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoCustomer> loadAllProjectInfoCustomer(Long[] projectInfoCustomerIds) {
	 /* 30 */     return loadAll(ProjectInfoCustomer.class, projectInfoCustomerIds);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoCustomer> loadAllProjectInfoCustomers() {
	 /* 34 */     return loadAll(ProjectInfoCustomer.class);
	 /*    */   }
	 /*    */ 
	 /*    */   public ProjectInfoCustomer loadProjectInfoCustomer(Long projectInfoCustomerId) {
	 /* 38 */     return (ProjectInfoCustomer)load(ProjectInfoCustomer.class, projectInfoCustomerId);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoCustomer> loadByKey(String keyName, Object keyValue) throws DaoException {
	 /* 42 */     return loadByKey(ProjectInfoCustomer.class, keyName, keyValue);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoCustomer> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
	 /* 46 */     return loadByKeyArray(ProjectInfoCustomer.class, keyNames, keyValues);
	 /*    */   }
	 /*    */ 
	 /*    */   public void storeProjectInfoCustomer(ProjectInfoCustomer projectInfoCustomer) {
	 /* 50 */     store(projectInfoCustomer);
	 /*    */   }
/*    */   
/*    */ }

