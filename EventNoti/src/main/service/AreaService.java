package main.service;

import java.util.List;

import main.pojo.Area;

import org.hibernate.Session;

public interface AreaService {
  
	    public List<Area> getAllCountryByType();
	    
	    public List<Area> getAreaByAreaid(Long areaId);
	    
		public Session getSuperSession();
	
}
