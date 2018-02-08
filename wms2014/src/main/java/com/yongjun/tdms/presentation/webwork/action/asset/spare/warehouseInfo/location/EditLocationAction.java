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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.warehouseInfo.location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * <p>
 * Title:EditLocationAction
 * <p>
 * Description:EditLocationAction
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
public class EditLocationAction
extends PrepareAction {
	private final RegionalManager regionalManager;

	private final WarehouseManager warehouseManager;

	private final CodeValueManager codeValueManager;

	private final LocationManager locationManager;
	
	private final UserManager userManager;

	private Location location;
	
	private String requestSourceType;   //页面请求来源，type:PopupWin|Win;popupWin表示弹出页面；win表示非弹出页面

	public EditLocationAction(final RegionalManager regionalManager,
			final WarehouseManager warehouseManager,
			final CodeValueManager codeValueManager,
			final LocationManager locationManager,
			final UserManager userManager) {
				this.regionalManager = regionalManager;
				this.warehouseManager = warehouseManager;
				this.codeValueManager = codeValueManager;
				this.locationManager = locationManager;
				this.userManager=userManager;	
			}

	public void prepare() throws Exception {
		if (this.hasId("location.id")) {
			this.location = this.locationManager.loadLocation(this
			.getId("location.id"));
		}
		else {
			this.location = new Location();
			User u = this.userManager.loadUser(this.userManager.getUser()
					.getId());
			Set<Warehouse> set = u.getWarehouses();
			if (null != set && set.size() > 0) {
				Warehouse[] house = set.toArray(new Warehouse[0]);
				int k = house.length;
				Warehouse w = house[k - 1];
				location.setStorageGrade(w.getStorageGrade());
				location.setWarehouse(w);
			}
		}
	}

	public String save(){
		
		boolean isNew = this.location.isNew();
		//填充体积
		if(request.getParameter("volume")!=null){
			if(!request.getParameter("volume").equals("")){
			this.location.setVolume(Double.valueOf(request.getParameter("volume")));
			}
		}
		//填充仓库级别
		if(this.hasId("storageGrade")){
			this.location.setStorageGrade(this.codeValueManager.loadCodeValue(this.getId("storageGrade")));
		}
		// 填充仓库
		if(this.hasId("warehouse")){
			this.location.setWarehouse(this.warehouseManager.loadWarehouse(this.getId("warehouse")));
		}
		//填充库区
		if(this.hasId("regional")){
			System.out.println("====== " + this.getId("regional"));
			this.location.setRegional(this.regionalManager.loadRegionals(this.getId("regional")));
		}
		//填充库位类型
		if(this.hasId("locationType")){
			this.location.setLocationType(this.codeValueManager.loadCodeValue(this.getId("locationType")));
		}
		//填充载重类型
		if(this.hasId("bearload")){
			this.location.setBearload(this.codeValueManager.loadCodeValue(this.getId("bearload")));
		}
		//填充混放模式
		if(this.hasId("mixmode")){
			this.location.setMixmode(this.codeValueManager.loadCodeValue(this.getId("mixmode")));
		}
		//填充相同备件存放
		if(this.hasId("samestore")){
			this.location.setSamestore(this.codeValueManager.loadCodeValue(this.getId("samestore")));
		}

		try{
			this.locationManager.storeLocation(this.location);
			if(isNew){
				this.addActionMessage(this.getText("location.add.success",Arrays.asList(new Object[]{this.location.getCode()})));
				return NEW;
			}else{
				this.addActionMessage(this.getText("location.edit.success",Arrays.asList(new Object[]{this.location.getCode()})));
			}
			return SUCCESS;
		}catch (Exception e) {
			if(isNew){
				this.addActionMessage(this.getText("location.add.error",Arrays.asList(new Object[]{this.location.getCode()})));
			}else{
				this.addActionMessage(this.getText("location.edit.error",Arrays.asList(new Object[]{this.location.getCode()})));
			}
				return ERROR;
		}
	}
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade(){
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}

	// 获得所有库区的集合
	public List<Regional> getAllRegional() {
		List<Regional> rs = new ArrayList<Regional>();
		Regional r = new Regional();
		r.setId(-1L);
		r.setName(this.getText(""));
		rs.add(0, r);
		return rs;
	}

	// 获得所有仓库的集合
	public List<Warehouse> getAllWarehouseName() {
		List<Warehouse> ws = new ArrayList<Warehouse>();
		List<Warehouse> list=new ArrayList<Warehouse>();
		Set<Warehouse> set=new HashSet<Warehouse>();
		Long userId=userManager.getUser().getId();
		User user=userManager.loadUser(userId);
		set=user.getWarehouses();
		list.addAll(set);
		for(Warehouse warehouse:list){
			if(!(warehouse.getDisabled())){
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

	// 获得所有库位类型的集合
	public List<CodeValue> getAllLocationType() {
		List<CodeValue> cvs = this.codeValueManager
		.LoadAllValuesByCode(CodeConstants.LOCAL_TYPE);
		CodeValue cv = new CodeValue();
		cv.setId(-1L);
		cv.setValue(this.getText(""));
		if (cvs == null) {
			cvs = new ArrayList<CodeValue>();
		}
		cvs.add(0, cv);
		return cvs;
	}

	// 获得载重类型的集合
	public List<CodeValue> getAllBearload() {
		List<CodeValue> cvs = this.codeValueManager
		.LoadAllValuesByCode(CodeConstants.BEARLOAD);
		CodeValue cv = new CodeValue();
		cv.setId(-1L);
		cv.setValue(this.getText(""));
		if (cvs == null) {
			cvs = new ArrayList<CodeValue>();
		}
		cvs.add(0, cv);
		return cvs;
	}

	// 获得混放模式的集合
	public List<CodeValue> getAllMixmode() {
		List<CodeValue> cvs = this.codeValueManager
		.LoadAllValuesByCode(CodeConstants.MIXMODE);
		CodeValue cv = new CodeValue();
		cv.setId(-1L);
		cv.setValue(this.getText(""));
		if (cvs == null) {
			cvs = new ArrayList<CodeValue>();
		}
		cvs.add(0, cv);
		return cvs;

	}

	// 获得相同备件存放的集合
	public List<CodeValue> getAllSamestore() {
		List<CodeValue> cvs = this.codeValueManager
		.LoadAllValuesByCode(CodeConstants.SAMESTORAGE);
		CodeValue cv = new CodeValue();
		cv.setId(-1L);
		cv.setValue(this.getText(""));
		if (cvs == null) {
			cvs = new ArrayList<CodeValue>();
		}
		cvs.add(0, cv);
		return cvs;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getRequestSourceType() {
		return requestSourceType;
	}

	public void setRequestSourceType(String requestSourceType) {
		this.requestSourceType = requestSourceType;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
