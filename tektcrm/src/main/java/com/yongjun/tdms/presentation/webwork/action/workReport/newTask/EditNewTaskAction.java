package com.yongjun.tdms.presentation.webwork.action.workReport.newTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workReport.newTask.NewTask;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.newTask.NewTaskManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditNewTaskAction extends PrepareAction {
	private static final long serialVersionUID = 3022668162752790885L;
	private final NewTaskManager newTaskManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private final GroupManager groupManager;
	private final DepartmentManager departmentManager;
	private Date startTime;
	private Date endTime;
	private NewTask newTask;
	private String json;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm");

	public EditNewTaskAction(NewTaskManager newTaskManager,CodeValueManager codeValueManager,
			PersonnelFilesManager personnelFilesManager,UserManager userManager,GroupManager groupManager,
			DepartmentManager departmentManager){
		this.newTaskManager = newTaskManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.groupManager = groupManager;
		this.departmentManager = departmentManager;
}

	public void prepare() throws Exception {
		if (hasId("newTask.id")) {
			this.newTask = newTaskManager.loadNewTask(getId("newTask.id"));
		}else {
			this.newTask = new NewTask();
			json ="{\"userIds\":\"\",\"groupIds\":\"\",\"deptIds\":\"\"}";
		}
		
		if (hasId("doString")) {
			String doString = request.getParameter("doString");
			this.newTask.setDoString(doString);
			this.newTask.setUserDo(doJson(doString));
		}
		
		if (hasId("copyString")) {
			String copyString = request.getParameter("copyString");
			this.newTask.setCopyString(copyString);
			this.newTask.setUserCopy(doJson(copyString));
		}
		
		if (hasId("startHour") && hasId("startMinute")) {
			String start = request.getParameter("newTask.startTime");
			String startHour = request.getParameter("startHour");
			String startMinute = request.getParameter("startMinute");
			this.startTime = format.parse(start+" "+startHour+":"+startMinute);
		}
		if (hasId("endHour") && hasId("endMinute")) {
			String end = request.getParameter("newTask.endTime");
			String endHour = request.getParameter("endHour");
			String endMinute = request.getParameter("endMinute");
			this.endTime = format.parse(end+" "+endHour+":"+endMinute);
		}
		if (hasId("time")) {
			String[] time =request.getParameterValues("time");
			this.newTask.setTime(Arrays.toString(time).replaceAll("\\[|\\]", "").replaceAll(" ", ""));
		}
	}

	public String save() {
		boolean isNew = this.newTask.isNew();
		try{
			if (hasId("complete")) {
				this.newTask.setState(this.codeValueManager.loadByKey("code", "30603").get(0));
				this.newTaskManager.storeNewTask(this.newTask);
				return SUCCESS;
			}
			if (hasId("state.id")) {
				this.newTask.setState(this.codeValueManager.loadCodeValue(getId("state.id")));
			}
			if (hasId("startHour") && hasId("startMinute")) {
				this.newTask.setStartTime(startTime);
			}
			if (hasId("endHour") && hasId("endMinute")) {
				this.newTask.setEndTime(endTime);
			}
			this.newTask.setIsSaved(this.request.getParameter("isSaved"));
			this.newTaskManager.storeNewTask(this.newTask);
			String submit = null;
			if ("1".equals(this.request.getParameter("isSaved"))) {
				//等待填充
				submit = "submit";
			}
			if (isNew) {
				addActionMessage(getText("newTask.add.success"));
				return NEW;
			}else {
				if (submit != null) {
					addActionMessage(getText("newTask.submit.success"));
				}else {
					addActionMessage(getText("newTask.edit.success"));
				}
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("newTask.add.error"));
		}
		return ERROR;
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
					return states;
				}
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public String doJson(String str) throws NumberFormatException, DaoException{
		Map<String, Object> map = new HashMap<String, Object>();
		Set<String> users = new HashSet<String>();
		if (str != null && !str.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(str);
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		String userIds= (String) map.get("userIds");//id串
		String groupIds= (String) map.get("groupIds");//id串
		String deptIds= (String) map.get("deptIds");//id串
		if (userIds != null && userIds.length()>0) {
			String user[] = userIds.split(",");
			if (user != null && user.length>0) {
				for (int i = 0; i < user.length; i++) {
					users.add(user[i]);
				}
			}
		}
		
		if (groupIds != null && groupIds.length()>0) {
			String[] groupId = groupIds.split(",");
			if (groupId != null && groupId.length>0) {
				for (int i = 0; i < groupId.length; i++) {
					Set<User> uSet = this.groupManager.loadGroup(Long.parseLong(groupId[i])).getUsers();
					if (uSet != null && !uSet.isEmpty()) {
						for (Iterator it = uSet.iterator(); it.hasNext();) {
							User user = (User) it.next();
							users.add(user.getId()+"");
						}
					}
				}
			}
		}
		
		if (deptIds != null && deptIds.length()>0) {
			String deptId[] = deptIds.split(",");
			if (deptId != null && deptId.length>0) {
				for (int i = 0; i < deptId.length; i++) {
					List<User> uList = userManager.loadByKey("department", Long.parseLong(deptId[i]));
					if (uList != null && uList.size()>0) {
						for (int j = 0;j<uList.size();j++) {
							users.add(uList.get(j).getId()+"");
						}
					}
				}
			}
		}
		return users.toString().replaceAll("\\[|\\]", "").replaceAll(" ", "");
	}
	public NewTask getNewTask() {
		return newTask;
	}

	public void setNewTask(NewTask newTask) {
		this.newTask = newTask;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
