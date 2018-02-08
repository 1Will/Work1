package com.yongjun.tdms.dao.runmaintenance.checkpoint.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointRuleDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;

/**
 * @author qs
 * @version $Id: HibernateCheckPointRule.java 7411 2007-09-17 05:07:50Z wzou $
 */
public class HibernateCheckPointRule extends BaseHibernateDao implements CheckPointRuleDao {

	public void storeRule(CheckPointRule rule) {
		this.store(rule);
	}

	public CheckPointRule loadRule(Long id) {
		return this.load(CheckPointRule.class,id);
	}

	public CheckPointRuleDetail loadCheckPointRuleDetail(Long id) {
		return this.load(CheckPointRuleDetail.class,id);
	}
	
	@Deprecated
	public void refresh(CheckPointRule rule) {
		super.initialize(rule.getDevice());
	}

	@SuppressWarnings("unchecked")
	public void deleteAllCheckPointRule(List list) {
		this.deleteAll(list);
	}

	public List<CheckPointRule> loadAllCheckPointRule(Long[] checkPointRuIds) {
		return loadAll(CheckPointRule.class,checkPointRuIds);
	}

	public List<CheckPointRule> loadAllCheckPointRules() {
		return loadAll(CheckPointRule.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> searchRuleTypeId(final Long deviceId) {
		return (List<Long>) this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
					  throws HibernateException, SQLException {
						return session.getNamedQuery("GetRuleTypeId")
						.setParameter("deviceId",deviceId)
						.list();
					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public CheckPointRule GetCheckPointRuleByIdAndTypeId(final Long ruleId, final Long deviceId, final Long ruleTypeId) {
		return (CheckPointRule) this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
					  throws HibernateException, SQLException {
						return session.getNamedQuery("GetCheckPointRuleByIdAndTypeId")
						.setParameter("ruleId", ruleId)
						.setParameter("deviceId",deviceId)
						.setParameter("ruleTypeId", ruleTypeId)
						.uniqueResult();
					}
				});
	}
}
	
				


