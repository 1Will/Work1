package com.yongjun.tdms.service.CustomerRelationship.customerProduct;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.customerProduct.CustomerProduct;

public abstract interface  CustomerProductManager {
   	  public abstract void storeCustomerProduct(CustomerProduct customerProduct);

	  public abstract CustomerProduct loadCustomerProduct(Long paramLong);

	  public abstract List<CustomerProduct> loadCustomerProduct();

	  public abstract List<CustomerProduct> loadAllCustomerProduct(Long[] paramArrayOfLong);

	  public abstract void deleteCustomerProduct(CustomerProduct paramCustomerProduct);

	  public abstract void deleteAllCustomerProduct(List<CustomerProduct> paramList);

	  public abstract List<CustomerProduct> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<CustomerProduct> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
