package main.service;


import org.hibernate.Session;

import main.pojo.ExpenseForm;

public interface ExpenseFormService {
	
    public ExpenseForm getExpenseFormById(Long id); //����id ��ȡ��������Ϣ����
    
    public Session getSuperSession();
	    
	    
	    
}
