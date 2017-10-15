package com.demo.booklist.xml;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import com.demo.booklist.model.Book;

/**
 *
 * @author tjahnsen
 */
public class XmlBookSerializerTest {
	
	private XmlBookSerializer serializer;
	
	@Before
	public void setupTest() {
		serializer = new XmlBookSerializer();
	}

    public XmlBookSerializerTest() {
    }

	@Test
	public void testCreateBookListXmlDoc()
			throws XmlException {
		List<Book> input = Arrays.asList(
			new Book("Bob", "Birds"),
			new Book("Hannah", "Bees"),
			new Book("Lucy", "Frogs"),
			new Book("Robert", "Cats"),
			new Book("Tom", "Dogs")
		);
		ByteArrayOutputStream result = new ByteArrayOutputStream(200);
		serializer.createBookListXmlDoc(input, result);
		String xml = new String(result.toByteArray(), StandardCharsets.UTF_8);
		System.out.println("Test Input XML:");
		System.out.println(xml);
	}

}