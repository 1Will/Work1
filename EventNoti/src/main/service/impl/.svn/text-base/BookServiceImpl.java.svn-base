package main.service.impl;

import main.dao.BookDao;
import main.pojo.Book;
import main.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;
    
    public String findBookById(int id){
    	
        return bookDao.findBookById(id);
    }
    public void saveBook(Book book){
        bookDao.saveBook(book);
        
    }
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
    
    
}