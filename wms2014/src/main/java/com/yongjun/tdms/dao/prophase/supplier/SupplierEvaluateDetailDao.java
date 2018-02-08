package com.yongjun.tdms.dao.prophase.supplier;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateDetail;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateView;

public interface SupplierEvaluateDetailDao {
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
	void storeSupplierEvaluateDetail(SupplierEvaluateDetail supplierEvaluateDetail);
	/**
	 * 删除供应商评估明细
	 * 
	 * @param SupplierEvaluateDetail 供应商评估实体明细
	 * @return
	 */
	void deleteSupplierEvaluateDetail(SupplierEvaluateDetail supplierEvaluateDetail);
	/**
	 * 根据传入的供应商评估明细集合，删除集合中的供应商评估明细
	 * 
	 * @param SupplierEvaluateDetail供应商评估明细集合
	 * @return
	 */
	void deleteAllSupplierEvaluateDetails(Collection<SupplierEvaluateDetail> SupplierEvaluateDetails);
	
	/**
	 * 根据传入的供应商评估，评估分数的标识，获取供应商评估明细
	 * @param supplierEvaluate 供应商评估
	 * @param gradeFlag 评估分数的标识
	 * @return
	 */
	SupplierEvaluateDetail loadSupplierEvaluateDetailBySupplierEvaluateIdAndGradFlag(final Long supplierEvaluateId, final String gradeFlag);
	List<SupplierEvaluateDetail> loadSupplierEvaluate(Long supplierEvaluateId);
}
