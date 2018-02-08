package com.yongjun.tdms.presentation.webwork.action.workReport.apply;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.apply.ApplyCar;
import com.yongjun.tdms.model.workReport.apply.ApplyGoods;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.apply.ApplyCarManager;
import com.yongjun.tdms.service.workReport.apply.ApplyGoodsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ListApplyGoodsAction extends ValueListAction {
	private static final long serialVersionUID = 675686785046421962L;
	private final ApplyGoodsManager applyGoodsManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
    private final PersonnelFilesManager personnelFilesManager;
    private final GroupManager groupManager;
	private List<ApplyGoods> goods;

	public ListApplyGoodsAction(ApplyGoodsManager applyGoodsManager, DepartmentManager departmentManager,
			CodeValueManager codeValueManager,PersonnelFilesManager personnelFilesManager,GroupManager groupManager) {
		this.applyGoodsManager = applyGoodsManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager=personnelFilesManager;
		this.groupManager=groupManager;
	}

	public void prepare() throws Exception {
		if (hasIds("applyGoodsIds"))
			this.goods = this.applyGoodsManager.loadAllApplyGoods(getIds("applyGoodsIds"));
	}

	protected String getAdapterName() {
		return "applyGoods";
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
		 			map.put("applyGoods.applyPerson.id", personnals.get(0).getId());
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
			this.applyGoodsManager.deleteApplyGoods(this.goods);
			addActionMessage(getText("applyGoods.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("applyGoods.delete.error"));
		}
		return "error";
	}
	 private String disabled()
	    {
	      try
	      {
	        this.applyGoodsManager.disabledAllApplyGoods(this.goods);
	        addActionMessage(getText("applyGoods.disabled.success"));
	        return "success";
	      } catch (RuntimeException e) {
	        addActionMessage(getText("applyGoods.disabled.error"));
	      }return "error";
	    }
	  
	    private String enabled()
	    {
	      try
	      {
	        this.applyGoodsManager.enabledAllApplyGoods(this.goods);
	        addActionMessage(getText("applyGoods.enabled.success"));
	        return "success";
	      } catch (RuntimeException e) {
	        e.printStackTrace();
	        addActionMessage(getText("applyGoods.enabled.error"));
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

/*	public List<CodeValue> getAllTypes() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "030");

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
	}*/

	public List<Department> getAllDepts() {
		List depts = this.departmentManager.loadAllDepartments();
		Department d = new Department();
		d.setId(Long.valueOf(-1L));
		d.setName(getText("select.option.all"));
		depts.add(0, d);
		return depts;
	}

	public List<ApplyGoods> getGoods() {
		return goods;
	}

	public void setGoods(List<ApplyGoods> goods) {
		this.goods = goods;
	}

	public List<CodeValue> getAllGoodsName() {
		List codes = null;
		try {
			codes = new ArrayList();
			String[] keys = {"code","name"};
			Object[] values = {"304","物品名称"};
			List one = this.codeValueManager.loadByKeyArray(keys, values);

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
	public List<CodeValue> getAllPurposes (){
		try {
			List<CodeValue> codeValues = new ArrayList<CodeValue>();
			String [] keys={"code","name"};
			Object [] values={"219","用途"};
			CodeValue one = codeValueManager.loadByKeyArray(keys, values).get(0);
			if (null != one) {
				List<CodeValue> list = codeValueManager.loadByKey("parentCV",one.getId());
				codeValues.addAll(list);
			}
			return codeValues;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}
		
}
}
