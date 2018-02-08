package main.dao;


import org.hibernate.Session;

import main.pojo.ExpenseForm;

// 报销单 

public interface ExpenseFormDao
{
    public ExpenseForm getExpenseFormById(Long id); //根据id 获取报销单信息集合
    
    public Session getSuperSession();
}