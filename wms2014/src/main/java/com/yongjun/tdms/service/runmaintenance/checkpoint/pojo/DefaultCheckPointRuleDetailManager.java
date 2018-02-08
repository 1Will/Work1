package com.yongjun.tdms.service.runmaintenance.checkpoint.pojo;

import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointRuleDetailDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleDetailManager;

/**
 * @author qs
 * @version $Id: DefaultCheckPointRuleDetailManager.java 7441 2007-09-18 12:18:17Z qsun $
 */
public class DefaultCheckPointRuleDetailManager implements
		CheckPointRuleDetailManager {
	private final CheckPointRuleDetailDao checkPointRuleDetailDao;
	
	public DefaultCheckPointRuleDetailManager(CheckPointRuleDetailDao checkPointRuleDetailDao) {
		this.checkPointRuleDetailDao = checkPointRuleDetailDao;
	}
	
	public void storeRuleDetail(CheckPointRuleDetail ruleDetail) {
		this.checkPointRuleDetailDao.storeRuleDetail(ruleDetail);
	}

	public void deleteAllCheckPointRuleDetail(List list) {
		this.checkPointRuleDetailDao.deleteAllCheckPointRuleDetail(list);
		
	}

	public List<CheckPointRuleDetail> loadAllCheckPointRuleDetail(Long[] checkPointRuleDetailIds) {
		return checkPointRuleDetailDao.loadAllCheckPointRuleDetail(checkPointRuleDetailIds);
	}

	public CheckPointRuleDetail loadCheckPointRuleDetail(Long id) {
		return this.checkPointRuleDetailDao.loadCheckPointRuleDetail(id);
	}

}
