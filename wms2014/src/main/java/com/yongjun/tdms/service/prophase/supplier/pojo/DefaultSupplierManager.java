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
package com.yongjun.tdms.service.prophase.supplier.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.Manager;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.dao.prophase.supplier.SupplierDao;
import com.yongjun.tdms.service.prophase.supplier.SupplierLevelHistoryManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;
import com.yongjun.tdms.workflow.service.job.WfJobManager;

/**
 * <p>Title:SupplierManager
 * <p>Description:供应商服务访问实现类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @see com.yongjun.tdms.service.prophase.supplier.SupplierManager
 * @version Id:
 */
public class DefaultSupplierManager implements SupplierManager,Manager{
	private final SupplierDao supplierDao;

	private final SequenceManager sequenceManager;

	private final WfJobManager wfJobManager;

	private final SupplierLevelHistoryManager supplierLevelHistoryManager;

	public DefaultSupplierManager(SupplierDao supplierDao,
			SequenceManager sequenceManager, WfJobManager wfJobManager,
			SupplierLevelHistoryManager supplierLevelHistoryManager) {
		this.supplierDao = supplierDao;
		this.sequenceManager = sequenceManager;
		this.wfJobManager = wfJobManager;
		this.supplierLevelHistoryManager = supplierLevelHistoryManager;
	}

	public void storeSupplier(Supplier supplier,
			SupplierLevelHistory supplierLevelHistory) {
		if (supplier.isNew()) {
			String supplierNo = (String) sequenceManager.generate("-");
			supplier.setSupplierNo(supplierNo);
//			supplier.setLevel("A");
		}
		supplierLevelHistoryManager.storeSupplierLevelHistory(supplierLevelHistory);
		supplierDao.storeSupplier(supplier);
	}

	public void storeSupplier(Supplier supplier) {
		if (supplier.isNew()) {
//			String supplierNo = (String) sequenceManager.generate("-");
//			supplier.setSupplierNo(supplierNo);
//			supplier.setLevel("A");
		}
		supplierDao.storeSupplier(supplier);
	}

	public Supplier loadSupplier(Long supplierId) {
		return supplierDao.loadSupplier(supplierId);
	}

	public void deleteSupplier(Supplier supplier) throws WFJobInProcException {
		if (supplier.getJob() != null) {
			throw new WFJobInProcException();
		}
		supplierDao.deleteSupplier(supplier);
	}

	// TODO: 这里删除操作不正确，没有必要捕获异常
	public void deleteAllSuppliers(Collection<Supplier> supplierIds)
			throws WFJobInProcException {
		for (Supplier supplier : supplierIds) {
			if (supplier.getJob() != null) {
				throw new WFJobInProcException();
			}
		}
		supplierDao.deleteAllSuppliers(supplierIds);
	}
	
	public List<Supplier> createSelectSuppliers(String label){
		Supplier supplier = new Supplier();
		supplier.setId(-1L);
		supplier.setName(label);
		List<Supplier> tmpList = this.supplierDao.loadAllSupplier();
		tmpList.add(0, supplier);
		return tmpList;
	}
	
	public List<Supplier> loadAllSuppliers(){
		List<Supplier> tmpList = this.supplierDao.loadAllSupplier();
		return tmpList;
	}

	public List<Supplier> loadAllSuppliers(Long[] supplierIds) {
		return supplierDao.loadAllSupplier(supplierIds);
	}

//	public void cancelJob(Supplier supplier) {
//		if(supplier.getJob().getDocState().getCode().equals("DOC_APPROVED")){
//			SupplierLevelHistory supplierLevelHistory=supplier.getWishLevel();
//			supplierLevelHistory.setInvalid(true);
//			supplierLevelHistoryManager.storeSupplierLevelHistory(supplierLevelHistory);
//			supplier.setLevel(supplierLevelHistory.getNewLevel());
//			//supplier.setWishLevel(null);
//		}
//		supplier.setJob(null);
//		storeSupplier(supplier);
//		this.wfJobManager.cancelJob(supplier);
//	}

	public List<Supplier> loadAllExternalHelpSupplier(Long supplierCategoryId) {
		return this.supplierDao.loadAllExternalHelpSupplier(supplierCategoryId);
	}

	public void disabledAllSuppliers(Collection<Supplier> suppliers) {
		for (Supplier oil : suppliers) {
			oil.setDisabled(true);
			this.supplierDao.storeSupplier(oil);
		}
		
	}

	public void enabledAllSuppliers(Collection<Supplier> suppliers) {
		for (Supplier oil : suppliers) {
			oil.setDisabled(false);
			this.supplierDao.storeSupplier(oil);
		}
		
	}

	public Supplier getSupplierByCode(String code) {
		return this.supplierDao.getSupplierByCode(code);
	}

	public String getMaxSupplierNoBySupplierCode(String code) {
		return this.supplierDao.getMaxSupplierNoBySupplierCode(code);
	}

	public List<Supplier> loadSuppliersByName(String name) {
		return this.supplierDao.loadSuppliersByName(name);
	}

	public Supplier loadSupplierByName(String name) {
		return this.supplierDao.loadSupplierByName(name);
	}

}
