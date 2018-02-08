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
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;
/**
 * @author qs
 * @version $Id: EditSubscribeDetailAction.java 11309 2008-03-13 05:51:55Z mwei $
 */
@SuppressWarnings("serial")
public class EditSubscribeCollectBillDetailAction extends PrepareAction{
	
	private final SubscribeCollectBillManager subscribeCollectBillManager;
	private SubscribeCollectBill subscribeCollectBill;
	private SubscribeDetail subscribeDtl;//申购单明细对象
	
	
	
	public EditSubscribeCollectBillDetailAction(SubscribeCollectBillManager subscribeCollectBillManager) {
		this.subscribeCollectBillManager = subscribeCollectBillManager;
	}

	public void prepare() throws Exception {
		
	}
	
	public String save() {
		return null;
		
	       
	}

	public SubscribeCollectBill getSubscribeCollectBill() {
		return subscribeCollectBill;
	}

	public void setSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill) {
		this.subscribeCollectBill = subscribeCollectBill;
	}

	public SubscribeDetail getSubscribeDtl() {
		return subscribeDtl;
	}

	public void setSubscribeDtl(SubscribeDetail subscribeDtl) {
		this.subscribeDtl = subscribeDtl;
	}
	



}
