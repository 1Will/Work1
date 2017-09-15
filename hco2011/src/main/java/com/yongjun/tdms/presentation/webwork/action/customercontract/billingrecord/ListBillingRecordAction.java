package com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import java.util.List;

public class ListBillingRecordAction extends ValueListAction {
	private static final long serialVersionUID = 675686785046421962L;
	private final BillingRecordManager billingRecordManager;
	private List<BillingRecord> billingRecords;

	public ListBillingRecordAction(BillingRecordManager billingRecordManager) {
		this.billingRecordManager = billingRecordManager;
	}

	public void prepare() throws Exception {
		if (hasIds("billingRecordIds"))
			this.billingRecords = this.billingRecordManager.loadAllBillingRecord(getIds("billingRecordIds"));
	}

	protected String getAdapterName() {
		return "billingRecords";
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
			this.billingRecordManager.deleteAllBillingRecord(this.billingRecords);
			addActionMessage(getText("billingRecord.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("billingRecord.delete.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.billingRecordManager.disabledAllBillingRecord(this.billingRecords);
			addActionMessage(getText("billingRecord.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("billingRecord.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.billingRecordManager.enabledAllBillingRecord(this.billingRecords);
			addActionMessage(getText("billingRecord.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("billingRecord.enabled.error"));
		}
		return "error";
	}

	public List<BillingRecord> getBillingRecords() {
		return this.billingRecords;
	}

	public void setBillingRecords(List<BillingRecord> billingRecords) {
		this.billingRecords = billingRecords;
	}
}
