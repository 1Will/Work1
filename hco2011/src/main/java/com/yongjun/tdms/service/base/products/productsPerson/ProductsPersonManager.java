package com.yongjun.tdms.service.base.products.productsPerson;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.products.productsPerson.ProductsPerson;

public abstract interface ProductsPersonManager {
	public abstract void storeProductsPerson(ProductsPerson paramProducts);

	public abstract List<ProductsPerson> loadAllProductsPerson(Long[] paramArrayOfLong);

	public abstract void deleteAllProductsPerson(List<ProductsPerson> paramList);

	public abstract ProductsPerson loadProductsPerson(Long paramLong);

	public abstract void deleteProductsPerson(ProductsPerson paramProducts);

	public abstract void disabledAllProductsPerson(Collection<ProductsPerson> paramCollection);

	public abstract void enabledAllProductsPerson(Collection<ProductsPerson> paramCollection);

	public abstract List<ProductsPerson> loadByKey(String paramString, Object paramObject) throws DaoException;
}
