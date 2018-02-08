package com.yongjun.tdms.service.workspace.warnning.pojo;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDao;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

public class DefaultWorkWarnningManager implements WorkWarnningManager {
	private final Log logger = LogFactory.getLog(getClass());
	private final WorkWarnningDao workWarnningDao;

	public DefaultWorkWarnningManager(WorkWarnningDao workWarnningDao) {
		this.workWarnningDao = workWarnningDao;
	}

	public WorkWarnning loadWorkWarnning(Long workWarnningId) {
		return this.workWarnningDao.loadWorkWarnning(workWarnningId);
	}

	public List<WorkWarnning> loadAllWorkWarnnings(Long[] workWarnningIds) {
		return this.workWarnningDao.loadAllWorkWarnnings(workWarnningIds);
	}

	public List<WorkWarnning> loadAllWorkWarnnings() {
		return this.workWarnningDao.loadAllWorkWarnnings();
	}

	public void storeWorkWarnning(WorkWarnning workWarnning) {
		this.workWarnningDao.storeWorkWarnning(workWarnning);
	}

	public void deleteWorkWarnning(WorkWarnning workWarnning) {
		this.workWarnningDao.deleteWorkWarnning(workWarnning);
	}

	public void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		this.workWarnningDao.deleteAllWorkWarnnings(workWarnnings);
	}

	public Long GetNumberOfUnReadWarnningByUserID(Long userId) {
		return this.workWarnningDao.GetNumberOfUnReadWarnningByUserID(userId);
	}

	public void readAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		for (WorkWarnning warnning : workWarnnings) {
			warnning.setReadFlag(true);
			this.workWarnningDao.storeWorkWarnning(warnning);
		}
	}

	public void unReadAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		for (WorkWarnning warnning : workWarnnings) {
			warnning.setReadFlag(false);
			this.workWarnningDao.storeWorkWarnning(warnning);
		}
	}

	public void sendWarnningMessage(List<User> warnningPeoples, String warnningType, String warnningContent) {
		if (null != warnningPeoples) {
			for (User warnningPeople : warnningPeoples)
				if ((null != warnningType) && (null != warnningContent)) {
					this.logger.info("start send warnning message for " + warnningPeople.getName());
					WorkWarnning warnning = new WorkWarnning();
					warnning.setType(warnningType);
					warnning.setWarnedPeople(warnningPeople);
					warnning.setContent(warnningContent);
					warnning.setWarnningDate(DateUtil.getSystemDate());
					try {
						this.workWarnningDao.storeWorkWarnning(warnning);
					} catch (Exception e) {
						this.logger
								.info("end send warnning message for " + warnningPeople.getName() + " send failure!");
					}
					this.logger.info("end send warnning message for " + warnningPeople.getName() + " send successful!");
				}
		}
	}

	public List<WorkWarnning> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.workWarnningDao.loadByKeyArray(keyNames, keyValues);
	}

	public List<WorkWarnning> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.workWarnningDao.loadByKey(keyName, keyValue);
	}
	
	public List<WorkWarnning> loadWarByUser(Long id){
		return this.workWarnningDao.loadWarByUser(id);
	}
}
