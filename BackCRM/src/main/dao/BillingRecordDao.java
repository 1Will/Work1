package main.dao;


import org.hibernate.Session;

import main.pojo.BillingRecord;

// ��Ʊ

public interface BillingRecordDao
{
    public BillingRecord getBillingRecordById(Long id); //����id ��ȡ��Ʊ��Ϣ����

    public void updateBillingRecordById(BillingRecord billingRecord); //����id ���¿�Ʊ��Ϣ����
    
    public Session getSuperSession();
}