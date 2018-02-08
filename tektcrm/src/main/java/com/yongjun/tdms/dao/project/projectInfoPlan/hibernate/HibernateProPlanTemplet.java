package com.yongjun.tdms.dao.project.projectInfoPlan.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoPlan.ProPlanTempletDao;
import com.yongjun.tdms.model.project.projectInfoPlan.ProPlanTemplet;

public class HibernateProPlanTemplet extends BaseHibernateDao  implements ProPlanTempletDao {

	public void storeProPlanTemplet(ProPlanTemplet paramProPlanTemplet) {
		store(paramProPlanTemplet);
	}

	public ProPlanTemplet loadProPlanTemplet(Long paramLong) {
		return (ProPlanTemplet)load(ProPlanTemplet.class, paramLong);
	}

	public List<ProPlanTemplet> loadAllProPlanTemplet(Long[] paramArrayOfLong) {
		return loadAll(ProPlanTemplet.class, paramArrayOfLong);
	}

	public List<ProPlanTemplet> loadAllProPlanTemplets() {
		return loadAll(ProPlanTemplet.class);
	}

	public void deleteProPlanTemplet(ProPlanTemplet paramProPlanTemplet) {
		delete(paramProPlanTemplet);
	}

	public void deleteAllProPlanTemplet(Collection<ProPlanTemplet> paramCollection) {
		deleteAll(paramCollection);
	}

	public List<ProPlanTemplet> loadByKey(String paramString, Object paramObject) throws DaoException {
		return loadByKey(ProPlanTemplet.class, paramString, paramObject);
	}

	public List<ProPlanTemplet> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return loadByKeyArray(ProPlanTemplet.class,paramArrayOfString, paramArrayOfObject);
	}

}
