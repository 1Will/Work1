package main.dao;


import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import main.pojo.Data;
import main.pojo.PersonnelFiles;

// 数据表

public interface DataDao
{

	  /*
	   
	  public abstract List<Data> loadAllData(Long[] paramArrayOfLong);

	  public abstract Data loadData(Long paramLong);

	  public abstract List<Data> loadAllData();

	  public abstract List<Data> loadByKey(String paramString, Object paramObject);

	  */
	
	public abstract List<Data> loadByMonthAndCode(String month, String code);
	 
	public abstract void storeData(Data data);  // 保存 data
	  
	  public Object loadAllDataByYear(HashMap map); // 获取所有data 通过map
	 
	  public Session getSuperSession();


}