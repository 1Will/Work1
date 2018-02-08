package com.yongjun.tdms.service.runmaintenance.checkpoint;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;

/**
 * @author qs
 * @version $Id: CheckPointRuleDetailManager.java 7287 2007-09-12 06:31:09Z qsun $
 */
@Transactional(readOnly = true)
public interface CheckPointRuleDetailManager {
	@Transactional
	void storeRuleDetail(CheckPointRuleDetail ruleDetail);
	
	List<CheckPointRuleDetail> loadAllCheckPointRuleDetail(Long[] checkPointRuleDetailIds);
	
	CheckPointRuleDetail loadCheckPointRuleDetail(Long id);
	
	@Transactional
	void deleteAllCheckPointRuleDetail(List list);
}
