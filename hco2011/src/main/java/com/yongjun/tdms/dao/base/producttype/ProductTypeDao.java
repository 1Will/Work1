package com.yongjun.tdms.dao.base.producttype;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.produttype.ProductType;
import java.util.Collection;
import java.util.List;

public abstract interface ProductTypeDao
{
  public abstract void storeProductType(ProductType paramProductType);

  public abstract void deleteProductType(ProductType paramProductType);

  public abstract void deleteAllProductType(Collection<ProductType> paramCollection);

  public abstract List<ProductType> loadAllProductType(Long[] paramArrayOfLong);

  public abstract ProductType loadProductType(Long paramLong);

  public abstract List<ProductType> loadAllProductType();

  public abstract List<ProductType> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Integer> getStepAfterGroupingByStep();

  public abstract int getMaxPTId();

  public abstract List<ProductType> loadAllProductTypes();
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.producttype.ProductTypeDao
 * JD-Core Version:    0.6.2
 */