package com.yongjun.tdms.service.productionOperation;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.advisory.Advisory;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;

import java.util.Collection;
import java.util.List;

public abstract interface ProductionOperationManager
{
  public abstract void storeProductionOperation(ProductionOperation paramProductionOperation);

  public abstract ProductionOperation loadProductionOperation(Long paramLong);

  public abstract List<ProductionOperation> loadAllProductionOperation(Long[] paramArrayOfLong);

  public abstract List<ProductionOperation> loadAllProductionOperations();

  public abstract void deleteProductionOperation(ProductionOperation paramProductionOperation);

  public abstract void deleteAllProductionOperation(Collection<ProductionOperation> paramCollection);

  public abstract List<ProductionOperation> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract void disabledAllProductionOperations(Collection<ProductionOperation> paramCollection);

  public abstract void enabledAllProductionOperations(Collection<ProductionOperation> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.advisory.AdvisoryManager
 * JD-Core Version:    0.6.2
 */