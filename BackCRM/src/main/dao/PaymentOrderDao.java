package main.dao;


import org.hibernate.Session;

import main.pojo.PaymentOrder;

// ����

public interface PaymentOrderDao
{
    public PaymentOrder getPaymentOrderById(Long id); //����id ��ȡ������Ϣ����
    
    public Session getSuperSession();
}