package com.yongjun.tdms.dao.CustomerRelationship.stock;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.stock.StockStructure;


public abstract interface StockStructureDao {
	
	public abstract void storeStockStructure(StockStructure paramStockStructure);

	  public abstract StockStructure loadStockStructure(Long paramLong);

	  public abstract List<StockStructure> loadAllStockStructure();

	  public abstract List<StockStructure> loadAllStockStructure(Long[] paramArrayOfLong);

	  public abstract void deleteStockStructure(StockStructure paramStockStructure);

	  public abstract void deleteAllStockStructure(List<StockStructure> paramList);

	  public abstract List<StockStructure> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<StockStructure> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;

}
