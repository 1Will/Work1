package main.dao;


import org.hibernate.Session;

import main.pojo.ExpenseForm;

// ������ 

public interface ExpenseFormDao
{
    public ExpenseForm getExpenseFormById(Long id); //����id ��ȡ��������Ϣ����
    
    public Session getSuperSession();
}