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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inWareHouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillStatus;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * <p>Title:EditSpareInBillAction
 * <p>Description:入库单维护页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class EditSpareInBillAction extends PrepareAction {
	private static final long serialVersionUID = 3855277349628759547L;

	private SpareInBill spareInBill;

	private Supplier supplier;

	private User user;

	private final SpareInBillManager spareInBillManager;

	private final SupplierManager supplierManager;

	private final UserManager userManager;

	private final EventNewManager eventNewManager;

	private final WarehouseManager newwarehouseManager;

	private final CodeValueManager codeValueManager;

	public EditSpareInBillAction(SpareInBillManager spareInBillManager,
			SupplierManager supplierManager, UserManager userManager,
			EventNewManager eventNewManager, WarehouseManager warehouseManager,
			CodeValueManager codeValueManager) {
		this.spareInBillManager = spareInBillManager;
		this.supplierManager = supplierManager;
		this.userManager = userManager;
		this.eventNewManager = eventNewManager;
		this.newwarehouseManager = warehouseManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (null == spareInBill) {
			//判断spareInBill值是新建的还是从页面传过来的
			if (this.hasId("spareInBill.id")) {
				spareInBill = spareInBillManager.loadSpareInBill(this
						.getId("spareInBill.id"));
			} else {
				spareInBill = new SpareInBill();
				//初始化入库人为当前登陆用户
				spareInBill.setInPeople(userManager.getUser());
				//初始化验收人为当前登陆用户
				spareInBill.setCheckPeople(userManager.getUser());
				spareInBill.setStatus(SpareInBillStatus.NEWSTATUS);
				//默认出库单的仓库为用户管理的仓库

				//Set<Warehouse> set= this.userManager.getUser().getWarehouses();

				User u = this.userManager.loadUser(this.userManager.getUser()
						.getId());
				Set<Warehouse> set = u.getWarehouses();
				if (null != set && set.size() > 0) {
					Warehouse[] house = set.toArray(new Warehouse[0]);
					int k = house.length;
					Warehouse w = house[k - 1];
					spareInBill.setStorageGrade(w.getStorageGrade());
					spareInBill.setWarehouse(w);
				}

			}
		}
	}

	public String execute() throws Exception {

		if (!StringUtils.isEmpty(request.getParameter("saveContinue"))
				&& "RENEW".equals(request.getParameter("saveContinue"))) {
			spareInBill = saveContinue(spareInBill);
		}
		return SUCCESS;
	}

	/**
	 * 保存入库单spareInBill对象,并提示是否成功消息
	 * 
	 * @param 
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {
		if (this.isSubmitRecord()) {
			return submitRecord();
		}

		Long id = this.getId("storageGrade.id");
		if (null != spareInBill && null != spareInBill.getStorageGrade()
				&& !spareInBill.getStorageGrade().getId().equals(id)) {
			if (id == 208) {//如果改为以及库存 则没有来源仓库的值
				spareInBill.setOutWarehouse(null);
				if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {
					this.supplier = supplierManager.loadSupplier(this
							.getId("supplier.id"));
					spareInBill.setSupplier(supplier);
				}
			}

			if (id == 209) {
				spareInBill.setSupplier(null);
				//					获得出仓库
				if (!StringUtils.isEmpty(request
						.getParameter("outWarehouse.id"))) {
					spareInBill.setOutWarehouse(this.newwarehouseManager
							.loadWarehouse(this.getId("outWarehouse.id")));

				}

			}

			spareInBill
					.setStorageGrade(this.codeValueManager.loadCodeValue(id));

		} else {

			//			获得仓库级别	
			if (!StringUtils.isEmpty(request.getParameter("storageGrade.id"))) {
				spareInBill.setStorageGrade(this.codeValueManager
						.loadCodeValue(this.getId("storageGrade.id")));

			}
			//			获得出仓库
			if (!StringUtils.isEmpty(request.getParameter("outWarehouse.id"))) {
				spareInBill.setOutWarehouse(this.newwarehouseManager
						.loadWarehouse(this.getId("outWarehouse.id")));

			}
			//			获得对应的供应商
			if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {
				this.supplier = supplierManager.loadSupplier(this
						.getId("supplier.id"));
				spareInBill.setSupplier(supplier);
			}

		}

		//获得对应的入仓库
		if (!StringUtils.isEmpty(request.getParameter("warehouse.id"))) {
			//通过id得到用户选择的仓库
			Warehouse warehouseChoiced = this.newwarehouseManager
					.loadWarehouse(this.getId("warehouse.id"));
			//将所选仓库放入到入库单中
			this.spareInBill.setWarehouse(warehouseChoiced);
		}

		//获得对应的入库人
		if (!StringUtils.isEmpty(request.getParameter("spareIn.inPeople.id"))) {
			this.user = userManager.loadUser(this.getId("spareIn.inPeople.id"));
			spareInBill.setInPeople(user);
		}
		//获得对应的验收人
		if (!StringUtils
				.isEmpty(request.getParameter("spareIn.checkPeople.id"))) {
			this.user = userManager.loadUser(this
					.getId("spareIn.checkPeople.id"));
			spareInBill.setCheckPeople(user);
			//spareInBill.setYanShouPeople(user.getName());						//验收人的冗余字段，为打印报表所用
		} else {
			spareInBill.setCheckPeople(null);
			//spareInBill.setYanShouPeople(null);									//验收人的冗余字段，为打印报表所用
		}
		//		获得出库单的状态
		if (!StringUtils.isEmpty(request.getParameter("status"))) {
			if (request.getParameter("status").equals("NEWSTATUS")) {
				spareInBill.setStatus(SpareInBillStatus.NEWSTATUS);
			}
			if (request.getParameter("status").equals("INWAREHOUSEING")) {
				spareInBill.setStatus(SpareInBillStatus.INWAREHOUSEING);
			}
			if (request.getParameter("status").equals("INWAREHOUSEED")) {
				spareInBill.setStatus(SpareInBillStatus.INWAREHOUSEED);
			}
		}
		boolean isNew = this.spareInBill.isNew();
		//		this.spareInBill.setSubmit(true);//默认情况下不发出去提醒

		this.spareInBillManager.storeSpareInBill(spareInBill);

		String logContent = "";
		if (isNew) {
			logContent = "被添加";
		} else {
			logContent = "被修改";
		}
		logContent = spareInBill.getCode() + logContent;
		//this.getLogger().logStore(spareInBill,logContent);

		if (isNew) {
			this.addActionMessage(this.getText("spareInBill.add.success",
					Arrays.asList(new Object[] { spareInBill.getCode() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareInBill.edit.success",
					Arrays.asList(new Object[] { spareInBill.getCode() })));
			return SUCCESS;
		}
	}

	/**
	 * 	继续新建 
	 * @param spareInBill
	 * @return
	 */
	private SpareInBill saveContinue(SpareInBill spareInBill) {
		SpareInBill tempSpareInBill = new SpareInBill();
		
		//初始化入库人为当前登陆用户
		tempSpareInBill.setInPeople(spareInBill.getInPeople());
		//初始化验收人为当前登陆用户
		tempSpareInBill.setCheckPeople(spareInBill.getCheckPeople());
		tempSpareInBill.setStatus(SpareInBillStatus.NEWSTATUS);
		tempSpareInBill.setName(spareInBill.getName());
		
		CodeValue  strageGrade = spareInBill.getWarehouse().getStorageGrade();
		if(208l==strageGrade.getId()){
			tempSpareInBill.setSupplier(spareInBill.getSupplier());
			tempSpareInBill.setStorageGrade(strageGrade);
			tempSpareInBill.setWarehouse(spareInBill.getWarehouse());
		}else if(209l==strageGrade.getId()){
			tempSpareInBill.setStorageGrade(strageGrade);
			tempSpareInBill.setWarehouse(spareInBill.getWarehouse());
			tempSpareInBill.setOutWarehouse(spareInBill.getOutWarehouse());
			 
		}
		
		tempSpareInBill.setInDate(spareInBill.getInDate());
		tempSpareInBill.setTotalPrice(0.0);
		

		return tempSpareInBill;
	}

	private boolean isSubmitRecord() {
		if (this.hasKey("submitRecord")) {
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
		this.eventNewManager.storeEventNew_InBill(1050, Calendar.getInstance()
				.getTime(), 0, 1, this.userManager.getUser().getId(), 1000,
				(spareInBill.getId()).intValue(), "");
		return SUCCESS;
	}

	/**
	 * @function 获得所有有效仓库
	 * 
	 * 获得所有的仓库，然后依次判断仓库的Disabled是否是false，如果是表示该仓库为有效仓库，就加入wareList中。
	 * 
	 * 将用户的所属仓库总系统中清除，故从将用户的所属仓库从用户的下拉列表中去除。
	 * 通过session中的User获得user的Id，在通过User从数据库中从新查询一次，以保证数据的实时更新。
	 * hjia 2010-5-21
	 * 
	 * @return
	 */
	public List<Warehouse> getAllValidWarehouse() {
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		wareList = this.newwarehouseManager.loadAllWarehouse();
		Warehouse w = new Warehouse();
		w.setId(-1l);
		w.setName("所有");
		wareList.add(w);

		//		Warehouse ware = this.userManager.getUser().getWarehouse();
		//		if(null != ware){
		//			Warehouse w = this.warehouseManager.loadWarehouse(ware.getId());
		//		    if(w.getDisabled() == false){
		//		    	wareList.add(w);
		//		    }
		//		}

		//获得用户的权限仓库，权限仓库就是用户的所有可见仓库
		List<Warehouse> list = new ArrayList<Warehouse>();
		//从数据库中得到user，保证实施更新
		Long userId = this.userManager.getUser().getId();
		User user = userManager.loadUser(userId);
		list.addAll(user.getWarehouses());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				//				System.out.println(i + ": " + list.get(i).getDisabled());
				if (list.get(i).getDisabled() == false) {
					wareList.add(list.get(i));
				}
			}

			//			wareList.addAll(list);
		}
		//		System.out.println(wareList.size());
		return wareList;
	}

	/**
	 * 入仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse() {
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		//wareList = this.warehouseManager.loadAllWarehouse();
		//		Warehouse w = new Warehouse();
		//		w.setId(-1l);
		//		w.setName("所有");
		//		wareList.add(0,w);
//		try{wareList = this.warehouseManager.loadByKey("storageGrade", "209");
//		
//		}catch(Exception e){
//			
//			e.printStackTrace();
//		}
		
		return wareList;
	}

	/**
	 * 出仓库 级别为一级库
	 * @return
	 */
	public List<Warehouse> getAllOutWarehouse() {
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		try {
			//wareList = this.warehouseManager.loadByKey("storageGrade", "208");
//			wareList=this.newwarehouseManager.loadAllWarehouse();
			String[] keys={"disabled","oldWarehouse"};
			String[] values={"0","N"};
			wareList = this.newwarehouseManager.loadByKeyArray(keys, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wareList;
	}

	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade() {
		return this.codeValueManager
				.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}

	/**
	 * @function 定义库区集合，以后仓库级联为其赋值
	 * @return
	 */
	public List<Regional> getAllRegional() {

		List<Regional> list = new ArrayList<Regional>();
		return list;
	}

	public SpareInBill getSpareInBill() {
		return spareInBill;
	}

	public void setSpareInBill(SpareInBill spareInBill) {
		this.spareInBill = spareInBill;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
