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
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * @author qs
 * @version $Id: ListSubscribeDetailAction.java 11309 2008-03-13 05:51:55Z mwei $
 */
@SuppressWarnings("serial")
public class ListSubscribeCollectBillDetailAction extends ValueListAction {

	private final SubscribeCollectBillManager subscribeCollectBillManager;

	private final SubscribeManager subscribeManager;
	
	private final SubscribeDao subscribeDao;

	private SubscribeCollectBill subscribeCollectBill;

	private List<SubscribeDetail> subscribeDetails;

	private SubscribeDetail subscribeDetail;

	private Subscribe subscribe;

	private String allSubscribeDetailId; // 申购单明细所有的ID

	private String allSubscribeCollectBillDetailBuyer; // 申购汇总单明细所有的采购人
	private String allBeizhu; //所有备注 zzb

	public ListSubscribeCollectBillDetailAction(
			SubscribeCollectBillManager subscribeCollectBillManager,
			SubscribeManager subscribeManager,
			SubscribeDao subscribeDao) {
		this.subscribeCollectBillManager = subscribeCollectBillManager;
		this.subscribeManager = subscribeManager;
		this.subscribeDao=subscribeDao;
	}

	public void prepare() throws DaoException {
		//System.out.println("########getPagingPage:"+super.getPagingPage());
		if (this.hasId("subscribeCollectBill.id")) {
			this.subscribeCollectBill = this.subscribeCollectBillManager.loadSubscribeCollectBill(getId("subscribeCollectBill.id"));
			 
		}
		if (this.subscribeDetails == null && this.hasIds("subscribeDtlIds")) {			
			//this.subscribeDetails = this.subscribeManager.loadAllSubscribeDetails(this.getIds("subscribeDtlIds"));
			this.subscribeDetails=this.subscribeDao.loadByKey("stocked", "0");
		}
		this.setFirst(false);
	}

	public String execute() throws Exception {
		if (this.isSaveDetail()) {
			return this.SaveSubscribeDetail();
		}
		if (this.isDelete()) {
			return delete();
		}
		if(this.isStock()){
			return notPurchase();
		}
		return SUCCESS;
	}



	private String SaveSubscribeDetail() {

		if (!StringUtils.isEmpty(request
				.getParameter("allSubscribeCollectBillDetailBuyer"))) {
			this.allSubscribeCollectBillDetailBuyer = request
					.getParameter("allSubscribeCollectBillDetailBuyer");
		}
		if (!StringUtils.isEmpty(request.getParameter("allSubscribeDetailIds"))) {
			this.allSubscribeDetailId = request.getParameter("allSubscribeDetailIds");
		}
		
		if (!StringUtils.isEmpty(request.getParameter("allBeizhu"))) {
			this.allBeizhu = request.getParameter("allBeizhu");
		} 
		//System.out.println("allSubscribeDetailId:"+allSubscribeDetailId);
		//if(null!=allSubscribeCollectBillDetailBuyer||null!=allSubscribeDetailId){
			 this.subscribeManager.storeSubscribeDetail(allSubscribeDetailId, null, null, null, 
					 null,allBeizhu, null,allSubscribeCollectBillDetailBuyer, subscribe,null);
		// }
		return SUCCESS;
	}

	/**
	 * 判断页面是否保存申购单明细"
	 * 
	 * @return
	 */
	private boolean isSaveDetail() {
		if (!StringUtils.isEmpty(request.getParameter("save"))) {
			if (this.hasKey("save")) {
				return true;
			}
		}
		return false;
	}

	public String saveAddQuarterPlan() {

		return null;
	}

	/**
	 * 删除选择的申购单明细
	 */
	public String delete() {
		try {
			this.subscribeManager.deleteAllSubscribeDetails(subscribeDetails,
					subscribe);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		this.addActionMessage(this.getText("subscribeDetails.delete.success"));
		return SUCCESS;

	}
	
	public boolean isStock() {
		return this.hasKey("stock");
	}
	/**
	 * 不采购
	 * @return
	 */
	private String notPurchase() throws Exception {	
		String notPurchaseIds = request.getParameter("notPurchaseIds");
		 
		if(null != notPurchaseIds && !"".equals(notPurchaseIds)){
			String[] strIds=notPurchaseIds.split(",");
			Long[] ids=new Long[strIds.length];
			for (int i = 0; i < strIds.length; i++) {		
				ids[i]=Long.valueOf(strIds[i]);
			}
			subscribeDetails = this.subscribeManager.loadAllSubscribeDetails(ids);
			for(SubscribeDetail sd:subscribeDetails){
				//状态改为不采购
				sd.setStatus(SubscribeDetailStatus.NOTPURCHASEED);
				sd.setStocked(true);
				subscribeManager.storeSubscribeDetail(sd);
                // 添加 事件 zzb
				this.subscribeManager.storeEventNew_subDetail(1062, sd.getId().intValue(),"不采购");
			}
			
		}
		 /*String[] arrys={"subscribeCollectBill","stocked"};
		 Object[] values={subscribeCollectBill.getId(),0};
		 List<SubscribeDetail> list = this.subscribeManager.loadDetailBykeyArry(arrys, values);
		subscribeCollectBill.setSumDetail(null == list?0:list.size());*/
		//updateDetailNum();
		this.subscribeCollectBillManager.getDetailMoreNum(subscribeCollectBill);
	
		return SUCCESS;
	}
	
	/**
	 * 更新 采购数、不采购、金额等
	 * @throws Exception
	 */
	public void updateDetailNum() throws Exception{
		List<SubscribeDetail> list = new ArrayList<SubscribeDetail>();
		String[] arrys={"status","subscribeCollectBill"};
		Object[] values={"NOTPURCHASEED",subscribeCollectBill.getId()};
	 
	    
		//不采购数
		int calNum = 0;
		list = this.subscribeManager.loadDetailBykeyArry(arrys, values);
		calNum = list.size();
		subscribeCollectBill.setCalNum(calNum);
		//采购数
		//Object[] values2 = {"PURCHASEED",subscribeCollectBill.getId()};
		//list = this.subscribeManager.loadDetailBykeyArry(arrys,values2);
		
		subscribeCollectBill.setPurNum(this.subscribeManager.getPurNum(subscribeCollectBill));
	
		
		Double dou = subscribeManager.getSumPrice(subscribeCollectBill);
		System.out.println("dou= "+dou);
		subscribeCollectBill.setTotalMoney(dou);
		//明细数量
		subscribeCollectBill.setSumDetail(subscribeCollectBill.getSubscribeDetails().size());
		//待确认数 总数-不采购-已采购（包括入库）
		int conNum = subscribeCollectBill.getSubscribeDetails().size()-calNum-this.subscribeManager.getPurNum(subscribeCollectBill);
		subscribeCollectBill.setConNum(conNum);
		
		this.subscribeCollectBillManager.storeSubscribeCollectBill(subscribeCollectBill);
			
		
	}
	

	@Override
	protected String getAdapterName() {
		return "subscribeCollectBillDetails";
	}

	public SubscribeCollectBill getSubscribeCollectBill() {
		return subscribeCollectBill;
	}

	public void setSubscribeCollectBill(
			SubscribeCollectBill subscribeCollectBill) {
		this.subscribeCollectBill = subscribeCollectBill;
	}

	public SubscribeDetail getSubscribeDetail() {
		return subscribeDetail;
	}

	public void setSubscribeDetail(SubscribeDetail subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}

	public List<SubscribeDetail> getSubscribeDetails() {
		return subscribeDetails;
	}

	public void setSubscribeDetails(List<SubscribeDetail> subscribeDetails) {
		this.subscribeDetails = subscribeDetails;
	}

}
