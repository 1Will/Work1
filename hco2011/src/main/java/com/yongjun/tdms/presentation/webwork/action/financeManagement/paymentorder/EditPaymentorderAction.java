package com.yongjun.tdms.presentation.webwork.action.financeManagement.paymentorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.financeManagement.paymentorder.Paymentorder;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.supplier.Supplier;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.financeManagement.paymentorder.PaymentorderManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.supplier.SupplierManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class EditPaymentorderAction extends FileTransportAction {
	private static final long serialVersionUID = 1L;
	private final PaymentorderManager paymentorderManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final SupplierManager supplierManager;
	private final ProjectInfoManager projectInfoManager;
	private final FileTransportManager fileTransportManager;
	private final EventTypeManager eventTypeManager;
	private final EventNewManager eventNewManager;
	private final PersonnelFilesToUserManager personnelFilesToUserManager;
	private Paymentorder paymentorder;
	private Supplier supplier;
	private PersonnelFiles paymentPersion = null;
	private Department department;
	private CodeValue produceType;
	private ProjectInfo projectInfo;
	private ContractManagementManager contractManagementManager;

	public EditPaymentorderAction(PaymentorderManager paymentorderManager, CodeValueManager codeValueManager,
			PersonnelFilesManager personnelFilesManager, DepartmentManager departmentManager,
			SupplierManager supplierManager, UserManager userManager, ProjectInfoManager projectInfoManager,
			ContractManagementManager contractManagementManager, FileTransportManager fileTransportManager,
			EventTypeManager eventTypeManager,EventNewManager eventNewManager,PersonnelFilesToUserManager personnelFilesToUserManager) {
		this.paymentorderManager = paymentorderManager;
		this.personnelFilesManager = personnelFilesManager;
		this.codeValueManager = codeValueManager;
		this.supplierManager = supplierManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.projectInfoManager = projectInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.fileTransportManager = fileTransportManager;
		this.eventTypeManager = eventTypeManager;
		this.eventNewManager = eventNewManager;
		this.personnelFilesToUserManager = personnelFilesToUserManager;
	}

	public void prepare() throws Exception {
		if (null == this.paymentorder) {
			if (hasId("paymentorder.id")) {
				this.paymentorder = this.paymentorderManager.loadPaymentorder(getId("paymentorder.id"));
			} else {
				this.paymentorder = new Paymentorder();
			}

		}

		if (null == this.department) {
			if (hasId("department.id")) {
				this.department = this.departmentManager.loadDepartment(getId("department.id"));
			} else {
				this.department = null;
			}
		}

		if (null == this.paymentPersion) {
			if (hasId("paymentPersion.id")) {
				this.paymentPersion = this.personnelFilesManager.loadPersonnel(getId("paymentPersion.id"));
			} else {
				this.paymentPersion = null;
			}
		}

		if (null == this.produceType) {
			if (hasId("produceType.id")) {
				this.produceType = this.codeValueManager.loadCodeValue(getId("produceType.id"));
			} else {
				this.produceType = null;
			}
		}

		if (null == this.supplier)
			if (hasId("supplier.id")) {
				this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
			} else
				this.supplier = null;
	}

	public String save() {
		boolean isNew = this.paymentorder.isNew();
		this.paymentorder.setOrganization(this.userManager.getOrganization());
		if (isNew) {
			String code = autoCompleteCode();
			this.paymentorder.setCode(code);
		}
		String submit = "";
		try {
			if (null == this.projectInfo) {
				if (hasId("project.id")) {
					this.projectInfo = this.projectInfoManager.loadProjectInfo(getId("project.id"));
				} else {
					this.projectInfo = null;
				}
			}
			this.paymentorder.setProjectInfo(projectInfo);
			if (hasId("contractManagement.id")) {
				ContractManagement contractManagement = this.contractManagementManager
						.loadContractManagement(getId("contractManagement.id"));
				this.paymentorder.setContractManagement(contractManagement);
			}
			this.paymentorder.setDepartment(this.department);
			this.paymentorder.setPaymentPersion(this.paymentPersion);
			this.paymentorder.setSupplier(this.supplier);
			this.paymentorder.setProduceType(this.produceType);
			if (!isNew) {
				if (this.paymentorder.getPosition() != null) {
					this.fileTransportManager.delete(this.request, this.paymentorder.getPosition());
				}
			}
			if (getFile() != null) {
				String location = this.fileTransportManager.upload(this.request, getFile(), "origFileName");
				this.paymentorder.setPosition(location);
			}
			System.out.println(this.paymentorder.getFileName());
			this.paymentorderManager.storePaymentorder(this.paymentorder);
			// 提交事件
			if ("1".equals(this.request.getParameter("paymentorder.isSaved"))) {
				try {
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10014");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventType不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName("付款事件");
					event.setUserId(this.userManager.getUser().getId() + "");
					Map<String, String> map = new HashMap();
					String pids = this.personnelFilesToUserManager.loadUserIdToStrByProjectInfoId(this.paymentorder.getProjectInfo().getId(), this.paymentorder.getProjectInfo().getCreateUser());
					map.put("users", pids);
					map.put("paymentorderId", this.paymentorder.getId() + "");
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					submit = "submit";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.paymentorder.setIsSaved(request.getParameter("paymentorder.isSaved"));
			
			if (isNew) {
				addActionMessage(getText("paymentorder.add.success"));
				return "new";
			}
			if("submit".equals(submit)){
				addActionMessage(getText("paymentorder.submit.success"));
				return "success";
			}
			addActionMessage(getText("paymentorder.edit.success"));
			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			if (isNew) {
				addActionMessage(getText("paymentorder.add.error"));
				return "new";
			}
			if("submit".equals(submit)){
				addActionMessage(getText("paymentorder.submit.error"));
				return ERROR;
			}
			addActionMessage(getText("paymentorder.edit.error"));
			return ERROR;
		}
	}

	public List<Department> getAllDepartment() {
		List depts = this.departmentManager.loadAllDepartments();
		return depts;
	}

	public String autoCompleteCode() {
		String prefix = "FKD";
		String maxCode = this.paymentorderManager.getMaxPFCode(prefix);
		if (null != maxCode) {
			int num = Integer.parseInt(maxCode) + 1;
			if (num < 10)
				return prefix + "-00000" + num;
			if (num < 100)
				return prefix + "-0000" + num;
			if (num < 1000)
				return prefix + "-000" + num;
			if (num < 10000)
				return prefix + "-00" + num;
			if (num < 100000) {
				return prefix + "-0" + num;
			}
			return prefix + "-" + num;
		}

		return prefix + "-000001";
	}

	public List<CodeValue> getAllProduceType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("072"));

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

	public User getUser() {
		return this.userManager.getUser();
	}

	public PersonnelFiles getPersonnelF() throws Exception {
		List pfs = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());

		if ((null != pfs) && (pfs.size() > 0)) {
			return (PersonnelFiles) pfs.get(0);
		}
		return null;
	}

	public Paymentorder getPaymentorder() {
		return this.paymentorder;
	}

	public void setPaymentorder(Paymentorder paymentorder) {
		this.paymentorder = paymentorder;
	}
}
