package main.service;


import java.util.HashMap;

import org.hibernate.Session;

import main.pojo.Data;
import main.pojo.PersonnelFiles;

public interface DataService {
	
	  public abstract void storeData(Data data);  // 保存 data
	  
	  public Object loadAllDataByYear(HashMap map); // 获取所有data 通过map
	 
	  public Session getSuperSession();

	  public abstract void storeData(PersonnelFiles personnelFiles,
			HashMap mapData);
	    
	    
	    
}
