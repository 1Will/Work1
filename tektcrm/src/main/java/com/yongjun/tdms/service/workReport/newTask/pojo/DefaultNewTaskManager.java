package com.yongjun.tdms.service.workReport.newTask.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.newTask.NewTaskDao;
import com.yongjun.tdms.model.workReport.newTask.NewTask;
import com.yongjun.tdms.service.workReport.newTask.NewTaskManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultNewTaskManager implements NewTaskManager{
	private final NewTaskDao newTaskDao;
	private final YongJunSequenceManager yongJunSequenceManager;

	public DefaultNewTaskManager(NewTaskDao newTaskDao,YongJunSequenceManager yongJunSequenceManager) {
		super();
		this.newTaskDao = newTaskDao;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void storeNewTask(NewTask newTask) {
		if (newTask.isNew()) {
			String toCode="RC";
			String code =(String)this.yongJunSequenceManager.generateeCodeTypeReplacFormtter(YongJunSequenceConstant.CODE_PROJECT,toCode);
			newTask.setCode(code);
		}
		this.newTaskDao.storeNewTask(newTask);
	}

	public void deleteNewTask(NewTask newTask) {
		this.newTaskDao.deleteNewTask(newTask);
	}

	public void deleteAllNewTask(Collection<NewTask> newTask) {
		this.newTaskDao.deleteAllNewTask(newTask);
	}

	public List<NewTask> loadAllNewTask(Long[] newTaskIds) {
		return this.newTaskDao.loadAllNewTask(newTaskIds);
	}

	public NewTask loadNewTask(Long newTaskId) {
		return this.newTaskDao.loadNewTask(newTaskId);
	}

	public List<NewTask> loadAllNewTask() {
		return this.newTaskDao.loadAllNewTask();
	}

	public List<NewTask> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.newTaskDao.loadByKey(keyName, keyValue);
	}

	public List<NewTask> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.newTaskDao.loadByKeyArray(keyNames, keyValues);
	}
}
