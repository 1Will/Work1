package com.yongjun.tdms.service.customercontract.billingrecord.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.billingrecord.BillingRecordDao;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;

public class DefaultBillingRecordManager extends BaseManager implements BillingRecordManager {
	private final BillingRecordDao billingRecordDao;

	public DefaultBillingRecordManager(BillingRecordDao billingRecordDao) {
		this.billingRecordDao = billingRecordDao;
	}

	public void storeBillingRecord(BillingRecord t) {
		this.billingRecordDao.storeBillingRecord(t);
	}

	public BillingRecord loadBillingRecord(Long id) {
		return this.billingRecordDao.loadBillingRecord(id);
	}

	public List<BillingRecord> loadBillingRecord() {
		return this.billingRecordDao.loadBillingRecord();
	}

	public List<BillingRecord> loadAllBillingRecord(Long[] tIds) {
		return this.billingRecordDao.loadAllBillingRecord(tIds);
	}

	public void deleteBillingRecord(BillingRecord t) {
		this.billingRecordDao.deleteBillingRecord(t);
	}

	public void deleteAllBillingRecord(List<BillingRecord> ts) {
		this.billingRecordDao.deleteAllBillingRecord(ts);
	}

	public List<BillingRecord> loadByKey(String key, Object value) throws DaoException {
		return this.billingRecordDao.loadByKey(key, value);
	}

	public List<BillingRecord> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.billingRecordDao.loadByKeyArray(keyNames, keyValues);
	}

	public void disabledAllBillingRecord(List<BillingRecord> billingRecords) {
		for (BillingRecord b : billingRecords) {
			b.setDisabled(true);
			this.billingRecordDao.storeBillingRecord(b);
		}
	}

	public void enabledAllBillingRecord(List<BillingRecord> billingRecords) {
		for (BillingRecord b : billingRecords) {
			b.setDisabled(false);
			this.billingRecordDao.storeBillingRecord(b);
		}
	}
	
	public String getMaxCode(String code){
		return this.billingRecordDao.getMaxCode(code);
	}
}
