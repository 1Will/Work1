package main.dao;


import org.hibernate.Session;


import main.pojo.Supplier;

// ��Ӧ��

public interface SupplierDao
{
    public Supplier getSupplierById(Long id); //����id ��ȡ��Ӧ����Ϣ����
    
    public Session getSuperSession();
}