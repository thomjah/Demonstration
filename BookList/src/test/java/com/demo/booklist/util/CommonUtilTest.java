package com.demo.booklist.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommonUtilTest {
	
	public CommonUtilTest() {
	}

	@Test
	public void testValidStringValue() {
		String input = "test";
		boolean result = CommonUtil.validString(input);
		assertTrue(result);
	}
	
	@Test
	public void testValidStringEmpty() {
		String input = "";
		boolean result = CommonUtil.validString(input);
		assertFalse(result);
	}
	
	@Test
	public void testValidStringNull() {
		String input = null;
		boolean result = CommonUtil.validString(input);
		assertFalse(result);
	}
	
	@Test
	public void testNull2blankStrPass() {
		String input = "filter";
		String expected = input;
		String result = CommonUtil.null2blank(input);
		assertEquals(expected, result);
	}
	
	@Test
	public void testNull2blankStrEmpty() {
		String input = "";
		String expected = input;
		String result = CommonUtil.null2blank(input);
		assertEquals(expected, result);
	}
	
	@Test
	public void testNull2blankStrNull() {
		String input = null;
		String expected = "";
		String result = CommonUtil.null2blank(input);
		assertEquals(expected, result);
	}
	
	@Test
	public void testNull2blankObjValue() {
		Integer input = 5;
		String expected = "5";
		String result = CommonUtil.null2blank(input);
		assertEquals(expected, result);
	}
	
	@Test
	public void testNull2blankObjNull() {
		java.util.Date input = null;
		String expected = "";
		String result = CommonUtil.null2blank(input);
		assertEquals(expected, result);
	}
	
	@Test
	public void testBlank2nullPass() {
		String input = "filter";
		String expected = input;
		String result = CommonUtil.blank2null(input);
		assertEquals(expected, result);
	}
	
	@Test
	public void testBlank2nullEmpty() {
		String input = "";
		String result = CommonUtil.blank2null(input);
		assertNull(result);
	}
	
	@Test
	public void testBlank2nullNull() {
		String input = null;
		String result = CommonUtil.blank2null(input);
		assertNull(result);
	}

	@Test
	public void testUpperCaseParamMap() {
		Map<String, String[]> input = new LinkedHashMap<>();
		input.put("Book", new String[] {"pet sematary"});
		input.put("PErsON", new String[] {"jon", "mike", "pete"});
		input.put("FILTER", new String[] {""});
		input.put("user", new String[0]);
		Map<String, String> expected = new LinkedHashMap<>();
		expected.put("BOOK", "pet sematary");
		expected.put("PERSON", "jon");
		expected.put("FILTER", null);
		expected.put("USER", null);
		Map<String, String> result = CommonUtil.upperCaseParamMap(input);
		assertEquals(expected, result);
	}
}
