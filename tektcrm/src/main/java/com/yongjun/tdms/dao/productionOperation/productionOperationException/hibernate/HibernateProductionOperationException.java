/*    */ package com.yongjun.tdms.dao.productionOperation.productionOperationException.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.productionOperation.productionOperationException.ProductionOperationExceptionDao;
import com.yongjun.tdms.model.productionOperation.productionOperationException.ProductionOperationException;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProductionOperationException extends BaseHibernateDao
/*    */   implements ProductionOperationExceptionDao
/*    */ {
/*    */   public void deleteProductionOperationException(ProductionOperationException productionOperationException)
/*    */   {
/* 21 */     delete(productionOperationException);
/*    */   }
/*    */ 
/*    */   public void deleteAllProductionOperationException(Collection<ProductionOperationException> productionOperationExceptions) {
/* 25 */     deleteAll(productionOperationExceptions);
/*    */   }
/*    */ 
/*    */   public ProductionOperationException loadProductionOperationException(Long productionOperationExceptionId) {
/* 29 */     return (ProductionOperationException)load(ProductionOperationException.class, productionOperationExceptionId);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperationException> loadAllProductionOperationException(Long[] productionOperationExceptionIds) {
/* 33 */     return loadAll(ProductionOperationException.class, productionOperationExceptionIds);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperationException> loadAllProductionOperationExceptions() {
/* 37 */     return loadAll(ProductionOperationException.class);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperationException> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 41 */     return loadByKey(ProductionOperationException.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public void storeProductionOperationException(ProductionOperationException productionOperationException) {
/* 45 */     store(productionOperationException);
/*    */   }
/*    */ }

