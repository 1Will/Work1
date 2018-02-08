/*    */ package com.yongjun.tdms.dao.productionOperation.productionOperationDetail.hibernate;
/*    */ 
/*    */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*    */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.productionOperation.productionOperationDetail.ProductionOperationDetailDao;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HibernateProductionOperationDetail extends BaseHibernateDao
/*    */   implements ProductionOperationDetailDao
/*    */ {
/*    */   public void deleteProductionOperationDetail(ProductionOperationDetail productionOperationDetail)
/*    */   {
/* 21 */     delete(productionOperationDetail);
/*    */   }
/*    */ 
/*    */   public void deleteAllProductionOperationDetail(Collection<ProductionOperationDetail> productionOperationDetails) {
/* 25 */     deleteAll(productionOperationDetails);
/*    */   }
/*    */ 
/*    */   public ProductionOperationDetail loadProductionOperationDetail(Long productionOperationDetailId) {
/* 29 */     return (ProductionOperationDetail)load(ProductionOperationDetail.class, productionOperationDetailId);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperationDetail> loadAllProductionOperationDetail(Long[] productionOperationDetailIds) {
/* 33 */     return loadAll(ProductionOperationDetail.class, productionOperationDetailIds);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperationDetail> loadAllProductionOperationDetails() {
/* 37 */     return loadAll(ProductionOperationDetail.class);
/*    */   }
/*    */ 
/*    */   public List<ProductionOperationDetail> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 41 */     return loadByKey(ProductionOperationDetail.class, keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public void storeProductionOperationDetail(ProductionOperationDetail productionOperationDetail) {
/* 45 */     store(productionOperationDetail);
/*    */   }
/*    */ }

