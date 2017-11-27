package com.yongjun.tdms.dao.CustomerRelationship.customerNews;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.customerNews.CustomerNews;

public abstract interface CustomerNewsDao {
	  public abstract void storeCustomerNews(CustomerNews customerNews);

	  public abstract CustomerNews loadCustomerNews(Long paramLong);

	  public abstract List<CustomerNews> loadCustomerNews();

	  public abstract List<CustomerNews> loadAllCustomerNews(Long[] paramArrayOfLong);

	  public abstract void deleteCustomerNews(CustomerNews paramCustomerNews);

	  public abstract void deleteAllCustomerNews(List<CustomerNews> paramList);

	  public abstract List<CustomerNews> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<CustomerNews> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
