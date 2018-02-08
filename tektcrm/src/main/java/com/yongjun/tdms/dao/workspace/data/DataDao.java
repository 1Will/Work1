package com.yongjun.tdms.dao.workspace.data;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.workspace.data.Data;
import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public abstract interface DataDao
{

  public abstract List<Data> loadAllData(Long[] paramArrayOfLong);

  public abstract Data loadData(Long paramLong);

  public abstract List<Data> loadAllData();

  public abstract List<Data> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Data> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  public abstract List<Data> loadAllDataByTeam(HashMap map);
  public abstract void storeData(Data data);
  public Object loadAllDataByYear(HashMap map);

public abstract List<Object[]> loadAllDataStatisticMonthByContion();

public abstract List<Object[]> loadAllDataStatisticDayByContion();

public abstract List<Object[]> loadAllMyDataMonthByContion(String timeStart,String timeEnd,String businessType ,String classification,String flag);

public abstract List<Object[]> loadAllMyDataYearByContion(String timeStart,String timeEnd,String businessType ,String classification,String flag);

public abstract List<Object[]> loadAllMyDataYearByAllContion(String timeStart,String timeEnd,String businessType ,String classification,String flag);

public abstract List<Object[]> loadAllMyDataMonthByAllContion(String timeStart,String timeEnd,String businessType ,String classification,String flag);

//public abstract List<Object[]> loadAllMyDataByBusinessType(String timeStart,String timeEnd,String flag);

//public abstract List<Object[]> loadAllMyDataByMilitary(String startTime, String endTime,String flag);
//
//public abstract List<Object[]> loadAllMyDataByCivilian(String startTime, String endTime,String flag);

public abstract List<Object[]> listStatisticalBusinessTypes(String date, String businessType, String classification);

public abstract List<Object[]> listYearStatisticalBusinessTypes(String dates, String businessType, String classification);

public abstract List<Object[]> listMonthSumStatisticalBusinessTypes(String timeStart, String timeEnd,
		String businessType, String classification, String flag);

public abstract List<Object[]> listSumStatisticalBusinessTypes(String startTime, String endTime, String businessType, String classification);

}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.leaveBill.LeaveBillDao
 * JD-Core Version:    0.6.2
 */