package com.yongjun.tdms.presentation.webwork.action.runmaintenance.calibration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationPassType;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationResultType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationManager;

public class ListCalibrationDetailAction extends ValueListAction {

	private static final long serialVersionUID = 2154511263295403588L;
	private final Log log = LogFactory.getLog(getClass());
	private final CalibrationDetailManager calibrationDetailManager;
	private final CalibrationManager calibrationManager;
	private final DepartmentManager departmentManager;
	
	private List<CalibrationDetail> calibrationDetails;
	private CalibrationDetail calibrationDetail;
	private Calibration calibration;
	private String planProcFlag;
	private String addToolingIds;                  				//新添加的工装ID字符串
	private String allCalibrationDetailId;						//明细列表中所有的ID值
	private String allDate; 				  					//计划完成时间或实施完成时间
	private String allDutyPeople;								//列表中的所有明细的ID和责任人ID
	private String allResult;									//实施明细列表中的实施结果的所有值
	private String allCalibrationResult;						//实施明细列表中的标定结果的所有值
	
	public ListCalibrationDetailAction(CalibrationDetailManager calibrationDetailManager,
			CalibrationManager calibrationManager,
			DepartmentManager departmentManager){
		this.calibrationDetailManager = calibrationDetailManager;
		this.calibrationManager = calibrationManager;
		this.departmentManager = departmentManager;
	}
	
	public void prepare() {
		if (this.hasId("calibration.id")) {
			this.calibration = this.calibrationManager.loadCalibration(this.getId("calibration.id"));
		}
		if (this.hasId("calibrationDetail.id"))	{
			this.calibrationDetail = this.calibrationDetailManager.loadCalibrationDetail(this.getId("calibrationDetail.id"));
		}
		if (null == this.calibrationDetails) {
			if (this.hasId("calibrationDetailIds")) {
				this.calibrationDetails = this.calibrationDetailManager.loadAllCalibrationDetails(this.getIds("calibrationDetailIds"));
			}
		}
		if (null == this.addToolingIds) {
			if (!StringUtils.isEmpty(request.getParameter("addToolingIds"))) {
				this.addToolingIds = request.getParameter("addToolingIds");
				log.debug("addToolingIds is " + this.addToolingIds);
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isAddTooling()) {
			return this.saveAddTooling();
		}
		if (this.isDelete()) {
			return delete();
		}
		if (this.isSave()) {
			return save();
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			this.calibrationDetailManager.deleteAllcalibrationDetails (calibrationDetails);
		} catch (Exception e) {
			this.addActionMessage(this.getText("calibrationDetails.delete.error"));
			return ERROR;
		}
		this.addActionMessage(this.getText("calibrationDetails.delete.success"));
		return SUCCESS;
	}
	
	public String save () {
		if (!StringUtils.isEmpty(request.getParameter("allCalibrationDetailId"))) {
			this.allCalibrationDetailId = request.getParameter("allCalibrationDetailId");
		}
		if (!StringUtils.isEmpty(request.getParameter("allDate"))) {
			this.allDate = request.getParameter("allDate");
		}
		if (!StringUtils.isEmpty(request.getParameter("allDutyPeople"))) {
			this.allDutyPeople = request.getParameter("allDutyPeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("allResult"))) {
			 this.allResult = request.getParameter("allResult");
		}
		if (!StringUtils.isEmpty(request.getParameter("allCalibrationResult"))) {
			 this.allCalibrationResult = request.getParameter("allCalibrationResult");
		}
		
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			this.calibrationDetailManager.storeCalibrationPlanDetail(
					allDutyPeople,
					allDate,
					allCalibrationDetailId);
		}
		else {
			this.calibrationDetailManager.storeCalibrationProcDetail(
					allResult,
					allDate,
					allCalibrationDetailId,
					allCalibrationResult);
		}
		return SUCCESS;
	}
	
	public boolean isSave() {
		return this.hasKey("save");
	}
	
	/**
	 * 判断页面传来的addTooling变量是否有值，且值是否等于addToolings
	 * 
	 * @return boolean true 添加新的工装 | false 不添加
	 */
	private boolean isAddTooling() {
		if (!StringUtils.isEmpty(request.getParameter("addTooling"))) {
			if (request.getParameter("addTooling").equals("addToolings"))
				return true;
		}
		return false;
	}
	
	/**
	 * 保存新添加的工装为标定计划明细
	 * @return SUCCESS
	 */
	public String saveAddTooling() {
		this.calibrationDetailManager.storeCalibrationDetail(calibration,addToolingIds);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("calibration.id")){
        	if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
        		map.put("calibrationPlan.id", this.getId("calibration.id"));
        	} else {
        		map.put("calibrationProc.id", this.getId("calibration.id"));
        	}
			
		}
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "calibrationPlanDetail";
		} else {
			return "calibrationProcDetail";
		}
	}
	
	/**
	 * 获取所有部门的集合
	 * @return List 部门的集合
	 */
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText(""));
	}
	
	public List<LabelValue> getProcResults() {
		LabelValue[] arrays = this.wrapEnum(CalibrationResultType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	public List<LabelValue> getPassResults() {
		LabelValue[] arrays = this.wrapEnum(CalibrationPassType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public CalibrationDetail getCalibrationDetail() {
		return calibrationDetail;
	}
	public void setCalibrationDetail(CalibrationDetail calibrationDetail) {
		this.calibrationDetail = calibrationDetail;
	}
	public List<CalibrationDetail> getCalibrationDetails() {
		return calibrationDetails;
	}
	public void setCalibrationDetails(List<CalibrationDetail> calibrationDetails) {
		this.calibrationDetails = calibrationDetails;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	public Calibration getCalibration() {
		return calibration;
	}

	public void setCalibration(Calibration calibration) {
		this.calibration = calibration;
	}

	public String getAddToolingIds() {
		return addToolingIds;
	}

	public void setAddToolingIds(String addToolingIds) {
		this.addToolingIds = addToolingIds;
	}


}
