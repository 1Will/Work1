package main.dao;


import org.hibernate.Session;

import main.pojo.BillingRecord;

// 开票

public interface BillingRecordDao
{
    public BillingRecord getBillingRecordById(Long id); //根据id 获取开票信息集合

    public void updateBillingRecordById(BillingRecord billingRecord); //根据id 更新开票信息集合
    
    public Session getSuperSession();
}