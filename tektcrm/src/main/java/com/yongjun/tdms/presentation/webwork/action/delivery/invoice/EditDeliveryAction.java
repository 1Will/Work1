package com.yongjun.tdms.presentation.webwork.action.delivery.invoice;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

@SuppressWarnings("serial")
public class EditDeliveryAction extends PrepareAction{
	
	private CodeValueManager codeValueManager;
	private InvoicesManager invoicesManager;
	private DepartmentManager departmentManager;
	private PersonnelFilesManager personnelFilesManager;
	private CustomerInfoManager customerInfoManager;
	private ContractManagementManager contractManagementManager;
	private final UserManager userManager;
	
	private Invoices invoices;
	private PersonnelFiles deliveryPerson;
	private CustomerInfo customerInfo;
	private ContractManagement contractManagement;
	private CodeValue deliveryWay;
	private CodeValue deliveryStatus;
	private Department department;
	private String notNewFlag;

	public EditDeliveryAction(InvoicesManager invoicesManager,CodeValueManager codeValueManager,DepartmentManager departmentManager
			,PersonnelFilesManager personnelFilesManager,CustomerInfoManager customerInfoManager,ContractManagementManager contractManagementManager
			,UserManager userManager) {
		// TODO Auto-generated constructor stub
		this.invoicesManager = invoicesManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contractManagementManager = contractManagementManager;
		this.userManager = userManager;
	}
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (this.invoices == null) {
			if(hasId("invoices.id")){
				this.invoices = invoicesManager.loadInvoices(getId("invoices.id"));
			}else{
				this.invoices = new Invoices();
				//设置负责人默认为当前用户
				PersonnelFiles personnelFiles = null;
				if ((null != this.userManager.getUser().getCode())
						&& (!"".equals(this.userManager.getUser().getCode()))) {
					User user = this.userManager.getUser();
					personnelFiles = personnelFilesManager.loadByKey("code", user.getCode()).get(0);
					this.invoices.setDepartment(user.getDepartment());
					this.invoices.setContactWay(user.getTelphoneNumber());
				}
				
				this.invoices.setDeliveryPerson(personnelFiles);
			}
		}
		if (this.deliveryPerson == null) {
			if(hasId("deliveryPerson.id")){
				this.deliveryPerson = personnelFilesManager.loadPersonnel(getId("deliveryPerson.id"));
				this.invoices.setDeliveryPerson(deliveryPerson);
			}
		}
		if (this.contractManagement == null) {
			if(hasId("contractManagement.id")){
				this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
				this.invoices.setContractManagement(contractManagement);
			}
		}
		if (this.customerInfo == null) {
			if (hasId("customerInfo.id")) {
				this.customerInfo = customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
				this.invoices.setCustomerInfo(customerInfo);
			}
		}
		if (this.department == null) {
			if (hasId("department.id")) {
				this.department = departmentManager.loadDepartment(getId("department.id"));
				this.invoices.setDepartment(department);
			}
		}
		if (this.deliveryWay == null) {
			if (hasId("deliveryWay.id")) {
				this.deliveryWay = this.codeValueManager.loadCodeValue(getId("deliveryWay.id"));
				this.invoices.setDeliveryWay(deliveryWay);
			}
		}
		if (this.deliveryStatus == null) {
			if (hasId("deliveryStatus.id")) {
				this.deliveryStatus = codeValueManager.loadCodeValue(getId("deliveryStatus.id"));
				this.invoices.setDeliveryStatus(deliveryStatus);
			}
		}
		if (request.getParameter("notNewFlag") != null) {
			this.notNewFlag = request.getParameter("notNewFlag");
		}
	}
	
	
	public String save(){
		boolean isNew = this.invoices.isNew();
		
		if(request.getParameter("invoices.contacter") != null){
			this.invoices.setContacter(request.getParameter("invoices.contacter"));
		}
		if(request.getParameter("invoices.address") != null){
			this.invoices.setDelivreyAddress(request.getParameter("invoices.address"));
		}
		
		String submit = "";
		try {
			this.invoicesManager.storeInvoices(invoices);
			// 提交事件
			if ("1".equals(this.request.getParameter("invoices.isSaved"))) {
				submit = "submit";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if ("submit".equals(submit)) {
				addActionMessage(getText("发货单提交失败！"));
				return ERROR;
			}
			addActionMessage(getText("发货单保存失败！"));
			return ERROR;
		}
		if (isNew) {
			addActionMessage(getText("发货单保存成功！"));
			return NEW;
		}
		if ("submit".equals(submit)) {
			addActionMessage(getText("发货单提交成功！"));
			return SUCCESS;
		}
		addActionMessage(getText("发货单编辑成功！"));
		return SUCCESS;
	}
	
	//获取所有发货方式
	public List<CodeValue> getAllDeliveryWay() throws DaoException{
		List<CodeValue> deliveryWays = new ArrayList<CodeValue>();
		List<CodeValue> one = this.codeValueManager.loadByKey("code", String.valueOf("240"));
		if(one!=null && one.size() > 0){
			deliveryWays = codeValueManager.loadByKey("parentCV", one.get(0).getId());	
		}
		/*CodeValue cv = new CodeValue();
		cv.setId(null);
		cv.setName(getText(""));
		
		deliveryWays.add(0, cv);*/
		
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
	public List<Department> getAllDepartment(){
		List<Department> departmentsList = departmentManager.loadAllDepartments();
		return departmentsList;
	}
	public Invoices getInvoices() {
		return invoices;
	}

	public void setInvoices(Invoices invoices) {
		this.invoices = invoices;
	}

	public String getNotNewFlag() {
		return notNewFlag;
	}

	public void setNotNewFlag(String notNewFlag) {
		this.notNewFlag = notNewFlag;
	}
	
}
