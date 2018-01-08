package com.yongjun.tdms.presentation.webwork.action.activitiFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.activitiFlow.ActiviFlow;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.activitiFlow.RunTask;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
import com.yongjun.tdms.service.activitiFlow.RunPointManager;
import com.yongjun.tdms.service.activitiFlow.RunTaskManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

public class EditMyRunTaskAction extends PrepareAction {
	private static final long serialVersionUID = 732668911930478662L;
	private RunTaskManager runTaskManager;
	private RunPointManager runPointManager;
	private ActivitFlowManager activitFlowManager;
	private RunTask runTask;
	private String departmentName;
	private final CodeValueManager codeValueManager;
	private final  PersonnelFilesManager  personnelFilesManager;
	private String idStr;//批量审批的id
	private List<RunTask> runTaskList = null;
	private String batchFinished = "0";//审批完成：0未完成

	public EditMyRunTaskAction(RunTaskManager runTaskManager, RunPointManager runPointManager,
			ActivitFlowManager activitFlowManager, CodeValueManager codeValueManager,PersonnelFilesManager  personnelFilesManager) {
		this.runTaskManager = runTaskManager;
		this.runPointManager = runPointManager;
		this.codeValueManager = codeValueManager;
		this.activitFlowManager = activitFlowManager;
		this.personnelFilesManager=personnelFilesManager;
	}

	public void prepare() throws Exception {
		if (hasId("runTask.id")) {
			this.runTask = this.runTaskManager.loadRunTask(getId("runTask.id"));
			departmentName = runTask.getSubmitPer().getDept().getName();
		} else if(hasId("idStr")){
			this.idStr = request.getParameter("idStr");
			this.runTaskList = new ArrayList<RunTask>();
			String[] idArr = this.idStr.split("\'");
			for (String eachId : idArr) {
				RunTask runTask = runTaskManager.loadRunTask(Long.parseLong(eachId));
				this.runTaskList.add(runTask);
			}
		}else {
			this.runTask = new RunTask();
		}
	}

	public String save() {
		boolean isBatch = false;//是否是批量审批
		try {
			if(this.runTaskList != null){
				//批量审批
				List<ActiviFlow> list = new ArrayList<ActiviFlow>();
				for (RunTask eachRunTask : this.runTaskList) {
					ActiviFlow tempFlow = approvalRuntask(eachRunTask);
					list.add(tempFlow);
				}
				isBatch = true;
				ActiviFlow activiFlow1 = new ActiviFlow();
				activiFlow1.setActiviFlows(list);
				this.activitFlowManager.storeAtivitiList(activiFlow1);
			}
			if(this.runTask != null){
				//单个审批
				ActiviFlow activiFlow2 = approvalRuntask(this.runTask);
				this.activitFlowManager.storeAtiviti(activiFlow2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("runTask.add.error", Arrays.asList(new Object[] { this.runTask.getName() })));
			if(!isBatch){
				return "error";
			}else{
				return "batchError";
			}
		}

		addActionMessage(getText("runTask.edit.success"));
		if(!isBatch){
			return "success";
		}else{
			this.batchFinished = "1";
			return "batchSuccess";
		}
	}
	/**
	 * 审批
	 * @throws DaoException
	 */
	private ActiviFlow approvalRuntask(RunTask eachRunTask) throws DaoException {
		ActiviFlow activiFlow = new ActiviFlow();
		// 当前审批运行节点id
		activiFlow.setRunTaskId(eachRunTask.getId());
		// 根据审批业务id 和审批人code 获取审批预节点
		String[] key = { "bussnessId", "inspectPser.code" ,"myNum"};
		String[] value = { eachRunTask.getBussnessId() + "", eachRunTask.getAssignee().getCode(),eachRunTask.getMyNum()+""};
		List<RunPoint> runPoints = this.runPointManager.loadByKeyArray(key, value);
		if (runPoints != null && runPoints.size() > 0) {
			activiFlow.setRunPointId(runPoints.get(0).getId());
		}
		PersonnelFiles transfer =null;
		if(hasId("transferId")){
			transfer =this.personnelFilesManager.loadPersonnel(getId("transferId"));
		}
		CodeValue codeValue = this.codeValueManager.loadCodeValue(getId("result.id"));
		activiFlow.setResult(codeValue);
		activiFlow.setRemark(request.getParameter("remark"));
		activiFlow.setTransfer(transfer);
		return activiFlow;
	}

	public List<CodeValue> getAllResultTypes() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "214");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					for (int i = 0; i < list.size(); i++) {
						CodeValue codeValue = (CodeValue) list.get(i);
						if (!codeValue.getName().equals("新建") && !codeValue.getName().equals("审核中") && !codeValue.getName().equals("重新提交")) {
							codes.add(codeValue);
						}
					}
				}

			}

			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public RunTask getRunTask() {
		return this.runTask;
	}

	public void setRunTask(RunTask runTask) {
		this.runTask = runTask;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public String getBatchFinished() {
		return batchFinished;
	}

	public void setBatchFinished(String batchFinished) {
		this.batchFinished = batchFinished;
	}
	
}
