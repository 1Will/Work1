package main.service;


import org.hibernate.Session;

import main.pojo.Supplier;

public interface SupplierService {
	
	  public Supplier getSupplierById(Long id); //����id ��ȡ��Ӧ����Ϣ����
	    
	    public Session getSuperSession();
	    
	    
	    
}
