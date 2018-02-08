package com.yongjun.tdms.presentation.webwork.action.delivery.invoice;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;

public class ListDeliveryTempletAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
    private String backCheckBox;
    private CodeValueManager codeValueManager;
    private DepartmentManager departmentManager;
    private InvoicesManager invoicesManager;
    private  List<Invoices> invoices;
    
	public ListDeliveryTempletAction(CodeValueManager codeValueManager,DepartmentManager departmentManagers,InvoicesManager invoicesManager) {
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManagers;
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public void prepare() throws Exception {
		if(hasId("invoicesIds")){
			this.invoices = invoicesManager.loadAllInvoices(getIds("invoicesIds"));
		}
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	private String delete() {
		try {
			//this.proPlanTempletManager.deleteAllProPlanTemplet(proPlanTemplets);
			addActionMessage(getText("proPlanTemplet.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("proPlanTemplet.delete.failer"));
		}
		return "error";
	}
    
	@Override
	protected String getAdapterName() {
		return "invoicesHQL";
	}

	//获取所有发货方式
	public List<CodeValue> getAllDeliveryWay() throws DaoException{
		List<CodeValue> deliveryWays = new ArrayList<CodeValue>();
		List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("240"));
		if(one!=null && one.size() > 0){
			deliveryWays = codeValueManager.loadByKey("parentCV", one.get(0).getId());	
		}
		CodeValue cv = new CodeValue();
		cv.setId(null);
		cv.setName(getText(""));
		
		deliveryWays.add(0, cv);
		
		return deliveryWays;
	}
	//获取所有发货状态
	public List<CodeValue> getAllDeliveryStatus() throws DaoException{
		List<CodeValue> deliveryStatus = new ArrayList<CodeValue>();
		List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("241"));
		if(one!=null && one.size() > 0){
			deliveryStatus = codeValueManager.loadByKey("parentCV", one.get(0).getId());
		}
		CodeValue cv = new CodeValue();
		cv.setId(null);
		cv.setName(getText(""));
		
		deliveryStatus.add(0, cv);
		return deliveryStatus;
	}
	//查询所有部门
	/*public List<Department> getAllDepartment(){
		List<Department> departmentsList = departmentManager.loadAllDepartments();
		Department department = new Department();
		department.setId(null);
		department.setName(getText("所有"));
		
		departmentsList.add(0, department);
		return departmentsList;
	}
*/
	public String getBackCheckBox() {
		return backCheckBox;
	}

	public void setBackCheckBox(String backCheckBox) {
		this.backCheckBox = backCheckBox;
	}
	
}
