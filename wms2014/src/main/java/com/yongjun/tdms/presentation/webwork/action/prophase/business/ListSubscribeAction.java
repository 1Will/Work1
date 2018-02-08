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
package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Role;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeTypeStatus;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * @author qs
 * @version $Id: ListSubscribeAction.java 11279 2008-03-12 01:12:13Z mwei $
 */
@SuppressWarnings("serial")
public class ListSubscribeAction extends ValueListAction {
	private final SubscribeManager subscribeManager;
	private final DepartmentManager departmentManager;
	private final SubscribeCollectBillManager subscribeCollectBillManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private SubscribeCollectBill subscribeCollectBill;
    private String toolingDevFlag;//工装和设备的标识
	private List<Subscribe> subscribes;
	
	
	public ListSubscribeAction(SubscribeManager subscribeManager,
			DepartmentManager departmentManager,
			SubscribeCollectBillManager subscribeCollectBillManager,
			UserManager userManager,
			CodeValueManager codeValueManager) {
		this.subscribeManager = subscribeManager;
		this.departmentManager=departmentManager;
		this.subscribeCollectBillManager = subscribeCollectBillManager;
		this.userManager=userManager;
		this.codeValueManager = codeValueManager;
	}
	public void prepare(){
		if (this.subscribes == null && this.hasIds("subscribeIds")) {
            this.subscribes = this.subscribeManager.loadAllSubscribes(this.getIds("subscribeIds"));
        }
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag=request.getParameter("toolingDevFlag");
		} 
	}
	
	public String execute() throws Exception{
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}
		if(this.isDelete()){
			return this.delete();
		}
		if(this.isSaveSubscribeCollectBill()){	//判断是否汇总申购单
			return this.saveSubscribeCollectBill();
		}
		return SUCCESS;
	}

	private String delete(){
		this.subscribeManager.deleteAllSubscribes(subscribes);
		this.addActionMessage(this.getText("subscribes.delete.success"));
		return SUCCESS;
	}
	
	/**
	 * 申购单查询添加只显示本部门的条件
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		//是否可以查看其他部门
//		if(!this.getLoginUser().isViewAll()){
//			 map.put("department.id",this.getLoginUser().getDepartment().getId());
//		}
		if(!this.getLoginUser().isViewAll()){
			if (!this.hasId("department.id")){
				map.put("department.id",this.getLoginUser().getDepartment().getId());
			}
		}
	  return map;
	}
	
	
	
	
	public String disabled() {
	   try{
		   this.subscribeManager.disabledAllSubscribes(subscribes); 
	     }catch(Exception e){
	    	 if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
	    		 this.addActionMessage(this.getText("subscribes.disabled.failer"));
					return ERROR; 
	    	 }else{
	    		 this.addActionMessage(this.getText("toolingsubscribes.disabled.failer"));
					return ERROR; 
	    	 }
	   }	
	     if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
	    	 this.addActionMessage(this.getText("subscribes.disabled.success"));
	 		return SUCCESS; 
	     }else{
	    	 this.addActionMessage(this.getText("toolingpurchase.disabled.success"));
		 		return SUCCESS;  
	     }
		
	}

	public String enabled() {
		
		this.subscribeManager.enabledAllSubscribes(subscribes);
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			this.addActionMessage(this.getText("subscribes.enabled.success"));	
		}else{
			this.addActionMessage(this.getText("purchaseBill.enabled.success"));	
		}	
		return SUCCESS;
	}

	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	@Override
	protected String getAdapterName() {
//		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
//			return "deviceSubscribes";
//			
//		}else{
//			return "toolingSubscribes";
//		}
		return "subscribes";
	}
	
	
	/**
	 * 判断页面传来的subscribeCollectBill变量是否有值，且值是否等于subscribeCollectBills
	 * 
	 * @return boolean true 汇总申购单 | false 不汇总
	 */ 
	private boolean isSaveSubscribeCollectBill() {
		if (!StringUtils.isEmpty(request.getParameter("subscribeCollectBill"))) {
			if (request.getParameter("subscribeCollectBill").equals("subscribeCollectBills"))
				return true;
		}
		return false;
	}
	
	/**
	 * 汇总申购单
	 * @return
	 */
	public String saveSubscribeCollectBill() throws Exception{
		 
		if(subscribes != null){
			//汇总日期、状态在实例化实体时已默认
			subscribeCollectBill = new SubscribeCollectBill();
			subscribeCollectBill.setCode(subscribeCollectBillManager.generateSubscribeCollectBillCode());	//汇总单编码
			Date time = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			subscribeCollectBill.setName(df.format(time)+"汇总单");		//汇总单名称
			subscribeCollectBill.setCollectDept(this.userManager.getUser().getDepartment()); //汇总部门
			subscribeCollectBill.setCollectPerson(userManager.getUser());	//汇总人
			subscribeCollectBillManager.storeSubscribeCollectBill(subscribeCollectBill);
			
			for(Subscribe subscribe:subscribes){
				//判断是否为采购中状态
				if(subscribe.getStatus().toString().equals("PURCHASING")){
					for(SubscribeDetail subscribeDetail:subscribe.getSubscribeDetails()){
						//判断是否有新建状态的明细
						if(subscribeDetail.getStatus().toString().equals("NEW")){
							subscribeDetail.setStatus(SubscribeDetailStatus.COLLECTED);
							subscribeDetail.setSubscribeCollectBill(subscribeCollectBill);
							subscribeDetail.setDepartment(subscribe.getDepartment().getName());//部门
							this.subscribeManager.storeSubscribeDetail(subscribeDetail);
							// 添加 事件 zzb
							this.subscribeManager.storeEventNew_subDetail(1062, subscribeDetail.getId().intValue(),"汇总");
						}
						
					}
					subscribe.setStatus(SubscribeStatus.PURCHASING);
					subscribe.setRemark(1);
					subscribeManager.storeSubscribe(subscribe);
				}
				//判断是否为新建状态的申购单
				if(subscribe.getStatus().toString().equals("NEWPURCHASE")||subscribe.getStatus().toString().equals("AUDITING")){
					for(SubscribeDetail subscribeDetail:subscribe.getSubscribeDetails()){
						subscribeDetail.setStatus(SubscribeDetailStatus.COLLECTED);
						subscribeDetail.setSubscribeCollectBill(subscribeCollectBill);
						subscribeDetail.setDepartment(subscribe.getDepartment().getName());//部门
						this.subscribeManager.storeSubscribeDetail(subscribeDetail);
						// 添加 事件 zzb
						this.subscribeManager.storeEventNew_subDetail(1062, subscribeDetail.getId().intValue(),"汇总");
						 
					}
					subscribe.setStatus(SubscribeStatus.SUMMARYED);
					subscribe.setRemark(1);
					subscribeManager.storeSubscribe(subscribe);
				}
			}
			this.addActionMessage(this.getText("subscribes.collectBill.success"));
		}
//		subscribeCollectBill = this.subscribeCollectBillManager.loadSubscribeCollectBill(subscribeCollectBill.getId());
//		subscribeCollectBill.setSumDetail(i);
//		subscribeCollectBill.setPurNum(5);
	    this.updateDetaimNum(subscribeCollectBill.getId());
		
		return SUCCESS;	
		
	}
	
	/**
	 * 更新汇总单的明细数量、金额等
	 * @param id
	 */
	public void updateDetaimNum(Long id){
		
		subscribeCollectBill = this.subscribeCollectBillManager.loadSubscribeCollectBill(id);
		try {
			this.subscribeCollectBillManager.getDetailMoreNum(subscribeCollectBill);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//获得验收单所有部门
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
	//获得类型
	public List<LabelValue> getSubTypeStatus(){
		LabelValue[] arrays = this.wrapEnum(SubscribeTypeStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		tmp.remove(arrays[3]);
//		if(toolingDevFlag.equals("DEVICE")){
//			
//			tmp.remove(arrays[1]);
//		}else{
//			tmp.remove(arrays[2]);
//		}
		return tmp;
	}
	

	public List<LabelValue> getSubStatus(){
		LabelValue[] arrays = this.wrapEnum(SubscribeStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	 /**
	 * 获得单据类型
	 * @return
	 */
	
	public List getAllDetailKind() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.DETAIL_KIND);
		
	}
	

	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
}
