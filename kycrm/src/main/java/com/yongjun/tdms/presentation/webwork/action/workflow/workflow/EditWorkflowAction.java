package com.yongjun.tdms.presentation.webwork.action.workflow.workflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.model.workflow.Workflow;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.workflow.WorkflowManager;

public class EditWorkflowAction extends PrepareAction {
	private static final long serialVersionUID = 732668911930478662L;
	private WorkflowManager workflowManager;
	private FlowManager flowManager;
	private CodeValueManager codeValueManager;
	private Workflow workflow;

	public EditWorkflowAction(WorkflowManager workflowManager, FlowManager flowManager,
			CodeValueManager codeValueManager) {
		this.workflowManager = workflowManager;
		this.flowManager = flowManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (null == this.workflow) {
			if (hasId("workflow.id")) {
				this.workflow = this.workflowManager.loadWorkflow(getId("workflow.id"));
			} else {
				this.workflow = new Workflow();
			}
		} else {
			this.workflow = new Workflow();
		}
	}

	public String save() {
		boolean isNew = this.workflow.isNew();
		try {
			if (hasId("flow.id")) {
				this.workflow.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request.getParameter("flow.id"))));

				this.workflow.setDepartment(this.flowManager.loadFlow(
						Long.valueOf(this.request.getParameter("flow.id"))).getDepartment());
			}
			if (hasId("affectObject")) {
				this.workflow.setAffectObject(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
						.getParameter("affectObject"))));
			}

			String[] keyNames = new String[2];
			keyNames[0] = "department";
			keyNames[1] = "affectObject";
			Object[] keyValues = new Object[2];
			keyValues[0] = this.workflow.getFlow().getDepartment().getId();
			keyValues[1] = this.workflow.getAffectObject().getId();
			List workFlowList = this.workflowManager.loadByKeyArray(keyNames, keyValues);

			if (isNew) {
				if (null == workFlowList) {
					this.workflowManager.storeWorkflow(this.workflow);
				} else {
					if (this.workflow.getOpenOrNot() == 0) {
						int openOrNot = 0;
						for (int i = 0; i < workFlowList.size(); i++) {
							openOrNot += ((Workflow) workFlowList.get(i)).getOpenOrNot();
						}
						if (openOrNot == workFlowList.size()) {
							this.workflowManager.storeWorkflow(this.workflow);
						} else {
							addActionMessage(getText(

									"add.exist",
									Arrays.asList(new Object[] { this.workflow.getDepartment().getName(),
											this.workflow.getAffectObject().getName() })));

							return "error";
						}
					}
					if (this.workflow.getOpenOrNot() == 1) {
						this.workflowManager.storeWorkflow(this.workflow);
					}
				}
			} else {
				int openOrNot = 0;
				Workflow workFlowDBList = null;
				for (int i = 0; i < workFlowList.size(); i++) {
					openOrNot += ((Workflow) workFlowList.get(i)).getOpenOrNot();
					if (((Workflow) workFlowList.get(i)).getOpenOrNot() == 0) {
						workFlowDBList = (Workflow) workFlowList.get(i);
					}

				}

				if (this.workflow.getOpenOrNot() == 0) {
					if ((openOrNot == workFlowList.size())
							|| ((openOrNot == workFlowList.size() - 1) && (this.workflow.getId() == workFlowDBList
									.getId()))) {
						this.workflowManager.storeWorkflow(this.workflow);
					} else {
						addActionMessage(getText(
								"edit.exist",
								Arrays.asList(new Object[] { this.workflow.getDepartment().getName(),
										this.workflow.getAffectObject().getName() })));

						return "error";
					}
				}

				if (this.workflow.getOpenOrNot() == 1) {
					this.workflowManager.storeWorkflow(this.workflow);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.workflow.getCode() })));

			return "error";
		}

		if (isNew) {
			addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.workflow.getCode() })));

			return "new";
		}

		addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.workflow.getCode() })));

		return "success";
	}

	public List<Flow> getAllFlow() {
		return this.flowManager.loadAllFlows();
	}

	public List<CodeValue> getAllAffectObject() {
		try {
			List father = this.codeValueManager.loadByKey("code", "021");
			List allAffectObject = new ArrayList();
			if ((null != father) && (!father.isEmpty()))
				;
			return this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) father.get(0)).getId());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Workflow getWorkflow() {
		return this.workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
}
