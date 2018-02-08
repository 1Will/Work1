package com.yongjun.tdms.presentation.webwork.action.workReport.apply.applyCar;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.apply.ApplyCar;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.apply.ApplyCarManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ListApplyCarAction extends ValueListAction {
	private static final long serialVersionUID = 675686785046421962L;
	private final ApplyCarManager applyCarManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
    private final PersonnelFilesManager personnelFilesManager;
	private final FlowManager flowManager;
	private final GroupManager groupManager;
    private List<ApplyCar> cars;

	public ListApplyCarAction(ApplyCarManager applyCarManager, DepartmentManager departmentManager,
			CodeValueManager codeValueManager,PersonnelFilesManager personnelFilesManager,FlowManager flowManager,GroupManager groupManager) {
		this.applyCarManager = applyCarManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager=personnelFilesManager;
		this.flowManager=flowManager;
		this.groupManager=groupManager;
	}

	public void prepare() throws Exception {
		if (hasIds("applyCarIds"))
			this.cars = this.applyCarManager.loadAllApplyCar(getIds("applyCarIds"));
	}

	protected String getAdapterName() {
		return "applyCars";
	}

	protected Map getRequestParameterMap()
   {
		Map map = super.getRequestParameterMap();
	     
	     User u = this.userManager.getUser();
	 	 Set<User> users = groupManager.getGroupByGroupName("考勤通知组").getUsers();
	 	 Boolean b=true;
	 	 for(User user:users){
	 		 if(u.getId().equals(user.getId()) || u.getId()==user.getId()){
	 			 b=false;
	 		 }
	 	 }
	     if (b) {
	    	 List<PersonnelFiles> personnals;
			try {
				personnals = personnelFilesManager.loadByKey("code", u.getCode());
				if(personnals!=null && personnals.size()>0){
		 			map.put("applyCar.applyPerson.id", personnals.get(0).getId());
		 		}
			} catch (DaoException e) {
				e.printStackTrace();
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
			this.applyCarManager.deleteApplyCar(this.cars);
			addActionMessage(getText("applyCar.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("applyCar.delete.error"));
		}
		return "error";
	}
	 private String disabled()
	    {
	      try
	      {
	        this.applyCarManager.disabledAllApplyCars(this.cars);
	        addActionMessage(getText("applyCar.disabled.success"));
	        return "success";
	      } catch (RuntimeException e) {
	        addActionMessage(getText("applyCar.disabled.error"));
	      }return "error";
	    }
	  
	    private String enabled()
	    {
	      try
	      {
	        this.applyCarManager.enabledAllApplyCars(this.cars);
	        addActionMessage(getText("applyCar.enabled.success"));
	        return "success";
	      } catch (RuntimeException e) {
	        e.printStackTrace();
	        addActionMessage(getText("applyCar.enabled.error"));
	      }return "error";
	    }
	public List<CodeValue> getAllStatus() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "020");

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


	public List<Department> getAllDepts() {
		List depts = this.departmentManager.loadAllDepartments();
		Department d = new Department();
		d.setId(Long.valueOf(-1L));
		d.setName(getText("select.option.all"));
		depts.add(0, d);
		return depts;
	}

	public List<ApplyCar> getCars() {
		return cars;
	}

	public void setCars(List<ApplyCar> cars) {
		this.cars = cars;
	}
	public List<Flow> getAllFlows() {
		List flowList = null;
		try {
			CodeValue codeValue =codeValueManager.loadByKey("code","t_applyCar").get(0);
			String[] keys = {"openOrNot","flowCode"};
			Object[] objects = {"0",codeValue.getId()};
		    flowList = flowManager.loadByKeyArray(keys, objects);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flowList;
	}
	
	public List<CodeValue> getAllTypes (){
		try {
			List<CodeValue> codeValues = new ArrayList<CodeValue>();
			String [] keys={"code","name"};
			Object [] values={"220","行车类别"};
			CodeValue one = codeValueManager.loadByKeyArray(keys, values).get(0);
			if (null != one) {
				List<CodeValue> list = codeValueManager.loadByKey("parentCV",one.getId());
				/*if ((null != list) && (list.size() > 0) && (addContent != null)) {
					CodeValue cv = new CodeValue();
					cv.setId(null);
					cv.setName(addContent);
					codeValues.add(0, cv);
				}*/
				codeValues.addAll(list);
			}
			return codeValues;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}
}
}
