package com.yongjun.tdms.dao.customercontract.plan.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.dao.customercontract.plan.ReturnPlanDao;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;

public class HibernateReturnPlanDao extends BaseHibernateDao implements ReturnPlanDao {
	public void storeReturnPlan(ReturnPlan returnPlan) {
		store(returnPlan);
	}

	public void deleteReturnPlan(ReturnPlan returnPlan) {
		delete(returnPlan);
	}

	public void deleteAllReturnPlans(Collection<ReturnPlan> returnPlans) {
		deleteAll(returnPlans);
	}

	public List<ReturnPlan> loadAllReturnPlans(Long[] returnPlanIds) {
		return loadAll(ReturnPlan.class, returnPlanIds);
	}

	public ReturnPlan loadReturnPlan(Long returnPlanId) {
		return (ReturnPlan) load(ReturnPlan.class, returnPlanId);
	}

	public List<ReturnPlan> loadAllReturnPlans() {
		return loadAll(ReturnPlan.class);
	}

	public List<ReturnPlan> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ReturnPlan.class, keyName, keyValue);
	}

	public List<ReturnPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ReturnPlan.class, keyNames, keyValues);
	}

	public List<ReturnPlan> contractManagementAndBatch(Long contractManagementId, Long batchsId, String code,
			boolean isNew) {
		String hql = null;
		if (isNew) {
			hql = "from ReturnPlan as r where r.contractManagement.id = " + contractManagementId + " and r.batch.id="
					+ batchsId;
		} else {
			hql = "from ReturnPlan as r where  r.disabled = 0 and r.contractManagement.id = " + contractManagementId
					+ " and r.batch.id=" + batchsId;
		}

		Session session = getSession();
		List list = new ArrayList();
		list = session.createQuery(hql).list();
		return list;
	}

	public List<CodeValue> contractAndBatch(Long contractManagementId, boolean isNew) {
		String hql = null;
		if (isNew) {
			hql = "select distinct r.batch.id,r.batch.name from ReturnPlan as r where r.isOrNot=1 and r.contractManagement.id = "
					+ contractManagementId;
		} else {
			hql = "select distinct r.batch.id,r.batch.name from ReturnPlan as r where r.isOrNot=1 and  r.disabled = 0 and r.contractManagement.id = "
					+ contractManagementId;
		}

		Session session = getSession();
		List list = new ArrayList();
		list = session.createQuery(hql).list();
		return list;
	}
	
	public List<CodeValue> batchForBill(Long contractManagementId, boolean isNew) {
		String hql = null;
		if (isNew) {
			hql = "select distinct r.batch.id,r.batch.name from ReturnPlan as r where r.isBill=1 and r.contractManagement.id = "
					+ contractManagementId;
		} else {
			hql = "select distinct r.batch.id,r.batch.name from ReturnPlan as r where r.isBill=1 and  r.disabled = 0 and r.contractManagement.id = "
					+ contractManagementId;
		}
		
		Session session = getSession();
		List list = new ArrayList();
		list = session.createQuery(hql).list();
		return list;
	}
}
