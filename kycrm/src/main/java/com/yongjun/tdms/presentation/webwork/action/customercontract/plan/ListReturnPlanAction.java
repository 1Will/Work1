package com.yongjun.tdms.presentation.webwork.action.customercontract.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.expensemanagement.expense.Rent;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.expensemanagement.expense.RentManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ListReturnPlanAction extends ValueListAction {
	private static final long serialVersionUID = 675686785046421962L;
	private final ReturnPlanManager returnPlanManager;
	private final CodeValueManager codeValueManager;
	private final FinancialManagementManager financialManagementManager;
	private final RentManager rentManager;
	private final BillingRecordManager billingRecordManager;
	
	private List<ReturnPlan> returnPlanes;
     private Long contractManagementId;
	public ListReturnPlanAction(ReturnPlanManager returnPlanManager, CodeValueManager codeValueManager,
			FinancialManagementManager financialManagementManager,RentManager rentManager,BillingRecordManager billingRecordManager) {
		this.returnPlanManager = returnPlanManager;
		this.codeValueManager = codeValueManager;
		this.financialManagementManager = financialManagementManager;
		this.rentManager = rentManager;
		this.billingRecordManager = billingRecordManager;
	}

	public void prepare() throws Exception {
		if (hasIds("returnPlanIds")){
			this.returnPlanes = this.returnPlanManager.loadAllReturnPlans(getIds("returnPlanIds"));
		}
		if(hasId("contractManagement.id")){
			this.contractManagementId=getId("contractManagement.id");
		}
			
	}
	protected String getAdapterName() {
		Map map = super.getRequestParameterMap();
        if(contractManagementId!=null){
          map.put("contractManagement.id", contractManagementId);
        }
		
		return "returnPlanes";
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

	public String delete()  {
		try {
			for (ReturnPlan r : this.returnPlanes) {
				//房租收款计划删除
				if(r.getContractManagement()!=null){
					String[] keyArry ={"contractManagement","batch"};
					Object[] keyValue ={r.getContractManagement().getId(),r.getBatch()};
					List flist = this.financialManagementManager.loadByKeyArray(keyArry, keyValue);
					List blist = this.billingRecordManager.loadByKeyArray(keyArry, keyValue);
					if ((null != flist) && (flist.size() > 0) && (null != blist) && (blist.size() > 0)) {
						addActionMessage(getText("returnPlan.delete.error"));
					} else {
						this.returnPlanManager.deleteReturnPlan(r);
						addActionMessage(getText("returnPlan.delete.success"));
					}
				}else {
					//水电收款计划删除
					List flist = this.financialManagementManager.loadByKey("returnPlan.id", r.getId());
					List blist = this.billingRecordManager.loadByKey("returnPlan.id", r.getId());
					if ((null != flist) && (flist.size() > 0) && (null != blist) && (blist.size() > 0)) {
						addActionMessage(getText("returnPlan.delete.error"));
					} else {
						this.returnPlanManager.deleteReturnPlan(r);
						addActionMessage(getText("returnPlan.delete.success"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("returnPlan.delete.error"));
			return "error";
		}
		return "success";
	}

	private String disabled() {
		try {
			this.returnPlanManager.disabledAllReturnPlan(this.returnPlanes);
			addActionMessage(getText("returnPlan.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("returnPlan.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.returnPlanManager.enabledAllReturnPlan(this.returnPlanes);
			addActionMessage(getText("returnPlan.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("returnPlan.enabled.error"));
		}
		return "error";
	}

	public List<CodeValue> getAllPayment() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("065"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (hasId("isOrNot")) {
			if (getId("isOrNot").equals(Long.valueOf("2")))
				map.put("cbv", null);
			else {
				map.put("cbv", Long.valueOf(getId("isOrNot").longValue()));
			}
		}
		if(hasId("rent.id")){
			try {
				Rent rent = rentManager.loadRent(getId("rent.id"));
				map.put("returnPlan.planDate_start", rent.getStartTime());
				map.put("returnPlan.planDate_end", rent.getEndTime());
				CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "23701").get(0);
				map.put("mold.id", codeValue.getId());
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public List getAllMold() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "237").get(0);
			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				CodeValue cv = new CodeValue();
				list.add(0, cv);
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<ReturnPlan> getReturnPlanes() {
		return this.returnPlanes;
	}

	public void setReturnPlanes(List<ReturnPlan> returnPlanes) {
		this.returnPlanes = returnPlanes;
	}

	public Long getContractManagementId() {
		return contractManagementId;
	}

	public void setContractManagementId(Long contractManagementId) {
		this.contractManagementId = contractManagementId;
	}
	
}
