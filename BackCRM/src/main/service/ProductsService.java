package main.service;


import org.hibernate.Session;

import main.pojo.Products;

public interface ProductsService {
	
	   public Products getProductById(Long id); //����id ��ȡ��Ʒ����
	    
	    public Session getSuperSession();
	    
	    
	    
}
