package main.dao;


import org.hibernate.Session;


import main.pojo.Supplier;

// 供应商

public interface SupplierDao
{
    public Supplier getSupplierById(Long id); //根据id 获取供应商信息集合
    
    public Session getSuperSession();
}