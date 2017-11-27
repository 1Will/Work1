package com.yongjun.tdms.dao.expensemanagement.airFee;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.airFee.AirHouseFee;

public interface AirHouseFeeDao {
	 public abstract void storeAirHouseFee(AirHouseFee paramAirHouseFee);

	  public abstract AirHouseFee loadAirHouseFee(Long paramLong);

	  public abstract List<AirHouseFee> loadAirHouseFee();

	  public abstract List<AirHouseFee> loadAllAirHouseFee(Long[] paramArrayOfLong);

	  public abstract void deleteAirHouseFee(AirHouseFee paramAirHouseFee);

	  public abstract void deleteAllAirHouseFee(List<AirHouseFee> paramList);

	  public abstract List<AirHouseFee> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<AirHouseFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;

	public abstract Double loadSumFeeByCusId(Long id, Date date);
}
