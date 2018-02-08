package com.yongjun.tdms.presentation.webwork.action.customercontract.billingrecord;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import java.util.List;
import java.util.Map;

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
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(hasId("billingRecord.billingTime_start")){
			map.put("billingRecord.billingTime_start",request.getParameter("billingRecord.billingTime_start"));
		}
		if(hasId("billingRecord.billingTime_end")){
			map.put("billingRecord.billingTime_end", request.getParameter("billingRecord.billingTime_end"));
		}
		 if(request.getParameter("personnelFiles.id")!=null && !"".equals(request.getParameter("personnelFiles.id"))){
				
			 String personnelFilesId=request.getParameter("personnelFiles.id");
			 map.put("personnelFiles.id", personnelFilesId);
			 map.remove("user.id");
		 }

	         if(hasId("month")){
	        	 String month = request.getParameter("month")+"%";
	        	 map.put("month", month);
	        	 map.put("submitNum", "return");
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
