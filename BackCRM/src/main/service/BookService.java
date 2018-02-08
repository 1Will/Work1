package main.service;

import main.pojo.Book;

public interface BookService{
    public String findBookById(int id);
    public void saveBook(Book book);
}
