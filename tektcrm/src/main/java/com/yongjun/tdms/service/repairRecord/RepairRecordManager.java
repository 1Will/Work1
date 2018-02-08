package com.yongjun.tdms.service.repairRecord;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.repairRecord.RepairRecord;

public abstract interface RepairRecordManager
{
	 public abstract void storeRepairRecord(RepairRecord paramRepairRecord);
  
	  public abstract RepairRecord loadRepairRecord(Long paramLong);

	  public abstract List<RepairRecord> loadAllRepairRecord(Long[] paramArrayOfLong);

	  public abstract List<RepairRecord> loadAllRepairRecords();

	  public abstract void deleteRepairRecord(RepairRecord paramRepairRecord);

	  public abstract void deleteAllRepairRecord(Collection<RepairRecord> paramCollection);

	  public abstract void disabledRepairRecords(List<RepairRecord> paramList);

	  public abstract void enableRepairRecords(List<RepairRecord> paramList);


	  public abstract List<RepairRecord> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<RepairRecord> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

