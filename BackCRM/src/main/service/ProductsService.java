package main.service;


import org.hibernate.Session;

import main.pojo.Products;

public interface ProductsService {
	
	   public Products getProductById(Long id); //根据id 获取产品集合
	    
	    public Session getSuperSession();
	    
	    
	    
}
