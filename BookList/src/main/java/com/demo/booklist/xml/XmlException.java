package com.demo.booklist.xml;

/**
 * If the XML writer fails. Can contain the inner exception from the XML processors.
 * @author tjahnsen
 */
public class XmlException extends Exception {

	public XmlException() {
	}

	public XmlException(String msg) {
		super(msg);
	}

	public XmlException(String message, Throwable cause) {
		super(message, cause);
	}
}
