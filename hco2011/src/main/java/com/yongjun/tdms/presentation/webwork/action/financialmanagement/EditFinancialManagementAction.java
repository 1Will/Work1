package com.yongjun.tdms.presentation.webwork.action.financialmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditFinancialManagementAction extends PrepareAction {
	private static final long serialVersionUID = -8934817289873115007L;
	private final FinancialManagementManager financialManagementManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContractManagementManager contractManagementManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final ReturnPlanManager returnPlanManager;
	private final EventTypeManager eventTypeManager;
	private final EventNewManager eventNewManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;

	private FinancialManagement financialManagement;
	private List<CodeValue> batchs = new ArrayList();
	private String popWindowFlag;

	public EditFinancialManagementAction(FinancialManagementManager financialManagementManager,
			CodeValueManager codeValueManager, PersonnelFilesManager personnelFilesManager,
			CustomerInfoManager customerInfoManager, ContractManagementManager contractManagementManager,
			DepartmentManager departmentManager, UserManager userManager, ReturnPlanManager returnPlanManager,
			EventTypeManager eventTypeManager, EventNewManager eventNewManager,
			PersonnelFilesToUserManager personnelFilesToUserManager) {
		this.financialManagementManager = financialManagementManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.returnPlanManager = returnPlanManager;
		this.eventTypeManager = eventTypeManager;
		this.eventNewManager = eventNewManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
	}

	public void prepare() throws Exception {
		if (request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
		if (hasId("financialManagement.id")) {
			this.financialManagement = this.financialManagementManager
					.loadFinancialManagement(getId("financialManagement.id"));

			CodeValue c = this.codeValueManager.loadCodeValue(this.financialManagement.getBatch().getId());
			if (null != c)
				this.batchs.add(c);
		} else {
			this.financialManagement = new FinancialManagement();
		}
		User user = this.userManager.getUser();
		if (null != user.getCode()) {
			List salemans = this.personnelFilesManager.loadByKey("code", user.getCode());
			if (null != salemans) {
				PersonnelFiles saleman = (PersonnelFiles) salemans.get(0);
				this.financialManagement.setSaleman(saleman);
				this.financialManagement.setPayee(saleman);
			}
		}
	}

	public String save() throws Exception {
		boolean isNew = this.financialManagement.isNew();

		if (!StringUtils.isEmpty(this.request.getParameter("payment.id"))) {
			this.financialManagement.setPayment(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("payment.id"))));
		}
		if (!StringUtils.isEmpty(this.request.getParameter("financialManagement.accountName"))) {
			this.financialManagement.setAccountName(this.request.getParameter("financialManagement.accountName"));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("collectionType.id"))) {
			this.financialManagement.setCollectionType(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("collectionType.id"))));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("batch.id"))) {
			this.financialManagement.setBatch(this.codeValueManager.loadCodeValue(Long.valueOf(this.request
					.getParameter("batch.id"))));
		}

		if (!StringUtils.isEmpty(this.request.getParameter("dept.id"))) {
			Department dept = this.departmentManager.loadDepartment(Long.valueOf(this.request.getParameter("dept.id")));

			if (null != dept) {
				this.financialManagement.setDept(dept);
			}

		}

		if (!StringUtils.isEmpty(this.request.getParameter("customer.id"))) {
			CustomerInfo customer = this.customerInfoManager.loadCustomerInfo(Long.valueOf(this.request
					.getParameter("customer.id")));

			if (null != customer) {
				this.financialManagement.setCustomerInfo(customer);
			}
		}

		if (!StringUtils.isEmpty(this.request.getParameter("payee.id"))) {
			PersonnelFiles payee = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request
					.getParameter("payee.id")));

			if (null != payee) {
				this.financialManagement.setPayee(payee);
			}
		}

		if (!StringUtils.isEmpty(this.request.getParameter("saleman.id"))) {
			PersonnelFiles saleman = this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request
					.getParameter("saleman.id")));

			if (null != saleman) {
				this.financialManagement.setSaleman(saleman);
			}

		}

		if (hasId("invoice")) {
			String invoice = this.request.getParameter("invoice");
			this.financialManagement.setInvoice(invoice);
		}

		if (!StringUtils.isEmpty(this.request.getParameter("contractManagement.id"))) {
			ContractManagement contractManagement = this.contractManagementManager.loadContractManagement(Long
					.valueOf(this.request.getParameter("contractManagement.id")));

			if (null != contractManagement) {
				this.financialManagement.setContractManagement(contractManagement);
			}
		}
		String submit = "";
		if ("1".equals(this.request.getParameter("financialManagement.isSaved"))) {
			try {
				EventType eventType = null;
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10007");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					eventType = new EventType();
					eventType.setId(8L);
				}
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setEventType(eventType);
				event.setName("收款事件");
				event.setUserId(this.userManager.getUser().getId() + "");
				Map<String, String> map = new HashMap();
				String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.financialManagement
						.getContractManagement().getProject().getId(), this.financialManagement.getContractManagement()
						.getProject().getCreateUser());
				map.put("users", pids);
				map.put("financialManagementId", this.financialManagement.getId() + "");
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				eventNewManager.storeEventNew(event);
				submit = "submit";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.financialManagement.setIsSaved(request.getParameter("financialManagement.isSaved"));
		if (isNew) {
			List<FinancialManagement> financialManagements = this.financialManagementManager.loadByKey(
					"contractManagement", this.financialManagement.getContractManagement().getId());

			if (null != financialManagements) {
				for (FinancialManagement f : financialManagements) {
					f.setTotalSum(f.getTotalSum());
					f.setWithoutGotSum(f.getWithoutGotSum());
					this.financialManagementManager.storeFinancialManagement(f);
				}
			}
			String code = autoCompleteCode();
			this.financialManagement.setCode(code);

			this.financialManagementManager.storeFinancialManagement(this.financialManagement);
			addActionMessage(getText("financialManagement.save.success"));

			ContractManagement contractManagement = this.contractManagementManager
					.loadContractManagement(this.financialManagement.getContractManagement().getId());

			if (null != contractManagement) {
				contractManagement.setPaidMoney(this.financialManagement.getTotalSum().doubleValue());
				this.contractManagementManager.storeContractManagement(contractManagement);
			}

			String[] keyArray = new String[2];
			Object[] valueArray = new Object[2];
			keyArray[0] = "contractManagement";
			keyArray[1] = "batch";
			valueArray[0] = this.financialManagement.getContractManagement().getId();
			valueArray[1] = this.financialManagement.getBatch();
			List list = this.returnPlanManager.loadByKeyArray(keyArray, valueArray);
			if (null != list) {
				ReturnPlan r = (ReturnPlan) list.get(0);

				r.setFactSum(this.financialManagement.getTrueSum());
				r.setPaytime(this.financialManagement.getCollectionDate());
				r.setPayee(this.financialManagement.getPayee());
				r.setIsOrNot("0");
				this.returnPlanManager.storeReturnPlan(r);
			}

			if (this.financialManagement.getWithoutGotSum().doubleValue() == 0.0D) {
				ContractManagement c = this.financialManagement.getContractManagement();
				if (null != c) {
					c.setOverGet("yes");
					this.contractManagementManager.storeContractManagement(c);
				}

				List<ReturnPlan> returnPlans = null;
				if (null != this.financialManagement.getContractManagement()) {
					returnPlans = this.returnPlanManager.loadByKey("contractManagement", this.financialManagement
							.getContractManagement().getId());

					if (null != returnPlans) {
						for (ReturnPlan r : returnPlans) {
							if (r.getFactSum().doubleValue() == 0.0D) {
								r.setFactSum(Double.valueOf(0.0D));
								r.setPaytime(this.financialManagement.getCollectionDate());
								r.setPayee(this.financialManagement.getPayee());
								r.setIsOrNot("0");
								this.returnPlanManager.storeReturnPlan(r);
							}
						}
					}
				}
			}
			return "new";
		}
		List<FinancialManagement> financialManagements = this.financialManagementManager.loadByKey(
				"contractManagement", this.financialManagement.getContractManagement().getId());

		if (null != financialManagements) {
			for (FinancialManagement f : financialManagements) {
				f.setTotalSum(f.getTotalSum());
				f.setWithoutGotSum(f.getWithoutGotSum());
				this.financialManagementManager.storeFinancialManagement(f);
			}
		}
		this.financialManagementManager.storeFinancialManagement(this.financialManagement);
		if ("submit".equals(submit)) {
			addActionMessage(getText("financialManagement.submit.success"));
		} else {
			addActionMessage(getText("financialManagement.edit.success"));
		}

		ContractManagement contractManagement = this.contractManagementManager
				.loadContractManagement(this.financialManagement.getContractManagement().getId());

		contractManagement.setPaidMoney(this.financialManagement.getTotalSum().doubleValue());
		this.contractManagementManager.storeContractManagement(contractManagement);

		String[] keyArray = new String[2];
		Object[] valueArray = new Object[2];
		keyArray[0] = "contractManagement";
		keyArray[1] = "batch";
		valueArray[0] = this.financialManagement.getContractManagement().getId();
		valueArray[1] = this.financialManagement.getBatch();
		List list = this.returnPlanManager.loadByKeyArray(keyArray, valueArray);
		if (null != list) {
			ReturnPlan r = (ReturnPlan) list.get(0);

			r.setFactSum(this.financialManagement.getTrueSum());
			r.setPaytime(this.financialManagement.getCollectionDate());
			r.setPayee(this.financialManagement.getPayee());
			r.setIsOrNot("0");
			this.returnPlanManager.storeReturnPlan(r);
		}

		if (this.financialManagement.getWithoutGotSum().doubleValue() == 0.0D) {
			List<ReturnPlan> returnPlans = null;
			if (null != this.financialManagement.getContractManagement()) {
				returnPlans = this.returnPlanManager.loadByKey("contractManagement", this.financialManagement
						.getContractManagement().getId());

				if (null != returnPlans) {
					for (ReturnPlan r : returnPlans) {
						if (r.getFactSum().doubleValue() == 0.0D) {
							r.setFactSum(Double.valueOf(0.0D));
							r.setPaytime(this.financialManagement.getCollectionDate());
							r.setPayee(this.financialManagement.getPayee());
							r.setIsOrNot("0");
							this.returnPlanManager.storeReturnPlan(r);
						}
					}
				}
			}
		}
		return "success";
	}

	public List<CodeValue> getAllBatchs() {
		return this.batchs;
	}

	public List<CodeValue> getAllPayments() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("046"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

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

	public List<CodeValue> getAllCollectionTypes() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("06513"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

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

	public List<Department> getAllDepts() {
		List codes = this.departmentManager.loadAllDepartments();
		return codes;
	}

	public String autoCompleteCode() {
		String maxCode = this.financialManagementManager.getMaxPFCode("SK");
		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return "SK-00000" + num;
			if (num < 100)
				return "SK-0000" + num;
			if (num < 1000)
				return "SK-000" + num;
			if (num < 10000)
				return "SK-00" + num;
			if (num < 100000) {
				return "SK-0" + num;
			}
			return "SK-" + num;
		}

		return "SK-000001";
	}

	public FinancialManagement getFinancialManagement() {
		return this.financialManagement;
	}

	public void setFinancialManagement(FinancialManagement financialManagement) {
		this.financialManagement = financialManagement;
	}

	public List<CodeValue> getBatchs() {
		return this.batchs;
	}

	public void setBatchs(List<CodeValue> batchs) {
		this.batchs = batchs;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
}
