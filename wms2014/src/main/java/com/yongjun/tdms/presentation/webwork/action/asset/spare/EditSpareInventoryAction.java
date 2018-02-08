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
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.event.EventNewManager;

/**
 * <p>
 * Title: EditSpareInventoryAction
 * <p>
 * Description: 备件盘存控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author mwei@yj-technology.com
 * @version $Id: ListDeviceReportController.java 9885 2007-12-28 02:58:01Z qsun $
 */
public class EditSpareInventoryAction extends PrepareAction {
	private static final long serialVersionUID = -445787872713751374L;

	private final SpareInventoryManager spareInventoryManager;

	private final SpareManager spareManager;

	private final UserManager userManager;

	private final EventNewManager eventNewManager;

	private SpareInventory spareInventory;

	private List<Spare> spares;

	private String spareIds;

	private String toolingDevFlag;

	private String matchCheckbox;

	private String inventorySpareStock;

	private String inventoryBillInfo;

	private String spareInventoryIsExists;

	private String storeOldSpareValue;

	private String popupPage;

	String reportId;

	public String getPopupPage() {
		return popupPage;
	}

	public void setPopupPage(String popupPage) {
		this.popupPage = popupPage;
	}

	public EditSpareInventoryAction(
			SpareInventoryManager spareInventoryManager,
			SpareManager spareManager, UserManager userManager,
			EventNewManager eventNewManager) {
		this.spareInventoryManager = spareInventoryManager;
		this.spareManager = spareManager;
		this.userManager = userManager;
		this.eventNewManager = eventNewManager;
	}

	/**
	 * 根据页面传递的toolingDevFlag,判断是工装还是设备 根据页面面传递的popupPage,判断是不是弹出窗口
	 * 根据页面传递的matchCheckbox，判断是否需要box行头
	 * 根据页面传递的inventoryBillInfo、inventorySpareStock、storeOldSpareValue、,获取相应的字符串信息
	 * 根据页面传递的spareIds，获取备件对象集合 根据页面传递的spareInventory.id，获取盘点对象,如果没有，则初始化盘点对象
	 * 
	 * @param
	 * @return
	 */
	public void prepare() throws Exception {
		if (request.getParameter("toolingDevFlag").equals("TOOLING")) {
			this.toolingDevFlag = "TOOLING";
		} else {
			this.toolingDevFlag = "DEVICE";
		}

		if (popupPage == null) {
			if (this.hasId("popupPage")) {
				popupPage = (String) request.getParameter("popupPage");
			} else {
				popupPage = "F";
			}
		}

		if (this.hasId("matchCheckbox")) {
			this.matchCheckbox = (String) request.getParameter("matchCheckbox");
		} else {
			this.matchCheckbox = "F";
		}

		if (inventoryBillInfo == null) {
			if (this.hasId("billInfo")) {
				inventoryBillInfo = (String) request.getParameter("billInfo");
			} else {
				inventoryBillInfo = "";
			}
		}

		if (inventorySpareStock == null) {
			inventorySpareStock = (String) request
					.getParameter("inventorySpareStock");
		}

		if (storeOldSpareValue == null) {
			if (this.hasId("storeOldSpareValue")) {
				storeOldSpareValue = (String) request
						.getParameter("storeOldSpareValue");
			}
		}

		if (null == this.spares) {
			if (this.hasId("spareIds")) {
				this.spares = new ArrayList<Spare>();
				spareIds = (String) request.getParameter("spareIds");
				String[] ary = spareIds.split(",");
				for (int i = 0; i < ary.length; i++) {
					this.spares.add(this.spareManager.loasSpare(Long
							.valueOf(ary[i])));
				}
			} else {
				this.spares = new ArrayList<Spare>();
			}
		}
		if (null == this.spareInventory) {
			if (this.hasId("spareInventory.id")) {
				this.spareInventory = this.spareInventoryManager
						.loadSpareInventory(this.getId("spareInventory.id"));
				spareInventoryIsExists = "spareInventory";
			} else {
				this.spareInventory = new SpareInventory();
			}
		}
		if (null == this.reportId) {
			if (!StringUtils.isEmpty(request.getParameter("reportId"))) {
				this.reportId = request.getParameter("reportId");
			}
		}
	}

	public SpareInventory getSpareInventory() {
		return spareInventory;
	}

	public void setSpareInventory(SpareInventory spareInventory) {
		this.spareInventory = spareInventory;
	}

	/**
	 * 存储盘点对象，并更新备件的库存，同时自动记录日志,并提示是否成功消息
	 * 
	 * @param
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {
		if (this.isSubmitRecord()) {
			return submitRecord();
		}
		boolean isNew = this.spareInventory.isNew();
		this.spareInventory.setSubmit(false);
		System.out.println("sss");
		if (!inventorySpareStock.equals("")) {
			spareInventoryManager.storeSpareInventory(spareInventory,
					inventorySpareStock);
		} else {
			spareInventoryManager.storeSpareInventory(spareInventory);
		}
		if (isNew) {
			this.addActionMessage(this.getText("spareInventory.add.success",
					Arrays.asList(new Object[] { spareInventory.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareInventory.edit.success",
					Arrays.asList(new Object[] { spareInventory.getName() })));
			return SUCCESS;
		}
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
	 * 提交记录，storeEventNew的参数 1.EventType ：1052表示点检报告的提交 2.Time 3.Status: 目前为0
	 * 4.OrgId: 目前为1 5.UserId 6.DocType:目前和EventType内容一致 7.DocId
	 * ：提交的多个报告ID,是字符串型 8.MoreInfo:一些相关信息
	 */
	public String submitRecord() {
		this.eventNewManager.storeEventNew_InvenTory(1052, Calendar.getInstance()
				.getTime(), 0, 1, this.userManager.getUser().getId(), 1000,
				Integer.valueOf(reportId), "");
		return SUCCESS;
	}

	public List<Spare> getSpares() {
		return spares;
	}

	public void setSpares(List<Spare> spares) {
		this.spares = spares;
	}

	public String getSpareInventoryIsExists() {
		return spareInventoryIsExists;
	}

	public void setSpareInventoryIsExists(String spareInventoryIsExists) {
		this.spareInventoryIsExists = spareInventoryIsExists;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public String getInventoryBillInfo() {
		return inventoryBillInfo;
	}

	public void setInventoryBillInfo(String inventoryBillInfo) {
		this.inventoryBillInfo = inventoryBillInfo;
	}

	public String getInventorySpareStock() {
		return inventorySpareStock;
	}

	public void setInventorySpareStock(String inventorySpareStock) {
		this.inventorySpareStock = inventorySpareStock;
	}

	public String getMatchCheckbox() {
		return matchCheckbox;
	}

	public void setMatchCheckbox(String matchCheckbox) {
		this.matchCheckbox = matchCheckbox;
	}

	public String getSpareIds() {
		return spareIds;
	}

	public void setSpareIds(String spareIds) {
		this.spareIds = spareIds;
	}

	public String getStoreOldSpareValue() {
		return storeOldSpareValue;
	}

	public void setStoreOldSpareValue(String storeOldSpareValue) {
		this.storeOldSpareValue = storeOldSpareValue;
	}

	public User getLoginUser() {
		return userManager.getUser();
	}
}
