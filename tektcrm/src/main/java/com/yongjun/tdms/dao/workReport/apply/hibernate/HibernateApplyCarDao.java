package com.yongjun.tdms.dao.workReport.apply.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.apply.ApplyCarDao;
import com.yongjun.tdms.model.workReport.apply.ApplyCar;

public class HibernateApplyCarDao extends BaseHibernateDao  implements ApplyCarDao{

	public void storeApplyCar(ApplyCar applyCar) {
		store(applyCar);
	}

	public void deleteApplyCar(ApplyCar applyCar) {
		delete(applyCar);
	}

	public void deleteApplyCar(Collection<ApplyCar> paramCollection) {
		deleteAll(paramCollection);
	}

	public List<ApplyCar> loadAllApplyCar(Long[] paramArrayOfLong) {
		return loadAll(ApplyCar.class, paramArrayOfLong);
	}

	public ApplyCar loadApplyCar(Long paramLong) {
		return load(ApplyCar.class, paramLong);
	}

	public List<ApplyCar> loadAllApplyCar() {
		return loadAll(ApplyCar.class);
	}

	public List<ApplyCar> loadByKey(String paramString, Object paramObject) throws DaoException {
		return loadByKey(ApplyCar.class,paramString, paramObject);
	}

	public List<ApplyCar> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject) throws DaoException {
		return loadByKeyArray(ApplyCar.class, paramArrayOfString, paramArrayOfObject);
	}

}
