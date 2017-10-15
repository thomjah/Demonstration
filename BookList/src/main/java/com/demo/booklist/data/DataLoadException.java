package com.demo.booklist.data;

/**
 * If the system cannot load the data.
 * @author tjahnsen
 */
public class DataLoadException extends Exception {

	public DataLoadException() {
	}

	public DataLoadException(String msg) {
		super(msg);
	}

	public DataLoadException(String message, Throwable cause) {
		super(message, cause);
	}
}
