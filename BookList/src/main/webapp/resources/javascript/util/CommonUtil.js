'use strict';

// Namespace
var BookList = BookList || {};
BookList.Util = BookList.Util || {};

BookList.Util.isValue = function (val) {
	return (val !== undefined && val !== null);
};

BookList.Util.createXmlDoc = function (xmlText) {
	var xmlDoc;
	if (window.ActiveXObject !== undefined && window.ActiveXObject !== null) {
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM"); // IE 5,6,7 and 8
		xmlDoc.async = false;
		xmlDoc.loadXML(xmlText);
	} else {
		var parser = new DOMParser();
		xmlDoc = parser.parseFromString(xmlText, "text/xml");
	}
	return xmlDoc;
};

/**
 * Use an XPath on a Document to extract a node value.
 * @param {XmlDocument} xmlDoc XML DOM Document created from text.
 * @param {String} xpath The XPath String. The XPath needs to end in /text().
 * @param {String} ieNS The Namespace string needed by IE.
 * @param {Function} domNsResolver Optional Namespace Resolver for DOM. Required for XML using default namespace.
 * @returns {String} The text value of the XPath element.
 */
BookList.Util.extractXPathText = function (xmlDoc, xpath, ieNS, domNsResolver) {
	var resultText;
	if (window.ActiveXObject !== undefined && window.ActiveXObject !== null) {
		resultText = BookList.Util.extractXPathTextIE(xmlDoc, xpath, ieNS);
	} else {
		resultText = BookList.Util.extractXPathTextDOM(xmlDoc, xpath, domNsResolver);
	}
	return resultText;
};

BookList.Util.extractXPathTextIE = function (xmlDoc, xpath, nsString) {
	var resultNode;
	var resultText = null;
	xmlDoc.setProperty("SelectionLanguage", "XPath");
	xmlDoc.setProperty("SelectionNamespaces", nsString);
	resultNode = xmlDoc.selectSingleNode(xpath);
	if (resultNode) {
		resultText = resultNode.text;
	}
	return resultText;
};

BookList.Util.extractXPathTextDOM = function (xmlDoc, xpath, nsResolver) {
	var result;
	var resultText = null;
	if (!nsResolver) {
		nsResolver = xmlDoc.createNSResolver(xmlDoc);
	}
	result = xmlDoc.evaluate(xpath, xmlDoc, nsResolver, XPathResult.STRING_TYPE, null);
	if (result) {
		resultText = result.stringValue;
		if (resultText === "") {
			resultText = null;
		}
	}
	return resultText;
};

/**
 * Use an XPath on a Document to extract a node list.
 * @param {XmlDocument} xmlDoc XML DOM Document created from text.
 * @param {String} xpath The XPath String. The XPath should end in a node (element or attribute).
 * @param {String} ieNS The Namespace string needed by IE.
 * @param {Function} domNsResolver Optional Namespace Resolver for DOM. Required for XML using default namespace.
 * @returns {Object} The DOM Node object of the XPath.
 */
BookList.Util.extractXPathNode = function (xmlDoc, xpath, ieNS, domNsResolver) {
	var resultNode;
	if (window.ActiveXObject !== undefined && window.ActiveXObject !== null) {
		resultNode = BookList.Util.extractXPathNodeIE(xmlDoc, xpath, ieNS);
	} else {
		resultNode = BookList.Util.extractXPathNodeDOM(xmlDoc, xpath, domNsResolver);
	}
	return resultNode;
};

BookList.Util.extractXPathNodeIE = function (xmlDoc, xpath, nsString) {
	var resultNode;
	xmlDoc.setProperty("SelectionLanguage", "XPath");
	xmlDoc.setProperty("SelectionNamespaces", nsString);
	resultNode = xmlDoc.selectSingleNode(xpath);
	if (!resultNode) {
		resultNode = null;
	}
	return resultNode;
};

BookList.Util.extractXPathNodeDOM = function (xmlDoc, xpath, nsResolver) {
	var result;
	var resultText = null;
	if (!nsResolver) {
		nsResolver = xmlDoc.createNSResolver(xmlDoc);
	}
	result = xmlDoc.evaluate(xpath, xmlDoc, nsResolver, XPathResult.FIRST_ORDERED_NODE_TYPE, null);
	if (result) {
		resultText = result.singleNodeValue;
	}
	return resultText;
};
