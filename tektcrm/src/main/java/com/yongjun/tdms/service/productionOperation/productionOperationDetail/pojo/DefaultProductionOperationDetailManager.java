/*     */ package com.yongjun.tdms.service.productionOperation.productionOperationDetail.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.productionOperation.productionOperationDetail.ProductionOperationDetailDao;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultProductionOperationDetailManager extends BaseManager
/*     */   implements ProductionOperationDetailManager
/*     */ {
/*     */   private final ProductionOperationDetailDao productionOperationDao;
/*     */ 
/*     */   public DefaultProductionOperationDetailManager(ProductionOperationDetailDao productionOperationDao)
/*     */   {
/*  19 */     this.productionOperationDao = productionOperationDao;
/*     */   }
/*     */   public void deleteProductionOperationDetail(ProductionOperationDetail productionOperation) {
/*  22 */     this.productionOperationDao.deleteProductionOperationDetail(productionOperation);
/*     */   }
/*     */ 
/*     */   public void deleteAllProductionOperationDetail(Collection<ProductionOperationDetail> productionOperations) {
/*  26 */     this.productionOperationDao.deleteAllProductionOperationDetail(productionOperations);
/*     */   }
/*     */ 
/*     */   public ProductionOperationDetail loadProductionOperationDetail(Long productionOperationId) {
/*  30 */     return this.productionOperationDao.loadProductionOperationDetail(productionOperationId);
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationDetail> loadAllProductionOperationDetail(Long[] productionOperationIds) {
/*  34 */     return this.productionOperationDao.loadAllProductionOperationDetail(productionOperationIds);
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationDetail> loadAllProductionOperationDetails() {
/*  38 */     return this.productionOperationDao.loadAllProductionOperationDetails();
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationDetail> loadByKey(String keyName, Object keyValue) throws DaoException {
/*  42 */     return this.productionOperationDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public void storeProductionOperationDetail(ProductionOperationDetail productionOperation)
/*     */   {
/*  48 */     this.productionOperationDao.storeProductionOperationDetail(productionOperation);
/*     */   }
/*     */ 
/*     */   public void disabledAllProductionOperationDetails(Collection<ProductionOperationDetail> productionOperations) {
/*  52 */     for (ProductionOperationDetail productionOperation : productionOperations) {
/*  53 */       productionOperation.setDisabled(true);
/*  54 */       this.productionOperationDao.storeProductionOperationDetail(productionOperation);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllProductionOperationDetails(Collection<ProductionOperationDetail> productionOperations) {
/*  59 */     for (ProductionOperationDetail productionOperation : productionOperations) {
/*  60 */       productionOperation.setDisabled(false);
/*  61 */       this.productionOperationDao.storeProductionOperationDetail(productionOperation);
/*     */     }
/*     */   }
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.productionOperation.pojo.DefaultProductionOperationDetailManager
 * JD-Core Version:    0.6.2
 */