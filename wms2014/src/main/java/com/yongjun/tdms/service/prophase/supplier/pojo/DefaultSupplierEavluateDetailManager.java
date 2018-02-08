package com.yongjun.tdms.service.prophase.supplier.pojo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yongjun.tdms.dao.prophase.supplier.SupplierEvaluateDetailDao;

import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateDetail;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateDetailManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierLevelHistoryManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

public class DefaultSupplierEavluateDetailManager implements
		SupplierEavluateDetailManager {
	private final SupplierEvaluateDetailDao supplierEvaluateDetailDao;
	private final SupplierManager supplierManager;
	private final SupplierEavluateManager supplierEavluateManager;
    private final SupplierLevelHistoryManager supplierLevelHistoryManager;
    private final CodeValueManager codeValueManager;
  
    private CodeValue level;
	public DefaultSupplierEavluateDetailManager(
			SupplierEvaluateDetailDao supplierEvaluateDetailDao,
			SupplierEavluateManager supplierEavluateManager,
			 CodeValueManager codeValueManager,
			 SupplierManager supplierManager,
			 SupplierLevelHistoryManager supplierLevelHistoryManager) {
		this.supplierEvaluateDetailDao = supplierEvaluateDetailDao;
		this.supplierEavluateManager=supplierEavluateManager;
		this.codeValueManager=codeValueManager;
		this.supplierManager=supplierManager;
		this.supplierLevelHistoryManager=supplierLevelHistoryManager;
	}

	public SupplierEvaluateDetail loadSupplierEvaluateDetail(
			Long SupplierEvaluateDetailId) {

		return supplierEvaluateDetailDao.loadSupplierEvaluateDetail(SupplierEvaluateDetailId);
				
	}

	public List<SupplierEvaluateDetail> loadSupplierSupplierEvaluateDetails(
			Long[] SupplierEvaluateDetailIds) {

		return supplierEvaluateDetailDao.loadSupplierSupplierEvaluateDetails(SupplierEvaluateDetailIds);
			
	}

	public List<SupplierEvaluateDetail> loadSupplierEvaluateDetails() {

		return supplierEvaluateDetailDao.loadSupplierEvaluateDetails();
	}
	
     
//	public void storeSupplierEvaluateDetail(Supplier supplier,SupplierEvaluateDetail supplierEvaluateDetail)throws Exception{ 
//	
//		Date date=new Date();
//		SupplierEvaluate evaluate=supplierEvaluateDetail.getSupplierevaluate();
//		supplier=supplierManager.loadSupplier(supplier.getId());
//		
//		if(evaluate.getEvaluatedate()==date.getYear()){
//			throw new Exception();
//		}
//		supplierEvaluateDetailDao.storeSupplierEvaluateDetail(supplierEvaluateDetail);
//				
//
//	}

	public void deleteSupplierEvaluateDetail(
			SupplierEvaluateDetail supplierEvaluateDetail) {
		supplierEvaluateDetailDao.deleteSupplierEvaluateDetail(supplierEvaluateDetail);
				

	}

	public void deleteAllSupplierEvaluateDetails(
			Collection<SupplierEvaluateDetail> SupplierEvaluateDetails) {
		supplierEvaluateDetailDao.deleteAllSupplierEvaluateDetails(SupplierEvaluateDetails);
				

	}
	public SupplierEvaluateDetail loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(Long supplierEvaluateId, String gradeFlag) {

		return this.supplierEvaluateDetailDao.loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(supplierEvaluateId, gradeFlag);
	}
	public void storeSupplierEvaluateDetail(SupplierEvaluate supplierEvaluate ,String designCapabilityInfo, 
			String makeCapabilityInfo,String qACapabilityInfo,
			String seviceCapabilityInfo,String basicControlCapabilityInfo) {
		String [] designCapability = null;
		String [] makeCapability = null;
		String [] qACapability=null;
		String [] seviceCapability=null;
		String [] basicControlCapability=null;
		
		if (null != designCapabilityInfo) {
			designCapability = designCapabilityInfo.split(",");
		}
		if (null != makeCapabilityInfo) {
			makeCapability = makeCapabilityInfo.split(",");
		}
		if(null!=qACapabilityInfo){
			qACapability=qACapabilityInfo.split(",");
		}
		if(null!=seviceCapabilityInfo){
			seviceCapability=seviceCapabilityInfo.split(",");
		}
		if(null!=basicControlCapabilityInfo){
			basicControlCapability=basicControlCapabilityInfo.split(",");
		}
		updateGradeAndComment(designCapability);       //更新设计能力的分数和备注
		updateGradeAndComment(makeCapability);         //更新制作能力的分数和备注
		updateGradeAndComment(qACapability);           //更新质量保证的分数和备注
		updateGradeAndComment(seviceCapability);       //更新服务能力的分数和备注
		updateGradeAndComment(basicControlCapability); //更新基本控制能力的分数和备注
		//计算评定总分,根据总分评定供应商等级,并修改供应商的原等级,同时保存一份变更历史纪录
		sum(supplierEvaluate);                    
	}
	
	private void sum(SupplierEvaluate supplierEvaluate){
		double  sum=0;
		//累计本次评估的总分
		for(SupplierEvaluateDetail detail:supplierEvaluate.getSupplierEvaluateDetail()){
			if(detail.getGrade()!=null){
				sum+=detail.getGrade().doubleValue();
				
			}
		}
		supplierEvaluate.setEvaluateMinute(sum);
		Supplier supplier=supplierEvaluate.getSupplier();
		
		SupplierLevelHistory levelHistory=new SupplierLevelHistory();
		//如果供应商评定分数小于60，改变供应商的原定级别,同时把供应商的原级别及改变后的新级别保存到供应商等级历史表中
		if(supplier.getLevel()!=null){
             //往级别变更历史纪录原等级
			levelHistory.setOrigLevel(supplier.getLevel().getValue());
			//supplier.setLevel(supplierEvaluate.getLevel());
		}
		
		if(sum<60){                   //不合格等级
			//往级别变更历史纪录新等级
			levelHistory.setNewLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_D)).getValue());
			//设置评定的新等级
			supplierEvaluate.setLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_D)));
			supplierEvaluate.setOriginalLevel(supplier.getLevel());
		}
		//如果供应商评定分数大于等于60并且小于70，改变供应商的原定级别,同时把供应商的原级别及改变后的新级别保存到供应商等级历史表中
		if(sum>=60&&sum<70){           //合格等级
			//往级别变更历史纪录新等级
			levelHistory.setNewLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_C)).getValue());
			//设置评定的新等级
			supplierEvaluate.setLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_C)));
			supplierEvaluate.setOriginalLevel(supplier.getLevel());
		}
        //如果供应商评定分数大于等于60并且小于等于80，改变供应商的原定级别,同时把供应商的原级别及改变后的新级别保存到供应商等级历史表中
		if(sum>=70&&sum<80){        //良好等级
			levelHistory.setNewLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_B)).getValue());
			supplierEvaluate.setOriginalLevel(supplier.getLevel());
			supplierEvaluate.setLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_B)));
		}
		 //如果供应商评定分数大于等于80并且小于等于100，改变供应商的原定级别,同时把供应商的原级别及改变后的新级别保存到供应商等级历史表中
		if(sum>=80&&sum<=100){       //优秀等级
			levelHistory.setNewLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_A)).getValue());
			supplierEvaluate.setOriginalLevel(supplier.getLevel());
		    supplierEvaluate.setLevel((this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.SUPPLIER_LEVEL_A)));
		}
		//更新供应商的等级
		supplier.setLevel(supplierEvaluate.getLevel());  
		//设置变更日期
		levelHistory.setChangDate(supplierEvaluate.getEvaluatedate());
		
		levelHistory.setSupplier(supplier);
		supplierLevelHistoryManager.storeSupplierLevelHistory(levelHistory);
		supplierManager.storeSupplier(supplier);
		supplierEavluateManager.storeSupplierEvaluate(supplierEvaluate);
	}
	private void updateGradeAndComment(String [] capability) {
		SupplierEvaluateDetail detail = null;
		
		for (int i=0; i<capability.length; i=i+2) {
			if (capability[i].equals("ID")) {
				detail = this.supplierEvaluateDetailDao.loadSupplierEvaluateDetail(Long.valueOf(capability[i+1])); 
			}
			if(null != detail) {
				if (capability[i].equals("grade")) {
					detail.setGrade(Double.valueOf(capability[i+1]));
					
				} else if (capability[i].equals("comment")){
					detail.setComent(capability[i+1]);
				} else {
					detail.setGrade(null);
					detail.setComent(null);
				}
			} else {
				detail.setGrade(null);
				detail.setComent(null);
			}
		}
		
		if(null != detail) {
			this.supplierEvaluateDetailDao.storeSupplierEvaluateDetail(detail);
		}
	}
	public List<SupplierEvaluateDetail> loadSupplierEvaluateDetailById(Long  supplierEvaluateId) {
		return supplierEvaluateDetailDao.loadSupplierEvaluate(supplierEvaluateId);
	}
	
}
