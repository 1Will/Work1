 package com.yongjun.tdms.presentation.webwork.action.workspace.leaveBill;
 
 import com.yongjun.pluto.exception.DaoException;
 import com.yongjun.pluto.model.codevalue.CodeValue;
 import com.yongjun.pluto.model.security.Department;
 import com.yongjun.pluto.model.security.User;
 import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
 import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
 import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
import java.util.Set;
 
 public class ListLeaveBillAction extends ValueListAction
 {
   private static final long serialVersionUID = 675686785046421962L;
   private final LeaveBillManager leaveBillManager;
   private final DepartmentManager departmentManager;
   private final CodeValueManager codeValueManager;
   private final GroupManager groupManager;
   private final PersonnelFilesManager personnelFilesManager;
   private List<LeaveBill> lbs;
 
   public ListLeaveBillAction(LeaveBillManager leaveBillManager, DepartmentManager departmentManager, CodeValueManager codeValueManager,GroupManager groupManager,PersonnelFilesManager personnelFilesManager)
   {
     this.leaveBillManager = leaveBillManager;
     this.departmentManager = departmentManager;
     this.codeValueManager = codeValueManager;
     this.groupManager=groupManager;
     this.personnelFilesManager=personnelFilesManager;
   }
 
   public void prepare()
     throws Exception
   {
     if (hasIds("leaveBillIds"))
       this.lbs = this.leaveBillManager.loadAllLeaveBill(getIds("leaveBillIds"));
   }
 
   protected String getAdapterName()
   {
     return "leaveBills";
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
	 			map.put("leaveBill.applyPerson.id", personnals.get(0).getId());
	 		}
		} catch (DaoException e) {
			e.printStackTrace();
		}
 		
     }
     return map;
   }
 
   public String execute()
     throws Exception
   {
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
 
   public String delete()
   {
     try
     {
       this.leaveBillManager.deleteAllLeaveBill(this.lbs);
       addActionMessage(getText("leaveBill.delete.success"));
       return "success";
     } catch (RuntimeException e) {
       addActionMessage(getText("leaveBill.delete.error"));
     }return "error";
   }
 
   private String disabled()
   {
     try
     {
       this.leaveBillManager.disabledAllLeaveBills(this.lbs);
       addActionMessage(getText("leaveBill.disabled.success"));
       return "success";
     } catch (RuntimeException e) {
       addActionMessage(getText("leaveBill.disabled.error"));
     }return "error";
   }
 
   private String enabled()
   {
     try
     {
       this.leaveBillManager.enabledAllLeaveBills(this.lbs);
       addActionMessage(getText("leaveBill.enabled.success"));
       return "success";
     } catch (RuntimeException e) {
       e.printStackTrace();
       addActionMessage(getText("leaveBill.enabled.error"));
     }return "error";
   }
 
   public List<CodeValue> getAllStatus()
   {
     List codes = null;
     try {
       codes = new ArrayList();
       List one = this.codeValueManager.loadByKey("code", "020");
 
       if ((null != one) && (one.size() > 0))
       {
         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
 
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
 
   public List<CodeValue> getAllTypes()
   {
     List codes = null;
     try {
       codes = new ArrayList();
       List one = this.codeValueManager.loadByKey("code", "030");
 
       if ((null != one) && (one.size() > 0))
       {
         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
 
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
 
   public List<Department> getAllDepts()
   {
     List depts = this.departmentManager.loadAllDepartments();
     Department d = new Department();
     d.setId(Long.valueOf(-1L));
     d.setName(getText("select.option.all"));
     depts.add(0, d);
     return depts;
   }
 
   public List<LeaveBill> getLbs()
   {
     return this.lbs;
   }
 
   public void setLbs(List<LeaveBill> lbs)
   {
     this.lbs = lbs;
   }
 }

