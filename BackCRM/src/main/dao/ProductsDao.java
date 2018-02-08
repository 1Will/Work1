package main.dao;


import org.hibernate.Session;

import main.pojo.Products;

// 产品

public interface ProductsDao
{
    public Products getProductById(Long id); //根据id 获取产品信息集合
    
    public Session getSuperSession();
}