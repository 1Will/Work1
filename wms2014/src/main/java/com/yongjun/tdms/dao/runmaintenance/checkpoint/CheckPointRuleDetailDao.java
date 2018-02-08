package com.yongjun.tdms.dao.runmaintenance.checkpoint;

import java.util.List;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;

/**
 * @author qs
 * @version $Id: CheckPointRuleDetailDao.java 7542 2007-09-20 01:10:59Z qsun $
 */
public interface CheckPointRuleDetailDao {
	void storeRuleDetail(CheckPointRuleDetail ruleDetail);
	
	CheckPointRuleDetail loadCheckPointRuleDetail(Long id);
	
	List<CheckPointRuleDetail> loadAllCheckPointRuleDetail(Long[] checkPointRuleDetailIds);
	
	void deleteAllCheckPointRuleDetail(List list);
}
