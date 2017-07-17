package com.yongjun.tdms.presentation.webwork.action.customercontract.plan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;

public class EditReturnPlanAction extends PrepareAction {
	private static final long serialVersionUID = 612315316215110285L;
	private final ReturnPlanManager returnPlanManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactArchivesManager contactArchivesManager;
	private final ContractManagementManager contractManagementManager;
	private final ProjectInfoManager projectInfoManager;
	private ReturnPlan returnPlan;
	private ProjectInfo projectInfo;
	private ContractManagement contractManagement;
	private String popWindowFlag;

	public EditReturnPlanAction(ReturnPlanManager returnPlanManager, CodeValueManager codeValueManager,
			PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager,
			ContactArchivesManager contactArchivesManager, ContractManagementManager contractManagementManager,
			ProjectInfoManager projectInfoManager) {
		this.returnPlanManager = returnPlanManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contactArchivesManager = contactArchivesManager;
		this.contractManagementManager = contractManagementManager;
		this.projectInfoManager = projectInfoManager;
	}

	public void prepare() throws Exception {
		if (hasId("returnPlan.id")) {
			this.returnPlan = this.returnPlanManager.loadReturnPlan(getId("returnPlan.id"));
		} else {
			this.returnPlan = new ReturnPlan();
		}
		if (this.request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = this.request.getParameter("popWindowFlag");
		}

		if (hasId("contractManagement.id")) {
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			if (null != this.contractManagement) {
				this.returnPlan.setContractManagement(contractManagement);
				this.returnPlan.setCustomerInfo(this.contractManagement.getCustomerInfo());
				this.returnPlan.setContactArchives(this.contractManagement.getLinkman());
				this.returnPlan.setPhone(this.contractManagement.getTelephone());
			}
		}
	}

	public String save() throws Exception {
		boolean isNew = this.returnPlan.isNew();

		if (hasId("payment.id")) {
			this.returnPlan.setPayment(this.codeValueManager.loadCodeValue(getId("payment.id")));
		}

		if (hasId("batch.id")) {
			this.returnPlan.setBatch(this.codeValueManager.loadCodeValue(getId("batch.id")));
		}

		if (hasId("customer.id")) {
			CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(getId("customer.id"));

			if (null != customer) {
				this.returnPlan.setCustomerInfo(customer);
			}
		}

		if (hasId("payee.id")) {
			PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(getId("payee.id"));

			if (null != payee) {
				this.returnPlan.setPayee(payee);
			}

		}

		if (hasId("contactArchives.id")) {
			ContactArchives linkman = this.contactArchivesManager.loadContactArchives(getId("contactArchives.id"));

			if (null != linkman) {
				this.returnPlan.setContactArchives(linkman);
			}
		}

		this.returnPlan.setIsOrNot("1");

		if (hasId("notOrIs")) {
			String notOrIs = this.request.getParameter("notOrIs");
			this.returnPlan.setNotOrIs(notOrIs);
		}

		if (hasId("billingOrNot")) {
			String billingOrNot = this.request.getParameter("billingOrNot");
			this.returnPlan.setBillingOrNot(billingOrNot);
		}
		if (StringUtils.isEmpty(this.request.getParameter("returnPlan.remark"))) {
			String remark = this.request.getParameter("returnPlan.remark");
			this.returnPlan.setRemark(remark);
		}
		this.returnPlanManager.storeReturnPlan(this.returnPlan);
		// 回款计划完成后，项目状态改为付费
		this.projectInfo = this.returnPlan.getContractManagement().getProject();
		this.projectInfo.setState(this.codeValueManager.loadCodeValue(466L));
		this.projectInfoManager.storeProjectInfo(this.projectInfo);
		List<ReturnPlan> lists = this.returnPlanManager.loadAllReturnPlans();
		Double sum = Double.valueOf(0.0D);
		if (null != lists) {
			for (ReturnPlan p : lists) {
				sum = Double.valueOf(sum.doubleValue() + p.getSum().doubleValue());
			}
		}
		if (!hasId("contractManagement.id")) {
			this.contractManagement = this.returnPlan.getContractManagement();
		}
		if ((null != contractManagement) && (sum.doubleValue() == contractManagement.getContractMoney())) {
			contractManagement.setOverReturnPlan("yes");
			this.contractManagementManager.storeContractManagement(contractManagement);
		}

		if (isNew) {
			addActionMessage(getText("returnPlan.save.success"));
			return "new";
		}
		addActionMessage(getText("returnPlan.edit.success"));
		return "success";
	}

	public List<CodeValue> getAllBatchs() {
		List<CodeValue> codes = null;
		try {
			codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("03330"));

			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public List<CodeValue> getAllPayments() {
		List<CodeValue> codes = null;
		try {
			codes = new ArrayList<CodeValue>();
			List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("046"));

			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public void setReturnPlan(ReturnPlan returnPlan) {
		this.returnPlan = returnPlan;
	}

	public ReturnPlan getReturnPlan() {
		return this.returnPlan;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
}
