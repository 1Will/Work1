package com.yongjun.tdms.dao.workspace.data;

import com.yongjun.pluto.exception.DaoException;
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

}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workspace.leaveBill.LeaveBillDao
 * JD-Core Version:    0.6.2
 */