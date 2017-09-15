package com.yongjun.tdms.dao.project.projectInfoPersonnels.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoPersonnels.ProjectInfoPersonnelDao;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;

public class HibernateProjectInfoPersonnels extends BaseHibernateDao implements ProjectInfoPersonnelDao {
	public void deleteAllProjectInfoPersonnels(Collection<ProjectInfoPersonnels> ProjectInfoPersonnelss) {
		deleteAll(ProjectInfoPersonnelss);
	}

	public void deleteProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
		delete(ProjectInfoPersonnels);
	}

	public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnels(Long[] ProjectInfoPersonnelsIds) {
		return loadAll(ProjectInfoPersonnels.class, ProjectInfoPersonnelsIds);
	}

	public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnelss() {
		return loadAll(ProjectInfoPersonnels.class);
	}

	public ProjectInfoPersonnels loadProjectInfoPersonnels(Long ProjectInfoPersonnelsId) {
		return (ProjectInfoPersonnels) load(ProjectInfoPersonnels.class, ProjectInfoPersonnelsId);
	}

	public List<ProjectInfoPersonnels> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ProjectInfoPersonnels.class, keyName, keyValue);
	}

	public List<ProjectInfoPersonnels> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ProjectInfoPersonnels.class, keyNames, keyValues);
	}

	public void storeProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
		store(ProjectInfoPersonnels);
	}

	public List<String> loadPersonnelsCodeByProjectInfoId(final Long projectInfoId) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select pp.proPerson.code from com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels pp where pp.projectInfo.id="
						+ projectInfoId;
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
	}

	public List<String> loadPersonnelsCodeByEnable() {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select pp.code from com.yongjun.tdms.model.personnelFiles.PersonnelFiles pp where pp.disabled=0";
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
	}
	
	public List<String> loadPersonnelsCodeByType(final String code) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select pp.code from com.yongjun.tdms.model.personnelFiles.PersonnelFiles pp where pp.disabled=0 and pp.businessType.code ='21003' or pp.businessType.code ='" + code+"'";
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
	}

	public List<Long> loadProjectInfoIdByPersonnel(final String code) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select pp.projectInfo.id from com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels pp where pp.proPerson.code='"+code+"'";
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
	}

}
