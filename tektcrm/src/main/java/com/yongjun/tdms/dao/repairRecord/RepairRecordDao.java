package com.yongjun.tdms.dao.repairRecord;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.repairRecord.RepairRecord;

import java.util.Collection;
import java.util.List;

public abstract interface RepairRecordDao
{
	 public abstract void storeRepairRecord(RepairRecord paramRepairRecord);

	  public abstract RepairRecord loadRepairRecord(Long paramLong);

	  public abstract List<RepairRecord> loadAllRepairRecord(Long[] paramArrayOfLong);

	  public abstract List<RepairRecord> loadAllRepairRecords();

	  public abstract void deleteRepairRecord(RepairRecord paramRepairRecord);

	  public abstract void deleteAllRepairRecord(Collection<RepairRecord> paramCollection);

	  public abstract List<RepairRecord> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<RepairRecord> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;

}

