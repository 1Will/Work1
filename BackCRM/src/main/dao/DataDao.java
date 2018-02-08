package main.dao;


import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import main.pojo.Data;
import main.pojo.PersonnelFiles;

// ���ݱ�

public interface DataDao
{

	  /*
	   
	  public abstract List<Data> loadAllData(Long[] paramArrayOfLong);

	  public abstract Data loadData(Long paramLong);

	  public abstract List<Data> loadAllData();

	  public abstract List<Data> loadByKey(String paramString, Object paramObject);

	  */
	
	public abstract List<Data> loadByMonthAndCode(String month, String code);
	 
	public abstract void storeData(Data data);  // ���� data
	  
	  public Object loadAllDataByYear(HashMap map); // ��ȡ����data ͨ��map
	 
	  public Session getSuperSession();


}