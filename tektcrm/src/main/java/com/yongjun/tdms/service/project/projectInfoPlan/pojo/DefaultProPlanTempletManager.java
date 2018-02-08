package com.yongjun.tdms.service.project.projectInfoPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoPlan.ProPlanTempletDao;
import com.yongjun.tdms.model.project.projectInfoPlan.ProPlanTemplet;
import com.yongjun.tdms.service.project.projectInfoPlan.ProPlanTempletManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultProPlanTempletManager extends BaseManager implements ProPlanTempletManager{
    private ProPlanTempletDao proPlanTempletDao;
    private YongJunSequenceManager yongJunSequenceManager;
    
	public DefaultProPlanTempletManager(ProPlanTempletDao proPlanTempletDao,YongJunSequenceManager yongJunSequenceManager) {
		this.proPlanTempletDao = proPlanTempletDao;
		this.yongJunSequenceManager=yongJunSequenceManager;
	}

	public void storeProPlanTemplet(ProPlanTemplet paramProPlanTemplet) {
		if(paramProPlanTemplet.isNew()){
			paramProPlanTemplet.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_PROPLANTEMPLET));
		}
		 
		proPlanTempletDao.storeProPlanTemplet(paramProPlanTemplet);
	}

	public ProPlanTemplet loadProPlanTemplet(Long paramLong) {
		return proPlanTempletDao.loadProPlanTemplet(paramLong);
	}

	public List<ProPlanTemplet> loadAllProPlanTemplet(Long[] paramArrayOfLong) {
		return proPlanTempletDao.loadAllProPlanTemplet(paramArrayOfLong);
	}

	public List<ProPlanTemplet> loadAllProPlanTemplets() {
		return proPlanTempletDao.loadAllProPlanTemplets();
	}

	public void deleteProPlanTemplet(ProPlanTemplet paramProPlanTemplet) {
		proPlanTempletDao.deleteProPlanTemplet(paramProPlanTemplet);
	}

	public void deleteAllProPlanTemplet(Collection<ProPlanTemplet> paramCollection) {
		proPlanTempletDao.deleteAllProPlanTemplet(paramCollection);
	}

	public List<ProPlanTemplet> loadByKey(String paramString, Object paramObject) throws DaoException {
		return proPlanTempletDao.loadByKey(paramString, paramObject);
	}

	public List<ProPlanTemplet> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return proPlanTempletDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

}
