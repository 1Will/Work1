package main.dao;


import org.hibernate.Session;

import main.pojo.Products;

// ��Ʒ

public interface ProductsDao
{
    public Products getProductById(Long id); //����id ��ȡ��Ʒ��Ϣ����
    
    public Session getSuperSession();
}