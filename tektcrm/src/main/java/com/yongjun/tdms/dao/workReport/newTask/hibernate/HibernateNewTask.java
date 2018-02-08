package com.yongjun.tdms.dao.workReport.newTask.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.workReport.newTask.NewTaskDao;
import com.yongjun.tdms.model.workReport.newTask.NewTask;

public class HibernateNewTask extends BaseHibernateDao implements NewTaskDao{
	public void storeNewTask(NewTask newTask) {
		super.store(newTask);
	}

	public void deleteNewTask(NewTask newTask) {
		super.delete(newTask);
	}

	public void deleteAllNewTask(Collection<NewTask> newTasks) {
		super.deleteAll(newTasks);
	}

	public List<NewTask> loadAllNewTask(Long[] newTaskIds) {
		return super.loadAll(NewTask.class, newTaskIds);
	}

	public NewTask loadNewTask(Long newTaskId) {
		return (NewTask) super.load(NewTask.class, newTaskId);
	}

	public List<NewTask> loadAllNewTask() {
		return super.loadAll(NewTask.class);
	}

	public List<NewTask> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(NewTask.class, keyName, keyValue);
	}

	public List<NewTask> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(NewTask.class, keyNames, keyValues);
	}
}
