/*    */ package com.yongjun.tdms.dao.productionOperation.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.productionOperation.ProductionOperationDao;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProductionOperation extends BaseHibernateDao
/*    */   implements ProductionOperationDao
/*    */ {
/*    */   public void deleteProductionOperation(ProductionOperation productionOperation)
/*    */   {
/* 21 */     delete(productionOperation);
/*    */   }
/*    */ 
/*    */   public void deleteAllProductionOperation(Collection<ProductionOperation> productionOperations) {
/* 25 */     deleteAll(productionOperations);
/*    */   }
/*    */ 
/*    */   public ProductionOperation loadProductionOperation(Long productionOperationId) {
/* 29 */     return (ProductionOperation)load(ProductionOperation.class, productionOperationId);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperation> loadAllProductionOperation(Long[] productionOperationIds) {
/* 33 */     return loadAll(ProductionOperation.class, productionOperationIds);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperation> loadAllProductionOperations() {
/* 37 */     return loadAll(ProductionOperation.class);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperation> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 41 */     return loadByKey(ProductionOperation.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public void storeProductionOperation(ProductionOperation productionOperation) {
/* 45 */     store(productionOperation);
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.advisory.hibernate.HibernateAdvisory
 * JD-Core Version:    0.6.2
 */