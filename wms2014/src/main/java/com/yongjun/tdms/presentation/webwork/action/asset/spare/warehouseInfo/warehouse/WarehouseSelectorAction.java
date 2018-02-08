/**
 * 
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.warehouseInfo.warehouse;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.PrepareAction;

/**
 *<p>WarehouseSelectorAction.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author hjzhang@yj-technology.com
 * @version 
 */
public class WarehouseSelectorAction extends PrepareAction {
    private static final long serialVersionUID = 1L;   
	private final UserManager userManager;
    private final WarehouseManager warehouseManager;
    private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager newwarehouseManager;
    private List joinableWarehouses;
    private List<Warehouse> userbleWarehouses;
    private User user;
    private Warehouse warehouse;
    private Set<User> set=new HashSet<User>();
    public WarehouseSelectorAction(UserManager userManager,WarehouseManager warehouseManager,com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager newwarehouseManager){
    	this.userManager = userManager;
    	this.warehouseManager=warehouseManager;
    	this.newwarehouseManager = newwarehouseManager;
    }
    
    public void prepare() throws Exception {
    	long sum=0L;
		if(this.user==null){
	    	if(this.hasId("userId")){
	    		this.user = userManager.loadUser(this.getId("userId"));
	    		userbleWarehouses= newwarehouseManager.loadByUser(user.getId());
	    		String wids ="";
	    		for(Warehouse i:userbleWarehouses){
	    			if(wids.equals("")){
	    				wids=i.getId()+"";
	    			}else{
	    				wids+=","+i.getId();
	    			}
	    		}
	    		
//                user.setWarehouses(ableRoleWarehouses);
	    		joinableWarehouses=this.newwarehouseManager.loadByAber(wids);
	    	}
		}
		
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	

	//获得所有仓库(除去用户拥有的)
	public List getAllWarehouse(){
		List<Warehouse> valid = warehouseManager.loadAllValidWarehouse();
		for(Warehouse wa :valid){
			for(Warehouse wa1 :userbleWarehouses){
				if(wa.getId() == wa1.getId()){
					valid.remove(wa);
				}
			}
		}
		
		return valid;
	}

	public String save(){
		return SUCCESS;
	}

	//添加权限仓库
	public String addWarehouse(){
		String ids = request.getParameter("ids");
		String id[] = null;
		id = ids.split(",");
		for(int i=0;i<id.length;i++){
			warehouse= warehouseManager.loadWarehouse(Long.valueOf(id[i]));
			set=warehouse.getUsers();
			set.add(user);
			warehouse.setUsers(set);
			warehouseManager.storeWarehouse(warehouse);
			
		}
		return SUCCESS;
	}
	
	//移除权限仓库
	public String quitWarehouse(){
		String ids = request.getParameter("ids");
		String id[] = null;
		id = ids.split(",");
		for(int i=0;i<id.length;i++){
			warehouse= warehouseManager.loadWarehouse(Long.valueOf(id[i]));
			set=warehouse.getUsers();
			set.remove(user);
			warehouse.setUsers(set);
			warehouseManager.storeWarehouse(warehouse);
		}		
		return SUCCESS;
	}
	
	public User getUser() {
    	return user;
    }

	public void setUser(User user) {
    	this.user = user;
    }


	public List getJoinableWarehouses() {
		return joinableWarehouses;
	}

	public void setJoinableWarehouses(List joinableWarehouses) {
		this.joinableWarehouses = joinableWarehouses;
	}

	public List getUserbleWarehouses() {
		return userbleWarehouses;
	}

	public void setUserbleWarehouses(List userbleWarehouses) {
		this.userbleWarehouses = userbleWarehouses;
	}
	

}
