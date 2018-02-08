package com.yongjun.tdms.service.repairRecord.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.repairRecord.RepairRecordDao;
import com.yongjun.tdms.model.repairRecord.RepairRecord;
import com.yongjun.tdms.service.repairRecord.RepairRecordManager;

public class DefaultRepairRecordManager extends BaseManager implements RepairRecordManager {
	private RepairRecordDao repairRecordDao;

	public DefaultRepairRecordManager(RepairRecordDao repairRecordDao) {
		this.repairRecordDao = repairRecordDao;
		
	}

	public void deleteAllRepairRecord(Collection<RepairRecord> RepairRecords) {
		this.repairRecordDao.deleteAllRepairRecord(RepairRecords);
	}

	public void deleteRepairRecord(RepairRecord RepairRecord) {
		this.repairRecordDao.deleteRepairRecord(RepairRecord);
	}

	public List<RepairRecord> loadAllRepairRecord(Long[] RepairRecordIds) {
		return this.repairRecordDao.loadAllRepairRecord(RepairRecordIds);
	}

	public List<RepairRecord> loadAllRepairRecords() {
		return this.repairRecordDao.loadAllRepairRecords();
	}

	public RepairRecord loadRepairRecord(Long RepairRecordId) {
		return this.repairRecordDao.loadRepairRecord(RepairRecordId);
	}

	public void storeRepairRecord(RepairRecord RepairRecord) {
		this.repairRecordDao.storeRepairRecord(RepairRecord);
	}

	public void setrepairRecordDao(RepairRecordDao repairRecordDao) {
		this.repairRecordDao = repairRecordDao;
	}

	public void disabledRepairRecords(List<RepairRecord> RepairRecords) {
		for (RepairRecord bv : RepairRecords) {
			bv.setDisabled(true);
			this.repairRecordDao.storeRepairRecord(bv);
		}
	}

	public void enableRepairRecords(List<RepairRecord> RepairRecords) {
		for (RepairRecord bv : RepairRecords) {
			bv.setDisabled(false);
			this.repairRecordDao.storeRepairRecord(bv);
		}
	}

	public List<RepairRecord> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.repairRecordDao.loadByKey(keyName, keyValue);
	}

	public List<RepairRecord> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.repairRecordDao.loadByKeyArray(keyNames, keyValues);
	}

	public RepairRecordDao getRepairRecordDao() {
		return repairRecordDao;
	}

	public void setRepairRecordDao(RepairRecordDao repairRecordDao) {
		this.repairRecordDao = repairRecordDao;
	}

	
}
