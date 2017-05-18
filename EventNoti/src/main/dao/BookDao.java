package main.dao;

import main.pojo.Book;


public interface BookDao
{
    public String findBookById(int id);
    
    public void saveBook(Book book);
}