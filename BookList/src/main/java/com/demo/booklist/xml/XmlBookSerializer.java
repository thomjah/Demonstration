package com.demo.booklist.xml;

import java.io.OutputStream;
import java.util.List;
import javax.xml.stream.*;

import com.demo.booklist.model.Book;
import com.demo.booklist.util.CommonUtil;

/**
 * XML Writer for the BookList XML output.
 * @author tjahnsen
 */
public class XmlBookSerializer {

	public static final String BOOKLIST_XML_NAMESPACE = "http://thomjah-demo.local/BookList";
	public static final String BOOKLIST_XML_PREFIX = "bl";
	
	public static final String XML_VERSION = "1.0";
	public static final String XML_ENCODING = "UTF-8";

	public XmlBookSerializer() {
	}
	
	public void createBookListXmlDoc(List<Book> inBookList, OutputStream ioDestination)
			throws XmlException {
		String NS = BOOKLIST_XML_NAMESPACE;
		XMLStreamWriter xmlWriter = createXmlWriter(ioDestination);
		try {
			xmlWriter.writeStartDocument(XML_ENCODING, XML_VERSION);
			xmlWriter.setPrefix(BOOKLIST_XML_PREFIX, NS);
			// Root
			xmlWriter.writeStartElement(NS, "BookList");
			xmlWriter.writeNamespace(BOOKLIST_XML_PREFIX, NS);
			
			for (Book ithBook : inBookList) {
				writeBookToXml(ithBook, xmlWriter);
			}
			
			xmlWriter.writeEndElement();	// BookList
			xmlWriter.writeEndDocument();
			xmlWriter.flush();
		} catch (XMLStreamException xse1) {
			throw new XmlException("Error from StAX XMLStreamWriter", xse1);
		}
	}
	
	/**
	 * Create the XMLStreamWriter on the given Stream, using default factories.
	 * @param ioDestination
	 * @return
	 * @throws XmlException 
	 */
	protected XMLStreamWriter createXmlWriter(OutputStream ioDestination)
			throws XmlException {
		XMLStreamWriter xmlStream = null;
		try {
			XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
			xmlStream = outputFactory.createXMLStreamWriter(ioDestination, XML_ENCODING);
		} catch (FactoryConfigurationError | XMLStreamException e1) {
			throw new XmlException("Error creating the XMLStreamWriter", e1);
		}
		return xmlStream;
	}
	
	/**
	 * Writes a single Book instance to the XML stream. The Book contains Author and Title elements.
	 * @param inBook
	 * @param ioWriter
	 * @throws XmlException 
	 */
	protected void writeBookToXml(Book inBook, XMLStreamWriter ioWriter)
			throws XmlException {
		String NS = BOOKLIST_XML_NAMESPACE;
		try {
			ioWriter.writeStartElement(NS, "Book");
			{
				ioWriter.writeStartElement(NS, "Author");
				ioWriter.writeCharacters(CommonUtil.null2blank(inBook.getAuthor()));
				ioWriter.writeEndElement();
				ioWriter.writeStartElement(NS, "Title");
				ioWriter.writeCharacters(CommonUtil.null2blank(inBook.getTitle()));
				ioWriter.writeEndElement();
			}
			ioWriter.writeEndElement();
		} catch (XMLStreamException xse1) {
			throw new XmlException("Error from StAX XMLStreamWriter", xse1);
		}
	}
	
}
