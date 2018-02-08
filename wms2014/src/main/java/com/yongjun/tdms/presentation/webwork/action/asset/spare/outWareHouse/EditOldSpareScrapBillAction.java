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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillStatus;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
/**
 * <p>Title: EditSpareOutBillAction
 * <p>Description: 备件出库维护页面控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhang@yj-technology.com
 * @version $Id: EditSpareOutBillAction.java 9149 2007-12-09 06:29:38Z mwei $
 */
public class EditOldSpareScrapBillAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
	private SpareOutBill spareOutBill;
	private SpareInBill spareInBill;
	private User outPeople;
	private Department department;	
	private String pagingPage;
	
	private final SpareOutBillManager spareOutBillManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final EventNewManager eventNewManager;
	private final WarehouseManager warehouseManager;
	private final CodeValueManager codeValueManager;
	private final SpareInBillManager spareInBillManager;
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
    newwarehouseManager;
	
	public EditOldSpareScrapBillAction(SpareOutBillManager spareOutBillManager,
			DepartmentManager departmentManager,
			UserManager userManager,
			EventNewManager eventNewManager,
			WarehouseManager warehouseManager,
			CodeValueManager codeValueManager,
			SpareInBillManager spareInBillManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
		    newwarehouseManager){
		
		this.spareOutBillManager = spareOutBillManager;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.eventNewManager = eventNewManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager=codeValueManager;
		this.spareInBillManager=spareInBillManager;
		this.newwarehouseManager = newwarehouseManager;
	}

	public void prepare() throws Exception {
		//根据id获得备件出库单
		if (spareOutBill == null) {
			if (this.hasId("spareOutBill.id")) {			
				this.spareOutBill = this.spareOutBillManager.loadSpareOutBill(this.getId("spareOutBill.id"));				
			} else {
				this.spareOutBill = new SpareOutBill();
				//出库人默认为当前的“出库人"
				spareOutBill.setOutPeople(userManager.getUser());
				//默认出库状态为“新建”
				spareOutBill.setStatus(SpareOutBillStatus.NEWSTATUS);
				//默认出库单的仓库为用户管理的仓库
				User u = this.userManager.loadUser(this.userManager.getUser()
						.getId());
				Set<Warehouse> set = u.getWarehouses();
				if(null != set && set.size()>0){
					Warehouse[] house = set.toArray(new Warehouse[0]);
					int k = house.length;
					Warehouse w = house[k - 1];
					spareOutBill.setStorageGrade(w.getStorageGrade());
					spareOutBill.setWarehouse(w);
				}
			}
		}
	}

	public String save() throws Exception{
		if (this.isSubmitRecord()){
			return submitRecord();
		}
		
		//获得对应的出仓库
		if(!StringUtils.isEmpty(request.getParameter("warehouse.id"))){
			//通过选择的仓库的id得到用户选择的仓库
			Warehouse warehouseChoiced = this.warehouseManager.loadWarehouse(this.getId("warehouse.id"));
			//将所选仓库放入到入库单中
			this.spareOutBill.setWarehouse(warehouseChoiced);
		}
		
		//获得对应的入仓库
		if(!StringUtils.isEmpty(request.getParameter("inWarehouse.id")) && !"-1".equals(request.getParameter("inWarehouse.id"))){
			//通过选择的仓库的id得到用户选择的仓库
			Warehouse inWarehouseChoiced = this.warehouseManager.loadWarehouse(this.getId("inWarehouse.id"));
			//将所选仓库放入到入库单中
			this.spareOutBill.setInWarehouse(inWarehouseChoiced);
		}
		
		//获得出库人
		if (!StringUtils.isEmpty(request.getParameter("spareOut.outPeople.id"))) {
			this.outPeople = userManager.loadUser(this.getId("spareOut.outPeople.id"));
			//将出库人保存到出库单中
//			if(208 == this.getId("storageGrade.id")){
//				spareOutBill.setOutPeople(outPeople);
//			}else{
//				spareOutBill.setOutPeople(null);
//			}
			spareOutBill.setOutPeople(outPeople);
		
		}
		//获得部门
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			this.department = departmentManager.loadDepartment(this.getId("department.id"));
			//将部门保存到出库单中
			spareOutBill.setDepartment(department);
		}
		//获得仓库
		if(this.hasId("warehouse.id")){
			this.spareOutBill.setWarehouse(this.warehouseManager.loadWarehouse(this.getId("warehouse.id")));
		}
		//获取仓库级别
		if(this.hasId("storageGrade.id")){
			this.spareOutBill.setStorageGrade(this.codeValueManager.loadCodeValue(this.getId("storageGrade.id")));
		}
		//获得出库单的状态
         if(!StringUtils.isEmpty(request.getParameter("status"))){	
			if (request.getParameter("status").equals("NEWSTATUS")) {
				spareOutBill.setStatus(SpareOutBillStatus.NEWSTATUS);
			}
			if(request.getParameter("status").equals("OUTWAREHOUSEING")){
				spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEING);
			}
			if(request.getParameter("status").equals("OUTWAREHOUSEED")){
				spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEED);
			}
		}
		boolean isNew = this.spareOutBill.isNew();
		if(!isNew){
			if(209 == spareOutBill.getStorageGrade().getId()){
				spareOutBill.setDepartment(null);
				//spareOutBill.setOutPeople(null);
				spareOutBill.setBorrower(null);
				spareOutBill.setInWarehouse(null);
				//spareOutBill.setOutDate(null);
			}
			
		}
		
		this.spareOutBillManager.storeSpareOutBill(spareOutBill);
		//新建一张入库单
	    this.newSpareInBill(spareOutBill);
	 	//日志 (注意该端代码必须添加在store方法之后)
		String logContent ="";
		if(isNew){
			logContent = "被添加";
		}else{
			logContent = "被修改";
		}
		logContent = spareOutBill.getCode()+logContent;
//		this.getLogger().logStore(spareOutBill,logContent);
		
		if (isNew) {
			this.addActionMessage(this.getText("报废单" + spareOutBill.getCode() + "保存成功",
					Arrays.asList(new Object[] { spareOutBill.getCode() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("报废单" + spareOutBill.getCode() + "修改成功",
					Arrays.asList(new Object[] { spareOutBill.getCode() })));
			return SUCCESS;
		}
		
	}
	
	public void newSpareInBill(SpareOutBill spareOutBill)throws Exception{
	
		
		/**
		 * 若出库是一级库 所入库是二级库 则在生成一个二级库的入库单
		 * 2010-10-27 13:59:52 qcshen
		 */

		if(null!=spareOutBill.getInWarehouse()){
			//if(208==spareOutBill.getStorageGrade().getId()){				
				if (null == this.spareInBillManager.loadByKey("spareOutBill", spareOutBill.getId())){//新建					
					spareInBill=new SpareInBill();
				}else {//维护			
					spareInBill = this.spareInBillManager.loadByKey("spareOutBill",spareOutBill.getId()).get(0);
				}			
				//入库单名称
				spareInBill.setName(spareOutBill.getName());
				//仓库级别
				spareInBill.setStorageGrade(this.codeValueManager.loadCodeValue(209l));
				//来源仓库
				spareInBill.setOutWarehouse(spareOutBill.getWarehouse());
				//入仓库
				spareInBill.setWarehouse(spareOutBill.getInWarehouse());
				//入库日期
				spareInBill.setInDate(spareOutBill.getOutDate());
				
				spareInBill.setSpareOutBill(spareOutBill);
				this.spareInBillManager.storeSpareInBill(spareInBill);
				spareOutBill.setSpareInBill(spareInBill);
				this.spareOutBillManager.storeSpareOutBill(spareOutBill);
			//}
		}
	}
	
	
	
	private boolean isSubmitRecord() {
		if(this.hasKey("submitRecord")){
			if (!StringUtils.isEmpty(request.getParameter("submitRecord"))) {
				return true;
			}
		}
		return false;
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1050表示入库提交,1051表示出库的提交
	 * 2.Time
	 * 3.Status: 目前为0
	 * 4.OrgId: 目前为1
	 * 5.UserId
	 * 6.DocType:目前和EventType内容一致
	 * 7.DocId ：提交的多个报告ID,是字符串型
	 * 8.MoreInfo:一些相关信息
	 */
	public String submitRecord() {
		this.eventNewManager.storeEventNew_OutBill(1051,
				Calendar.getInstance().getTime(),
				0,1,this.userManager.getUser().getId(),1000,(spareOutBill.getId()).intValue(),"");
		return SUCCESS;
	}
	
	/**
	 * @function 获得所有仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){		
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
	/**
	 * 出到二级仓库 
	 * @return
	 */
	public List<Warehouse> getAllOutWarehouse(){
		List<Warehouse> list = new ArrayList<Warehouse>();
		List<Warehouse> list2 = new ArrayList<Warehouse>();
		try {
		//	list = this.warehouseManager.loadByKey("storageGrade", "209");
			String[] keys={"storageGrade","disabled"};
			String[] values={"209","0"};
			String[] values1={"208","0"};
			list = this.warehouseManager.loadByKeyArray(keys, values);
			list2 = this.warehouseManager.loadByKeyArray(keys, values1);
			list.add(list2.get(0));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		Warehouse ware = new Warehouse();
		ware.setId(-1L);
		ware.setName(this.getText(""));
		list.add(0, ware);
		return list;
	}
	/**
	 * @function 定义库区集合，以后仓库级联为其赋值
	 * @return
	 */
	public List<Regional> getAllRegional(){
		
		List<Regional> list = new ArrayList<Regional>();
		Regional reg = new Regional();
		reg.setId(-1L);
		reg.setName(this.getText(""));
		list.add(0, reg);
		return list;
	}
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade(){
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}
	
	
	public SpareOutBill getSpareOutBill() {
		return spareOutBill;
	}
	
	public void setSpareOutBill(SpareOutBill spareOutBill) {
		this.spareOutBill = spareOutBill;
	}
	
	public List<Department> getDepartments(){
		List<Department> list = departmentManager.loadAllDepartments();
		return list;
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	/**
	 * 获得登陆用户的仓库
	 * @return
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	public List<Warehouse> getUserWarehouse(){
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		Long userId=userManager.getUser().getId();
		User user=userManager.loadUser(userId);
		Set<Warehouse> set=user.getWarehouses();
		List<Warehouse> list = new ArrayList<Warehouse>();
		list.addAll(set);
		for(Warehouse warehouse:list){
			if(!(warehouse.getDisabled())){
				wareList.add(warehouse);
			}
		}
 
		return wareList;
	}

	public String getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(String pagingPage) {
		this.pagingPage = pagingPage;
	}
}
