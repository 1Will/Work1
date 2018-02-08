package com.yongjun.tdms.dao.repairRecord.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.repairRecord.RepairRecordDao;
import com.yongjun.tdms.model.repairRecord.RepairRecord;

public class HibernateRepairRecord extends BaseHibernateDao implements RepairRecordDao {
	public void deleteAllRepairRecord(Collection<RepairRecord> RepairRecords) {
		deleteAll(RepairRecords);
	}

	public void deleteRepairRecord(RepairRecord RepairRecord) {
		delete(RepairRecord);
	}

	public List<RepairRecord> loadAllRepairRecord(Long[] RepairRecordIds) {
		return loadAll(RepairRecord.class, RepairRecordIds);
	}

	public List<RepairRecord> loadAllRepairRecords() {
		return loadAll(RepairRecord.class);
	}

	public RepairRecord loadRepairRecord(Long RepairRecordId) {
		return (RepairRecord) load(RepairRecord.class, RepairRecordId);
	}

	public List<RepairRecord> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(RepairRecord.class, keyName, keyValue);
	}

	public List<RepairRecord> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(RepairRecord.class, keyNames, keyValues);
	}

	public void storeRepairRecord(RepairRecord ProjectInfo) {
		store(ProjectInfo);
	}


}
