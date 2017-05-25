package main.dao;

import java.util.List;

import org.hibernate.Session;

import main.pojo.Area;

public interface AreaDao {
    public List<Area> getAllCountryByType();
    
    public List<Area> getAreaByAreaid(Long areaId);
    
	public Session getSuperSession();
	
	
}
