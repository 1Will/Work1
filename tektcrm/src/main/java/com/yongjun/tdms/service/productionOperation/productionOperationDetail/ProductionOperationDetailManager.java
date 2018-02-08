package com.yongjun.tdms.service.productionOperation.productionOperationDetail;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;

import java.util.Collection;
import java.util.List;

public abstract interface ProductionOperationDetailManager
{
  public abstract void storeProductionOperationDetail(ProductionOperationDetail paramProductionOperationDetail);

  public abstract ProductionOperationDetail loadProductionOperationDetail(Long paramLong);

  public abstract List<ProductionOperationDetail> loadAllProductionOperationDetail(Long[] paramArrayOfLong);

  public abstract List<ProductionOperationDetail> loadAllProductionOperationDetails();

  public abstract void deleteProductionOperationDetail(ProductionOperationDetail paramProductionOperationDetail);

  public abstract void deleteAllProductionOperationDetail(Collection<ProductionOperationDetail> paramCollection);

  public abstract List<ProductionOperationDetail> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract void disabledAllProductionOperationDetails(Collection<ProductionOperationDetail> paramCollection);

  public abstract void enabledAllProductionOperationDetails(Collection<ProductionOperationDetail> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.advisory.AdvisoryManager
 * JD-Core Version:    0.6.2
 */