package com.demo.booklist.data;

/**
 * Simple access to the BookLoader interface.
 * @author tjahnsen
 */
public class BookLoaderFactory {

	public BookLoaderFactory() {
	}

	public BookLoader getBookLoader() {
		return new InternFileBookLoader();
	}
}
