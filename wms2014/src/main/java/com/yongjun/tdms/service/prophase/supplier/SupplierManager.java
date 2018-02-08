/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.prophase.supplier;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.Manager;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title:SupplierManager
 * <p>Description:供应商服务访问接口类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public interface SupplierManager extends Manager{
	@Transactional
	void storeSupplier(Supplier supplier,
			SupplierLevelHistory supplierLevelHistory);

	@Transactional
	void storeSupplier(Supplier supplier);

	@Transactional
	Supplier loadSupplier(Long supplierId);

	@Transactional
	void deleteSupplier(Supplier supplier) throws WFJobInProcException;

	@Transactional
	void deleteAllSuppliers(Collection<Supplier> supplierIds)
			throws WFJobInProcException;

	@Transactional
	List<Supplier> loadAllSuppliers(Long[] supplierIds);

	List<Supplier> createSelectSuppliers(String label);
	
	List<Supplier> loadAllSuppliers();
//
//	@Transactional
//	void cancelJob(Supplier supplier);
	
	/**
	 * 根据供应商类别获取供应商集合
	 * @param supplierCategoryId 供应商类别
	 * @return List 供应商集合
	 */
	List<Supplier> loadAllExternalHelpSupplier(Long supplierCategoryId);
	
	@Transactional
	void disabledAllSuppliers(Collection<Supplier> suppliers);
	
	@Transactional
	void enabledAllSuppliers(Collection<Supplier> suppliers);
	
	/**
	 * 根据供应商编号获取供应商对象
	 * @param code 供应商编号
	 * @return Supplier 供应商对象
	 */
	Supplier getSupplierByCode(String code);
	
	String getMaxSupplierNoBySupplierCode(String code);
	
	List<Supplier> loadSuppliersByName(String name);
	
	Supplier loadSupplierByName(String name);

}
