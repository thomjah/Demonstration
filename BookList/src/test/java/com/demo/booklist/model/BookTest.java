package com.demo.booklist.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tjahnsen
 */
public class BookTest {

    public BookTest() {
    }

	@Test
	public void testContainsStringTrueAuthor() {
		Book book = new Book("Bob", "Birds");
		String input = "ob";
		assertTrue(book.containsString(input));
	}

	@Test
	public void testContainsStringTrueTitle() {
		Book book = new Book("Bob", "Birds");
		String input = "rd";
		assertTrue(book.containsString(input));
	}

	@Test
	public void testContainsStringFalse() {
		Book book = new Book("Bob", "Birds");
		String input = "to";
		assertFalse(book.containsString(input));
	}

	@Test
	public void testContainsStringCased() {
		Book book = new Book("Bob", "Birds");
		String input = "bi";
		assertTrue(book.containsString(input));
	}

	@Test
	public void testContainsStringTrueNullTitle() {
		Book book = new Book("Mary", null);
		String input = "mar";
		assertTrue(book.containsString(input));
	}

	@Test
	public void testContainsStringTrueNullAuthor() {
		Book book = new Book(null, "Cats");
		String input = "at";
		assertTrue(book.containsString(input));
	}

	@Test
	public void testContainsStringFalseNullTitle() {
		Book book = new Book("Mary", null);
		String input = "ryb";
		assertFalse(book.containsString(input));
	}

	@Test
	public void testContainsStringFalseNullAuthor() {
		Book book = new Book(null, "Cats");
		String input = "dog";
		assertFalse(book.containsString(input));
	}

	@Test
	public void testContainsStringFalseAllNull() {
		Book book = new Book(null, null);
		String input = "a";
		assertFalse(book.containsString(input));
	}

	@Test
	public void testContainsStringTrueEmpty() {
		Book book = new Book("Scott", "Fishes");
		String input = "";
		assertTrue(book.containsString(input));
	}

}