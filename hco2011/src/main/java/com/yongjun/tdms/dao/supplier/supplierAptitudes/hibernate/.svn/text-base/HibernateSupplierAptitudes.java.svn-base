/*     */ package com.yongjun.tdms.dao.supplier.supplierAptitudes.hibernate;
/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.supplier.supplierAptitudes.SupplierAptitudesDao;
/*     */ import com.yongjun.tdms.model.supplier.supplierAptitudes.SupplierAptitudes;
/*     */ import java.util.List;
/*     */ 
/*     */ public class HibernateSupplierAptitudes extends BaseHibernateDao
/*     */   implements SupplierAptitudesDao
/*     */ {
/*     */   public void storeSupplierAptitudes(SupplierAptitudes sas)
/*     */   {
/*  55 */     super.store(sas);
/*     */   }
/*     */ 
/*     */   public SupplierAptitudes loadSupplierAptitudes(Long sasId)
/*     */   {
/*  67 */     return (SupplierAptitudes)super.load(SupplierAptitudes.class, sasId);
/*     */   }
/*     */ 
/*     */   public List<SupplierAptitudes> loadAllSupplierAptitudes()
/*     */   {
/*  76 */     return super.loadAll(SupplierAptitudes.class);
/*     */   }
/*     */ 
/*     */   public List<SupplierAptitudes> loadAllSupplierAptitudes(Long[] sasIds)
/*     */   {
/*  88 */     return super.loadAll(SupplierAptitudes.class, sasIds);
/*     */   }
/*     */ 
/*     */   public void deleteSupplierAptitudes(SupplierAptitudes sas)
/*     */   {
/*  98 */     super.delete(sas);
/*     */   }
/*     */ 
/*     */   public void deleteAllSupplierAptitudes(List<SupplierAptitudes> sasIds)
/*     */   {
/* 108 */     super.deleteAll(sasIds);
/*     */   }
/*     */ 
/*     */   public List<SupplierAptitudes> loadByKey(String key, Object value)
/*     */     throws DaoException
/*     */   {
/* 122 */     return super.loadByKey(SupplierAptitudes.class, key, value);
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.supplier.supplierAptitudes.hibernate.HibernateSupplierAptitudes
 * JD-Core Version:    0.6.2
 */