package com.yongjun.tdms.service.customercontract.contractmanagement.houseList;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;

public abstract interface HouseListManager
{
  public abstract void storeHouseList(HouseList paramHouseList);

  public abstract HouseList loadHouseList(Long paramLong);

  public abstract List<HouseList> loadHouseList();

  public abstract List<HouseList> loadAllHouseList(Long[] paramArrayOfLong);

  public abstract void deleteHouseList(HouseList paramHouseList);

  public abstract void deleteAllHouseList(List<HouseList> paramList);

  public abstract List<HouseList> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<HouseList> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract double getSum(long paramLong)
    throws DaoException;
  
  public abstract double getAllSquare(long paramLong)
		  throws DaoException;
  public abstract String  getHouseListEndTime(long paramLong)
		  throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.contractmanagement.productlist.HouseListManager
 * JD-Core Version:    0.6.2
 */