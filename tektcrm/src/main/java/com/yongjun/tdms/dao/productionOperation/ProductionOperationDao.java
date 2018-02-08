package com.yongjun.tdms.dao.productionOperation;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;

import java.util.Collection;
import java.util.List;

public abstract interface ProductionOperationDao
{
  public abstract void storeProductionOperation(ProductionOperation paramProductionOperation);

  public abstract ProductionOperation loadProductionOperation(Long paramLong);

  public abstract List<ProductionOperation> loadAllProductionOperation(Long[] paramArrayOfLong);

  public abstract List<ProductionOperation> loadAllProductionOperations();

  public abstract void deleteProductionOperation(ProductionOperation paramProductionOperation);

  public abstract void deleteAllProductionOperation(Collection<ProductionOperation> paramCollection);

  public abstract List<ProductionOperation> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

