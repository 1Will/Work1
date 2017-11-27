package com.yongjun.tdms.dao.CustomerRelationship.customerProduct.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.customerProduct.CustomerProductDao;
import com.yongjun.tdms.model.CustomerRelationship.customerProduct.CustomerProduct;

public class HibernateCustomerProduct extends BaseHibernateDao implements CustomerProductDao{

	public void storeCustomerProduct(CustomerProduct customerProduct) {
           super.store(customerProduct);		
	}

	public CustomerProduct loadCustomerProduct(Long paramLong) {
		return (CustomerProduct) super.load(CustomerProduct.class, paramLong);	
	}

	public List<CustomerProduct> loadCustomerProduct() {
		return  super.loadAll(CustomerProduct.class);
	}

	public List<CustomerProduct> loadAllCustomerProduct(Long[] paramArrayOfLong) {
		return  super.loadAll(CustomerProduct.class, paramArrayOfLong);
	}

	public void deleteCustomerProduct(CustomerProduct paramCustomerProduct) {
		super.delete(paramCustomerProduct);		
	}

	public void deleteAllCustomerProduct(List<CustomerProduct> paramList) {
		super.deleteAll(paramList);		
	}

	public List<CustomerProduct> loadByKey(String paramString, Object paramObject) throws DaoException {
		return super.loadByKey(CustomerProduct.class, paramString, paramObject);
	}

	public List<CustomerProduct> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return super.loadByKeyArray(CustomerProduct.class,paramArrayOfString, paramArrayOfObject);
	}

}
