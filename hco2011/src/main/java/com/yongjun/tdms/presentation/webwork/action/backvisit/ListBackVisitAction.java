package com.yongjun.tdms.presentation.webwork.action.backvisit;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListBackVisitAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final UserManager userManager;
	protected final GroupManager groupManager;
	protected final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private CodeValueManager codeValueManager;
	private BackVisitManager backVisitManager;
	private List<BackVisit> backVisits;
	private PersonnelFilesManager personnelFilesManager;

	public ListBackVisitAction(UserManager userManager, GroupManager groupManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager) {
		this.userManager = userManager;
		this.groupManager = groupManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
	}

	public void setBackVisitManager(BackVisitManager backVisitManager) {
		this.backVisitManager = backVisitManager;
	}

	public List<BackVisit> getBackVisits() {
		return this.backVisits;
	}

	public void setBackVisits(List<BackVisit> backVisits) {
		this.backVisits = backVisits;
	}

	public BackVisitManager getBackVisitManager() {
		return this.backVisitManager;
	}

	public CodeValueManager getCodeValueManager() {
		return this.codeValueManager;
	}

	public PersonnelFilesManager getPersonnelFilesManager() {
		return this.personnelFilesManager;
	}

	public void setPersonnelFilesManager(PersonnelFilesManager personnelFilesManager) {
		this.personnelFilesManager = personnelFilesManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	protected String getAdapterName() {
		return "backvisitHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
//		PersonnelFiles personnelFiles = null;
//		List<Long> backVisitIds = new ArrayList<Long>();
//		List<BackVisit> backVisitList = new ArrayList<BackVisit>();
//		BackVisit b = new BackVisit();
//		b.setId(0L);
//		//防止空报错
//		backVisitList.add(b);
//		try {
//			List<PersonnelFiles> tempList = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
//			//除了admin，其他的都有访问限制
//			if (tempList != null && tempList.size() > 0) {
//				personnelFiles = tempList.get(0);
//				//访问人是自己的，肯定能看到
//				List<BackVisit> bvts1 = backVisitManager.loadByKey("employee.id", personnelFiles.getId());
//				if(bvts1!=null && bvts1.size()>0){
//					backVisitList = bvts1;
//				}
//				//是否在项目管理组里
//				if (isProjectInfoGroup()) {
//					if (personnelFiles.getBusinessType() != null) {
//						if (personnelFiles.getBusinessType().getName().equals("军品")|| personnelFiles.getBusinessType().getName().equals("民品")) {
//							List<BackVisit> bvts2 = backVisitManager.loadByKey("projectInfo.customer.businessType.id",personnelFiles.getBusinessType().getId());
//							if(bvts2!=null && bvts2.size()>0){
//								backVisitList.addAll(bvts2);
//							}
//						} else {
//							List<BackVisit> bvts3 = backVisitManager.loadBackVisitByPj(" is not null");
//							if(bvts3!=null && bvts3.size()>0){
//								backVisitList.addAll(bvts3);
//							}
//						}
//						for(int i =0;i<backVisitList.size();i++){
//							backVisitIds.add(backVisitList.get(i).getId());
//						}
//						
//						map.put("backVisitIds", backVisitIds);
//					}
//				} else {
//					List<Long> proIdList = this.projectInfoPersonnelsManager.loadProjectInfoIdByPersonnel(this.userManager.getUser().getCode());
//					if(proIdList!=null && proIdList.size()>0){
//						List<BackVisit> bvts4 = backVisitManager.loadBackVisitByPj(".id in "+ proIdList.toString().replace("[", "(").replace("]", ")"));
//						if(bvts4!=null && bvts4.size()>0){
//							backVisitList.addAll(bvts4);
//						}
//					}
//					for(int i =0;i<backVisitList.size();i++){
//						backVisitIds.add(backVisitList.get(i).getId());
//					}
//					map.put("backVisitIds", backVisitIds);
//				}
//			}
//
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
		if (hasId("continueBackVisit")) {
			if (getId("continueBackVisit").equals(Long.valueOf("2")))
				map.put("cbv", null);
			else {
				map.put("cbv", Long.valueOf(getId("continueBackVisit").longValue()));
			}
		}
		return map;
	}

	public void prepare() throws Exception {
		if ((this.backVisits == null) && (hasIds("backVisitIds")))
			this.backVisits = this.backVisitManager.loadAllBackVisit(getIds("backVisitIds"));
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
			this.backVisitManager.deleteAllBackVisit(this.backVisits);
			addActionMessage(getText("backVisits.delete.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.delete.failer"));
		}
		return "error";
	}

	private String enable() {
		try {
			this.backVisitManager.enableBackVisits(this.backVisits);
			addActionMessage(getText("backVisits.enable.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.enable.failer"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.backVisitManager.disabledBackVisits(this.backVisits);
			addActionMessage(getText("backVisits.disabled.success"));
			return "success";
		} catch (Exception e) {
			addActionMessage(getText("backVisits.disabled.failer"));
		}
		return "error";
	}

	public List<CodeValue> getAllBackVisitType() {
		List cust_types = new ArrayList();
		try {
			CodeValue custType = (CodeValue) this.codeValueManager.loadByKey("code", "888").get(0);
			cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
			if (cust_types != null) {
				CodeValue cv = new CodeValue();
				cv.setId(Long.valueOf(-1L));
				cv.setName(getText("select.option.all"));
				cust_types.add(0, cv);
				return cust_types;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public List<CodeValue> getAllBackVisitWay() {
		List cust_types = new ArrayList();
		try {
			CodeValue custType = (CodeValue) this.codeValueManager.loadByKey("code", "007").get(0);
			cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
			if (cust_types != null) {
				CodeValue cv = new CodeValue();
				cv.setId(Long.valueOf(-1L));
				cv.setName(getText("select.option.all"));
				cust_types.add(0, cv);
				return cust_types;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public List<CodeValue> getAllSteps() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("022"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(1)).getId());
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
			List codes = new ArrayList();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		}
	}

	public boolean isProjectInfoGroup() {
		boolean isDailyGroup = false;
		Set<User> noticePers = groupManager.getGroupByGroupName("项目管理组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
			System.out.println(u.getId() + "==" + user.getId());
			if (user.getId().longValue() == u.getId().longValue()) {
				isDailyGroup = true;
			}
		}

		return isDailyGroup;
	}

	public void setCodeValueManager(CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}

	public void BackVisitManager(BackVisitManager backVisitManager) {
		this.backVisitManager = backVisitManager;
	}
}
