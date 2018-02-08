package main.dao;


import org.hibernate.Session;

import main.pojo.PaymentOrder;

// 付款

public interface PaymentOrderDao
{
    public PaymentOrder getPaymentOrderById(Long id); //根据id 获取付款信息集合
    
    public Session getSuperSession();
}