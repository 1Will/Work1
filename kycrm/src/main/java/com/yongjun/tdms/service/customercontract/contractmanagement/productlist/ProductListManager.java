package com.yongjun.tdms.service.customercontract.contractmanagement.productlist;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;

import java.util.Date;
import java.util.List;

public abstract interface ProductListManager
{
  public abstract void storeProductList(ProductList paramProductList);

  public abstract ProductList loadProductList(Long paramLong);

  public abstract List<ProductList> loadProductList();

  public abstract List<ProductList> loadAllProductList(Long[] paramArrayOfLong);

  public abstract void deleteProductList(ProductList paramProductList);

  public abstract void deleteAllProductList(List<ProductList> paramList);

  public abstract List<ProductList> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ProductList> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);

  public abstract void disabledAllProductList(List<ProductList> paramList);

  public abstract void enabledAllProductList(List<ProductList> paramList);

  public abstract double getSumTotalPrice(long paramLong)
    throws DaoException;
  public abstract List<ProductList> loadProductListsByTime(long cusId,String proName, Date date);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager
 * JD-Core Version:    0.6.2
 */