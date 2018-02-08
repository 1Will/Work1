package com.yongjun.tdms.dao.runmaintenance.lubrication.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationPlanDetailDao;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;

public class HibernateLubricationPlanDetail extends BaseHibernateDao implements
		LubricationPlanDetailDao{

	public void storeLubricationPlanDetail(LubricationPlanDetail lubricationPlanDetail){
		this.store(lubricationPlanDetail);
	}
	
	public LubricationPlanDetail loadLubricationPlanDetail(Long id){
		return this.load(LubricationPlanDetail.class,id);
	}
	
	public void deleteAllLubricationPlanDetail(List<LubricationPlanDetail> list){
		this.deleteAll(list);
	}
	
	public List<LubricationPlanDetail> loadAllLubricationPlanDetail(Long[] lubricationPlanDetailIds){
		return this.loadAll(LubricationPlanDetail.class,lubricationPlanDetailIds);
	}
	
	public List<LubricationPlanDetail> loadAllLubricationPlanDetail(){
		return this.loadAll(LubricationPlanDetail.class);
	}

	public Date loadMaxActualExecDateByDeviceIDAndRuleID(final Long deviceId, final Long ruleId) {
		return (Date) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadMaxActualExecDateByDeviceIDAndRuleID")
								.setParameter("deviceId", deviceId)
								.setParameter("ruleId", ruleId)
								.uniqueResult();
					}
				});
	}
}
