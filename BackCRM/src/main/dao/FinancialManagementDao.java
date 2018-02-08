package main.dao;


import org.hibernate.Session;

import main.pojo.FinancialManagement;

// 收款

public interface FinancialManagementDao
{
    public FinancialManagement getFinancialManagementById(Long id); //根据id 获取收款信息集合

    public void updateFinancialManagementById(FinancialManagement fManagement); //根据id 更新收款信息集合
    
    public Session getSuperSession();
}