package com.yongjun.tdms.service.prophase.supplier;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateView;

@Transactional(readOnly=true)
public interface SupplierEavluateManager {
	/**
	 * 根据传入的供应商评估单ID，获取供应商
	 * 
	 * @param supplierEvaluateId 供应商评估ID
	 * @return supplierEvaluateId 供应商评估
	 */
	SupplierEvaluate loadSupplierEvaluate(Long supplierEvaluateId);
	/**
	 * 根据传入的供应商评估ID集合，获取供应商评估集合
	 * 
	 * @param supplierEvaluateId 供应商评估ID集合
	 * @return List 供应商评估集合
	 */
	List<SupplierEvaluate> loadSupplierEvaluates(Long[] supplierEvaluateIds);
	/**
	 * 获取集合中的供应商评估
	 * 
	 * @return List  供应商评估集合
	 */
	List<SupplierEvaluate> loadSupplierEvaluates();
	/**
	 * 保存供应商评估
	 * 
	 * @param supplierEvaluate 供应商评估实体
	 * @return
	 */
	@Transactional
	void storeSupplierEvaluate(SupplierEvaluate supplierEvaluate, LabelValue [] supplierEvaluateDetailFlag) throws Exception;
	
	@Transactional
	void storeSupplierEvaluate(SupplierEvaluate supplierEvaluate);
	/**
	 * 删除供应商评估
	 * 
	 * @param supplierEvaluate 供应商评估实体
	 * @return
	 */
	@Transactional
	void deleteSupplierEvaluate(SupplierEvaluate supplierEvaluate);
	/**
	 * 根据传入的供应商评估集合，删除集合中的供应商评估
	 * 
	 * @param supplierEvaluate 供应商评估集合
	 * @return
	 */
	@Transactional
	void deleteAllMSupplierEvaluates(Collection<SupplierEvaluate> supplierEvaluates);
	
	@Transactional
	void disabledAllEvaluates(Collection<SupplierEvaluate> supplierEvaluates);
	
	@Transactional
	void enabledAllEvaluates(Collection<SupplierEvaluate> supplierEvaluates);
	
	/**
	 * 根据传入供应商，获取所有供应商评估实体
	 * @param supplier 供应商
	 */
	List<SupplierEvaluate> loadAllSupplierEvaluateBySupplier(Supplier supplier);
	List<SupplierEvaluateView> loadAllSupplierEvaluateBySupplierEvaluateid(Long supplierEvaluateid);
	
}

