package com.yongjun.tdms.service.expensemanagement.electricFee;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;

public interface ElectricFeeManager {

		public abstract void storeElectricFee(ElectricFee paramElectricFee);

		public abstract ElectricFee loadElectricFee(Long paramLong);

		public abstract List<ElectricFee> loadElectricFee();

		public abstract List<ElectricFee> loadAllElectricFee(Long[] paramArrayOfLong);

		public abstract void deleteElectricFee(ElectricFee paramElectricFee);

		public abstract void deleteAllElectricFee(List<ElectricFee> paramList);

		public abstract List<ElectricFee> loadByKey(String paramString, Object paramObject) throws DaoException;

		public abstract List<ElectricFee> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
				throws DaoException;
		public abstract ElectricFee loadByStartTime(Date date);
}
