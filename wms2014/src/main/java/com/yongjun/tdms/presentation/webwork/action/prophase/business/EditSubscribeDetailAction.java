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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.security.GroupDao;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.SpareDetailTypeManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;
/**
 * @author qs
 * @version $Id: EditSubscribeDetailAction.java 11309 2008-03-13 05:51:55Z mwei $
 */
@SuppressWarnings("serial")
public class EditSubscribeDetailAction extends PrepareAction{
	private final SubscribeManager subscribeManager;
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final SpareTypeManager spareTypeManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	private final SpareManager spareManager;
	private final SequenceManager sequenceManager;
	private final WorkWarnningManager workWarnningManager;
	private final UserManager userManager;
    private String toolingDevFlag;
	private Subscribe subscribe;//申购单对象
	private SubscribeDetail subscribeDtl;//申购单明细对象
	private Supplier factory;
	private Supplier equFactory;
	/**
	 * 判断是否是从维护页面传过去的
	 */
	private boolean fromDetail=false;
	
	
	public EditSubscribeDetailAction(SubscribeManager subscribeManager,
			SupplierManager supplierManager,CodeValueManager codeValueManager,SpareTypeManager spareTypeManager,SpareDetailTypeManager spareDetailTypeManager,SpareManager spareManager,SequenceManager sequenceManager, UserManager userManager,WorkWarnningManager workWarnningManager) {
		this.subscribeManager = subscribeManager;
		this.supplierManager = supplierManager;
		this.codeValueManager=codeValueManager;
		this.spareTypeManager = spareTypeManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.spareManager = spareManager;
		this.sequenceManager = sequenceManager;
		this.userManager = userManager;
		this.workWarnningManager=workWarnningManager;
	}
	public void prepare() throws Exception {
		String strFromDetail = this.request.getParameter("fromDetail");
		if(null!=strFromDetail){
			fromDetail = Boolean.parseBoolean(strFromDetail);
		}
		
		  //获得申购单对象
			if (this.hasId("subscribe.id")) {
				this.subscribe = this.subscribeManager.loadSubscribe(getId("subscribe.id"));
			} 
			
		if (null == this.subscribeDtl) {//获得申购单明细对象
			if (hasId("subscribeDtl.id")) {
				this.subscribeDtl = this.subscribeManager.loadSubscribeDetail(getId("subscribeDtl.id"));
				//this.subscribe = this.subscribeDtl.getSubscribe();//当申购单明细不为空时,同时获取申购单对象,以免抛出空异常 
				//	生产厂家
				if(!this.hasId("factory.id")){
					this.factory = this.subscribeDtl.getFactory();
				}
				//设备厂家
				if(!this.hasId("equFactory.id")){
					this.equFactory = this.subscribeDtl.getEquFactory();
				}
			} else {
				this.subscribeDtl = new SubscribeDetail();//新建 状态默认为"新建"
				this.subscribeDtl.setStatus(SubscribeDetailStatus.NEW);
			}
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
			
		}
	}
	
	public String save() {
		boolean isNew = this.subscribeDtl.isNew();
		subscribeDtl.setSubscribe(subscribe);

		//	种类
		if(this.hasId("category.id")){
			subscribeDtl.setCategory(this.spareTypeManager.loadSpareType(this.getId("category.id")));
		}
		//	明细种类
		if(this.hasId("detailCategory.id")){
			subscribeDtl.setDetailCategory(this.spareDetailTypeManager.loadSpareDetailType(this.getId("detailCategory.id")));
		}
		//	设置计量单位
		if(this.hasId("calUnit.id")){
			subscribeDtl.setCalUnit(this.codeValueManager.loadCodeValue(this.getId("calUnit.id")));
		}
		//生产厂家
		if(this.hasId("factory.id")){
			subscribeDtl.setFactory(this.supplierManager.loadSupplier(getId("factory.id")));
		}else if(!"".equals(subscribeDtl.getFactoryStr().trim()) && null != subscribeDtl.getFactoryStr().trim()){
			String factoryStr = subscribeDtl.getFactoryStr().trim();
			if(null !=(this.supplierManager.loadSupplierByName(factoryStr))){		//已经存在的生产厂家
				subscribeDtl.setFactory(this.supplierManager.loadSupplierByName(factoryStr));
			}else{																										//不存在的生产厂家
				factory=new Supplier();   //创建生产厂家对象
				factory.setCategory("FACTORY"); //标识类别是“生产厂家”
				String supplierNo = (String) sequenceManager.generate("-");
				factory.setSupplierNo(supplierNo);
				factory.setName(subscribeDtl.getFactoryStr());
				this.supplierManager.storeSupplier(factory);
				subscribeDtl.setFactory(this.supplierManager.loadSupplier(factory.getId()));
			}
			
		}
		//设备厂家
		if(this.hasId("equFactory.id")){
			subscribeDtl.setEquFactory(this.supplierManager.loadSupplier(getId("equFactory.id")));
		}
		//申购关联备件
		if(this.hasId("spare.id")){
			Spare spare = this.spareManager.loasSpare(getId("spare.id"));
			
			if(request.getParameter("subscribeDtl.name")!=null){
				spare.setName(request.getParameter("subscribeDtl.name"));
				}
				spare.setSpareNo(request.getParameter("subscribeDtl.graphNo").trim());
				if (this.toolingDevFlag != null) {
					if (this.toolingDevFlag.equals("TOOLING")) {
						spare.setToolingDevFlag(SysModel.TOOLING);
					}
					else if(this.toolingDevFlag.equals("DEVICE")) {
						spare.setToolingDevFlag(SysModel.DEVICE);
					}
				}
				if(request.getParameter("subscribeDtl.model")!=null){
				spare.setModelSpecs(request.getParameter("subscribeDtl.model"));
				}
				if (hasId("equFactory.id")) {
					spare.setSupplier(this.supplierManager.loadSupplier(getId("equFactory.id")));
				}
				if(hasId("factory.id")){
					spare.setFactory(factory);
					spare.setFactoryStr(factory.getName());
				}
				if(this.hasId("category.id")){
					spare.setCategory(this.spareTypeManager.loadSpareType(this.getId("category.id")));
				}
				if(request.getParameter("subscribeDtl.orderNo")!=null){
				spare.setOrderNo(request.getParameter("subscribeDtl.orderNo"));
				}
				
				if(request.getParameter("subscribeDtl.unitPrice")!=null){
					spare.setUnitPrice(Double.parseDouble(request.getParameter("subscribeDtl.unitPrice")));
					}
				
				spareManager.storeSpare(spare);
			
			
			subscribeDtl.setSpare(spare);
			subscribeDtl.setUnitPrice(spare.getUnitPrice());   //设置单价
			subscribeDtl.setTotalPrice(spare.getUnitPrice()*subscribeDtl.getAmount());//设置总价
		}else{
			if(request.getParameter("subscribeDtl.graphNo")!=null){
				//创建台账
				Spare spare = new Spare();
				if(request.getParameter("subscribeDtl.name")!=null){
				spare.setName(request.getParameter("subscribeDtl.name"));
				}
				spare.setSpareNo(request.getParameter("subscribeDtl.graphNo").trim());
				if (this.toolingDevFlag != null) {
					if (this.toolingDevFlag.equals("TOOLING")) {
						spare.setToolingDevFlag(SysModel.TOOLING);
					}
					else if(this.toolingDevFlag.equals("DEVICE")) {
						spare.setToolingDevFlag(SysModel.DEVICE);
					}
				}
				if(request.getParameter("subscribeDtl.model")!=null){
				spare.setModelSpecs(request.getParameter("subscribeDtl.model"));
				}
				if (hasId("equFactory.id")) {
					spare.setSupplier(this.supplierManager.loadSupplier(getId("equFactory.id")));
				}
				if(hasId("factory.id")){
					spare.setFactory(factory);
					spare.setFactoryStr(factory.getName());
				}
				if(this.hasId("category.id")){
					spare.setCategory(this.spareTypeManager.loadSpareType(this.getId("category.id")));
				}
				if(request.getParameter("subscribeDtl.orderNo")!=null){
				spare.setOrderNo(request.getParameter("subscribeDtl.orderNo"));
				}
				
				if(request.getParameter("subscribeDtl.unitPrice")!=null){
					spare.setUnitPrice(Double.parseDouble(request.getParameter("subscribeDtl.unitPrice")));
					}
				
				spareManager.storeSpare(spare);
				//创建台账结束
				
				
				//发送提醒
				Date date = new Date();
				List<Long> userids = workWarnningManager.loadUsersByGroup(42l);
				if(userids!=null&&userids.size()>0){
				Long[] userArr  = (Long[]) userids.toArray(new Long[userids.size()]);
				List<User> users = userManager.loadAllUsers(userArr);
				String warnningContent = userManager.getUser().getName()+"于"+DateUtil.getDate(date, "yyyy年MM月dd日")+"创建一条新的备件,备件编码为:"+spare.getSpareNo()+",型号为:"+spare.getModelSpecs()+",备件名称为:"+spare.getName();
				String warnningType = "新建备件信息";
				workWarnningManager.sendWarnningMessage(users, warnningType, warnningContent);
				}
				//发送提醒结束
				Spare spareToSave = this.spareManager.loadBySpareNo(spare.getSpareNo());
				subscribeDtl.setGraphNo(request.getParameter("subscribeDtl.graphNo"));
				subscribeDtl.setSpare(spareToSave);
				subscribeDtl.setUnitPrice(spare.getUnitPrice());   //设置单价
				subscribeDtl.setTotalPrice(spare.getUnitPrice()*subscribeDtl.getAmount());//设置总价
			}else{
				subscribeDtl.setGraphNo(null);
				subscribeDtl.setSpare(null);
			}
			
		
		}


		subscribeManager.storeSubscribeDetail(subscribeDtl);
	
		 
		//更新申购单的明细数量字段
		subscribe.setSumDetail(subscribe.getSubscribeDetails().size());
		this.subscribeManager.storeSubscribe(subscribe);
		
		
		if (isNew) {
			 //添加到事件 zzb
			this.subscribeManager.storeEventNew_subDetail(1060, subscribeDtl.getId().intValue(),"添加");
			
			if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
				addActionMessage(getText("subscribeDetail.add.success",
	                    Arrays.asList(new Object[]{subscribeDtl.getName()})));
	            return NEW;
			}else{
				addActionMessage(getText("subscribeDetailPurchaseDtl.add.success",
	                    Arrays.asList(new Object[]{subscribeDtl.getName()})));
	            return NEW;
			}
			
	            
	      } else {
	    		//添加到事件 zzb
	  		this.subscribeManager.storeEventNew_subDetail(1062, subscribeDtl.getId().intValue(),"更新");
	    	  if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
	    		   addActionMessage(getText("subscribeDetail.edit.success",
	                        Arrays.asList(new Object[]{subscribeDtl.getName()})));
	            return SUCCESS;
	        } else{
	        	  addActionMessage(getText("subscribeDetailPurchase.edit.success",
	                        Arrays.asList(new Object[]{subscribeDtl.getName()})));
	            return SUCCESS;
	        }
	     }
	             
	}
	/**
	 * 获取工装计量单位集合
	 * @return List 工装计量单位
	 */
	public List getCalUnits() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}
	
	/**
	 * 获取种类
	 * @return List 种类
	 */
	public List getAllCategory() {
		return this.spareTypeManager.loadAllSpareTypeByToolingDevFlag(toolingDevFlag);
	}
	
	/**
	 * 获取明细种类
	 * @return List 明细种类
	 */
	public List getAllDetailCategory(){
		List list = new ArrayList();
		return list;
	} 
	
	public Subscribe getSubscribe() {
		return subscribe;
	}
	
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	
	public SubscribeDetail getSubscribeDtl() {
		return this.subscribeDtl;
	}
	
	public void setSubscribeDtl(SubscribeDetail subscribeDtl) {
		this.subscribeDtl = subscribeDtl;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public Supplier getFactory() {
		return factory;
	}
	public void setFactory(Supplier factory) {
		this.factory = factory;
	}
	public Supplier getEquFactory() {
		return equFactory;
	}
	public void setEquFactory(Supplier equFactory) {
		this.equFactory = equFactory;
	}
	public boolean isFromDetail() {
		return fromDetail;
	}
	public void setFromDetail(boolean fromDetail) {
		this.fromDetail = fromDetail;
	}

}
