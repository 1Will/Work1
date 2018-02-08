package com.yongjun.tdms.service.runmaintenance.calibration.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.calibration.CalibrationDetailDao;
import com.yongjun.tdms.model.runmaintenance.calibration.Calibration;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationPassType;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationResultType;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceResultType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;

public class DefaultCalibrationDetailManager implements CalibrationDetailManager{
	private final CalibrationDetailDao calibrationDetailDao;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final DeviceCardManager toolingCardManager;
	
	public DefaultCalibrationDetailManager (CalibrationDetailDao calibrationDetailDao,
			DepartmentManager departmentManager,
			UserManager userManager,
			DeviceCardManager toolingCardManager) {
		this.calibrationDetailDao = calibrationDetailDao;
		this.departmentManager = departmentManager;
		this.userManager= userManager;
		this.toolingCardManager = toolingCardManager;
	}
	
	public List<CalibrationDetail> loadAllCalibrationDetails(Long[] calibrationDetailIds) {
		return this.calibrationDetailDao.loadAllCalibrationDetails(calibrationDetailIds);
	}

	public CalibrationDetail loadCalibrationDetail(Long calibrationDetailId) {
		return this.calibrationDetailDao.loadCalibrationDetail(calibrationDetailId);
	}

	public void storeCalibrationDetail(CalibrationDetail calibrationDetail) {
		this.calibrationDetailDao.storeCalibrationDetail(calibrationDetail);
	}

	public void storeCalibrationPlanDetail(String allCalibrationDetailDutyPeople,
			    String allDate,
				String allCalibrationDetailId) {
		String [] dutyPeople = null;
		String [] date = null;
		String [] calibrationDetailId = null;
		int calibrationDetailIdNum = 0;
		CalibrationDetail calibrationDetail = null;
		
		if (null != allCalibrationDetailId) {
			calibrationDetailId = allCalibrationDetailId.split(",");    //标定明细ID  
		}
		if (null != allDate) {
			date = allDate.split(",");
		}
		if (null != allCalibrationDetailDutyPeople) {
			dutyPeople = allCalibrationDetailDutyPeople.split(",");          //计划明细中的责任人
		}
		updateCalibrationPlanDetailContent (calibrationDetailId,
				date,
				dutyPeople);
	}
		
	private void updateCalibrationPlanDetailContent(String [] calibrationDetailId,
			String [] date,
			String [] dutyPeople){
		int detailNum = 0;
		while (detailNum < calibrationDetailId.length) {
			CalibrationDetail detail = this.calibrationDetailDao.loadCalibrationDetail(Long.valueOf(calibrationDetailId[detailNum]));
			
			if (null != date) {
				for (int i=0;i<date.length;i+=2) {
					if (date[i].equals(calibrationDetailId[detailNum])) {
						detail.setPlanDate(DateUtil.toDate(date[i+1],"yyyy-MM-dd"));
						break;
					}else {
						detail.setPlanDate(null);
					}
				}
			}else {
				detail.setPlanDate(null);
			}
			if (null != dutyPeople) {                   //更新负责人
				for (int i=0; i<dutyPeople.length; i=i+2) {
					if (dutyPeople[i].equals(calibrationDetailId[detailNum])) {
						detail.setDutyPeople(this.userManager.loadUser(Long.valueOf(dutyPeople[i+1])));
						break;
					} else {
						detail.setDutyPeople(null);
					}
				}
			} else {
				detail.setDutyPeople(null);
			}
		this.calibrationDetailDao.storeCalibrationDetail(detail);
		detailNum++;
		}
	}
	
	public void storeCalibrationProcDetail(String allResult, String allDate, String allCalibrationDetailId,String allCalibrationResult) {
		String [] result = null;
		String [] date = null;
		String [] calibrationDetailId = null;
		String [] calibrationResult = null;
		
		if (null != allCalibrationDetailId) {
			calibrationDetailId = allCalibrationDetailId.split(",");
		}
		if (null != allDate) {
			date = allDate.split(",");
		}
		if (null != allResult) {
			result = allResult.split(",");
		}
		if (null != allCalibrationResult) {
			calibrationResult = allCalibrationResult.split(",");
		}
		updateCalibrationProcDetailContent (calibrationDetailId,date,result,calibrationResult);
	}

	private void updateCalibrationProcDetailContent(
			String [] calibrationDetailId,String [] date,String [] result,String [] calibrationResult) {
		int detailNum = 0;
		while (detailNum < calibrationDetailId.length) {
			CalibrationDetail detail = this.calibrationDetailDao.loadCalibrationDetail(Long.valueOf(calibrationDetailId[detailNum]));
			
			if (null != date) {
				for (int i=0;i<date.length;i+=2) {
					if (date[i].equals(calibrationDetailId[detailNum])) {
						detail.setActualDate(DateUtil.toDate(date[i+1],"yyyy-MM-dd"));
						break;
					}else {
						detail.setActualDate(null);
					}
				}
			}else {
				detail.setActualDate(null);
			}
			if (null != result) {
				for (int i=0; i<result.length; i=i+2) {
					if (result[i].equals(calibrationDetailId[detailNum])) {
						if (result[i+1].equals(CalibrationResultType.UNFINISHED.toString())) {
							detail.setResult(CalibrationResultType.UNFINISHED);
						} else {
							detail.setResult(CalibrationResultType.FINISHED);
						}
						break;
					} else {
						detail.setResult(null);
					}
				}
			} else {
				detail.setResult(null);
			}
			if (null != calibrationResult) {
				for (int i=0; i<calibrationResult.length; i=i+2) {
					if (calibrationResult[i].equals(calibrationDetailId[detailNum])) {
						if (calibrationResult[i+1].equals(CalibrationPassType.PASS.toString())) {
							detail.setCalibrationResult(CalibrationPassType.PASS);
						} else {
							detail.setCalibrationResult(CalibrationPassType.NOPASS);
						}
						break;
					} else {
						detail.setCalibrationResult(null);
					}
				}
			} else {
				detail.setCalibrationResult(null);
			}
			this.calibrationDetailDao.storeCalibrationDetail(detail);
			detail.getTooling().setPreDemarcateTime(detail.getActualDate());
			toolingCardManager.storeDevice(detail.getTooling());
			detailNum++;
		}
	}
	
	public void storeCalibrationProcDetail(String allCalibrationDetailResult,
			String allCalibrationDetailId) {
		String [] procExecResult = null;
		
		String [] calibrationDetailId = null;
		int calibrationDetailIdNum = 0;
		CalibrationDetail calibrationDetail = null;
		
		if (null != allCalibrationDetailId) {
			calibrationDetailId = allCalibrationDetailId.split(",");    //标定明细ID  
		}
		if (null != allCalibrationDetailResult) {
			procExecResult = allCalibrationDetailResult.split(",");  
		}
		
		while(calibrationDetailId != null && calibrationDetailIdNum < calibrationDetailId.length) {
			calibrationDetail = this.calibrationDetailDao.loadCalibrationDetail(Long.valueOf(calibrationDetailId[calibrationDetailIdNum]));
			if (null != procExecResult) {
				for (int i=0; i<procExecResult.length; i++) {
					if (procExecResult[i].equals(calibrationDetailId[calibrationDetailIdNum]))	{
						if (procExecResult[i+1].equals(CalibrationResultType.FINISHED.toString())) {
							calibrationDetail.setResult(CalibrationResultType.FINISHED);
						}else {
							calibrationDetail.setResult(CalibrationResultType.UNFINISHED);
						}
					}
				}
			}
		}
		this.calibrationDetailDao.storeCalibrationDetail(calibrationDetail);
		calibrationDetailIdNum++;
	}

	public void deleteAllcalibrationDetails(Collection<CalibrationDetail> calibrationDetails) throws Exception {
		for (CalibrationDetail calibrationDetail : calibrationDetails) {
			if(calibrationDetail.getResult().equals(CalibrationResultType.FINISHED)) {
				throw new Exception();
			}
		}
		this.calibrationDetailDao.deleteAllcalibrationDetails(calibrationDetails);
	}

	public void storeCalibrationDetail(Calibration calibrationPlan, String addToolingIds) {
		String [] toolingId = null;
		if (null != addToolingIds) {
			toolingId = addToolingIds.split(",");
		}
		for (int i=0; i<toolingId.length; i++) {
			CalibrationDetail calibrationDetail = new CalibrationDetail();
			calibrationDetail.setTooling(this.toolingCardManager.loadDevice(Long.valueOf(toolingId[i])));
			calibrationDetail.setPlan(calibrationPlan);
			for (Calibration calibrationProc : calibrationPlan.getCalibrationProc()) {
				calibrationDetail.setProc(calibrationProc);
			}
			calibrationDetail.setResult(CalibrationResultType.UNFINISHED);
			//calibrationDetail.setCalibrationResult(CalibrationPassType.PASS);
			this.calibrationDetailDao.storeCalibrationDetail(calibrationDetail);
		}
	}

}
