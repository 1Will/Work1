package main.dao;


import org.hibernate.Session;

import main.pojo.FinancialManagement;

// �տ�

public interface FinancialManagementDao
{
    public FinancialManagement getFinancialManagementById(Long id); //����id ��ȡ�տ���Ϣ����

    public void updateFinancialManagementById(FinancialManagement fManagement); //����id �����տ���Ϣ����
    
    public Session getSuperSession();
}