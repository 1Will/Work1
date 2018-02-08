package com.yongjun.tdms.presentation.webwork.action.activitiFlow;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Code;

import com.opensymphony.webwork.views.freemarker.ValueStackModel;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.activitiFlow.RunPointManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;

public class EditRunPointAction extends PrepareAction {
	private static final long serialVersionUID = 732668911930478662L;
	private RunPointManager runPointManager;
	private FlowManager flowManager;
	private PersonnelFilesManager personnelFilesManager;
	private CodeValueManager codeValueManager;
	
	private RunPoint runPoint;
	private Long flowId;
	private String historyTask;

	public EditRunPointAction(RunPointManager runPointManager,FlowManager flowManager,PersonnelFilesManager personnelFilesManager
			,CodeValueManager codeValueManager) {
		this.runPointManager = runPointManager;
		this.flowManager = flowManager;
		this.personnelFilesManager = personnelFilesManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (null == this.runPoint) {
			if (hasId("runPoint.id")) {
				this.runPoint = this.runPointManager.loadRunPoint(getId("runPoint.id"));
				this.flowId = this.runPoint.getFlow().getId();
			} else {
				if ((null != this.request.getParameter("flow.id")) && ("" != this.request.getParameter("flow.id"))) {
					this.flowId = Long.valueOf(this.request.getParameter("flow.id"));
				}
				this.runPoint = new RunPoint();
			}
		} else {
			this.flowId = Long.valueOf(this.request.getParameter("flow.id"));
			this.runPoint = new RunPoint();
		}
	}

	public String save() {
		boolean isNew = this.runPoint.isNew();
		try {
			if (hasId("runPoint.inspectPser.id")) {
				 PersonnelFiles personnelFiles=personnelFilesManager.loadPersonnel(Long.valueOf(this.request.getParameter("runPoint.inspectPser.id")));
				this.runPoint.setInspectPser(personnelFiles);
				//操作名称
				String runPoint_name=request.getParameter("runPoint.name");
				if(runPoint_name==null || "".equals(runPoint_name)){
					this.runPoint.setName("审批-"+personnelFiles.getDept().getName()+"-"+personnelFiles.getName());
				}
			}

			if (hasId("flow.id")) {
				this.runPoint.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request.getParameter("flow.id"))));
			}
			
			
			if (isNew) {
				//设置runpPoint序号
				String []keys={"bussnessId","flow.id"};
				Object []values={this.runPoint.getBussnessId(),this.runPoint.getFlow().getId()};
				List<RunPoint> runPoints = runPointManager.loadByKeyArray(keys, values                                                                                                            );
				if(runPoints != null){
					this.runPoint.setMyNum(runPoints.size()+1);
				}else {
					this.runPoint.setMyNum(1);
				}
				//设置默认状态
				List<CodeValue> codeValues = codeValueManager.loadByKey("code", "21401");
				if(codeValues != null){
					this.runPoint.setResult(codeValues.get(0));
				}
				this.runPointManager.storeRunPoint(this.runPoint);
			} else {
				this.runPointManager.storeRunPoint(this.runPoint);
			}

		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("runPoint.add.error", Arrays.asList(new Object[] { this.runPoint.getName() })));

			return "error";
		}

		if (isNew) {
			addActionMessage(getText("runPoint.add.success", Arrays.asList(new Object[] { this.runPoint.getName() })));

			return "new";
		}

		addActionMessage(getText("runPoint.edit.success", Arrays.asList(new Object[] { this.runPoint.getName() })));

		return "success";
	}

	/*public List<Point> getAllPreviousPoint() {
		List pointList = this.pointManager.loadAllPoints();
		return pointList;
	}

	public List<Point> getAllRearPoint() {
		List pointList = this.pointManager.loadAllPoints();
		return pointList;
	}*/

	public RunPoint getRunPoint() {
		return this.runPoint;
	}

	public void setRunPoint(RunPoint runPoint) {
		this.runPoint = runPoint;
	}

	public Long getFlowId() {
		return this.flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public String getHistoryTask() {
		return historyTask;
	}

	public void setHistoryTask(String historyTask) {
		this.historyTask = historyTask;
	}
	
}
