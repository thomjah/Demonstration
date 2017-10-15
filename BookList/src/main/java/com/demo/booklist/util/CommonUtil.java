package com.demo.booklist.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Utility functions.
 *
 * @author tjahnsen
 */
public class CommonUtil {

	private static final Logger logger = Logger.getLogger(CommonUtil.class.getName());

	/**
	 * Returns true if the input is considered valid - not null and not empty.
	 */
	public static boolean validString(String inStr) {
		return (inStr != null && !inStr.isEmpty());
	}

	/**
	 * Returns an empty string if the value is null, else the value itself.
	 */
	public static String null2blank(String str) {
		return (str != null ? str : "");
	}

	/**
	 * Returns an empty string if the value is null, else the toString value
	 * itself.
	 */
	public static String null2blank(Object obj) {
		return (obj != null ? obj.toString() : "");
	}

	/**
	 * Returns null if the string is empty, else the value itself.
	 */
	public static String blank2null(String str) {
		return (str != null ? (str.isEmpty() ? null : str) : null);
	}

	/**
	 * Converts the parameter map given by the Servlet to a more usable Map.
	 * All keys are converted to upper case.
	 * It will pick the first value in the array.
	 * If any of the values is an empty string, it's converted to NULL.
	 * @param inParamMap
	 * @return 
	 */
	public static Map<String, String> upperCaseParamMap(Map<String, String[]> inParamMap) {
		Map<String, String> newMap = new LinkedHashMap<>(inParamMap.size());
		for (String ithKey : inParamMap.keySet()) {
			String[] val = inParamMap.get(ithKey);
			String newKey = (ithKey != null ? (ithKey.toUpperCase()) : null);
			newMap.put(newKey, (val != null && val.length > 0) ? CommonUtil.blank2null(val[0]) : null);
		}
		return newMap;
	}
	
	/**
	 * Will do a close() and eat the IOException
	 * @param inCloseable 
	 */
	public static void safeClose(Closeable inCloseable) {
		if (inCloseable != null) {
			try {
				inCloseable.close();
			} catch (IOException ioe1) {
				logger.log(Level.WARNING, "Close threw IOException", ioe1);
			}
		}
	}
}
