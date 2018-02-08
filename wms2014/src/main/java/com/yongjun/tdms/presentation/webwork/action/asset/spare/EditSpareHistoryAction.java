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
package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.SpareInOutHistory;
import com.yongjun.tdms.service.asset.spare.SpareInOutHistoryManager;

/**
 * <p>Title: EditSpareHistoryAction
 * <p>Description: 备件出、入库历史页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mwei@yj-technology.com
 * @version $Id: EditSpareHistoryAction.java 10321 2008-01-11 02:44:30Z mwei $
 */
public class EditSpareHistoryAction extends PrepareAction{
	private static final long serialVersionUID = 7853970955804140077L;

	private SpareInOutHistory sparInOutBill;
	
	private SpareInOutHistoryManager spareInOutHistoryManager;
	
	private long firstNumger;
	
	public long getFirstNumger() {
		return firstNumger;
	}
	public void setFirstNumger(long firstNumger) {
		this.firstNumger = firstNumger;
	}
	public EditSpareHistoryAction(SpareInOutHistoryManager spareInOutHistoryManager){
		this.spareInOutHistoryManager=spareInOutHistoryManager;
	}
	
	/**
	 * 根据页面传递的sparInOutBill.id,获取入库单或者是出库单对象
	 * 如果有入、出库单对象，获取出、入库单的Id号
	 * 
	 * @param 
	 * @return 
	 */
	public void prepare() throws Exception {
		if (sparInOutBill == null) {
			if (this.hasId("sparInOutBill.id")) {
				this.sparInOutBill = this.spareInOutHistoryManager.loadSpareInOutHistory(this.getId("sparInOutBill.id"));
				firstNumger=sparInOutBill.getNumber();
			} else {
				this.sparInOutBill = new SpareInOutHistory();
			}
		}
	}
	
	/**
	 * 根据页面传递的sparInOutBill.id,获取入库单或者是出库单对象
	 * 
	 * @param 
	 * @return 
	 */
	public String save() {
		boolean isNew = this.sparInOutBill.isNew();
		this.spareInOutHistoryManager.storeSpareInOutHistory(sparInOutBill,firstNumger);
		if (isNew) {
			this.addActionMessage(this.getText("sparInOutBill.add.success",
					Arrays.asList(new Object[] { sparInOutBill.getBill().getBillNo() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("sparInOutBill.edit.success",
					Arrays.asList(new Object[] { sparInOutBill.getBill().getBillNo() })));
			return SUCCESS;
		}
	}

	public SpareInOutHistory getSparInOutBill() {
		return sparInOutBill;
	}

	public void setSparInOutBill(SpareInOutHistory sparInOutBill) {
		this.sparInOutBill = sparInOutBill;
	}
}
