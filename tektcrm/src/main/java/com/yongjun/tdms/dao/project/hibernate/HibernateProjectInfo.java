package com.yongjun.tdms.dao.project.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.ProjectInfoDao;
import com.yongjun.tdms.model.project.ProjectInfo;

public class HibernateProjectInfo extends BaseHibernateDao implements ProjectInfoDao {
	public void deleteAllProjectInfo(Collection<ProjectInfo> ProjectInfos) {
		deleteAll(ProjectInfos);
	}

	public void deleteProjectInfo(ProjectInfo ProjectInfo) {
		delete(ProjectInfo);
	}

	public List<ProjectInfo> loadAllProjectInfo(Long[] ProjectInfoIds) {
		return loadAll(ProjectInfo.class, ProjectInfoIds);
	}

	public List<ProjectInfo> loadAllProjectInfos() {
		return loadAll(ProjectInfo.class);
	}

	public ProjectInfo loadProjectInfo(Long ProjectInfoId) {
		return (ProjectInfo) load(ProjectInfo.class, ProjectInfoId);
	}

	public List<ProjectInfo> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ProjectInfo.class, keyName, keyValue);
	}

	public List<ProjectInfo> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ProjectInfo.class, keyNames, keyValues);
	}

	public void storeProjectInfo(ProjectInfo ProjectInfo) {
		store(ProjectInfo);
	}

	public List<ProjectInfo> loadByDate(String date, String name) {
		String hql = "from ProjectInfo p where convert(varchar,p.createdTime,120) like '" + date
				+ "%' and p.creator = '" + name + "'";
		return getSession().createQuery(hql).list();
	}

	public int loadProjectInfoCountByName(String name) {
		// TODO Auto-generated method stub
		String hql="select count(*) from ProjectInfo p where p.name ="+name;
		return (Integer) getSession().createQuery(hql).uniqueResult();
	}

}
