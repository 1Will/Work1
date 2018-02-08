package com.yongjun.tdms.dao.runmaintenance.checkpoint;

import java.util.List;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;

/**
 * @author qs
 * @version $Id: CheckPointRuleDao.java 7411 2007-09-17 05:07:50Z wzou $
 */
public interface CheckPointRuleDao {
	void storeRule(CheckPointRule Rule);
	
	public CheckPointRule loadRule(Long id);
	
	CheckPointRuleDetail loadCheckPointRuleDetail(Long id);

	void refresh(CheckPointRule rule);
	
	void deleteAllCheckPointRule(List list);
	
	List<CheckPointRule> loadAllCheckPointRule(Long[] checkPointRuIds);

	List<CheckPointRule> loadAllCheckPointRules();
	
	List<Long> searchRuleTypeId(final Long deviceId);
	
	CheckPointRule GetCheckPointRuleByIdAndTypeId(final Long ruleId, final Long deviceId, final Long ruleTypeId);
	
}
