/*    */ package com.yongjun.tdms.service.project.projectInfoProduct.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;
import com.yongjun.tdms.service.project.projectInfoProduct.ProjectInfoProductManager;
import com.yongjun.tdms.dao.project.projectInfoProduct.ProjectInfoProductDao;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProjectInfoProductManager extends BaseManager
/*    */   implements ProjectInfoProductManager
/*    */ {
/*    */   private ProjectInfoProductDao projectInfoProductDao;
			public DefaultProjectInfoProductManager(ProjectInfoProductDao ProjectInfoProductDao){
			this.projectInfoProductDao = ProjectInfoProductDao;
			}
/*    */  public void deleteAllProjectInfoProduct(Collection<ProjectInfoProduct> ProjectInfoProduct)
/*    */   {
/* 28 */     this.projectInfoProductDao.deleteAllProjectInfoProduct(ProjectInfoProduct);
/*    */   }
/*    */ 
/*    */   public void deleteProjectInfoProduct(ProjectInfoProduct ProjectInfoProduct) {
/* 32 */     this.projectInfoProductDao.deleteProjectInfoProduct(ProjectInfoProduct);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoProduct> loadAllProjectInfoProduct(Long[] ProjectInfoProductIds) {
/* 36 */     return this.projectInfoProductDao.loadAllProjectInfoProduct(ProjectInfoProductIds);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoProduct> loadAllProjectInfoProducts() {
/* 40 */     return this.projectInfoProductDao.loadAllProjectInfoProducts();
/*    */   }
/*    */ 
/*    */   public ProjectInfoProduct loadProjectInfoProduct(Long ProjectInfoProductId) {
/* 44 */     return this.projectInfoProductDao.loadProjectInfoProduct(ProjectInfoProductId);
/*    */   }
/*    */ 
/*    */   public void storeProjectInfoProduct(ProjectInfoProduct ProjectInfoProduct) {
/* 48 */     this.projectInfoProductDao.storeProjectInfoProduct(ProjectInfoProduct);
/*    */   }
/*    */ 
/*    */ 
/*    */   public void disabledProjectInfoProducts(List<ProjectInfoProduct> ProjectInfoProducts) {
/* 56 */     for (ProjectInfoProduct bv : ProjectInfoProducts) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.projectInfoProductDao.storeProjectInfoProduct(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableProjectInfoProducts(List<ProjectInfoProduct> ProjectInfoProducts) {
/* 63 */     for (ProjectInfoProduct bv : ProjectInfoProducts) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.projectInfoProductDao.storeProjectInfoProduct(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoProduct> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.projectInfoProductDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoProduct> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.projectInfoProductDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   
/*    */
		public void storeProjectInfoProducts(ProjectInfo projectInfo, String [] arrId) {
			if(projectInfo!=null&&arrId!=null){
				if(arrId!=null&&arrId.length>0){
					for(int i=0;i<arrId.length;i++){
					ProjectInfoProduct pp = new ProjectInfoProduct();
					Products products = new Products();
					products.setId(Long.parseLong(arrId[i]));
					pp.setProducts(products);
					pp.setProjectInfo(projectInfo);
					this.projectInfoProductDao.storeProjectInfoProduct(pp);
					}
					
				}
				
				
			}
	
		} 
	}

