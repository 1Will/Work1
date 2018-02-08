/*    */ package com.yongjun.tdms.dao.project.projectInfoProduct.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoProduct.ProjectInfoProductDao;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProjectInfoProduct extends BaseHibernateDao
/*    */   implements ProjectInfoProductDao
/*    */ {
	 			public void deleteAllProjectInfoProduct(Collection<ProjectInfoProduct> ProjectInfoProducts)
	 /*    */   {
	 /* 22 */     deleteAll(ProjectInfoProducts);
	 /*    */   }
	 /*    */ 
	 /*    */   public void deleteProjectInfoProduct(ProjectInfoProduct ProjectInfoProduct) {
	 /* 26 */     delete(ProjectInfoProduct);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoProduct> loadAllProjectInfoProduct(Long[] ProjectInfoProductIds) {
	 /* 30 */     return loadAll(ProjectInfoProduct.class, ProjectInfoProductIds);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoProduct> loadAllProjectInfoProducts() {
	 /* 34 */     return loadAll(ProjectInfoProduct.class);
	 /*    */   }
	 /*    */ 
	 /*    */   public ProjectInfoProduct loadProjectInfoProduct(Long ProjectInfoProductId) {
	 /* 38 */     return (ProjectInfoProduct)load(ProjectInfoProduct.class, ProjectInfoProductId);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoProduct> loadByKey(String keyName, Object keyValue) throws DaoException {
	 /* 42 */     return loadByKey(ProjectInfoProduct.class, keyName, keyValue);
	 /*    */   }
	 /*    */ 
	 /*    */   public List<ProjectInfoProduct> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
	 /* 46 */     return loadByKeyArray(ProjectInfoProduct.class, keyNames, keyValues);
	 /*    */   }
	 /*    */ 
	 /*    */   public void storeProjectInfoProduct(ProjectInfoProduct ProjectInfoProduct) {
	 /* 50 */     store(ProjectInfoProduct);
	 /*    */   }
/*    */   
/*    */ }

