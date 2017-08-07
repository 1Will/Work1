package com.yongjun.tdms.dao.base.products.productsPerson;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.products.productsPerson.ProductsPerson;

public abstract interface ProductsPersonDao {
	public abstract void storeProducts(ProductsPerson paramProducts);

	public abstract ProductsPerson loadProductType(Long paramLong);

	public abstract List<ProductsPerson> loadAllProducts(Long[] paramArrayOfLong);

	public abstract List<ProductsPerson> loadAllProducts();

	public abstract void deleteProducts(ProductsPerson paramProducts);

	public abstract void deleteAllProducts(Collection<ProductsPerson> paramCollection);

	public abstract List<ProductsPerson> loadByKey(String paramString, Object paramObject) throws DaoException;
}