/*     */ package com.yongjun.tdms.service.productionOperation.productionOperationException.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.productionOperation.productionOperationException.ProductionOperationExceptionDao;
import com.yongjun.tdms.model.productionOperation.productionOperationException.ProductionOperationException;
import com.yongjun.tdms.service.productionOperation.productionOperationException.ProductionOperationExceptionManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultProductionOperationExceptionManager extends BaseManager
/*     */   implements ProductionOperationExceptionManager
/*     */ {
/*     */   private final ProductionOperationExceptionDao productionOperationExceptionDao;
			private final YongJunSequenceManager yongJunSequenceManager;
/*     */ 
/*     */   public DefaultProductionOperationExceptionManager(ProductionOperationExceptionDao productionOperationExceptionDao,YongJunSequenceManager yongJunSequenceManager)
/*     */   {
/*  19 */     this.productionOperationExceptionDao = productionOperationExceptionDao;
              this.yongJunSequenceManager=yongJunSequenceManager;
/*     */   }
/*     */   public void deleteProductionOperationException(ProductionOperationException productionOperation) {
/*  22 */     this.productionOperationExceptionDao.deleteProductionOperationException(productionOperation);
/*     */   }
/*     */ 
/*     */   public void deleteAllProductionOperationException(Collection<ProductionOperationException> productionOperations) {
/*  26 */     this.productionOperationExceptionDao.deleteAllProductionOperationException(productionOperations);
/*     */   }
/*     */ 
/*     */   public ProductionOperationException loadProductionOperationException(Long productionOperationId) {
/*  30 */     return this.productionOperationExceptionDao.loadProductionOperationException(productionOperationId);
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationException> loadAllProductionOperationException(Long[] productionOperationIds) {
/*  34 */     return this.productionOperationExceptionDao.loadAllProductionOperationException(productionOperationIds);
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationException> loadAllProductionOperationExceptions() {
/*  38 */     return this.productionOperationExceptionDao.loadAllProductionOperationExceptions();
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationException> loadByKey(String keyName, Object keyValue) throws DaoException {
/*  42 */     return this.productionOperationExceptionDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public void storeProductionOperationException(ProductionOperationException productionOperation)
/*     */   {
			if(productionOperation.isNew()){
				productionOperation.setCode((String)this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_EXCEPTION));
			    }
/*  48 */     this.productionOperationExceptionDao.storeProductionOperationException(productionOperation);
/*     */   }
/*     */ 
/*     */   public void disabledAllProductionOperationExceptions(Collection<ProductionOperationException> productionOperations) {
/*  52 */     for (ProductionOperationException productionOperation : productionOperations) {
/*  53 */       productionOperation.setDisabled(true);
/*  54 */       this.productionOperationExceptionDao.storeProductionOperationException(productionOperation);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllProductionOperationExceptions(Collection<ProductionOperationException> productionOperations) {
/*  59 */     for (ProductionOperationException productionOperation : productionOperations) {
/*  60 */       productionOperation.setDisabled(false);
/*  61 */       this.productionOperationExceptionDao.storeProductionOperationException(productionOperation);
/*     */     }
/*     */   }
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.productionOperation.pojo.DefaultProductionOperationExceptionManager
 * JD-Core Version:    0.6.2
 */