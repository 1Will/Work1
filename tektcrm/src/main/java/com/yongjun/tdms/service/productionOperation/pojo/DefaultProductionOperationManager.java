/*     */ package com.yongjun.tdms.service.productionOperation.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.productionOperation.ProductionOperationDao;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;
import com.yongjun.tdms.service.productionOperation.ProductionOperationManager;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultProductionOperationManager extends BaseManager
/*     */   implements ProductionOperationManager
/*     */ {
/*     */   private final ProductionOperationDao productionOperationDao;
/*     */ 
/*     */   public DefaultProductionOperationManager(ProductionOperationDao productionOperationDao)
/*     */   {
/*  19 */     this.productionOperationDao = productionOperationDao;
/*     */   }
/*     */   public void deleteProductionOperation(ProductionOperation productionOperation) {
/*  22 */     this.productionOperationDao.deleteProductionOperation(productionOperation);
/*     */   }
/*     */ 
/*     */   public void deleteAllProductionOperation(Collection<ProductionOperation> productionOperations) {
/*  26 */     this.productionOperationDao.deleteAllProductionOperation(productionOperations);
/*     */   }
/*     */ 
/*     */   public ProductionOperation loadProductionOperation(Long productionOperationId) {
/*  30 */     return this.productionOperationDao.loadProductionOperation(productionOperationId);
/*     */   }
/*     */ 
/*     */   public List<ProductionOperation> loadAllProductionOperation(Long[] productionOperationIds) {
/*  34 */     return this.productionOperationDao.loadAllProductionOperation(productionOperationIds);
/*     */   }
/*     */ 
/*     */   public List<ProductionOperation> loadAllProductionOperations() {
/*  38 */     return this.productionOperationDao.loadAllProductionOperations();
/*     */   }
/*     */ 
/*     */   public List<ProductionOperation> loadByKey(String keyName, Object keyValue) throws DaoException {
/*  42 */     return this.productionOperationDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public void storeProductionOperation(ProductionOperation productionOperation)
/*     */   {
/*  48 */     this.productionOperationDao.storeProductionOperation(productionOperation);
/*     */   }
/*     */ 
/*     */   public void disabledAllProductionOperations(Collection<ProductionOperation> productionOperations) {
/*  52 */     for (ProductionOperation productionOperation : productionOperations) {
/*  53 */       productionOperation.setDisabled(true);
/*  54 */       this.productionOperationDao.storeProductionOperation(productionOperation);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllProductionOperations(Collection<ProductionOperation> productionOperations) {
/*  59 */     for (ProductionOperation productionOperation : productionOperations) {
/*  60 */       productionOperation.setDisabled(false);
/*  61 */       this.productionOperationDao.storeProductionOperation(productionOperation);
/*     */     }
/*     */   }
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.productionOperation.pojo.DefaultProductionOperationManager
 * JD-Core Version:    0.6.2
 */