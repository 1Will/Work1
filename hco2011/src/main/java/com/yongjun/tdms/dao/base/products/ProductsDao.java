package com.yongjun.tdms.dao.base.products;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.products.Products;
import java.util.Collection;
import java.util.List;

public abstract interface ProductsDao
{
  public abstract void storeProducts(Products paramProducts);

  public abstract Products loadProductType(Long paramLong);

  public abstract List<Products> loadAllProducts(Long[] paramArrayOfLong);

  public abstract List<Products> loadAllProducts();

  public abstract void deleteProducts(Products paramProducts);

  public abstract void deleteAllProducts(Collection<Products> paramCollection);

  public abstract List<Products> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.products.ProductsDao
 * JD-Core Version:    0.6.2
 */