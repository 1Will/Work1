package com.yongjun.tdms.service.project.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.project.ProjectInfoDao;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultProjectInfoManager extends BaseManager implements ProjectInfoManager {
	private ProjectInfoDao projectInfoDao;
	private final SequenceManager ciSequenceManager;
	private final UserManager userManager;
	private final YongJunSequenceManager yongJunSequenceManager;
	public DefaultProjectInfoManager(ProjectInfoDao projectInfoDao, SequenceManager ciSequenceManager,
			UserManager userManager,YongJunSequenceManager yongJunSequenceManager) {
		this.projectInfoDao = projectInfoDao;
		this.ciSequenceManager = ciSequenceManager;
		this.userManager = userManager;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void deleteAllProjectInfo(Collection<ProjectInfo> ProjectInfos) {
		this.projectInfoDao.deleteAllProjectInfo(ProjectInfos);
	}

	public void deleteProjectInfo(ProjectInfo ProjectInfo) {
		this.projectInfoDao.deleteProjectInfo(ProjectInfo);
	}

	public List<ProjectInfo> loadAllProjectInfo(Long[] ProjectInfoIds) {
		return this.projectInfoDao.loadAllProjectInfo(ProjectInfoIds);
	}

	public List<ProjectInfo> loadAllProjectInfos() {
		return this.projectInfoDao.loadAllProjectInfos();
	}

	public ProjectInfo loadProjectInfo(Long ProjectInfoId) {
		return this.projectInfoDao.loadProjectInfo(ProjectInfoId);
	}

	public void storeProjectInfo(ProjectInfo ProjectInfo) {
		if (ProjectInfo.isNew()) {
			ProjectInfo.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_PROJECT));
		}
		this.projectInfoDao.storeProjectInfo(ProjectInfo);
	}

	public void setprojectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}

	public void disabledProjectInfos(List<ProjectInfo> ProjectInfos) {
		for (ProjectInfo bv : ProjectInfos) {
			bv.setDisabled(true);
			this.projectInfoDao.storeProjectInfo(bv);
		}
	}

	public void enableProjectInfos(List<ProjectInfo> ProjectInfos) {
		for (ProjectInfo bv : ProjectInfos) {
			bv.setDisabled(false);
			this.projectInfoDao.storeProjectInfo(bv);
		}
	}

	public List<ProjectInfo> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.projectInfoDao.loadByKey(keyName, keyValue);
	}

	public List<ProjectInfo> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.projectInfoDao.loadByKeyArray(keyNames, keyValues);
	}

	public ProjectInfoDao getProjectInfoDao() {
		return projectInfoDao;
	}

	public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}

	// public List<ProjectInfo> loadProjectByDate(String date){
	public int loadProjectByDate(String userId, String date) {
		User user = userManager.loadUser(Long.parseLong(userId));
		List<ProjectInfo> plist = this.projectInfoDao.loadByDate(date, user.getName());
		int size = plist.size();
		return size;
	}

}
