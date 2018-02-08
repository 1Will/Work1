package main.service;


import org.hibernate.Session;

import main.pojo.ExpenseForm;

public interface ExpenseFormService {
	
    public ExpenseForm getExpenseFormById(Long id); //根据id 获取报销单信息集合
    
    public Session getSuperSession();
	    
	    
	    
}
