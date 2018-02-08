/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.outWareHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillStatus;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
/**
 * <p>Title: ListSpareOutBillAction
 * <p>Description: 备件出库列表页面控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhang@yj-technology.com
 * @version $Id: ListSpareOutBillAction 9149 2007-12-09 06:29:38Z mwei $
 */
public class ListOldSpareScrapBillAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private List<SpareOutBill> spareOutBills;
	private final SpareOutBillManager spareOutBillManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final WarehouseManager warehouseManager;
	private final CodeValueManager codeValueManager;
	private String pagingPage;
	 private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
	    newwarehouseManager;
    /**
     * 显示页面列表的总金额
     */
	private Double showOutTotalPrice = 0.0;
	
	//	登陆用户所属权限仓库ID值集合
	private List<Long>  warehouseIds = new ArrayList<Long>();
	
	public ListOldSpareScrapBillAction(SpareOutBillManager spareOutBillManager,
	                              DepartmentManager departmentManager,
	                              UserManager userManager,
	                              WarehouseManager warehouseManager,
	                              CodeValueManager codeValueManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
	                      	    newwarehouseManager){
		this.spareOutBillManager = spareOutBillManager;
		this.departmentManager = departmentManager;
		this.userManager=userManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager=codeValueManager;
		this.newwarehouseManager = newwarehouseManager;
		
	}

	/**
	 * 根据页面传来的出库单Id集合，获取出库单对象集合
	 */
	public void prepare() throws Exception {
		
		
	 
		
		if(this.hasIds("spareOutBillIds")){
			spareOutBills = spareOutBillManager.loadAllSpareOutBill(this.getIds("spareOutBillIds"));
		}
		
		//加默认权限仓库，解决权限仓库空值sql出错问题
		warehouseIds.add(-1L);
		//获取登陆用户所属权限仓库ID值集合
		if(null != getAllWarehouse()){
			for(Warehouse warehouse:getAllWarehouse()){
				warehouseIds.add(warehouse.getId());
			}
		}
		setFirst(false);
	}

	public String execute() throws Exception {
		if (this.isDelete()) {
			return delete();
		}
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	/**
	 * 失效选中出库单的集合
	 * @return
	 */
	private String disabled(){
		try{
			spareOutBillManager.disabledAllSpareOutBill(spareOutBills);
			this.addActionMessage(this.getText("spareOutBills.disabled.success"));	
		}catch(Exception e){
			this.addActionError(this.getText("spareOutBills.disabled.farile"));
		}
	
		return SUCCESS;
	}
	/**
	 * 有效选中出库单的集合
	 * @return
	 */
	private String enabled(){
		spareOutBillManager.enabledAllSpareOutBill(spareOutBills);
		this.addActionMessage(this.getText("spareOutBills.enabled.success"));
		return SUCCESS;
	}
	

	/**
	 * 删除选中出库单的集合
	 * @return
	 */
	private String delete(){
		try {
			spareOutBillManager.deleteAllSpareOutBill(spareOutBills);
			this.addActionMessage(this.getText("spareOutBills.delete.success"));
		}catch(Exception e){
			this.addActionError(this.getText("spareOutBills.delete.farile"));
		}
		return SUCCESS;
	}
	public List<LabelValue> getOutStatus() {  //获得备件领用单状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(SpareOutBillStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	@SuppressWarnings("unchecked")
	public List<Department> getDepartments(){
		List<Department> list = departmentManager.createSelectDepartments(this.getText("select.option.all"));
		return list;
	}
	
	/**
	 * @function 获得所有仓库
	 * @return
	 */
	
//	获得所有仓库的集合
	public List<Warehouse> getAllWarehouse() {
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		User user = this.userManager.getUser();
		List<Warehouse> allWa = null;
		try {
			allWa = newwarehouseManager.loadByUser(user.getId());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Warehouse  ws :allWa){
			if(ws.getOldWarehouse()!=null&&ws.getOldWarehouse().equals("Y")){
				wareList.add(ws);
			}
			
		}
		return wareList;
	}
	
	//	获得所有二级库进仓库
	public List<Warehouse> getAllInWarehouse(){
		List<Warehouse> list = new ArrayList<Warehouse>();
		List<Warehouse> list1 = new ArrayList<Warehouse>();
		try {
			String[] keys={"storageGrade","disabled"};
			String[] values={"209","0"};
			String[] values1={"208","0"};
			list = this.warehouseManager.loadByKeyArray(keys, values);
			list1=this.warehouseManager.loadByKeyArray(keys, values1);
			if(list1.size()!=0){
			list.add(list1.get(0));
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		Warehouse ware = new Warehouse();
		ware.setId(-1L);
		ware.setName(this.getText("select.option.all"));
		list.add(0, ware);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (null != warehouseIds) {
			map.put("warehouseIds", warehouseIds);
		}
		String  instatus = (String)map.get("outStatus");
		if(instatus!=null&&instatus.equals("-1")){
			map.remove("outStatus");
		}
		String department = (String) map.get("spareOutBill.department");
		if(department!=null&&department.equals("-1")){
			map.remove("spareOutBill.department");
		}
		return map;
	}
	
	protected String getAdapterName() {
		showOutTotalPrice=this.sumOutTotalPrice();
		return "oldSpareScrapBills";
	}
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List<CodeValue> getAllStorageGrade(){
		List<CodeValue> list=this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
		CodeValue c=new CodeValue();
		c.setId(-1L);
		c.setValue(this.getText("select.option.all"));
		list.add(0,c);
		return list;
	}
	/**
	 * 在页面显示总金额
	 * @return
	 */
	public Double sumOutTotalPrice(){
		Double allTtotlePrice = null;
		String spareOutBillNO="";
		String spareOutBillName="";
		String department="";
		String borrowerPeople="";
		String outPeople="";
		String outDate_strt="";
		String outDate_end="";
		String storageGrade="";
		String outWarehouse="";
		String inWareHouse="";
		String onlyInvalid = "";
		String onlyValid = "";
		String status = "";
		if (!StringUtils.isEmpty(request.getParameter("code"))) {
			spareOutBillNO = request.getParameter("code");
		}
		if (!StringUtils.isEmpty(request.getParameter("name"))) {
			spareOutBillName = request.getParameter("name");
		}
		if (!StringUtils.isEmpty(request.getParameter("spareOutBill.department"))) {
			department = request.getParameter("spareOutBill.department");
		}
		if (!StringUtils.isEmpty(request.getParameter("borrower"))) {
			borrowerPeople = request.getParameter("borrower");
		}
		if (!StringUtils.isEmpty(request.getParameter("outPeople"))) {
			outPeople = request.getParameter("outPeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("outDate_start"))) {
			outDate_strt = request.getParameter("outDate_start");
		}
		if (!StringUtils.isEmpty(request.getParameter("outDate_end"))) {
			outDate_end = request.getParameter("outDate_end");
		}
		if (!StringUtils.isEmpty(request.getParameter("spareOutBill.storageGrade"))) {
			storageGrade = request.getParameter("spareOutBill.storageGrade");
		}
		if (!StringUtils.isEmpty(request.getParameter("spareOutBill.warehouse"))) {
			outWarehouse = request.getParameter("spareOutBill.warehouse");
		}
		if (!StringUtils.isEmpty(request.getParameter("spareOutBill.inWarehouse"))) {
			inWareHouse = request.getParameter("spareOutBill.inWarehouse");
		}
		if (!StringUtils.isEmpty(request.getParameter("onlyValid"))) {
			onlyValid = request.getParameter("onlyValid");
		}
		if (!StringUtils.isEmpty(request.getParameter("onlyInvalid"))) {
			onlyInvalid = request.getParameter("onlyInvalid");
		}
		if (!StringUtils.isEmpty(request.getParameter("outStatus"))) {
			status = request.getParameter("outStatus");
		}
		
		String yiId ="";
	    String erId="";
	    String allId="";
	    //重新加载一下User
	    Set<Warehouse> set = this.userManager.loadUser(this.getLoginUser().getId()).getWarehouses();
	    int size = set.size();
	    if(size>0){
	    	 for(Warehouse w : set){
	 	    	Long wId =  w.getStorageGrade().getId();
	 	    	if(208L == wId){
	 	    		yiId = yiId +w.getId().toString()+",";
	 	    	}
	 	    	if(209L == wId){
	 	    		erId = erId+w.getId().toString()+",";
	 	    	}
	 	    	allId = allId+w.getId().toString()+",";
	 	     }
	    	 
	 	     if(erId.length()>0){
	 	    	 erId =erId.substring(0,erId.length()-1);
	 	     }
	 	     if(yiId.length()>0){
	 	    	  yiId =yiId.substring(0,yiId.length()-1);
	 	     }
	 	     if(allId.length()>0){
	 	    	 allId =allId.substring(0,allId.length()-1);
	 	     }
	    	 
	    	 if("208".equals(storageGrade)){
	 			outWarehouse = yiId;
	 		 }else if("209".equals(storageGrade)){
	 			outWarehouse = erId;
	 		 }else{
	 			outWarehouse = allId;
	 		 }
	 		 if(!"".equals(outWarehouse)){
	 			 String[] param = {spareOutBillNO,spareOutBillName,
	 	 				department,borrowerPeople,
	 	 				outPeople,outDate_strt,
	 	 				outDate_end,storageGrade,
	 	 				outWarehouse,inWareHouse,
	 	 				onlyInvalid,onlyValid,status};
	 	 		
	 	 		allTtotlePrice = this.spareOutBillManager.showOldScrapTotalPrice(param);
	 	 		if(null == allTtotlePrice){
		 	    	allTtotlePrice=0.00;
		 	    }
	 	 		
	 		 }else{
	 			allTtotlePrice=0.00;
	 		 }
	 		
	 	    
	    	 
	     }else{
	    	 allTtotlePrice=0.00;
	     }
	    
		return allTtotlePrice;
	}
	
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	public List<SpareOutBill> getSpareOutBills() {
		return spareOutBills;
	}
	public void setSpareOutBills(List<SpareOutBill> spareOutBills) {
		this.spareOutBills = spareOutBills;
	}
	public Double getShowOutTotalPrice() {
		return showOutTotalPrice;
	}
	public void setShowOutTotalPrice(Double showOutTotalPrice) {
		this.showOutTotalPrice = showOutTotalPrice;
	}

	public String getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(String pagingPage) {
		this.pagingPage = pagingPage;
	}

}
