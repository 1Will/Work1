package com.yongjun.tdms.dao.base.industryType.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.industryType.IndustoryDao;
import com.yongjun.tdms.model.base.industryType.Industry;

public class HibernateIndustry extends BaseHibernateDao implements IndustoryDao{

	public void storeIndustry(Industry industry) {
		// TODO Auto-generated method stub
		store(industry);
	}

	public Industry loadIndustry(Long id) {
		// TODO Auto-generated method stub
		return load(Industry.class, id);
	}

	public List<Industry> loadAllIndustry(Long[] ids) {
		// TODO Auto-generated method stub
		return loadAll(Industry.class, ids);
	}

	public List<Industry> loadAllIndustry() {
		// TODO Auto-generated method stub
		return loadAll(Industry.class);
	}

	public void deleteIndustry(Industry industry) {
		// TODO Auto-generated method stub
		delete(industry);
	}

	public void deleteAllIndustry(Collection<Industry> industryCollection) {
		// TODO Auto-generated method stub
		deleteAll(industryCollection);
	}

	public List<Industry> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return loadByKey(Industry.class, key, value);
	}

	public List<Industry> loadByKeyArray(String[] keys, Object[] values) throws DaoException {
		// TODO Auto-generated method stub
		return loadByKeyArray(Industry.class, keys, values);
	}



}
