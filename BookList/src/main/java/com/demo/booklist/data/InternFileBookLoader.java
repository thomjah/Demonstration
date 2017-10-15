package com.demo.booklist.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.demo.booklist.model.Book;
import com.demo.booklist.util.CommonUtil;

/**
 * The Internal File loader will load data from a fixed file on the classpath. 
 * The data file is bundled in the app.
 * @author tjahnsen
 */
public class InternFileBookLoader implements BookLoader {

	private static final Logger logger = Logger.getLogger(InternFileBookLoader.class.getName());
	
	protected static final String INTERN_FILE_NAME = "com/demo/booklist/data/BookData.txt";
	protected static final String SEPARATOR = "\t";
	protected static final String COMMENT = "#";
	protected static final Charset FILE_ENCODING = StandardCharsets.UTF_8;

	@Override
	public List<Book> getAllBooks() 
			throws DataLoadException {
		List<Book> result = null;
		InputStream is = getFileStream(INTERN_FILE_NAME);
		if (is != null) {
			try {
				result = parseStreamToBooks(is);
			} catch (Exception e1) {
				throw new DataLoadException("Reading the data file caused an exception", e1);
			} finally {
				CommonUtil.safeClose(is);
			}
		} else {
			throw new DataLoadException("There were no data file to read");
		}
		return result;
	}

	@Override
	public List<Book> getFilteredBooks(String inFilter) 
			throws DataLoadException {
		List<Book> allBooks = getAllBooks();
		List<Book> filteredBooks = filterBookList(allBooks, inFilter);
		return filteredBooks;
	}
	
	/**
	 * Loads the data file from internal classpath. See "resources" in the project.
	 * @param inCLPath Classloader path
	 * @return 
	 */
	protected InputStream getFileStream(String inCLPath) {
		InputStream is = null;
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		is = cl.getResourceAsStream(inCLPath);
		return is;
	}
	
	/**
	 * Will parse each line to a Book instance. It's very easy - it just splits by SEPARATOR
	 * and put 1st element into Author and 2nd into Title.
	 * Lines starting with COMMENT are ignored.
	 * @param inLine
	 * @return 
	 */
	protected Book parseLineToBook(String inLine) {
		Book result = null;
		if (CommonUtil.validString(inLine)) {
			// Comment lines are ignored.
			if (inLine.startsWith(COMMENT)) {
				return result;
			}
			String[] lineSplit = inLine.split(SEPARATOR);
			// Must have two elements.
			if (lineSplit.length >= 2) {
				result = new Book();
				result.setAuthor(lineSplit[0].trim());
				result.setTitle(lineSplit[1].trim());
			}
		}
		return result;
	}
	
	/**
	 * Reads the full stream and parses it into a list of Books.
	 * If the file is blank, or no valid records were found, the list is empty.
	 * @param inStream
	 * @return
	 * @throws IOException If reading from the stream fails.
	 */
	protected List<Book> parseStreamToBooks(InputStream inStream)
			throws IOException {
		Reader reader = new InputStreamReader(inStream, FILE_ENCODING);
		return parseStreamToBooks(reader);
	}
	
	/**
	 * Reads the full stream and parses it into a list of Books.
	 * If the file is blank, or no valid records were found, the list is empty.
	 * @param inReader The stream with a charset set.
	 * @return
	 * @throws IOException If reading from the stream fails.
	 */
	protected List<Book> parseStreamToBooks(Reader inReader)
			throws IOException {
		List<Book> result = new LinkedList<>();
		BufferedReader bufReader = new BufferedReader(inReader);
		String ithLine = null;
		while ((ithLine = bufReader.readLine()) != null) {
			Book ithBook = parseLineToBook(ithLine);
			if (ithBook != null) {
				result.add(ithBook);
			}
		}
		return result;
	}

	/**
	 * Filters books by the input string.
	 * Condition: author or title contains the string.
	 * @param allBooks
	 * @param inFilter
	 * @return 
	 */
	protected List<Book> filterBookList(List<Book> allBooks, String inFilter) {
		List<Book> result = new LinkedList<>();
		String lowFilter = inFilter.toLowerCase();
		for (Book ithBook : allBooks) {
			if (ithBook.containsString(lowFilter)) {
				result.add(ithBook);
			}
		}
		return result;
	}
}
