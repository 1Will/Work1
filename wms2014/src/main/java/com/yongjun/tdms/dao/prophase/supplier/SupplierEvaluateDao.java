package com.yongjun.tdms.dao.prophase.supplier;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;


public interface SupplierEvaluateDao {
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
	void storeSupplierEvaluate(SupplierEvaluate supplierEvaluate);
	/**
	 * 删除供应商评估
	 * 
	 * @param supplierEvaluate 供应商评估实体
	 * @return
	 */
	void deleteSupplierEvaluate(SupplierEvaluate supplierEvaluate);
	/**
	 * 根据传入的供应商评估集合，删除集合中的供应商评估
	 * 
	 * @param supplierEvaluate 供应商评估集合
	 * @return
	 */
	void deleteAllSupplierEvaluates(Collection<SupplierEvaluate> supplierEvaluates);
	
	/**
	 * 根据传入供应商，获取所有供应商评估实体
	 * @param supplier 供应商
	 */
	List<SupplierEvaluate> loadAllSupplierEvaluateBySupplier(Supplier supplier);
	
}
