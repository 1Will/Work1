package com.yongjun.tdms.dao.runmaintenance.checkpoint.hibernate;

import java.util.List;



import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointRuleDetailDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;

/**
 * @author qs
 * @version $Id: HibernateCheckPointRuleDetail.java 7542 2007-09-20 01:10:59Z qsun $
 */
public class HibernateCheckPointRuleDetail extends BaseHibernateDao implements CheckPointRuleDetailDao {

	public void storeRuleDetail(CheckPointRuleDetail ruleDetail) {
		this.store(ruleDetail);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteAllCheckPointRuleDetail(List list) {
		this.deleteAll(list);
		
	}

	public List<CheckPointRuleDetail> loadAllCheckPointRuleDetail(Long[] checkPointRuleDetailIds) {
		return loadAll(CheckPointRuleDetail.class,checkPointRuleDetailIds);
	}

	public CheckPointRuleDetail loadCheckPointRuleDetail(Long id) {
		return this.load(CheckPointRuleDetail.class, id);
	}
	
}
