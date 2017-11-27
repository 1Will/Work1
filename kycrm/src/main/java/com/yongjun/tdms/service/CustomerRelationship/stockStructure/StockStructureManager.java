package com.yongjun.tdms.service.CustomerRelationship.stockStructure;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;
import com.yongjun.tdms.model.CustomerRelationship.stock.StockStructure;

import java.util.List;

public abstract interface StockStructureManager
{
  public abstract void storeStockStructure(StockStructure paramStockStructure);

  public abstract StockStructure loadStockStructure(Long paramLong);

  public abstract List<StockStructure> loadAllStockStructure();

  public abstract List<StockStructure> loadAllStockStructure(Long[] paramArrayOfLong);

  public abstract void deleteStockStructure(StockStructure paramStockStructure);

  public abstract void deleteAllStockStructure(List<StockStructure> paramList);

  public abstract List<StockStructure> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract void disabledAllStockStructure(List<StockStructure> paramList);

  public abstract void enabledAllStockStructure(List<StockStructure> paramList);

  public abstract List<StockStructure> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;



  
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager
 * JD-Core Version:    0.6.2
 */