package com.yongjun.tdms.service.CustomerRelationship.customerProduct.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.customerProduct.CustomerProductDao;
import com.yongjun.tdms.model.CustomerRelationship.customerProduct.CustomerProduct;
import com.yongjun.tdms.service.CustomerRelationship.customerProduct.CustomerProductManager;

public class DefaultCustomerProductManager extends BaseManager implements CustomerProductManager{
    private CustomerProductDao customerProductDao;
	public DefaultCustomerProductManager(CustomerProductDao customerProductDao) {
		this.customerProductDao = customerProductDao;
	}

	public void storeCustomerProduct(CustomerProduct customerProduct) {
		customerProductDao.storeCustomerProduct(customerProduct);
	}

	public CustomerProduct loadCustomerProduct(Long paramLong) {
		return customerProductDao.loadCustomerProduct(paramLong);
	}

	public List<CustomerProduct> loadCustomerProduct() {
		return customerProductDao.loadCustomerProduct();
	}

	public List<CustomerProduct> loadAllCustomerProduct(Long[] paramArrayOfLong) {
		return customerProductDao.loadAllCustomerProduct(paramArrayOfLong);
	}

	public void deleteCustomerProduct(CustomerProduct paramCustomerProduct) {
		customerProductDao.deleteCustomerProduct(paramCustomerProduct);
	}

	public void deleteAllCustomerProduct(List<CustomerProduct> paramList) {
		customerProductDao.deleteAllCustomerProduct(paramList);
	}

	public List<CustomerProduct> loadByKey(String paramString, Object paramObject) throws DaoException {
		return customerProductDao.loadByKey(paramString, paramObject);
	}

	public List<CustomerProduct> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return customerProductDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

}
