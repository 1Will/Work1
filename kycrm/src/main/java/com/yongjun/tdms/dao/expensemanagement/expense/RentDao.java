package com.yongjun.tdms.dao.expensemanagement.expense;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.expensemanagement.expense.Rent;

public interface RentDao {
	 public abstract void storeRent(Rent paramRent);

	  public abstract Rent loadRent(Long paramLong);

	  public abstract List<Rent> loadRent();

	  public abstract List<Rent> loadAllRent(Long[] paramArrayOfLong);

	  public abstract void deleteRent(Rent paramRent);

	  public abstract void deleteAllRent(List<Rent> paramList);

	  public abstract List<Rent> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<Rent> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
