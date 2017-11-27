package com.yongjun.tdms.dao.CustomerRelationship.customerNews.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.customerNews.CustomerNewsDao;
import com.yongjun.tdms.model.CustomerRelationship.customerNews.CustomerNews;

public class HibernateCustomerNews extends BaseHibernateDao implements CustomerNewsDao {

	public void storeCustomerNews(CustomerNews customerNews) {
		super.store(customerNews);
	}

	public CustomerNews loadCustomerNews(Long paramLong) {
		return super.load(CustomerNews.class, paramLong);
	}

	public List<CustomerNews> loadCustomerNews() {
		return super.loadAll(CustomerNews.class);
	}

	public List<CustomerNews> loadAllCustomerNews(Long[] paramArrayOfLong) {
		return super.loadAll(CustomerNews.class, paramArrayOfLong);
	}

	public void deleteCustomerNews(CustomerNews paramCustomerNews) {
		super.delete(paramCustomerNews);
	}

	public void deleteAllCustomerNews(List<CustomerNews> paramList) {
		super.deleteAll(paramList);
	}

	public List<CustomerNews> loadByKey(String paramString, Object paramObject) throws DaoException {
		return super.loadByKey(CustomerNews.class, paramString, paramObject);
	}

	public List<CustomerNews> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return super.loadByKeyArray(CustomerNews.class, paramArrayOfString, paramArrayOfObject);
	}



}
