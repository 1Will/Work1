package com.yongjun.tdms.dao.productionOperation.productionOperationException;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.productionOperation.productionOperationException.ProductionOperationException;

import java.util.Collection;
import java.util.List;

public abstract interface ProductionOperationExceptionDao
{
  public abstract void storeProductionOperationException(ProductionOperationException paramProductionOperationException);

  public abstract ProductionOperationException loadProductionOperationException(Long paramLong);

  public abstract List<ProductionOperationException> loadAllProductionOperationException(Long[] paramArrayOfLong);

  public abstract List<ProductionOperationException> loadAllProductionOperationExceptions();

  public abstract void deleteProductionOperationException(ProductionOperationException paramProductionOperationException);

  public abstract void deleteAllProductionOperationException(Collection<ProductionOperationException> paramCollection);

  public abstract List<ProductionOperationException> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

