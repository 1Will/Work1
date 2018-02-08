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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareInOutBill;
import com.yongjun.tdms.service.asset.spare.SpareInOutBillManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.event.EventNewManager;

/**
 * <p>Title: EditSpareInAction
 * <p>Description: 备件入库页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mwei@yj-technology.com
 * @version $Id: EditSpareInAction.java 10321 2008-01-11 02:44:30Z mwei $
 */
public class EditSpareInAction extends PrepareAction {
	private static final long serialVersionUID = 4229748951179236929L;
	
	private final SpareManager spareManger;
	private final SpareInOutBillManager spareInOutBillManager;
	private final UserManager userManager;
	private final EventNewManager eventNewManager;
	
	private List<Spare> spares;
	
	private String storeOldSpareValue;
	private String spareIdAndValueString;
	private String spareIdString;
	private String popupPage;
	private SpareInOutBill spareInBill;
	private String historyValue;
	private String billInfo;
	private String toolingDevFlag;
	String reportId;

	public EditSpareInAction(SpareManager spareManger,
			SpareInOutBillManager spareInOutBillManager,
			UserManager userManager,
			EventNewManager eventNewManager) {
		this.spareManger = spareManger;
		this.spareInOutBillManager = spareInOutBillManager;
		this.userManager = userManager;
		this.eventNewManager = eventNewManager;
	}


	/**
	 * 根据页面传递的toolingDevFlag,判断是工装还是设备
	 * 根据页面面传递的billInfo，获取入库单信息
	 * 根据页面传递的popupPage，判断是否是弹出页面
	 * 根据页面传递的spareIdAndValueString,storeOldSpareValue,获取相应的字符串
	 * 根据spareIds数组，获取备件对象集合
	 * 根据spareInBill.id，获取入库单对象
	 * 
	 * @param 
	 * @return 
	 */
	public void prepare() throws Exception {
		if(request.getParameter("toolingDevFlag").equals("TOOLING")){
			this.toolingDevFlag="TOOLING";
		}
		else {
			this.toolingDevFlag="DEVICE";
		}
		if (billInfo == null) {
			if (this.hasId("billInfo")) {
				billInfo = (String) request.getParameter("billInfo");
			} else {
				billInfo = "";
			}
		}
		if (popupPage == null) {
			if (this.hasId("popupPage")) {
				popupPage = (String) request.getParameter("popupPage");
			} else {
				popupPage = "F";
			}
		}
		if (spareIdAndValueString == null) {
			spareIdAndValueString = (String) request.getParameter("spareValueIds");
		}
		
		if(storeOldSpareValue==null){
			if(this.hasId("storeOldSpareValue")){
				storeOldSpareValue=(String) request.getParameter("storeOldSpareValue");
			}
		}
		if (null == this.spares) {
			if (this.hasId("spareIds")) {
				this.spares = new ArrayList<Spare>();
				String spareIds = (String) request.getParameter("spareIds");
				spareIdString = spareIds;
				String[] ary = spareIds.split(",");
				for (int i = 0; i < ary.length; i++) {
					this.spares.add(this.spareManger.loasSpare(Long
							.valueOf(ary[i])));
				}
			} else {
				this.spares = new ArrayList<Spare>();
			}
		}
		if (spareInBill == null) {
			if (this.hasId("spareInBill.id")) {
				this.spareInBill = this.spareInOutBillManager
						.loadSpareInOutBill(this.getId("spareInBill.id"));
				historyValue = "spareInBill";
			} else {
				this.spareInBill = new SpareInOutBill();
			}
		}
		if (null == this.reportId) {
			if (!StringUtils.isEmpty(request.getParameter("reportId"))) {
				this.reportId = request.getParameter("reportId");
			}
		}
	}

	/**
	 * 保存入库单spareInBill对象,并提示是否成功消息
	 * 
	 * @param 
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {
		if (this.isSubmitRecord()){
			return submitRecord();
		}
		boolean isNew = this.spareInBill.isNew();
		this.spareInBill.setSubmit(false);			//每一次保存都将该备件入库、出库单置为未提交提示。
		if (!spareIdAndValueString.equals("")) {
			this.spareInOutBillManager.storeSpareInOutBill(spareInBill,
					spareIdAndValueString, true);
		} else {
			this.spareInOutBillManager.storeSpareInOutBill(spareInBill);
		}

		if (isNew) {
			this.addActionMessage(this.getText("spareInBill.add.success",
					Arrays.asList(new Object[] { spareInBill.getBillNo() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareInBill.edit.success",
					Arrays.asList(new Object[] { spareInBill.getBillNo() })));
			return SUCCESS;
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
		this.eventNewManager.storeEventNew(1050,
				Calendar.getInstance().getTime(),
				0,1,this.userManager.getUser().getId(),1000,Integer.valueOf(reportId),"");
		return SUCCESS;
	}

	public SpareInOutBill getSpareInBill() {
		return spareInBill;
	}

	public void setSpareInBill(SpareInOutBill spareInBill) {
		this.spareInBill = spareInBill;
	}

	public List<Spare> getSpares() {
		return spares;
	}

	public void setSpares(List<Spare> spares) {
		this.spares = spares;
	}

	public String getSpareIdAndValueString() {
		return spareIdAndValueString;
	}

	public void setSpareIdAndValueString(String spareIdAndValueString) {
		this.spareIdAndValueString = spareIdAndValueString;
	}

	public String getSpareIdString() {
		return spareIdString;
	}

	public void setSpareIdString(String spareIdString) {
		this.spareIdString = spareIdString;
	}

	public String getHistoryValue() {
		return historyValue;
	}

	public void setHistoryValue(String historyValue) {
		this.historyValue = historyValue;
	}

	public String getPopupPage() {
		return popupPage;
	}

	public void setPopupPage(String popupPage) {
		this.popupPage = popupPage;
	}

	public String getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(String billInfo) {
		this.billInfo = billInfo;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getStoreOldSpareValue() {
		return storeOldSpareValue;
	}

	public void setStoreOldSpareValue(String storeOldSpareValue) {
		this.storeOldSpareValue = storeOldSpareValue;
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
