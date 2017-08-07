package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expenseForm;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.expensemanagement.expenseForm.ExpenseFormManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

public class EditExpenseFormAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final ExpenseFormManager expenseFormManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final ContractManagementManager contractManagementManager;
	private ExpenseForm expenseForm;
	private ProjectInfo projectInfo;
	private PersonnelFiles applyPeople;
	private ContractManagement contractManagement;

	public EditExpenseFormAction(ExpenseFormManager expenseFormManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager, DepartmentManager departmentManager,
			CodeValueManager codeValueManager,ContractManagementManager contractManagementManager) {
		this.expenseFormManager = expenseFormManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.contractManagementManager = contractManagementManager;
	}

	public void prepare() throws Exception {
		if (hasId("expenseForm.id")) {
			this.expenseForm = expenseFormManager.loadExpenseForm(getId("expenseForm.id"));
		} else {
			this.expenseForm = new ExpenseForm();
		}
		if (hasId("projectInfo.id")) {
			this.projectInfo = projectInfoManager.loadProjectInfo(getId("projectInfo.id"));
		}
		if (hasId("applyPeople.id")) {
			this.applyPeople = personnelFilesManager.loadPersonnel(getId("applyPeople.id"));
		}
		if(hasId("contractManagement.id")){
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			this.expenseForm.setContractManagement(this.contractManagement);
		}
	}

	public String save() {
		boolean isNew = this.expenseForm.isNew();
		this.expenseForm.setProjectInfo(this.projectInfo);
		this.expenseForm.setApplyPeople(this.applyPeople);
		try {
			this.expenseFormManager.storeExpenseForm(this.expenseForm);

			if (isNew) {
				addActionMessage(getText("expenseForm.add.success"));
				return NEW;
			}
			addActionMessage(getText("expenseForm.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			if (isNew) {
				addActionMessage(getText("expenseForm.add.error"));
				return ERROR;
			}
			addActionMessage(getText("expenseForm.edit.error"));
			return ERROR;
		}
	}

	public ExpenseForm getExpenseForm() {
		return expenseForm;
	}

	public void setExpenseForm(ExpenseForm expenseForm) {
		this.expenseForm = expenseForm;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public PersonnelFiles getApplyPeople() {
		return applyPeople;
	}

	public void setApplyPeople(PersonnelFiles applyPeople) {
		this.applyPeople = applyPeople;
	}
}
