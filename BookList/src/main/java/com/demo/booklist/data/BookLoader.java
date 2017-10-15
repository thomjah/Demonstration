package com.demo.booklist.data;

import java.util.List;

import com.demo.booklist.model.Book;

/**
 * Interface for getting books.
 * @author tjahnsen
 */
public interface BookLoader {

	List<Book> getAllBooks() throws DataLoadException;
	
	List<Book> getFilteredBooks(String inFilter) throws DataLoadException;
}
