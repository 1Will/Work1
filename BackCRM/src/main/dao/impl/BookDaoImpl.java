package main.dao.impl;

import main.dao.BookDao;
import main.pojo.Book;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl extends HibernateDaoSupport implements BookDao{
    
    
    public String findBookById(int id){
        String hql="SELECT t.bookName from Book t where t.id="+id;
        System.out.println("====sql===="+hql);
        Query query=getSession().createQuery(hql);
        String str= query.uniqueResult().toString();
        return str;
    }
    

	@Override
	public void saveBook(Book book) {
		
		this.getHibernateTemplate().save(book);
		System.out.println("====book====="+book.getId());
	}
    
    
}