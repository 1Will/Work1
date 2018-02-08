package com.yongjun.tdms.presentation.webwork.action.customerservicemanagement.repairs;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customerservicemanagement.repairs.Repairs;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.customerservicemanagement.repairs.RepairsManager;

public class EditRepairsAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final RepairsManager repairsManager;
	private final CodeValueManager codeValueManager;
	private final CustomerInfoManager customerInfoManager;
	private Repairs repairs = null;
	private CustomerInfo customerInfo;
	private CodeValue applyMode;
	private CodeValue callbackValidate;
	private CodeValue disposalState;

	public EditRepairsAction(RepairsManager repairsManager, CodeValueManager codeValueManager,
			CustomerInfoManager customerInfoManager) {
		this.repairsManager = repairsManager;
		this.codeValueManager = codeValueManager;
		this.customerInfoManager = customerInfoManager;
	}

	public void prepare() throws Exception {
		if (null == this.repairs) {
			if (hasId("repairs.id")) {
				this.repairs = this.repairsManager.loadRepairs(getId("repairs.id"));
			} else {
				this.repairs = new Repairs();
			}
		}

		if (null == this.customerInfo) {
			if (hasId("customerInfo.id"))
				this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			else {
				this.customerInfo = null;
			}
		}
		if (null == this.applyMode) {
			if (hasId("applyMode.id"))
				this.applyMode = this.codeValueManager.loadCodeValue(getId("applyMode.id"));
			else {
				this.applyMode = null;
			}
		}
		if (null == this.callbackValidate) {
			if (hasId("callbackValidate.id"))
				this.callbackValidate = this.codeValueManager.loadCodeValue(getId("callbackValidate.id"));
			else {
				this.callbackValidate = null;
			}
		}

		if (null == this.disposalState)
			if (hasId("disposalState.id"))
				this.disposalState = this.codeValueManager.loadCodeValue(getId("disposalState.id"));
			else
				this.disposalState = null;
	}

	public String save() {
		boolean isNew = this.repairs.isNew();
		if (isNew) {
			String code = autoCompleteCode();
			this.repairs.setCode(code);
		}
		try {
			this.repairs.setCustomerInfo(this.customerInfo);
			this.repairs.setApplyMode(this.applyMode);
			this.repairs.setCallbackValidate(this.callbackValidate);
			this.repairs.setDisposalState(this.disposalState);
			this.repairsManager.storeRepairs(this.repairs);
			if (isNew) {
				addActionMessage(getText("repairs.add.success"));
				return "new";
			}
			addActionMessage(getText("repairs.edit.success"));
			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			if (isNew) {
				addActionMessage(getText("repairs.add.error"));
				return "new";
			}
			addActionMessage(getText("repairs.edit.error"));
		}
		return "success";
	}

	public List<CodeValue> getAllApplyMode() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("111"));

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

	public List<CodeValue> getAllCallbackValidate() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("112"));

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

	public List<CodeValue> getAllDisposalState() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("113"));

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

	public String autoCompleteCode() {
		String prefix = "REP";
		String maxCode = this.repairsManager.getMaxPFCode(prefix);
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

	public Repairs getRepairs() {
		return this.repairs;
	}

	public void setRepairs(Repairs repairs) {
		this.repairs = repairs;
	}
}
