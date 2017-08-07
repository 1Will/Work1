package com.yongjun.tdms.service.workspace.data;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workspace.data.Data;

import java.util.HashMap;
import java.util.List;

public abstract interface DataManager
{
  public abstract void storeData(Data paramData);



  public abstract List<Data> loadAllData(Long[] paramArrayOfLong);

  public abstract Data loadData(Long paramLong);

  public abstract List<Data> loadAllData();

  public abstract List<Data> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Data> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  public void storeData(PersonnelFiles pf, HashMap map) throws DaoException;
  public Object loadAllDataByYear(HashMap map);


}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager
 * JD-Core Version:    0.6.2
 */