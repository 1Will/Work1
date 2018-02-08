package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.lubricationOil.LubricationOilManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationPlanManager;

public class EditLubricationPlanDetailAction extends PrepareAction {
	private static final long serialVersionUID = 8868735021533659497L;
	
	private final LubricationPlanManager lubricationPlanManager;
	private final LubricationPlanDetailManager lubricationPlanDetailManager;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	private final LubricationOilManager lubricationOilManager;
	
	private DeviceCard device;
	private LubricationPlan lubrication;
	private LubricationPlanDetail lubricationDetail;
	private LubricationOil lubricationOil;
	
	public EditLubricationPlanDetailAction(LubricationPlanManager lubricationPlanManager,
			LubricationPlanDetailManager lubricationPlanDetailManager,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager,
			LubricationOilManager lubricationOilManager) {
		this.lubricationPlanManager = lubricationPlanManager;
		this.lubricationPlanDetailManager = lubricationPlanDetailManager;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		this.lubricationOilManager = lubricationOilManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.lubricationDetail) {
			if (this.hasId("lubricationDetail.id")) {
				this.lubricationDetail = this.lubricationPlanDetailManager.loadLubricationPlanDetail(this.getId("lubricationDetail.id"));
				if (!this.hasId("device.id")) {
					this.device = this.lubricationDetail.getDevice();
				}
				if (!this.hasId("lubricationOil.id")) {
					this.lubricationOil = this.lubricationDetail.getLubricatingOil();
				}
			} else {
				this.lubricationDetail = new LubricationPlanDetail();
			}
		}
		if (this.hasId("lubrication.id")) {
			this.lubrication = this.lubricationPlanManager.loadLubricationPlan(this.getId("lubrication.id"));
		}
	}
	
	public String save() {
		boolean isNew = this.lubricationDetail.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			this.lubricationDetail.setDevice(device);
		}
		if (!StringUtils.isEmpty(request.getParameter("lubricationOil.id"))) {
			this.lubricationOil = this.lubricationOilManager.loadLubricationOil(this.getId("lubricationOil.id"));
			this.lubricationDetail.setLubricatingOil(lubricationOil);
		}
		/*
		if (!StringUtils.isEmpty(request.getParameter("lubricationMethod.id"))) {
			this.lubricationDetail.setMethodDsp(this.codeValueManager.loadCodeValue(this.getId("lubricationMethod.id")));
		}
		*/
		if (!StringUtils.isEmpty(request.getParameter("planDutyPeople.name"))) {
			this.lubricationDetail.setPlanExePeople(request.getParameter("planDutyPeople.name"));
		}
		this.lubricationDetail.setPlan(lubrication);
		
		this.lubricationPlanDetailManager.storeLubricationPlanDetail(lubricationDetail);
		if (isNew) {
			this.addActionMessage(this.getText("lubricationDetail.add.success",
					Arrays.asList(new Object[] { device.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("lubricationDetail.edit.success",
					Arrays.asList(new Object[] { device.getName() })));
			return SUCCESS;
		}
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public LubricationPlan getLubrication() {
		return lubrication;
	}

	public void setLubrication(LubricationPlan lubrication) {
		this.lubrication = lubrication;
	}

	public LubricationPlanDetail getLubricationDetail() {
		return lubricationDetail;
	}

	public void setLubricationDetail(LubricationPlanDetail lubricationDetail) {
		this.lubricationDetail = lubricationDetail;
	}
	
	/**
	 * 获取所有的润滑方法
	 * @return List 润滑方法集合
	 */
	public List getLubricationMethods() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.LUBRICATION_METHOD);
	}

	public LubricationOil getLubricationOil() {
		return lubricationOil;
	}

	public void setLubricationOil(LubricationOil lubricationOil) {
		this.lubricationOil = lubricationOil;
	}

}
