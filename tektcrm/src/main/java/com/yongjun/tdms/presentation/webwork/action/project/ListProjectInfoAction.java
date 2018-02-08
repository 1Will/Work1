package com.yongjun.tdms.presentation.webwork.action.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.expensemanagement.expenseForm.ExpenseForm;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;
import com.yongjun.tdms.model.workReport.weekPlan.WeekPlan;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.expensemanagement.expenseForm.ExpenseFormManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
import com.yongjun.tdms.service.project.projectInfoProduct.ProjectInfoProductManager;
import com.yongjun.tdms.service.workReport.weekPlan.WeekPlanManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListProjectInfoAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoManager projectInfoManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	protected final GroupManager groupManager;
	private final UserManager userManager;
	private final ProjectInfoCustomerManager projectInfoCustomerManager;
	private final ProjectInfoContractManager projectInfoContractManager;
	private final ApplicationDocManager applicationDocManager;
	private final ProjectInfoProductManager projectInfoProductManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private final WeekPlanManager weekPlanManager;
	private final ExpenseFormManager expenseFormManager;
	private final ContractManagementManager contractManagementManager;

	private List<ProjectInfo> projectInfos;
	private String contactArchivesFlag;
	private String backVisitCheckBox;
	private Long customerId;

	public ListProjectInfoAction(CodeValueManager codeValueManager, ProjectInfoManager projectInfoManager,
			PersonnelFilesManager personnelFilesManager, UserManager userManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager, GroupManager groupManager,
			ProjectInfoCustomerManager projectInfoCustomerManager,ProjectInfoContractManager projectInfoContractManager,
			ApplicationDocManager applicationDocManager,ProjectInfoProductManager projectInfoProductManager,
			ProjectInfoPlanManager projectInfoPlanManager,WeekPlanManager weekPlanManager,ExpenseFormManager expenseFormManager,
			ContractManagementManager contractManagementManager) {
		this.codeValueManager = codeValueManager;
		this.projectInfoManager = projectInfoManager;
		this.personnelFilesManager = personnelFilesManager;
		this.userManager = userManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
		this.groupManager = groupManager;
		this.projectInfoCustomerManager = projectInfoCustomerManager;
		this.projectInfoContractManager = projectInfoContractManager;
		this.applicationDocManager = applicationDocManager;
		this.projectInfoProductManager = projectInfoProductManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
		this.weekPlanManager = weekPlanManager;
		this.expenseFormManager = expenseFormManager;
		this.contractManagementManager = contractManagementManager;

	}

	protected String getAdapterName() {
		return "getProjectInfo";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		PersonnelFiles personnelFiles = null;
		
		User thisUser = this.userManager.getUser();
		
		List<PersonnelFiles> tempList=null;
		try {
			tempList = this.personnelFilesManager.loadByKey("code", thisUser.getCode());
		
			if(hasId("month")){

				String month = request.getParameter("month")+"%";
				map.put("month", month);
				map.put("submitNum", "return");
				if(request.getParameter("personnelFiles.id")!=null  && !"".equals(request.getParameter("personnelFiles.id"))){
					
					 String pCode=this.personnelFilesManager.loadPersonnel(Long.parseLong(request.getParameter("personnelFiles.id"))).getCode();
					 map.put("personnelFiles.id", pCode);
					 map.remove("user.id");
				 }else {
					 if (tempList != null && tempList.size() > 0) {
						 
						 map.put("personnelFiles.id", tempList.get(0).getCode());
						 map.remove("user.id");
					 }
					
				}
			}
		if (thisUser.getName().equals("admin")) {
			return map;
		}
			if (isProjectInfoGroup()) {// 如果是项目管理组里的人员
				// 再次区分是军品还是民品的
				if (tempList != null && tempList.size() > 0) {
					personnelFiles = tempList.get(0);
					if (personnelFiles.getBusinessType() != null) {
						// 只有这个人是军品或者 民品会绑定检索条件。只能看所属权限的
						if (personnelFiles.getBusinessType().getName().equals("军品")
								|| personnelFiles.getBusinessType().getName().equals("民品")) {
							map.put("businessType", "%" + personnelFiles.getBusinessType().getName() + "%");
						}

					}
				}
			} else {// 如果不是项目管理组的人员。就是查询这个人所在的所有项目
				List<Long> proIdList = this.projectInfoPersonnelsManager.loadProjectInfoIdByPersonnel(thisUser
						.getCode());
				if (proIdList == null || proIdList.size() < 1) {
					// 如果查询结果为空。 即不不是任何项目组成员，默认为0
					proIdList = new ArrayList<Long>();
					proIdList.add(0l);
				}
				map.put("proIds", proIdList);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}

		if (hasId("projectInfo.creator")) {
			User user = this.userManager.loadUser(getId("projectInfo.creator"));
			map.put("projectInfo.creator", user.getName());
		}
		if (hasId("customer.id")) {
			map.put("customerId", getId("customer.id"));
		}
		if (this.request.getParameter("projectInfo.createdTime") != null ) {
			map.put("projectInfo.createdTime", this.request.getParameter("projectInfo.createdTime") + "%");
		}
		 
		if(this.request.getParameter("projectInfoByCopyId")!=null){
			List<Long> projectInfoIdByCopy=new ArrayList<Long>();
			projectInfoIdByCopy.add(Long.parseLong(this.request.getParameter("projectInfoByCopyId")));
			map.put("projectInfoIdByCopy", projectInfoIdByCopy);
		}
		return map;
	}

	public void prepare() throws Exception {
		if ((this.projectInfos == null) && (hasIds("projectInfoIds"))) {
			this.projectInfos = this.projectInfoManager.loadAllProjectInfo(getIds("projectInfoIds"));
		}
		if (this.request.getParameter("contactArchivesFlag") != null) {
			this.contactArchivesFlag = this.request.getParameter("contactArchivesFlag");

		}
		if (this.request.getParameter("backVisitCheckBox") != null) {
			this.backVisitCheckBox = this.request.getParameter("backVisitCheckBox");
		}
		if (hasId("customerInfo.id")) {
			this.customerId = getId("customerInfo.id");
			setFirst(false);
		}
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			return disabled();
		}

		if (isEnable()) {
			return enable();
		}

		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public boolean getIsPersonnelFiles() throws Exception {
		if (null == this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode())) {
			return false;
		}
		return true;
	}

	private String delete() {
		try {
			this.projectInfoManager.deleteAllProjectInfo(this.projectInfos);
			addActionMessage(getText("projectInfos.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String enable() {
		try {
			this.projectInfoManager.enableProjectInfos(this.projectInfos);
			addActionMessage(getText("projectInfos.enable.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("projectInfos.enable.fail"));
		}
		return "error";
	}

	private String disabled() {
		try {
			int a = 0;
			for (int i = 0; i < projectInfos.size(); i++) {
				List<ProjectInfoPersonnels> pPersonnels = projectInfoPersonnelsManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (pPersonnels !=null && pPersonnels.size() > 1) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				List<ProjectInfoCustomer> pCustomers  = projectInfoCustomerManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (pCustomers !=null && pCustomers.size() > 1) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				List<ProjectInfoContract> pContracts = projectInfoContractManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (pContracts !=null && pContracts.size() > 1) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				List<ApplicationDoc> aDocs = applicationDocManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (aDocs !=null && aDocs.size() > 0) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				List<ProjectInfoProduct> pProducts = projectInfoProductManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (pProducts !=null && pProducts.size() > 0) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				
				List<ProjectInfoPlan> pPlans = projectInfoPlanManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (pPlans !=null && pPlans.size() > 0) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				
				List<WeekPlan> wPlans = weekPlanManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (wPlans !=null && wPlans.size() > 0) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				
				List<ExpenseForm> eForms = expenseFormManager.loadByKey("projectInfo.id",projectInfos.get(i).getId());
				if (eForms !=null && eForms.size() > 0) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
				
				List<ContractManagement> cManagements = contractManagementManager.loadByKey("project.id",projectInfos.get(i).getId());
				if (cManagements !=null && cManagements.size() > 0) {
					projectInfos.remove(i);
					a+=1;
					continue;
				}
			}
			if (this.projectInfos!=null && this.projectInfos.size()>0) {
				this.projectInfoManager.disabledProjectInfos(this.projectInfos);
				if (a>0) {
					addActionMessage(getText("project.disabled.incomplete",Arrays.asList(new Object[] {a})));
				}else {
					addActionMessage(getText("project.disabled.success"));
				}
				return "success";
			}else {
				addActionMessage(getText("project.disabled.incomplete",Arrays.asList(new Object[] {a})));
				return "success";
			}
		} catch (Exception e) {
			addActionMessage(getText("project.disabled.fail"));
			return "error";
		}
	}

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "201");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					CodeValue cv = new CodeValue();
					cv.setId(null);
					cv.setName(getText("所有"));
					codes.add(0, cv);
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public boolean isProjectInfoGroup() {
		boolean isDailyGroup = false;
		Set<User> noticePers = groupManager.getGroupByGroupName("项目管理组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
//			System.out.println(u.getId() + "==" + user.getId());
			if (user.getId().longValue() == u.getId().longValue()) {
				isDailyGroup = true;
			}
		}

		return isDailyGroup;
	}

	public List<ProjectInfo> getProjectInfos() {
		return projectInfos;
	}

	public String getContactArchivesFlag() {
		return contactArchivesFlag;
	}

	public String getBackVisitCheckBox() {
		return backVisitCheckBox;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setProjectInfos(List<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

	public void setContactArchivesFlag(String contactArchivesFlag) {
		this.contactArchivesFlag = contactArchivesFlag;
	}

	public void setBackVisitCheckBox(String backVisitCheckBox) {
		this.backVisitCheckBox = backVisitCheckBox;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
