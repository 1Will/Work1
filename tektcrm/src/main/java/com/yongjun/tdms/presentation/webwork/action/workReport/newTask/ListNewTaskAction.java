package com.yongjun.tdms.presentation.webwork.action.workReport.newTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workReport.newTask.NewTask;
import com.yongjun.tdms.service.workReport.newTask.NewTaskManager;

public class ListNewTaskAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final NewTaskManager newTaskManager;
	private final CodeValueManager codeValueManager;
	private NewTask newTask;
	private List<NewTask> newTasks;

	public ListNewTaskAction(NewTaskManager newTaskManager,CodeValueManager codeValueManager){
		this.newTaskManager = newTaskManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (hasId("newTask.id")) {
			this.newTask = newTaskManager.loadNewTask(getId("newTask.id"));
		}
		
		if (hasIds("newTaskIds")) {
			this.newTasks = this.newTaskManager.loadAllNewTask(getIds("newTaskIds"));
		}
	}

	protected String getAdapterName() {
		return "newTaskHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	private String delete() {
		try {
			this.newTaskManager.deleteAllNewTask(this.newTasks);
			addActionMessage(getText("newTask.delete.success"));
		} catch (Exception e) {
			addActionError(getText("newTask.delete.error"));
		}
		return "success";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public List<CodeValue> getAllState() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { String.valueOf("306"), Boolean.valueOf(false) };
			List stateList = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
			if (stateList != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) stateList.get(0)).getId(), Boolean.valueOf(false) };
				List states = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
				if (states != null){
					CodeValue n = new CodeValue();
					states.add(0, n);
					return states;
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public NewTask getNewTask() {
		return newTask;
	}

	public List<NewTask> getNewTasks() {
		return newTasks;
	}

}
