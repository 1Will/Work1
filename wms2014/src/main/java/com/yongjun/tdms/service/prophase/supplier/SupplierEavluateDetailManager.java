package com.yongjun.tdms.service.prophase.supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateDetail;
@Transactional(readOnly=true)
public interface SupplierEavluateDetailManager {
	/**
	 * 根据传入的供应商评估明细单ID，获取供应商
	 * 
	 * @param SupplierEvaluateDetailId 供应商评估明细ID
	 * @return SupplierEvaluateDetailId 供应商评估明细
	 */
	SupplierEvaluateDetail loadSupplierEvaluateDetail(Long SupplierEvaluateDetailId);
	/**
	 * 根据传入的供应商评估ID明细集合，获取供应商评估明细集合
	 * 
	 * @param SupplierEvaluateDetailId 供应商评估明细ID集合
	 * @return List 供应商评估明细集合
	 */
	List<SupplierEvaluateDetail> loadSupplierSupplierEvaluateDetails(Long[] SupplierEvaluateDetailIds);
	/**
	 * 获取集合中的供应商评估明细
	 * 
	 * @return List  供应商评估明细集合
	 */
	List<SupplierEvaluateDetail> loadSupplierEvaluateDetails();
	/**
	 * 保存供应商评估明细
	 * 
	 * @param SupplierEvaluateDetail 供应商评估实体明细
	 * @return
	 */
	//@Transactional
	//void storeSupplierEvaluateDetail(Supplier supplier,SupplierEvaluateDetail supplierEvaluateDetail);
	/**
	 * 删除供应商评估明细
	 * 
	 * @param SupplierEvaluateDetail 供应商评估实体明细
	 * @return
	 */
	@Transactional
	void deleteSupplierEvaluateDetail(SupplierEvaluateDetail supplierEvaluateDetail);
	/**
	 * 根据传入的供应商评估明细集合，删除集合中的供应商评估明细
	 * 
	 * @param SupplierEvaluateDetail供应商评估明细集合
	 * @return
	 */
	@Transactional
	void deleteAllSupplierEvaluateDetails(Collection<SupplierEvaluateDetail> SupplierEvaluateDetails);
//	@Transactional
//	void storeSupplierEvaluate(String supplierEavluategrade,String supplierEavluatecoment);
	
	/**
	 * 根据传入的供应商评估，评估分数的标识，获取供应商评估明细
	 * @param supplierEvaluate 供应商评估
	 * @param gradeFlag 评估分数的标识
	 * @return
	 */
	SupplierEvaluateDetail loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(Long supplierEvaluateId, String gradeFlag);

	@Transactional
	void storeSupplierEvaluateDetail(SupplierEvaluate supplierEvaluate ,String designCapabilityInfo, String makeCapabilityInfo,String qACapabilityInfo,String seviceCapabilityInfo,String basicControlCapabilityInfo);
    List<SupplierEvaluateDetail> loadSupplierEvaluateDetailById(Long supplierEvaluateId);
}
