package com.yongjun.tdms.service.expensemanagement.airFee;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.airFee.AirFee;

public interface AirFeeManager {

		public abstract void storeAirFee(AirFee paramAirFee);

		public abstract AirFee loadAirFee(Long paramLong);

		public abstract List<AirFee> loadAirFee();

		public abstract List<AirFee> loadAllAirFee(Long[] paramArrayOfLong);

		public abstract void deleteAirFee(AirFee paramAirFee);

		public abstract void deleteAllAirFee(List<AirFee> paramList);

		public abstract List<AirFee> loadByKey(String paramString, Object paramObject) throws DaoException;

		public abstract List<AirFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
				throws DaoException;
}
