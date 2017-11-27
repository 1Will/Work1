package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoProduct.ProjectInfoProductManager;

public class ListProjectInfoProductAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private CodeValueManager codeValueManager;
	private ProjectInfoManager projectInfoManager;
	private List<ProjectInfoProduct> ProjectInfoProduct;
	private final UserManager userManager;
	private final ProjectInfoProductManager projectInfoProductManager;
	private String projectInfoId;
	private String productIds;
	private String saveFlag;

	public ListProjectInfoProductAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			UserManager userManager, ProjectInfoProductManager ProjectInfoProductManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.userManager = userManager;
		this.projectInfoProductManager = ProjectInfoProductManager;

	}

	public CodeValueManager getCodeValueManager() {
		return this.codeValueManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	protected String getAdapterName() {
		return "projectInfoProduct";
	}

	protected Map getRequestParameterMap() {
		// Map map= new HashMap();//super.getRequestParameterMap();
		// if (hasId("projectInfo.id")) {
		// map.put("projectInfo.id",
		// this.request.getParameter("projectInfo.id"));
		// }
		// map.put("onlyValid", true);
		Map map = super.getRequestParameterMap();
		if (hasId("projectInfo.id")) {
			map.put("projectInfoId", getId("projectInfo.id"));
		}

		return map;
	}

	public void prepare() throws Exception {
		if (hasId("projectInfo.id")) {
			this.projectInfoId = this.request.getParameter("projectInfo.id");
		}
		if (hasId("productIds")) {
			this.productIds = this.request.getParameter("productIds");
		}
		if ((this.ProjectInfoProduct == null) && (hasIds("projectInfoProductIds"))) {
			this.ProjectInfoProduct = this.projectInfoProductManager
					.loadAllProjectInfoProduct(getIds("projectInfoProductIds"));
		}
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			return disabled();
		}

		if (isEnable()) {
			return enable();
		}

		if (isDelete()) {
			return delete();
		}
		if (saveFlag != null && saveFlag.equals("saveFlag")) {
			return saveProduct();
		}
		return "success";
	}

	private String delete() {
		try {
			this.projectInfoProductManager.deleteAllProjectInfoProduct(this.ProjectInfoProduct);
			addActionMessage(getText("projectInfos.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String saveProduct() {
		try {
			ProjectInfo projectInfo = this.projectInfoManager.loadProjectInfo(Long.parseLong(projectInfoId));
			if (projectInfo != null && productIds != null) {
				String[] arrid = productIds.split(",");
				this.projectInfoProductManager.storeProjectInfoProducts(projectInfo, arrid);
			}

			addActionMessage(getText("projectInfos.delete.success"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String enable() {
		try {
			this.projectInfoProductManager.enableProjectInfoProducts(this.ProjectInfoProduct);
			addActionMessage(getText("projectInfos.enable.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.enable.failer"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.projectInfoProductManager.disabledProjectInfoProducts(this.ProjectInfoProduct);
			addActionMessage(getText("backVisits.disabled.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.disabled.failer"));
		}
		return "error";
	}

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("201"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}

			}

			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public ProjectInfoManager getProjectInfoManager() {
		return projectInfoManager;
	}

	public void setProjectInfoManager(ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	public String getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(String projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

	public List<ProjectInfoProduct> getProjectInfoProduct() {
		return ProjectInfoProduct;
	}

	public void setProjectInfoProduct(List<ProjectInfoProduct> ProjectInfoProduct) {
		this.ProjectInfoProduct = ProjectInfoProduct;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public void setCodeValueManager(CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}

}
