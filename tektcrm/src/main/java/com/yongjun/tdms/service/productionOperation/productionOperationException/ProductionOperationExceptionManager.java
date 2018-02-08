package com.yongjun.tdms.service.productionOperation.productionOperationException;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.productionOperation.productionOperationException.ProductionOperationException;

import java.util.Collection;
import java.util.List;

public abstract interface ProductionOperationExceptionManager
{
  public abstract void storeProductionOperationException(ProductionOperationException paramProductionOperationException);

  public abstract ProductionOperationException loadProductionOperationException(Long paramLong);

  public abstract List<ProductionOperationException> loadAllProductionOperationException(Long[] paramArrayOfLong);

  public abstract List<ProductionOperationException> loadAllProductionOperationExceptions();

  public abstract void deleteProductionOperationException(ProductionOperationException paramProductionOperationException);

  public abstract void deleteAllProductionOperationException(Collection<ProductionOperationException> paramCollection);

  public abstract List<ProductionOperationException> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract void disabledAllProductionOperationExceptions(Collection<ProductionOperationException> paramCollection);

  public abstract void enabledAllProductionOperationExceptions(Collection<ProductionOperationException> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.advisory.AdvisoryManager
 * JD-Core Version:    0.6.2
 */