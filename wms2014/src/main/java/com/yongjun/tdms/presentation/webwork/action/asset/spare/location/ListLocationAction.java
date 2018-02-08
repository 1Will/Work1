package com.yongjun.tdms.presentation.webwork.action.asset.spare.location;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.LocationStatus;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;

/**
 * <p>
 * Title:ListLocationAction
 * <p>
 * Description:库位页面访问控制类
 * </P>
 * <p>
 * Copyright:Copyright (c) 2009 yj-technology
 * </P>
 * <p>
 * Company:www.yj-technology.com
 * </P>
 * @author chengding@yj-technology.com
 * @version Id:
 */
public class ListLocationAction extends ValueListAction {
	private static final long serialVersionUID = -3347771591059187305L;

	private final LocationManager locationManager;

	private List<Location> locations;

	public ListLocationAction(LocationManager locationManager) {
		this.locationManager = locationManager;
	}

	@Override
	public void prepare() throws Exception {
		if (null == locations && this.hasIds("locationIds")) {
			this.locations = locationManager.loadAllLocations(this
					.getIds("locationIds"));
		}
	}

	@Override
	public String execute() throws Exception {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return enabled();
		}
		return SUCCESS;
	}

	/**
	 * 失效所有的入库单
	 * 
	 */
	public String disabled() {
		try {
			this.locationManager.disabledAllLocation(locations);
			this.addActionMessage(this.getText("location.disasbled.success"));
			return SUCCESS;
		} catch (Exception e) {
			this.addActionError(this.getText("location.disasbled.farile"));
		}
		return SUCCESS;
	}

	/**
	 * 有效所有的入库单
	 * 
	 */
	public String enabled() {
		try {
			this.locationManager.enabledAllLocation(locations);
			this.addActionMessage(this.getText("location.enabled.success"));
			return SUCCESS;
		} catch (Exception e) {
			this.addActionError(this.getText("location.enabled.farile"));
			return ERROR;
		}
	}

	public List<LabelValue> getLocationStatus() { // 获得库位状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(LocationStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> temp = new ArrayList<LabelValue>();
		temp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			temp.add(arrays[i]);
		}
		return temp;
	}

	/**
	 * 根据上下文，获得按钮名称为enabled的按钮
	 * 
	 * @return
	 */
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}

	@Override
	protected String getAdapterName() {
		return "location";
	}

}
