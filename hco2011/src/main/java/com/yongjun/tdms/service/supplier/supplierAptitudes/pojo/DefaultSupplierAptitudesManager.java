/*     */ package com.yongjun.tdms.service.supplier.supplierAptitudes.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.supplier.supplierAptitudes.SupplierAptitudesDao;
/*     */ import com.yongjun.tdms.model.supplier.supplierAptitudes.SupplierAptitudes;
/*     */ import com.yongjun.tdms.service.supplier.supplierAptitudes.SupplierAptitudesManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultSupplierAptitudesManager extends BaseManager
/*     */   implements SupplierAptitudesManager
/*     */ {
/*     */   private final SupplierAptitudesDao supplierAptitudesDao;
/*     */ 
/*     */   public DefaultSupplierAptitudesManager(SupplierAptitudesDao supplierAptitudesDao)
/*     */   {
/*  60 */     this.supplierAptitudesDao = supplierAptitudesDao;
/*     */   }
/*     */ 
/*     */   public void deleteAllSupplierAptitudes(List<SupplierAptitudes> sasIds)
/*     */   {
/*  69 */     this.supplierAptitudesDao.deleteAllSupplierAptitudes(sasIds);
/*     */   }
/*     */ 
/*     */   public void deleteSupplierAptitudes(SupplierAptitudes sas)
/*     */   {
/*  78 */     deleteSupplierAptitudes(sas);
/*     */   }
/*     */ 
/*     */   public List<SupplierAptitudes> loadAllSupplierAptitudes()
/*     */   {
/*  87 */     return this.supplierAptitudesDao.loadAllSupplierAptitudes();
/*     */   }
/*     */ 
/*     */   public List<SupplierAptitudes> loadAllSupplierAptitudes(Long[] sasIds)
/*     */   {
/*  98 */     return this.supplierAptitudesDao.loadAllSupplierAptitudes(sasIds);
/*     */   }
/*     */ 
/*     */   public List<SupplierAptitudes> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/* 104 */     return this.supplierAptitudesDao.loadByKey(key, value);
/*     */   }
/*     */ 
/*     */   public SupplierAptitudes loadSupplierAptitudes(Long sasId)
/*     */   {
/* 115 */     return this.supplierAptitudesDao.loadSupplierAptitudes(sasId);
/*     */   }
/*     */ 
/*     */   public void storeSupplierAptitudes(SupplierAptitudes sas)
/*     */   {
/* 125 */     this.supplierAptitudesDao.storeSupplierAptitudes(sas);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.supplier.supplierAptitudes.pojo.DefaultSupplierAptitudesManager
 * JD-Core Version:    0.6.2
 */