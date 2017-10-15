package com.demo.booklist.data;

import java.io.InputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.demo.booklist.model.Book;

public class InternFileBookLoaderTest {
	
	private static final String TEST_DATA_FILE = "com/demo/booklist/test/TestData.txt";
	
	private InternFileBookLoader loader;
	
	@Before
	public void setupTest() {
		loader = new InternFileBookLoader();
	}

    public InternFileBookLoaderTest() {
    }
	
	@Test
	public void testGetFileStreamExists() {
		java.io.InputStream result = loader.getFileStream(InternFileBookLoader.INTERN_FILE_NAME);
		assertNotNull(result);
	}

	@Test
	public void testParseLineToBook2Elem() {
		String input = "Bob Authorson\tBooky McBookface";
		Book expected = new Book("Bob Authorson", "Booky McBookface");
		Book result = loader.parseLineToBook(input);
		assertEquals(expected, result);
	}

	@Test
	public void testParseLineToBook3Elem() {
		String input = "Heather Wannaberich\tKiss in the Rain\tA Romance";
		Book expected = new Book("Heather Wannaberich", "Kiss in the Rain");
		Book result = loader.parseLineToBook(input);
		assertEquals(expected, result);
	}

	@Test
	public void testParseLineToBookNull() {
		String input = null;
		Book result = loader.parseLineToBook(input);
		assertNull(result);
	}

	@Test
	public void testParseLineToBookComment() {
		String input = "# A comment\tDon't read me!";
		Book result = loader.parseLineToBook(input);
		assertNull(result);
	}

	@Test
	public void testParseLineToBook1Elem() {
		String input = "Anton Unpublished :-(";
		Book result = loader.parseLineToBook(input);
		assertNull(result);
	}

	@Test
	public void testParseStreamToBooksString() 
			throws IOException {
		String input = "# Author\tTitle\nBob\tBirds\nLucy\tFrogs\n";
		List<Book> expected = Arrays.asList(
			new Book("Bob", "Birds"),
			new Book("Lucy", "Frogs")
		);
		List<Book> result = loader.parseStreamToBooks(new StringReader(input));
		assertEquals(expected, result);
	}
	
	@Test
	public void testParseStreamToBooksFile()
			throws IOException {
		InputStream input = loader.getFileStream(TEST_DATA_FILE);
		List<Book> expected = Arrays.asList(
			new Book("Robert", "How to Offend"),
			new Book("Mary", "Friendship for Dummies")
		);
		List<Book> result = loader.parseStreamToBooks(input);
		assertEquals(expected, result);
	}

	@Test
	public void testFilterBookList() 
			throws IOException {
		String filter = "ob";
		List<Book> input = Arrays.asList(
			new Book("Bob", "Birds"),
			new Book("Hannah", "Bees"),
			new Book("Lucy", "Frogs"),
			new Book("Robert", "Cats"),
			new Book("Tom", "Dogs")
		);
		List<Book> expected = Arrays.asList(
			new Book("Bob", "Birds"),
			new Book("Robert", "Cats")
		);
		List<Book> result = loader.filterBookList(input, filter);
		assertEquals(expected, result);
	}
}