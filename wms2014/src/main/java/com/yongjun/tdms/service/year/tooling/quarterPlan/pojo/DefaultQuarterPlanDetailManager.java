/**
 * 
 */
package com.yongjun.tdms.service.year.tooling.quarterPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.year.tooling.quarterPlan.QuarterPlanDao;
import com.yongjun.tdms.dao.year.tooling.quarterPlan.QuarterPlanDetailDao;
import com.yongjun.tdms.dao.year.tooling.yearPlan.YearPlanDetailDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.tooling.pojo.CalculateAllFeeManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;

/**
 * @author zhangzhibao
 *
 */
public class DefaultQuarterPlanDetailManager extends CalculateAllFeeManager implements
		QuarterPlanDetailManager {

	
	private final QuarterPlanDetailDao quarterPlanDetailDao;
	private final YearPlanDetailDao yearPlanDetailDao;
	private final SpareManager spareManager;
	private final BudgetDetailManager budgetDetailManager;
	
	public DefaultQuarterPlanDetailManager(QuarterPlanDetailDao quarterPlanDetailDao,
			YearPlanDetailDao yearPlanDetailDao,
			SpareManager spareManager,
			BudgetDetailManager budgetDetailManager) {
		this.quarterPlanDetailDao = quarterPlanDetailDao;
		this.yearPlanDetailDao = yearPlanDetailDao;
		this.spareManager = spareManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	public void deleteAllQuarterPlanDetails(
			Collection<QuarterPlanDetail> quarterPlanDetails) {
		this.quarterPlanDetailDao.deleteAllQuarterPlanDetails(quarterPlanDetails);
	}

	public void deleteQuarterPlanDetail(QuarterPlanDetail QuarterPlanDetail) {
		this.quarterPlanDetailDao.deleteQuarterPlanDetail(QuarterPlanDetail);
	}

	public List<QuarterPlanDetail> loadAllQuarterPlanDetails(
			Long[] quarterPlanDetailIds) {
		return this.quarterPlanDetailDao.loadAllQuarterPlanDetails(quarterPlanDetailIds);
	}

	public List<QuarterPlanDetail> loadAllQuarterPlanDetails() {
		return this.quarterPlanDetailDao.loadAllQuarterPlanDetails();
	}

	public QuarterPlanDetail loadQuarterPlanDetail(Long quarterPlanDetailId) {
		return this.quarterPlanDetailDao.loadQuarterPlanDetail(quarterPlanDetailId);
	}

	public void storeQuarterPlanDetail(QuarterPlanDetail quarterPlanDetail) {
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlanDetail.getQuarterPlan().getDepartment(),
				quarterPlanDetail.getQuarterPlan().getYear(), quarterPlanDetail.getDetailType().toString());
		// 按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlanDetail.getQuarterPlan().getId(),
				quarterPlanDetail.getDetailType().toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		this.quarterPlanDetailDao.storeQuarterPlanDetail(quarterPlanDetail);
		//计算季度计划的总费用
		QuarterPlan quarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlanDetail.getQuarterPlan());
		
		//获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				quarterPlanDetail.getDetailType().toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()+detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	public void storeQuarterPlanToolingMakeDetail(QuarterPlan quarterPlan, 
			String addYearToolingQuarterPlanDetailIds) {
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.TOOLING_MAKE.toString());
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TOOLING_MAKE.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		String addYearToolingQuarterPlanDetailId [] = null;
		if (null == addYearToolingQuarterPlanDetailIds) {
			return ;
		}
		addYearToolingQuarterPlanDetailId = addYearToolingQuarterPlanDetailIds.split(",");
		//根据年度计划的工装制作明细ID,创建季度计划的工装制作明细
		for (int i=0; i<addYearToolingQuarterPlanDetailId.length; i++) {
			QuarterPlanDetail quarterDetail = new QuarterPlanDetail();    //创建新的季度计划的工装制作明细
			//根据工装制作明细ID,获取年度计划的工装制作明细ID
			YearPlanDetail  yearDetail = this.yearPlanDetailDao.loadYearPlanDetail(Long.valueOf(addYearToolingQuarterPlanDetailId[i]));
			//拷贝年度计划中的工装制作明细到季度计划的工装制作明细
			copyYearToolingMakeDetailToQuarterToolingMakeDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearPlanDetail(yearDetail);       //设置相关的年度计划中的工装制作明细
			quarterDetail.setDetailType(YearPlanDetailCategory.TOOLING_MAKE);     //设置该季度计划明细为工装制造明细
			this.quarterPlanDetailDao.storeQuarterPlanDetail(quarterDetail);     //保存季度计划工装制作明细
			
			//设置该年度计划的工装制作明细为已列入季度计划
			this.setCreatedQuartePlanStatusForYearPlanDetail(yearDetail);
			
			//季度计划添加工装制作明细
			quarterPlan.getQuarterPlanDetails().add(quarterDetail);
		}
		//计算季度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
         //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.TOOLING_MAKE.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	/**
	 * 拷贝年度计划中的工装制作明细到季度计划的工装制作明细
	 * @param yearDetail 年度计划中的工装制作明细
	 * @param quarterDetail 季度计划的工装制作明细
	 */
	private void copyYearToolingMakeDetailToQuarterToolingMakeDetail(YearPlanDetail yearDetail, 
			QuarterPlanDetail quarterDetail) {
		quarterDetail.setGraphNo(yearDetail.getGraphNo());                  				//拷贝图号
		quarterDetail.setName(yearDetail.getName());	                    				//拷贝品名
		if(yearDetail.getToolingCategory()!=null){
			quarterDetail.setToolingCategory(yearDetail.getToolingCategory());  			//拷贝类别Id
		}
		quarterDetail.setCategoryName(yearDetail.getCategoryName());        				//拷贝类别名称
		quarterDetail.setDetailCategoryName(yearDetail.getDetailCategoryName()); 			//拷贝明细类别名称
		if(yearDetail.getDetailCategory()!=null){
			quarterDetail.setDetailCategory(yearDetail.getDetailCategory());
		}
		quarterDetail.setSpecification(yearDetail.getSpecification());      				//拷贝规格
		quarterDetail.setModel(yearDetail.getModel());                      				//拷贝型号
		quarterDetail.setUnitPrice(yearDetail.getUnitPrice());              				//拷贝单价
		quarterDetail.setNumber(yearDetail.getNumber());                    				//拷贝数量
		quarterDetail.setAllPrice(yearDetail.getAllPrice());                				//拷贝总费用
		quarterDetail.setRequestDate(yearDetail.getRequestDate());          				//拷贝需求日期
		quarterDetail.setRequestReason(yearDetail.getRequestReason());         				//拷贝需求原因
		quarterDetail.setComment(yearDetail.getComment());                     				//拷贝备注
		if(yearDetail.getCalUnit()!=null){
			quarterDetail.setCalUnit(yearDetail.getCalUnit());								//拷贝单位
		}
	}

	public void deleteAllToolingMakeDetailOfQuarterPlan(
			Collection<QuarterPlanDetail> toolingMakeDetails, QuarterPlan quarterPlan) throws Exception{
		//如果该明细已制定采购单,抛出异常,
		for (QuarterPlanDetail detail : toolingMakeDetails) {
			if (detail.isCreatePurchaseFlag()) {
				throw new Exception();
			}
		}
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.TOOLING_MAKE.toString());
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TOOLING_MAKE.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		//删除传入工装制作明细
		this.quarterPlanDetailDao.deleteAllQuarterPlanDetails(toolingMakeDetails);
        //清除季度计划关联的工装制作明细,以及改变该明细关联的年度计划的工装制作明细为未列入季度计划状态
		for (QuarterPlanDetail detail : toolingMakeDetails) {
			if (null != detail.getYearPlanDetail()) {
				this.setNotCreatedQuartePlanStatusForYearPlanDetail(detail.getYearPlanDetail());
			}
			quarterPlan.getQuarterPlanDetails().remove(detail);
		}
		//更新季度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.TOOLING_MAKE.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	public void storeQuarterPlanSparePurchaseDetail(QuarterPlanDetail quarterPlanDetail) {
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlanDetail.getQuarterPlan().getDepartment(),
				quarterPlanDetail.getQuarterPlan().getYear(), quarterPlanDetail.getDetailType().toString());
		// 按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlanDetail.getQuarterPlan().getId(),
				quarterPlanDetail.getDetailType().toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		this.quarterPlanDetailDao.storeQuarterPlanDetail(quarterPlanDetail);
		//计算季度计划的总费用
		QuarterPlan quarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlanDetail.getQuarterPlan());
		
		//获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				quarterPlanDetail.getDetailType().toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	public void storeQuarterPlanSparePurchaseDetail(QuarterPlan quarterPlan, 
			String addYearSparePurchaseDetailIds) {
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.SPARE_PURCHASE.toString());
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		String addYearSparePurchaseDetailId [] = null;
		if (null == addYearSparePurchaseDetailIds) {
			return ;
		}
		addYearSparePurchaseDetailId = addYearSparePurchaseDetailIds.split(",");
		//根据年度计划的备件采购明细ID,创建季度计划的备件采购明细
		for (int i=0; i<addYearSparePurchaseDetailId.length; i++) {
			QuarterPlanDetail quarterDetail = new QuarterPlanDetail();    //创建新的季度计划的备件采购明细
			//根据备件采购明细ID,获取年度计划的备件采购明细ID
			YearPlanDetail yearDetail = this.yearPlanDetailDao.loadYearPlanDetail(Long.valueOf(addYearSparePurchaseDetailId[i]));
			//拷贝年度计划中的备件采购明细到季度计划的备件采购明细
			copyYearSparePurchaseDetailToQuarterSparePurchaseDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearPlanDetail(yearDetail);      //设置相关的年度计划中的备件采购明细
			this.quarterPlanDetailDao.storeQuarterPlanDetail(quarterDetail);    //保存季度计划备件采购明细
			
			//设置该年度计划的备件采购明细为已列入季度计划
			this.setCreatedQuartePlanStatusForYearPlanDetail(yearDetail);
			
			//季度计划添加备件采购明细
			quarterPlan.getQuarterPlanDetails().add(quarterDetail);
		}
		
		//计算季度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	/**
	 * 拷贝年度计划中的备件采购明细到季度计划的备件采购明细
	 * @param yearDetail 年度计划中的备件采购明细
	 * @param quarterDetail 季度计划的备件采购明细
	 */
	private void copyYearSparePurchaseDetailToQuarterSparePurchaseDetail(YearPlanDetail yearDetail, 
			QuarterPlanDetail quarterDetail) {
		quarterDetail.setGraphNo(yearDetail.getGraphNo());                       					//拷贝图号
		quarterDetail.setName(yearDetail.getName());                            					//  拷贝品名
		if(yearDetail.getToolingCategory()!=null){
			quarterDetail.setToolingCategory(yearDetail.getToolingCategory());  					//拷贝类别Id
		}
		quarterDetail.setCategoryName(yearDetail.getCategoryName());            					//拷贝类别
		quarterDetail.setDetailCategoryName(yearDetail.getDetailCategoryName());					//拷贝明细类别
		quarterDetail.setCategory(yearDetail.getCategory());      									//  拷贝类别
		quarterDetail.setDetailCategory(yearDetail.getDetailCategory());       						//拷贝明细类别
		quarterDetail.setSpecification(yearDetail.getSpecification());         						//拷贝规格
		quarterDetail.setModel(yearDetail.getModel());                         						//拷贝型号
		quarterDetail.setUnitPrice(yearDetail.getUnitPrice());                						 //拷贝单价
		quarterDetail.setNumber(yearDetail.getNumber());                       						//拷贝数量
		quarterDetail.setAllPrice(yearDetail.getAllPrice());                   						//拷贝总价
		quarterDetail.setRequestDate(yearDetail.getRequestDate());             						//拷贝需求日期
	//	quarterDetail.setUsedTooling(yearDetail.getUsedTooling());             						//拷贝使用工装
		quarterDetail.setRequestReason(yearDetail.getRequestReason());         						//拷贝需求原因
		quarterDetail.setComment(yearDetail.getComment());                     						//拷贝备注
		quarterDetail.setDetailType(yearDetail.getDetailType());               						//拷贝明细类别[备件采购]
		if(yearDetail.getCalUnit()!=null){
			quarterDetail.setCalUnit(yearDetail.getCalUnit());										//拷贝单位
		}
		if(yearDetail.getSpare()!=null){															//关联备件
			quarterDetail.setSpare(yearDetail.getSpare());
		}
	}

	public void setCreatedQuartePlanStatusForYearPlanDetail(YearPlanDetail yearPlanDetail) {
		yearPlanDetail.setCreateQuarterFlag(true);      //设置该年度计划明细已制定了季度计划状态
		this.yearPlanDetailDao.storeYearPlanDetail(yearPlanDetail);
	}

	public void setNotCreatedQuartePlanStatusForYearPlanDetail(YearPlanDetail yearPlanDetail) {
		yearPlanDetail.setCreateQuarterFlag(false);      //设置该年度计划明细为未制定季度计划状态
		this.yearPlanDetailDao.storeYearPlanDetail(yearPlanDetail);		
	}

	public void storeSpareForQuarterPlan(QuarterPlan quarterPlan, String allSparePurchaseDetailIds, String allNumbers, String allRequestDates, String allRequestReasons, String allComments, String allUnitPrices) {
		String [] allSparePurchaseDetailId = null;      //采购明细id数组
		String [] allNumber = null;                     //数量数组
		String [] allRequestDate = null;                //需求日期数组
		String [] allRequestReason = null;              //需求原因数组
		String [] allComment = null;                   //备注数组
		String [] allUnitPrice = null;                 //单价数组
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.SPARE_PURCHASE.toString());
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		//备件采购明细id分割
		if (null != allSparePurchaseDetailIds) {
			allSparePurchaseDetailId =  allSparePurchaseDetailIds.split(",");
		}
		//数量分割
		if (null != allNumbers) {
			allNumber = allNumbers.split(",");
		}
		//需求日期分割
		if (null != allRequestDates) {
			allRequestDate = allRequestDates.split(",");
		}
		//需求原因分割
		if (null != allRequestReasons) {
			allRequestReason = allRequestReasons.split(",");
		}
		//备注分割
		if (null != allComments) {
			allComment = allComments.split(",");
		}
		//单价分割
		if (null != allUnitPrices) {
			allUnitPrice = allUnitPrices.split(",");
		}
        //更新备件采购明细的数量，需求日期，需求原因，备注的值
		this.updateSparePurchaseDetailValueForQuarterPlan(quarterPlan, allSparePurchaseDetailId,allNumber,
				allRequestDate, allRequestReason, allComment,allUnitPrice);
        //计算年度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	//更新备件采购明细的数量，需求日期，需求原因，备注的值
	private void updateSparePurchaseDetailValueForQuarterPlan (QuarterPlan quarterPlan, String [] allSparePurchaseDetailId, String [] allNumber, String [] allRequestDate,
			String [] allRequestReason, String [] allComment,String [] allUnitPrice) {
		int sparePurchaseCount = 0;
		
		while (null != allSparePurchaseDetailId && sparePurchaseCount<allSparePurchaseDetailId.length) {
			QuarterPlanDetail sparePurchaseDetail = this.quarterPlanDetailDao.loadQuarterPlanDetail(Long.valueOf(allSparePurchaseDetailId[sparePurchaseCount]));
		   
			if (null != allUnitPrice) {     //设置备件采购明细单价
		    	for (int i=0; i<allUnitPrice.length; i=i+2) {
		    		if (allUnitPrice[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setUnitPrice(Double.valueOf(allUnitPrice[i+1]));
		    			break;
		    		} else {
		    			sparePurchaseDetail.setUnitPrice(0.0);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setUnitPrice(0.0);
		    }
			if (null != allNumber) {     //设置备件采购明细数量
		    	for (int i=0; i<allNumber.length; i=i+2) {
		    		if (allNumber[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setNumber(Integer.valueOf(allNumber[i+1]));
		    			//设置总价
		    			sparePurchaseDetail.setAllPrice(sparePurchaseDetail.getUnitPrice()*Integer.valueOf(allNumber[i+1]));
		    			break;
		    		} else {
		    			sparePurchaseDetail.setNumber(0);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setNumber(0);
		    }
		    if (null != allRequestDate) {      //设置备件采购明细需求日期
		    	for (int i=0; i<allRequestDate.length; i=i+2) {
		    		if (allRequestDate[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setRequestDate(DateUtil.toDate(allRequestDate[i+1], "yyyy-MM-dd"));
		    			break;
		    		} else {
		    			sparePurchaseDetail.setRequestDate(null);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setRequestDate(null);
		    }
		    if (null != allRequestReason) {    //设置备件采购明细需求原因   
		    	for (int i=0; i<allRequestReason.length; i=i+2) {
		    		if (allRequestReason[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setRequestReason(allRequestReason[i+1]);
		    			break;
		    		} else {
		    			sparePurchaseDetail.setRequestReason(null);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setRequestReason(null);
		    }
		    if (null != allComment) {    //设置备件采购明细备注  
		    	for (int i=0; i<allComment.length; i=i+2) {
		    		if (allComment[i].equals(allSparePurchaseDetailId[sparePurchaseCount])) {
		    			sparePurchaseDetail.setComment(allComment[i+1]);
		    			break;
		    		} else {
		    			sparePurchaseDetail.setComment(null);
		    		}
		    	}
		    } else {
		    	sparePurchaseDetail.setComment(null);
		    }
		    sparePurchaseCount++;
		    this.quarterPlanDetailDao.storeQuarterPlanDetail(sparePurchaseDetail);
		    quarterPlan.getQuarterPlanDetails().add(sparePurchaseDetail);
		}
	}

	public void storeNewAddSpareForQuarterPlan(QuarterPlan quarterPlan, String addSpareIds) {
		String[] spareIds = addSpareIds.split(",");
		for (int i=0; i<spareIds.length; i++) {
			QuarterPlanDetail sparePurchaseDetail = new QuarterPlanDetail();
			sparePurchaseDetail.setQuarterPlan(quarterPlan);
			Spare spare = this.spareManager.loasSpare(Long.valueOf(spareIds[i]));
			//设置关联的备件
			sparePurchaseDetail.setSpare(spare);
			//设置关联的图号
			sparePurchaseDetail.setGraphNo(spare.getSpareNo());
			//设置备件名称
			sparePurchaseDetail.setName(spare.getName());
			//设置备件规格
			sparePurchaseDetail.setSpecification(spare.getSpecification());
			//设置备件型号
			sparePurchaseDetail.setModel(spare.getModelSpecs());
			//设置备件单价
			sparePurchaseDetail.setUnitPrice(spare.getUnitPrice());
			//设置备件类别名称
			if (null != spare.getCategory()) {
				sparePurchaseDetail.setCategoryName(spare.getCategory().getName());
				sparePurchaseDetail.setCategory(spare.getCategory());
			}
			//设置备件明细类别名称
			if (null != spare.getSpareDetailType()) {
				sparePurchaseDetail.setDetailCategoryName(spare.getSpareDetailType().getName());
				sparePurchaseDetail.setDetailCategory(spare.getSpareDetailType());
			}
			//设置关联单位
			if(null != spare.getUnit()){
				sparePurchaseDetail.setCalUnit(spare.getUnit());
			}
			sparePurchaseDetail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
			this.quarterPlanDetailDao.storeQuarterPlanDetail(sparePurchaseDetail);
		}
	}
	
	public void deleteAllSparePurchaseDetailOfQuarterPlan(Collection<QuarterPlanDetail> sparePurchaseDetails, 
			QuarterPlan quarterPlan) throws Exception{
		//如果该明细已制定采购单,抛出异常,
		for (QuarterPlanDetail detail : sparePurchaseDetails) {
			if (detail.isCreatePurchaseFlag()) {
				throw new Exception();
			}
		}
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.SPARE_PURCHASE.toString());
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		this.quarterPlanDetailDao.deleteAllQuarterPlanDetails(sparePurchaseDetails);
        //清除季度计划关联的备件采购明细,以及改变该明细关联的年度计划的备件采购明细为未列入季度计划状态
		for (QuarterPlanDetail detail : sparePurchaseDetails) {
			if (null != detail.getYearPlanDetail()) {
				this.setNotCreatedQuartePlanStatusForYearPlanDetail(detail.getYearPlanDetail());
			}
			quarterPlan.getQuarterPlanDetails().remove(detail);
		}
		//更新季度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.SPARE_PURCHASE.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	public void storeQuarterPlanRepairMaintenanceDetail(QuarterPlan quarterPlan, 
			String addRepairMaintenanceDetailIds) {
		String addRepairMaintenanceDetailId [] = null;
		if (null == addRepairMaintenanceDetailIds) {
			return ;
		}
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.REPAIR_MAINTENANCE.toString());
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.REPAIR_MAINTENANCE.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		addRepairMaintenanceDetailId = addRepairMaintenanceDetailIds.split(",");
		//根据年度计划的维修保养明细ID,创建季度计划的维修保养明细
		for (int i=0; i<addRepairMaintenanceDetailId.length; i++) {
			QuarterPlanDetail quarterDetail = new QuarterPlanDetail();    //创建新的季度计划的维修保养明细
			//根据维修保养明细ID,获取年度计划的维修保养明细ID
			YearPlanDetail yearDetail = this.yearPlanDetailDao.loadYearPlanDetail(Long.valueOf(addRepairMaintenanceDetailId[i]));
			//拷贝年度计划中的备件采购明细到季度计划的维修保养明细
			copyYearRepairMaintenanceDetailToQuarterRepairMaintenanceDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearPlanDetail(yearDetail);      //设置相关的年度计划中的维修保养明细
			this.quarterPlanDetailDao.storeQuarterPlanDetail(quarterDetail);    //保存季度计划维修保养明细
			
			//设置该年度计划的维修保养明细为已列入季度计划
			this.setCreatedQuartePlanStatusForYearPlanDetail(yearDetail);
			
			//季度计划添加维修保养明细
			quarterPlan.getQuarterPlanDetails().add(quarterDetail);
		}
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.REPAIR_MAINTENANCE.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	/**
	 * 拷贝年度计划中的维修保养明细到季度计划的维修保养明细
	 */
	private void copyYearRepairMaintenanceDetailToQuarterRepairMaintenanceDetail(YearPlanDetail yearDetail,
			QuarterPlanDetail quarterDetail) {
		if(yearDetail.getTooling()!=null){
			quarterDetail.setTooling(yearDetail.getTooling());             //拷贝工装
		}
		quarterDetail.setGraphNo(yearDetail.getGraphNo());             //拷贝图号
		quarterDetail.setName(yearDetail.getName());                   //拷贝名称
		if(yearDetail.getToolingCategory()!=null){
			quarterDetail.setToolingCategory(yearDetail.getToolingCategory());  //拷贝类别Id
		}
		quarterDetail.setCategoryName(yearDetail.getCategoryName());    //拷贝类别
		quarterDetail.setDetailCategoryName(yearDetail.getDetailCategoryName());
		if(yearDetail.getDetailCategory()!=null){
			quarterDetail.setDetailCategory(yearDetail.getDetailCategory());		//拷贝类别明细
		}
		quarterDetail.setModel(yearDetail.getModel());							//拷贝型号
		quarterDetail.setSpecification(yearDetail.getSpecification());			//拷贝规格
		quarterDetail.setAllPrice(yearDetail.getAllPrice());            //拷贝总费用
		quarterDetail.setRequestDate(yearDetail.getRequestDate());      //拷贝需求日起
		quarterDetail.setRequestReason(yearDetail.getRequestReason());  //拷贝需求原因
		quarterDetail.setComment(yearDetail.getComment());                      //拷贝备注
		quarterDetail.setDetailType(yearDetail.getDetailType());         //拷贝明细类别
		if(null != yearDetail.getCalUnit()){
			quarterDetail.setCalUnit(yearDetail.getCalUnit());
		}
		
	}
	
	public void deleteAllRepairMaintenanceDetailOfQuarterPlan(
			Collection<QuarterPlanDetail> repairMaintenanceDetails, QuarterPlan quarterPlan)throws Exception {
		//如果该明细已制定采购单,抛出异常,
		for (QuarterPlanDetail detail : repairMaintenanceDetails) {
			if (detail.isCreatePurchaseFlag()) {
				throw new Exception();
			}
		}
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.REPAIR_MAINTENANCE.toString());
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.REPAIR_MAINTENANCE.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		this.quarterPlanDetailDao.deleteAllQuarterPlanDetails(repairMaintenanceDetails);
		 //清除季度计划关联的维修保养明细
		for (QuarterPlanDetail detail : repairMaintenanceDetails) {
			if (null != detail.getYearPlanDetail()) {
				this.setNotCreatedQuartePlanStatusForYearPlanDetail(detail.getYearPlanDetail());
			}
			quarterPlan.getQuarterPlanDetails().remove(detail);
		}
		//更新季度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
		
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.REPAIR_MAINTENANCE.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	public void storeQuarterPlanTechAlterDetail(QuarterPlan quarterPlan, String addYearTechAlterDetailIds) {
		String addYearTechAlterDetailId [] = null;
		if (null == addYearTechAlterDetailIds) {
			return ;
		}
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.TECH_ALTER.toString());
		
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TECH_ALTER.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		addYearTechAlterDetailId = addYearTechAlterDetailIds.split(",");
		//根据年度计划的技术改造明细ID,创建季度计划的技术改造明细
		for (int i=0; i<addYearTechAlterDetailId.length; i++) {
			QuarterPlanDetail quarterDetail = new QuarterPlanDetail();    //创建新的季度计划的技术改造明细
			//根据技术改造明细ID,获取年度计划的技术改造明细ID
			YearPlanDetail yearDetail = this.yearPlanDetailDao.loadYearPlanDetail(Long.valueOf(addYearTechAlterDetailId[i]));
			//拷贝年度计划中的备件采购明细到季度计划的技术改造明细
			copyYearTechAlterDetailToQuarterTechAlterDetail(yearDetail, quarterDetail);
			quarterDetail.setQuarterPlan(quarterPlan);                //设置关联的季度计划
			quarterDetail.setYearPlanDetail(yearDetail);      //设置相关的年度计划中的维修保养明细
			this.quarterPlanDetailDao.storeQuarterPlanDetail(quarterDetail);   //保存季度计划维修保养明细
			
			this.setCreatedQuartePlanStatusForYearPlanDetail(yearDetail);    //设置该年度计划的维修保养明细为已列入季度计划
			
			//季度计划添加维修保养明细
			quarterPlan.getQuarterPlanDetails().add(quarterDetail);
		}
		//计算季度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
		
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.TECH_ALTER.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
	
	/**
	 * 拷贝年度计划中的技术改造明细到季度计划的技术改造明细
	 * @param yearDetail  年度计划中的技术改造
	 * @param quarterDetail 季度计划的技术改造明细
	 */
	private void copyYearTechAlterDetailToQuarterTechAlterDetail(YearPlanDetail yearDetail, 
			QuarterPlanDetail quarterDetail) {
		if(yearDetail.getTooling()!=null){
			quarterDetail.setTooling(yearDetail.getTooling());                         //拷贝关联的工装
		}
		if(yearDetail.getGraphNo()!=null){
			quarterDetail.setGraphNo(yearDetail.getGraphNo());                         //拷贝图号
		}
		quarterDetail.setName(yearDetail.getName());                               //拷贝名称
		if(yearDetail.getToolingCategory()!=null){
			quarterDetail.setToolingCategory(yearDetail.getToolingCategory());  //拷贝类别Id
		}
		quarterDetail.setCategoryName(yearDetail.getCategoryName());               //拷贝类别
		quarterDetail.setDetailCategoryName(yearDetail.getDetailCategoryName());
		if(yearDetail.getDetailCategory()!=null){
			quarterDetail.setDetailCategory(yearDetail.getDetailCategory());		//拷贝类别明细
		}
		quarterDetail.setModel(yearDetail.getModel());							//拷贝型号
		quarterDetail.setSpecification(yearDetail.getSpecification());			//拷贝规格
		quarterDetail.setAllPrice(yearDetail.getAllPrice());                       //拷贝总费用
		quarterDetail.setRequestDate(yearDetail.getRequestDate());                 //拷贝需求日期
		quarterDetail.setRequestReason(yearDetail.getRequestReason());             //拷贝需求原因
		quarterDetail.setComment(yearDetail.getComment());                         //拷贝备注
		quarterDetail.setDetailType(yearDetail.getDetailType());                   //拷贝明细类别
		if(yearDetail.getCalUnit()!=null){
			quarterDetail.setCalUnit(yearDetail.getCalUnit());
		}
	}
	
	public void deleteAllTechAlterDetailOfQuarterPlan(
			Collection<QuarterPlanDetail> techAlterDetails, QuarterPlan quarterPlan) throws Exception{
		//如果该明细已制定采购单,抛出异常,
		for (QuarterPlanDetail detail : techAlterDetails) {
			if (detail.isCreatePurchaseFlag()) {
				throw new Exception();
			}
		}
		
		//获取该部门的年度预算明细
		BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
				quarterPlan.getYear(), YearPlanDetailCategory.TECH_ALTER.toString());
		
        //按照季度计划明细类型获取季度计划明细费用
		Double detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(quarterPlan.getId(),
				YearPlanDetailCategory.TECH_ALTER.toString().trim());
		//如果年度预算明细，季度计划总费用都不为0，则从年度预算明细中减去季度计划总费用
		if ((budgetDetail != null)&&(budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0)
				&& (detailFee != null && detailFee > 0)) {
			budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee()-detailFee);
		}
		
		this.quarterPlanDetailDao.deleteAllQuarterPlanDetails(techAlterDetails);
		 //清除季度计划关联的技术改造明细,以及改变该明细关联的年度计划的技术改造明细为未列入季度计划状态
		for (QuarterPlanDetail detail : techAlterDetails) {
			if (null != detail.getYearPlanDetail()) {
				this.setNotCreatedQuartePlanStatusForYearPlanDetail(detail.getYearPlanDetail());
			}
			quarterPlan.getQuarterPlanDetails().remove(detail);
		}
		//更新季度计划的总费用
		QuarterPlan newQuarterPlan = this.calulateAllFeeForQuarterPlan(quarterPlan);
		
        //获取更新后的明细总费用
		detailFee = this.quarterPlanDetailDao.getAllQuarterPlanDetailFeeByQuarterPlanId(newQuarterPlan.getId(),
				YearPlanDetailCategory.TECH_ALTER.toString().trim());
		//把改变后的季度计划总费用重新加入年度预明细中
		if (budgetDetail != null) {
			if (budgetDetail.getQuarterPlanFee()!= null && budgetDetail.getQuarterPlanFee() > 0) {
				budgetDetail.setQuarterPlanFee(budgetDetail.getQuarterPlanFee() + detailFee);
			} else {
				budgetDetail.setQuarterPlanFee(detailFee);
			}
			this.budgetDetailManager.storeBudgetDetail(budgetDetail);
		}
	}
}
