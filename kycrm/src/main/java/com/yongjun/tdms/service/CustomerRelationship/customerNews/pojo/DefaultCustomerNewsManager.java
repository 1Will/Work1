package com.yongjun.tdms.service.CustomerRelationship.customerNews.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.customerNews.CustomerNewsDao;
import com.yongjun.tdms.model.CustomerRelationship.customerNews.CustomerNews;
import com.yongjun.tdms.service.CustomerRelationship.customerNews.CustomerNewsManager;

public class DefaultCustomerNewsManager extends BaseManager implements CustomerNewsManager{
	private CustomerNewsDao customerNewsDao;

	public DefaultCustomerNewsManager(CustomerNewsDao customerNewsDao) {
		this.customerNewsDao = customerNewsDao;
	}

	public void storeCustomerNews(CustomerNews customerNews) {
		customerNewsDao.storeCustomerNews(customerNews);
	}

	public CustomerNews loadCustomerNews(Long paramLong) {
		return customerNewsDao.loadCustomerNews(paramLong);
	}

	public List<CustomerNews> loadCustomerNews() {
		return customerNewsDao.loadCustomerNews();
	}

	public List<CustomerNews> loadAllCustomerNews(Long[] paramArrayOfLong) {
		return customerNewsDao.loadAllCustomerNews(paramArrayOfLong);
	}

	public void deleteCustomerNews(CustomerNews paramCustomerNews) {
		customerNewsDao.deleteCustomerNews(paramCustomerNews);
	}

	public void deleteAllCustomerNews(List<CustomerNews> paramList) {
		customerNewsDao.deleteAllCustomerNews(paramList);
	}

	public List<CustomerNews> loadByKey(String paramString, Object paramObject) throws DaoException {
		return customerNewsDao.loadByKey(paramString, paramObject);
	}

	public List<CustomerNews> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return customerNewsDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

}
