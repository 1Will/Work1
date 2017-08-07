package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ListContractManagementAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ContractManagementManager contractManagementManager;
	private final ContactToHistoryManager contactToHistoryManager;
	private final CodeValueManager codeValueManager;
	private List<ContractManagement> contractManagements = null;
	private Long customerInfoId;

	public ListContractManagementAction(ContractManagementManager contractManagementManager,
			CodeValueManager codeValueManager, ContactToHistoryManager contactToHistoryManager) {
		this.contractManagementManager = contractManagementManager;
		this.codeValueManager = codeValueManager;
		this.contactToHistoryManager = contactToHistoryManager;
	}

	public void prepare() throws Exception {
		if ((null == this.contractManagements) && (hasIds("contractManagementIds")))
			this.contractManagements = this.contractManagementManager
					.loadAllContractManagement(getIds("contractManagementIds"));
		if (hasId("customerInfo.id")) {
			this.customerInfoId = getId("customerInfo.id");
		}
	}

	protected String getAdapterName() {
		String flag = this.request.getParameter("flag");
		String flag2 = this.request.getParameter("flag2");
		if (null != flag)
			return "contractManagementHQL2";
		if (null != flag2) {
			return "contractManagementHQL3";
		}
		return "contractManagementHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.request.getParameter("contractManagement.id") != null) {
			int rNId = Integer.valueOf(this.request.getParameter("contractManagement.id")).intValue();
			map.put("contractManagement.id", Integer.valueOf(rNId));
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
			for (int i = 0; i < this.contractManagements.size(); i++) {
				//删除修改历史关联表
				List<ContactToHistory> contactToHistorys = this.contactToHistoryManager.loadByKey(
						"contractManagement.id", this.contractManagements.get(i).getId());
				if (contactToHistorys != null) {
					this.contactToHistoryManager.deleteAllContactToHistory(contactToHistorys);
				}
			}
			//删除合同s
			this.contractManagementManager.deleteAllContractManagement(this.contractManagements);
			addActionMessage(getText("contractManagement.delete.success"));
			return "success";
		} catch (DaoException e) {
			addActionMessage(getText("contractManagement.delete.error"));
		}
		return "error";
	}

	private String disabled() {
		try {

			this.contractManagementManager.disabledAllContractManagement(this.contractManagements);
			addActionMessage(getText("contractManagement.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("contractManagement.disabled.error"));
		}
		return "error";
	}

	public List<CodeValue> getAllComplaintType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("058"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();

			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
		}
		return codes;
	}

	private String enabled() {
		try {
			this.contractManagementManager.enabledAllContractManagement(this.contractManagements);
			addActionMessage(getText("contractManagement.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("contractManagement.enabled.error"));
		}
		return "error";
	}

	public List<CodeValue> getAllContractType() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("064"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();

			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
		}
		return codes;
	}

	public List<CodeValue> getAllState() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("066"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();

			CodeValue cv = new CodeValue();
			cv.setId(null);
			cv.setName(getText("所有"));
			codes.add(0, cv);
		}
		return codes;
	}

	public Long getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(Long customerInfoId) {
		this.customerInfoId = customerInfoId;
	}
}
