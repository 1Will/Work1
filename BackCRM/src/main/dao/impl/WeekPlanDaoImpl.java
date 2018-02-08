package main.dao.impl;


import java.util.List;

import main.dao.WeekPlanDao;
import main.pojo.WeekPlan;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class WeekPlanDaoImpl extends HibernateDaoSupport implements WeekPlanDao {

	@Override
	public void saveWeekPlan(WeekPlan weekPlan) {

		try {
			this.getHibernateTemplate().save(weekPlan);
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}
	@Override
	public void updateWeekPlan(WeekPlan weekPlan) {
		
		try {
			this.getHibernateTemplate().merge(weekPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WeekPlan> getWeekPlanByWeekId(Long weekId, Long userid) {
		String hql="from WeekPlan w where w.weekId="+weekId+" and w.userid="+userid+" and w.weeklyId is null";
		return this.getHibernateTemplate().find(hql);
	}
    
	//根据id 获取周计划 实例
	@Override
	public WeekPlan getWeekPlanById(Long id) {
		WeekPlan weekPlan=null;
		try {
			weekPlan=(WeekPlan)getSession().load(WeekPlan.class, id);
		} catch (Exception e) {
		       e.printStackTrace();
		}
		return weekPlan;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WeekPlan> getWeekPlanByWeeklyId(Long weeklyId) {
		String hql="from WeekPlan w where w.weeklyId="+weeklyId;
		return this.getHibernateTemplate().find(hql);
	}


	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}



	
}
