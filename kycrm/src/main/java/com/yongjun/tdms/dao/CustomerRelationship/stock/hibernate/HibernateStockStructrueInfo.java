/*     */ package com.yongjun.tdms.dao.CustomerRelationship.stock.hibernate;
import java.util.List;

/*     */ 
/*     */ import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.tdms.dao.CustomerRelationship.stock.StockStructureDao;

import com.yongjun.tdms.model.CustomerRelationship.stock.StockStructure;

/*     */ 
/*     */ public class HibernateStockStructrueInfo extends BaseHibernateDao
/*     */   implements StockStructureDao
/*     */ {

	public void storeStockStructure(StockStructure ca) {
		super.store(ca);
	}

	public StockStructure loadStockStructure(Long caId) {
		return (StockStructure) super.load(StockStructure.class, caId);
	}

	public List<StockStructure> loadAllStockStructure() {
		return super.loadAll(StockStructure.class);
	}

	public List<StockStructure> loadAllStockStructure(Long[] caIds) {
		return super.loadAll(StockStructure.class, caIds);
	}

	public void deleteStockStructure(StockStructure ca) {
		super.delete(ca);
	}

	public void deleteAllStockStructure(List<StockStructure> caIds) {
		super.deleteAll(caIds);
	}

	public List<StockStructure> loadByKey(String key, Object value) throws DaoException {
		return super.loadByKey(StockStructure.class, key, value);
	}

	public List<StockStructure> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(StockStructure.class, keyNames, keyValues);
	}
	}
/*     */   
/*     */

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.customerProfiles.hibernate.HibernateCustomerInfo
 * JD-Core Version:    0.6.2
 */