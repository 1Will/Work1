package com.yongjun.tdms.service.prophase.supplier.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.prophase.supplier.SupplierEvaluateDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierEvaluateDetailDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierEvaluateViewDao;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluate;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateDetail;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateView;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateManager;
public class DefaultSupplierEavluateManager implements SupplierEavluateManager{
    private final SupplierEvaluateDao supplierEvaluateDao ;
    private final SupplierEvaluateDetailDao supplierEvaluateDetailDao;
   private final  SupplierEvaluateViewDao supplierEvaluateViewDao;
    
    public DefaultSupplierEavluateManager(SupplierEvaluateDao supplierEvaluateDao,
    		SupplierEvaluateDetailDao supplierEvaluateDetailDao,SupplierEvaluateViewDao supplierEvaluateViewDao){
    	this.supplierEvaluateDao=supplierEvaluateDao;
    	this.supplierEvaluateDetailDao = supplierEvaluateDetailDao;
    	this.supplierEvaluateViewDao=supplierEvaluateViewDao;
    }
	public SupplierEvaluate loadSupplierEvaluate(Long supplierEvaluateId) {
		
		return supplierEvaluateDao.loadSupplierEvaluate(supplierEvaluateId) ;
	}

	public List<SupplierEvaluate> loadSupplierEvaluates(Long[] supplierEvaluateIds) {
		
		return supplierEvaluateDao.loadSupplierEvaluates(supplierEvaluateIds);
	}

	public List<SupplierEvaluate> loadSupplierEvaluates() {
	
		return supplierEvaluateDao.loadSupplierEvaluates();
	}

	public void storeSupplierEvaluate(SupplierEvaluate supplierEvaluate, LabelValue [] supplierEvaluateDetailFlag) throws Exception{
		boolean isNew = supplierEvaluate.isNew();
		if (isNew) {
			List<SupplierEvaluate> allSupplierEvaluates = this.loadAllSupplierEvaluateBySupplier(supplierEvaluate.getSupplier());
			//比较新建的供应商评定在本年度是否已经评定过了，如果评定过了，则抛出异常
			for (SupplierEvaluate evaluate : allSupplierEvaluates) {
				if (DateUtil.getYear(evaluate.getUnitYear()) == DateUtil.getYear(supplierEvaluate.getUnitYear())) {
					throw new Exception();
				}
			} 
		}
		supplierEvaluateDao.storeSupplierEvaluate(supplierEvaluate);
		
		if (isNew) {              
			//生成评定的明细（设计能,制作能力,质量保证能力,服务能力,基本控制能力）
			for (int i=0; i<supplierEvaluateDetailFlag.length; i++) {
				SupplierEvaluateDetail detail = new SupplierEvaluateDetail();
				detail.setGradeFlag(supplierEvaluateDetailFlag[i].getValue());
				detail.setSupplierevaluate(supplierEvaluate);
				this.supplierEvaluateDetailDao.storeSupplierEvaluateDetail(detail);
			}
		}
		
	}

	public void deleteSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
		supplierEvaluateDao.deleteSupplierEvaluate(supplierEvaluate);
		
	}

	public void deleteAllMSupplierEvaluates(Collection<SupplierEvaluate> supplierEvaluates) {
		supplierEvaluateDao.deleteAllSupplierEvaluates(supplierEvaluates);
		
	}

	public void disabledAllEvaluates(Collection<SupplierEvaluate> supplierEvaluates) {
		for (SupplierEvaluate oil : supplierEvaluates) {
			oil.setDisabled(true);
			this.supplierEvaluateDao.storeSupplierEvaluate(oil);
		}
		
	}

	public void enabledAllEvaluates(Collection<SupplierEvaluate> supplierEvaluates) {
		
		for (SupplierEvaluate oil : supplierEvaluates) {
			oil.setDisabled(false);
			this.supplierEvaluateDao.storeSupplierEvaluate(oil);
		}
	}
	public void storeSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
		supplierEvaluateDao.storeSupplierEvaluate(supplierEvaluate);		
	}
	public List<SupplierEvaluate> loadAllSupplierEvaluateBySupplier(Supplier supplier) {
		return this.supplierEvaluateDao.loadAllSupplierEvaluateBySupplier(supplier);
	}
	public List<SupplierEvaluateView> loadAllSupplierEvaluateBySupplierEvaluateid(Long supplierEvaluateid) {
	
		return supplierEvaluateViewDao.loadSupplierEvaluateView(supplierEvaluateid);
	}

}
