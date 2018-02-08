package com.yongjun.tdms.presentation.webwork.action.financialmanagement;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;

public class ListFinancialManagementAction extends ValueListAction {
	private static final long serialVersionUID = -1498451112049602252L;
	private final FinancialManagementManager financialManagementManager;
	private List<FinancialManagement> financialManagements;

	public ListFinancialManagementAction(FinancialManagementManager financialManagementManager) {
		this.financialManagementManager = financialManagementManager;
	}

	public void prepare() throws Exception {
		if (hasIds("financialManagementIds"))
			this.financialManagements = this.financialManagementManager
					.loadAllFinancialManagement(getIds("financialManagementIds"));
	}

	protected String getAdapterName() {
		return "financialManagements";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();

		

		if (hasId("month")) {
			String month = request.getParameter("month") + "%";
			map.put("month", month);
			map.put("submitNum", "return");
			if (request.getParameter("personnelFiles.id") != null
					&& !" ".equals(request.getParameter("personnelFiles.id"))) {

				String personnelFilesId = request.getParameter("personnelFiles.id");
				map.put("personnelFiles.id", personnelFilesId);
				map.remove("user.id");
			}else {
				map.put("personnelFiles.code", this.userManager.getUser().getCode());
				map.remove("user.id");
			}
		}

		return map;

	}

	public String execute() throws Exception {
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}
		if (isDelete()) {
			delete();
		}
		return "success";
	}

	public String delete() {
		try {
			this.financialManagementManager.deleteAllFinancialManagement(this.financialManagements);
			addActionMessage(getText("financialManagement.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("financialManagement.delete.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.financialManagementManager.disabledAllFinancialManagements(this.financialManagements);
			addActionMessage(getText("financialManagement.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("financialManagement.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.financialManagementManager.enabledAllFinancialManagements(this.financialManagements);

			addActionMessage(getText("returnPlan.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("financialManagement.enabled.error"));
		}
		return "error";
	}

	public List<FinancialManagement> getFinancialManagements() {
		return this.financialManagements;
	}

	public void setFinancialManagements(List<FinancialManagement> financialManagements) {
		this.financialManagements = financialManagements;
	}
}
