package com.yongjun.tdms.service.base.products;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.products.Products;

import java.util.Collection;
import java.util.List;

public abstract interface ProductsManager
{
  public abstract void storeProducts(Products paramProducts);

  public abstract List<Products> loadAllProducts(Long[] paramArrayOfLong);

  public abstract void deleteAllProducts(List<Products> paramList);

  public abstract Products loadProducts(Long paramLong);

  public abstract void deleteProducts(Products paramProducts);

  public abstract void disabledAllProducts(Collection<Products> paramCollection);

  public abstract void enabledAllProducts(Collection<Products> paramCollection);

  public abstract List<Products> getAllProductsByNull(String paramString);

  public abstract List<Products> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  public abstract void saveProductfoByImp(List<Products> products);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.products.ProductsManager
 * JD-Core Version:    0.6.2
 */