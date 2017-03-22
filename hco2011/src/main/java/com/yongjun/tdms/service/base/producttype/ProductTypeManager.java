package com.yongjun.tdms.service.base.producttype;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.produttype.ProductType;
import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public abstract interface ProductTypeManager
{
  public abstract void storeProductType(ProductType paramProductType);

  public abstract void deleteProductType(ProductType paramProductType);

  public abstract void deleteAllProductType(Collection<ProductType> paramCollection);

  public abstract List<ProductType> loadAllProductType(Long[] paramArrayOfLong);

  @Transactional
  public abstract ProductType loadProductType(Long paramLong);

  public abstract List<ProductType> loadAllProductType();

  public abstract void disabledAllProductType(Collection<ProductType> paramCollection);

  public abstract void enabledAllProductType(Collection<ProductType> paramCollection);

  public abstract List createSelectProductTypes(String paramString);

  public abstract List<ProductType> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Integer> getStepAfterGroupingByStep();

  public abstract int getMaxPTId();

  public abstract List<ProductType> getPT(ProductType paramProductType, List<ProductType> paramList, String paramString);

  public abstract List<ProductType> getAllProductTypeByNull(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.producttype.ProductTypeManager
 * JD-Core Version:    0.6.2
 */