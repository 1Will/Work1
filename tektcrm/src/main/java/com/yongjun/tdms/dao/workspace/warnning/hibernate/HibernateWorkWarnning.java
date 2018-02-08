package com.yongjun.tdms.dao.workspace.warnning.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDao;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;

public class HibernateWorkWarnning extends BaseHibernateDao implements WorkWarnningDao {
	public WorkWarnning loadWorkWarnning(Long workWarnningId) {
		return (WorkWarnning) load(WorkWarnning.class, workWarnningId);
	}

	public List<WorkWarnning> loadAllWorkWarnnings(Long[] workWarnningIds) {
		return loadAll(WorkWarnning.class, workWarnningIds);
	}

	public List<WorkWarnning> loadAllWorkWarnnings() {
		return loadAll(WorkWarnning.class);
	}

	public void storeWorkWarnning(WorkWarnning workWarnning) {
		store(workWarnning);
	}

	public void deleteWorkWarnning(WorkWarnning workWarnning) {
		delete(workWarnning);
	}

	public void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		deleteAll(workWarnnings);
	}

	public Long GetNumberOfUnReadWarnningByUserID(final Long userId) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.getNamedQuery("GetNumberOfUnReadWarnningByUserID").setParameter("userId", userId)
						.uniqueResult();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkWarnning> loadWarByUser(Long userId){
		String hql = "from WorkWarnning as w where w.readFlag =0 and w.warnedPeople.id = " + userId +" order by w.id desc";
		List<WorkWarnning> wList = getSession().createQuery(hql).list();
		return wList;
	}

	public List<WorkWarnning> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(WorkWarnning.class, keyNames, keyValues);
	}

	public List<WorkWarnning> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(WorkWarnning.class, keyName, keyValue);
	}
}
