package com.yongjun.tdms.service.base.industoryType.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.industryType.IndustoryDao;
import com.yongjun.tdms.model.base.industryType.Industry;
import com.yongjun.tdms.service.base.industoryType.IndustryManager;

public class DefaultIndustryManager implements IndustryManager {
	
	private IndustoryDao industoryDao;
	public DefaultIndustryManager(IndustoryDao industoryDao) {
		this.industoryDao = industoryDao;
	}
	public void storeIndustry(Industry industry) {
		// TODO Auto-generated method stub
		industoryDao.storeIndustry(industry);
	}
	public Industry loadIndustry(Long id) {
		// TODO Auto-generated method stub
		return industoryDao.loadIndustry(id);
	}
	public List<Industry> loadAllIndustry(Long[] ids) {
		// TODO Auto-generated method stub
		return industoryDao.loadAllIndustry(ids);
	}
	public List<Industry> loadAllIndustry() {
		// TODO Auto-generated method stub
		return industoryDao.loadAllIndustry();
	}
	public void deleteIndustry(Industry industry) {
		// TODO Auto-generated method stub
		industoryDao.deleteIndustry(industry);
	}
	public void deleteAllIndustry(Collection<Industry> inudstryCollection) {
		// TODO Auto-generated method stub
		industoryDao.deleteAllIndustry(inudstryCollection);
	}
	public List<Industry> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return industoryDao.loadByKey(key, value);
	}
	public List<Industry> loadByPid(Long value) throws DaoException {
		// TODO Auto-generated method stub
		return industoryDao.loadByKey("pid", value);
	}
	public List<Industry> loadByKeyArray(String[] keys, Object[] values) throws DaoException {
		// TODO Auto-generated method stub
		return industoryDao.loadByKeyArray(keys, values);
	}
	

}
