package com.yongjun.tdms.presentation.webwork.action.asset.spare.location;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.SystemOutLogger;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.LocationStatus;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillStatus;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;

/**
 * <p>
 * Title:EditLocationAction
 * <p>
 * Description:库位维护页面访问控制类
 * </P>
 * <p>
 * Copyright:Copyright (c) 2009 yj-technology
 * </P>
 * Company:www.yj-technology.com
 * </P>
 * 
 * @author chengding@yj-technology.com
 * @version Id:
 */
public class EditLocationAction extends PrepareAction {
	private static final long serialVersionUID = 3855277349628759547L;

	private final LocationManager locationManager;

	private Location location;

	public EditLocationAction(LocationManager locationManager) {
		this.locationManager = locationManager;
	}

	public String execute() throws Exception {
		if(!StringUtils.isEmpty(request.getParameter("saveContinue"))&&"RENEW".equals(request.getParameter("saveContinue"))){
			location = saveContinue(location);
		}
		return SUCCESS;
	}

	public void prepare() throws Exception {
		if (null == location) {
			if (this.hasId("location.id")) {// 判断location值是新建的还是从页面传过来的
				location = locationManager.loadLocation(this
						.getId("location.id"));
			} else {
				location = new Location();
				location.setStatus(LocationStatus.NON_USE);
			}
		}
	}

	/**
	 * 保存库位location对象,并提示是否成功消息
	 * 
	 * @param
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {
		// 改变库位体积
		double volume = location.getLength() * location.getWide()
				* location.getHight();
		location.setVolume(volume);
		// 获得出库单的状态
		if (!StringUtils.isEmpty(request.getParameter("status"))) {
			if (request.getParameter("status").equals("NON_USE")) {
				location.setStatus(LocationStatus.NON_USE);
			}
			if (request.getParameter("status").equals("USED")) {
				location.setStatus(LocationStatus.USED);
			}
		}
		
		

		boolean isNew = this.location.isNew();
		try {
			//保存location对象
			this.locationManager.storeLocation(location);
			System.out.println("ID： " + location.getCode());
		} catch (Exception e) {
			if (isNew) {
				this.addActionError(this.getText("location.add.error", Arrays
						.asList(new Object[] { location.getCode() })));
			} else {
				this.addActionError(this.getText("location.edit.error", Arrays
						.asList(new Object[] { location.getCode() })));
			}
			location.setCode(null);
			return INPUT;
		}
		if (isNew) {
			this.addActionMessage(this.getText("location.add.success", Arrays
					.asList(new Object[] { location.getCode() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("location.edit.success", Arrays
					.asList(new Object[] { location.getCode() })));
			return SUCCESS;
		}

	}
	
//	继续新建库位 
	private Location saveContinue(Location location){
		Location tempLocation = new Location();
		
		tempLocation.setMaxWeight(location.getMaxWeight());
		tempLocation.setLength(location.getLength());
		tempLocation.setWide(location.getWide());
		tempLocation.setHight(location.getHight());
		tempLocation.setVolume(location.getVolume());
		tempLocation.setStatus(location.getStatus());
		tempLocation.setDescribe(location.getDescribe());
		
		return tempLocation;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
