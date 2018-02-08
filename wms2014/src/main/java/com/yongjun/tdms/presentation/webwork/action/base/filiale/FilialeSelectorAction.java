/**
 * 
 */
package com.yongjun.tdms.presentation.webwork.action.base.filiale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 *<p>Title:FilialeSelectorAction.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
public class FilialeSelectorAction extends PrepareAction {
    private static final long serialVersionUID = 1L;
    
	protected final UserManager userManager;
    protected final DepartmentManager departmentManager;
    private final FilialeManager filialeManager;
    private User user;
    public FilialeSelectorAction(UserManager userManager,DepartmentManager departmentManager,FilialeManager filialeManager){
    	this.userManager = userManager;
    	this.departmentManager = departmentManager;
    	this.filialeManager = filialeManager;
    }
    
	public void prepare() throws Exception {
		if(this.user==null){
	    	if(this.hasId("userId")){
	    		this.user = userManager.loadUser(this.getId("userId"));
	    	}
		}
		
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	//加入组织区域时调用的
	//主要是获得所有分公司
	public List getAllFiliale(){
		List<Filiale> filiales =filialeManager.loadAllFiliale();
		return filiales;
	}
	
	//获得所有分公司下的所有部门
	public List getAllDepartment(){
		List<Filiale> filiales =filialeManager.loadAllFiliale();;
		List<Department> depts = new ArrayList<Department>();
		for(Filiale filiale:filiales){
			List<Department> dept = this.departmentManager.loadDepartmentByFiliale(filiale.getId());
			//System.out.println("length="+dept.size());
			depts.addAll(dept);
		}
		depts.remove(user.getDepartment());
		depts.removeAll(user.getDepartments());
		//System.out.println(depts.size());
		return depts;
	}
	
	//获得所有分公司的长度
	public int getCount(){
		List<Filiale> filiales =filialeManager.loadAllFiliale();
		return filiales.size();
	}
	
	//获得该用户下的所有部门
	public List<Department> getDepartments(){
		List<Department> list = new ArrayList<Department>();
		list.addAll(this.user.getDepartments());
		return list ;
	}
	
	//退出组织区域时，根据部门获得分公司
	public List getFiliales(){
		List<Filiale> filiales = new ArrayList<Filiale>();
		List<Department> depts = this.getDepartments();
		for(Department dept : depts){
			Filiale filiale = dept.getFiliale();
			filiales.add(filiale);
		}
		
		//将重复的分公司放入Set集合，去除重复值
		Set<Filiale> sets = new HashSet<Filiale>();
		sets.addAll(filiales);
		//再将set集合转换成list集合
		List<Filiale> lists = new ArrayList<Filiale>();
		lists.addAll(sets);
		return lists;
	}
	
	public User getUser() {
    	return user;
    }

	public void setUser(User user) {
    	this.user = user;
    }

	public String save(){
		if(this.isAddFilAndDept()){
			addFilialeAndDept();
		}
		
		if(this.isQuitFilAndDept()){
			quitFilialeAndDept();
		}
		return SUCCESS;
	}
	
	public boolean isAddFilAndDept(){
		return this.hasKey("join");
	}
	
	public boolean isQuitFilAndDept(){
		return this.hasKey("leave");
	}

	public String addFilialeAndDept(){
		String ids = request.getParameter("ids");
		String id[] = null;
		id = ids.split(",");
		for(int i=0;i<id.length;i++){
			Department dept = departmentManager.loadDepartment(Long.valueOf(id[i]));
			dept.addUser(user);
			departmentManager.storeDepartment(dept);
		}
		return SUCCESS;
	}
	
	public String quitFilialeAndDept(){
		String ids = request.getParameter("ids");
		String id[] = null;
		id = ids.split(",");
		List<Department> depts = new ArrayList<Department>();
		for(int i=0;i<id.length;i++){
			Department dept = departmentManager.loadDepartment(Long.valueOf(id[i]));
			depts.add(dept);
		}
		for (Iterator i$ = depts.iterator(); i$.hasNext(); ) {
			Department dept = (Department)i$.next();
	        dept.removeUser(user);
	        this.departmentManager.storeDepartment(dept);
		}
		return SUCCESS;
	}
	
}
