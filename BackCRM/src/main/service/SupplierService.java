package main.service;


import org.hibernate.Session;

import main.pojo.Supplier;

public interface SupplierService {
	
	  public Supplier getSupplierById(Long id); //根据id 获取供应商信息集合
	    
	    public Session getSuperSession();
	    
	    
	    
}
