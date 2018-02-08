package com.yongjun.tdms.dao.runmaintenance.checkpoint.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.checkpoint.CheckPointPlanDetailDao;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;

public class HibernateCheckPointPlanDetail extends BaseHibernateDao implements CheckPointPlanDetailDao {

	public void storePlanDetail(CheckPointPlanDetail planDetail) {
		this.store(planDetail);
	}

	public CheckPointPlanDetail loadCheckPointPlanDetail(Long id) {
		return this.load(CheckPointPlanDetail.class,id);
	}

	public List<CheckPointPlanDetail> loadAllCheckPointPlanDetail(Long[] checkPointPlanDetailIds) {
		return loadAll(CheckPointPlanDetail.class,checkPointPlanDetailIds);
	}
	@SuppressWarnings("unchecked")
	public void deleteAllCheckPointPlanDetail(List list) {
		this.deleteAll(list);
	}

	public Long getPlanDetailMaxSerialNo(final CheckPointPlan plan) {
		Long maxSerialNo = (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetPlanDetailMaxSerialNo")
								.setParameter("planId", plan.getId())
								.uniqueResult();
					}
				});
		
		if (null == maxSerialNo) {
			return Long.valueOf(0L);
		} else {
			return maxSerialNo;
		}
	}
	
}
