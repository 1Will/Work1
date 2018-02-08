package com.yongjun.tdms.dao.base.org.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.dao.base.org.DepartmentDao;

public class HibernateDepartment extends BaseHibernateDao implements DepartmentDao {
	public void storeDepartment(Department department) {
		store(department);
		getHibernateTemplate().flush();
	}

	public Department loadDepartment(Long departmentId) {
		return (Department) load(Department.class, departmentId);
	}

	public void deleteDepartment(Department department) {
		delete(department);
	}

	public void deleteAllDepartments(Collection<Department> departmentIds) {
		deleteAll(departmentIds);
	}

	public List<Department> loadAllDepartment(Long[] departmentIds) {
		return loadAll(Department.class, departmentIds);
	}

	public List<Department> loadAllDepartments() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.getNamedQuery("LoadAllDepartmentsBySortIdx").list();
			}
		});
	}

	public List<Integer> getDeptStepAfterGroupingByStep() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				return session.getNamedQuery("getDeptStepAfterGroupingByStep").list();
			}
		});
	}

	public List<Department> getDeptsByStep(final int step) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				return session.getNamedQuery("getDeptsByStep").setParameter("step", Integer.valueOf(step)).list();
			}
		});
	}

	public List<Department> getDeptsByParentDept(final Long parentId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				return session.getNamedQuery("getDeptsByParentDept").setParameter("parentId", parentId).list();
			}
		});
	}

	public List displaySortUser(Long departmentId) {
		Transaction tx = null;
		Connection conn = null;
		PreparedStatement resultList = null;
		ResultSet result = null;
		Session session = getSession();
		List list = new ArrayList();
		try {
			tx = session.beginTransaction();
			conn = session.connection();
			String resultSet = "select *from t_department_user where DEPARTMENT_ID=" + departmentId;
			resultList = conn.prepareStatement(resultSet);
			result = resultList.executeQuery();
			while (result.next()) {
				for (int i = 2; i < result.getMetaData().getColumnCount() + 1; i += 3) {
					list.add(Long.valueOf(result.getLong("USER_ID")));
				}
			}
		} catch (Exception e) {
		} finally {
			releaseSession(session);
		}
		return list;
	}

	public List<Department> InstitutionSelectDept(Long instId, boolean isNew) {
		String hql = null;
		if (isNew)
			hql = "select dept.id,dept.name from Department as dept where dept.inst.id = " + instId;
		else if (instId == -1l) {
			hql = "select dept.id,dept.name from Department as dept where dept.disabled = false ";
		} else {
			hql = "select dept.id,dept.name from Department as dept where dept.disabled = false and dept.inst.id = "
					+ instId;
		}
		Session session = getSession();
		List list = new ArrayList();
		list = session.createQuery(hql).list();
		return list;
	}

	public List<Department> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(Department.class, keyName, keyValue);
	}

	public int getDeptSteps() {
		int count = ((Integer) getSession().getNamedQuery("GetDeptSteps").uniqueResult()).intValue();
		return count + 1;
	}

	public List<Department> getDeptsByUserId(Long userId, String flag) throws Exception {
		List depts = getDeptByUserId(userId);

		String sql = "";
		if ("search".equals(flag))
			sql = "select * from t_department as dept where dept.id in (select du.department_id from t_department_user as du where du.user_id = "
					+ userId + ")";
		else if ("edit".equals(flag)) {
			sql = "select * from t_department as dept where dept.disabled=0 and dept.id in (select du.department_id from t_department_user as du where du.user_id = "
					+ userId + ")";
		}
		Session session = getSession();
		ResultSet rs = null;
		try {
			rs = session.connection().createStatement().executeQuery(sql);
			Department d;
			if (rs != null) {
				while (rs.next()) {
					d = new Department();
					d.setId(Long.valueOf(rs.getLong("id")));
					d.setName(rs.getString("name"));
					Institution inst = new Institution();
					inst.setId(Long.valueOf(rs.getLong("inst_id")));
					d.setInst(inst);
					depts.add(d);
				}
			}
			return depts;
		} finally {
			if (rs != null) {
				rs.close();
			}
			releaseSession(session);
		}
	}

	public List<Department> getDeptByUserId(Long userId) throws Exception {
		String sql = "select * from t_department as dept where dept.id = (select u.department_id from t_users as u where u.id ="
				+ userId + ")";
		Session session = getSession();
		ResultSet rs = null;
		try {
			rs = session.connection().createStatement().executeQuery(sql);
			List depts = new ArrayList();
			Department d;
			if (rs != null) {
				while (rs.next()) {
					d = new Department();
					d.setId(Long.valueOf(rs.getLong("id")));
					d.setName(rs.getString("name"));
					Institution inst = new Institution();
					inst.setId(Long.valueOf(rs.getLong("inst_id")));
					d.setInst(inst);
					depts.add(d);
				}
			}
			return depts;
		} finally {
			if (rs != null) {
				rs.close();
			}
			releaseSession(session);
		}
	}

	public List<Department> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(Department.class, keyNames, keyNames);
	}
}
