package main.service;


import java.util.HashMap;

import org.hibernate.Session;

import main.pojo.Data;
import main.pojo.PersonnelFiles;

public interface DataService {
	
	  public abstract void storeData(Data data);  // ���� data
	  
	  public Object loadAllDataByYear(HashMap map); // ��ȡ����data ͨ��map
	 
	  public Session getSuperSession();

	  public abstract void storeData(PersonnelFiles personnelFiles,
			HashMap mapData);
	    
	    
	    
}
