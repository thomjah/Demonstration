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
