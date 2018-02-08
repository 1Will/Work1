package com.yongjun.tdms.service.runmaintenance.lubrication;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;

@Transactional(readOnly=true)
public interface LubricationPlanDetailManager {
	
	/**
	 * 存储润滑计划实体
	 */
	@Transactional
	void storeLubricationPlanDetail(LubricationPlanDetail lubricationPlanDetail);

	/**
	 * 根据Id数组的集合加载所有的润滑计划实体
	 */
	List<LubricationPlanDetail> loadAllLubricationPlanDetail(Long[] lubricationPlanDetailIds);

	/**
	 * 加载所有的润滑计划实体
	 */
	LubricationPlanDetail loadLubricationPlanDetail(Long id);

	/**
	 * 删除所有的润滑计划实体集合
	 */
	@Transactional
	void deleteAllLubricationPlanDetail(List<LubricationPlanDetail> list) throws Exception;
	
	/**
	 * 根据润滑明细ID字符串,计划执行人字符串,计划执行时间字符串,备注字符串,更新润滑计划明细字段值
	 * @param allLubricationPlanDetailId 润滑明细ID字符串
	 * @param allLubricationOilQty  润滑计量的字符串
	 * @param allPlanExePeople   计划执行人字符串
	 * @param allEstimateExecDate  计划执行时间字符串
	 * @param allComment 备注字符串
	 */
	@Transactional
	void storeLubricationPlanDetail(String allLubricationPlanDetailId, String allLubricationOilQty,
			String allPlanExePeople,String allEstimateExecDate, String allComment,String allPalnExectPeople);
	
	/**
	 * 根据润滑明细ID字符串,实际执行人字符串,实际执行时间字符串,实际润滑计量字符串,执行结果字符串,更新润滑实施明细字段值
	 * @param allLubricationProcDetailId  润滑明细ID字符串
	 * @param allProcExePeople  实际执行人字符串
	 * @param allProcEstimateExecDate 实际执行时间字符串
	 * @param allLubricationOilQty 实际润滑计量字符串
	 * @param allLubricationResult 执行结果字符串
	 */
	@Transactional
	void storeLubricationProcDetail(String allLubricationProcDetailId,
			String allProcEstimateExecDate,
			String allProcLubricationOilQty, String allLubricationResult,String allActualExectPeople);
	
	/**
	 * 把润滑计划明细中的实际执行时间,实际润滑计量,实际执行人,设置为计划执行时间,计划润滑计量,计划执行人的值
	 * @param procDetail  实施明细
	 * @param planDetail  计划明细
	 */
	void setLubricationProcDetailDefaultValue(LubricationPlanDetail procDetail, LubricationPlanDetail planDetail);
}
