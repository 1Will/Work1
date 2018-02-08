package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;

import java.util.ArrayList;
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
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactToHistoryManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ListContractManagementAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ContractManagementManager contractManagementManager;
	private final ContactToHistoryManager contactToHistoryManager;
	private final CodeValueManager codeValueManager;
	private final GroupManager groupManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private final UserManager userManager;

	private PersonnelFilesManager personnelFilesManager;
	private List<ContractManagement> contractManagements = null;
	private Long customerInfoId;

	public ListContractManagementAction(ContractManagementManager contractManagementManager,CodeValueManager codeValueManager, 
			ContactToHistoryManager contactToHistoryManager,PersonnelFilesManager personnelFilesManager,GroupManager groupManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager,UserManager userManager) {
		this.contractManagementManager = contractManagementManager;
		this.codeValueManager = codeValueManager;
		this.contactToHistoryManager = contactToHistoryManager;
		this.personnelFilesManager=personnelFilesManager;
		this.groupManager=groupManager;
		this.projectInfoPersonnelsManager=projectInfoPersonnelsManager;
		this.userManager=userManager;

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
		if (null != flag){
			return "contractManagementHQL2";
		}
		if (null != flag2) {
			return "contractManagementHQL3";
		}
		return "contractManagementHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		PersonnelFiles personnelFiles =null;
		try {
			
			if(isProjectInfoGroup()){//如果是项目管理组里的人员
				//再次区分是军品还是民品的
				List<PersonnelFiles>  tempList=this.personnelFilesManager.loadByKey("code", userManager.getUser().getCode());
				if(tempList!=null&&tempList.size()>0){
					personnelFiles  = tempList.get(0);
					if(personnelFiles.getBusinessType()!=null){
						//只有这个人是军品或者 民品会绑定检索条件。只能看所属权限的
						if(personnelFiles.getBusinessType().getName().equals("军品")||personnelFiles.getBusinessType().getName().equals("民品")){
							map.put("businessType", "%"+personnelFiles.getBusinessType().getName()+"%");
						}
					}
				}
			}else {//如果不是项目管理组的人员。就是查询这个人所在的所有项目
				List<Long> proIdList = this.projectInfoPersonnelsManager.loadProjectInfoIdByPersonnel(userManager.getUser().getCode());
				if(proIdList==null||proIdList.size()<1){
					//如果查询结果为空。 即不不是任何项目组成员，默认为0
					proIdList = new ArrayList<Long>();
					proIdList.add(0l);
				}
					map.put("proIds", proIdList);
			}
			
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		if (this.request.getParameter("contractManagement.id") != null) {
			int rNId = Integer.valueOf(this.request.getParameter("contractManagement.id")).intValue();
			map.put("contractManagement.id", Integer.valueOf(rNId));
		}
		if (hasId("projectInfo.id")) {
			map.put("project.id", getId("projectInfo.id"));
		}
		
		//获取页面传过来负责人的id
		 String pfCode = request.getParameter("personnelFiles.id");
			if(hasId("month") ){
				
				String month = request.getParameter("month")+"%";
				map.put("month", month);
				map.put("submitNum", "return");
				if(pfCode!=null && !"".equals(pfCode)){
					 String pcode=this.personnelFilesManager.loadPersonnel(Long.parseLong(pfCode)).getCode();
					 map.put("pcode", pcode);
					 map.remove("user.id");
				 }else {
					 map.put("pcode", userManager.getUser().getCode());
					 map.remove("user.id");
				}
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
	
	public boolean isProjectInfoGroup(){
		boolean isDailyGroup =false;
		Set<User> noticePers = groupManager.getGroupByGroupName("项目管理组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
			System.out.println(u.getId()+"=="+user.getId());
			if(user.getId().longValue() == u.getId().longValue()){
				isDailyGroup = true;
			}
		}
		
		return isDailyGroup;
	}

	public Long getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(Long customerInfoId) {
		this.customerInfoId = customerInfoId;
	}
}
