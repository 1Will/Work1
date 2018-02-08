/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.warehouseInfo.regional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;

/**
 * <p>
 * Title:EditRegionalAction
 * <p>
 * Description:库区管理编辑类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author bxchen@yj-technology.com
 * @version
 */
public class EditRegionalAction extends PrepareAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2889910304491617116L;

	private final RegionalManager regionalManager;

	private final WarehouseManager warehouseManager;

	private final UserManager userManager;

	private final CodeValueManager codeValueManager;

	private Regional regional;
	
	private User loginUser;

	public EditRegionalAction(RegionalManager regionalManager,
			WarehouseManager warehouseManager, UserManager userManager,
			CodeValueManager codeValueManager) {
		this.regionalManager = regionalManager;
		this.warehouseManager = warehouseManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("regional.id")) {
			this.regional = this.regionalManager.loadRegionals(this
					.getId("regional.id"));
		} else {
			this.regional = new Regional();
		}
	}

	public String save() {
		boolean isNew = this.regional.isNew();
        //获得级别
		if(!StringUtils.isEmpty(request.getParameter("storageGrade.id"))){
			this.regional.setStorageGrade(this.codeValueManager.loadCodeValue(this.getId("storageGrade.id")));
		}
		//获得仓库
		if (this.hasId("warehouse.id")) {
			this.regional.setWarehouse(this.warehouseManager.loadWarehouse(this.getId("warehouse.id")));
		}
	 
		try {

			if (isNew) {
//				if (regionalManager.loadByKey("code", regional.getCode()) != null) {
//					this.addActionMessage(this.getText("regional.add.error",
//							Arrays.asList(new Object[] { this.regional
//									.getCode() })));
//				} else {
					this.regionalManager.storeRegional(regional);
					this.addActionMessage(this.getText("regional.add.success",
							Arrays.asList(new Object[] { this.regional
									.getCode() })));
					System.out.println("haohaoahao");
//				}
				return NEW;
			} else {
				this.regionalManager.storeRegional(regional);
				this.addActionMessage(this
						.getText("regional.edit.success",
								Arrays.asList(new Object[] { this.regional
										.getCode() })));
				return SUCCESS;
			}
		} catch (Exception e) {
			if (isNew) {
				this.addActionMessage(this.getText("regional.add.error", Arrays
						.asList(new Object[] { this.regional.getCode() })));
			} else {
				this.addActionMessage(this
						.getText("regional.edit.error",
								Arrays.asList(new Object[] { this.regional
										.getCode() })));
			}
			return ERROR;
		}
	}

	/**
	 * 获得库存级别
	 * 
	 * @return
	 */
	public List getAllStorageGrade() {
		return this.codeValueManager
				.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}

	/**
	 * 仓库
	 * 
	 * @return
	 */
	public List<Warehouse> getAllWarehouse() {
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		wareList = this.warehouseManager.loadAllWarehouse();
		return wareList;
	}

	public List<Warehouse> getAllWarehouseName() {
		List<Warehouse> ws = new ArrayList<Warehouse>();
		List<Warehouse> list = new ArrayList<Warehouse>();
		Long userId = this.userManager.getUser().getId();
		User user = userManager.loadUser(userId);
		list.addAll(user.getWarehouses());
		for (Warehouse warehouse : list) {
			if (!(warehouse.getDisabled())) {
				ws.add(warehouse);
			}
		}

		Warehouse w = new Warehouse();
		w.setId(-1L);
		w.setName(this.getText(""));
		if (ws == null) {
			ws = new ArrayList<Warehouse>();
		}
		ws.add(0, w);
		return ws;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}

}
