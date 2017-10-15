package com.demo.booklist.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.booklist.data.BookLoader;
import com.demo.booklist.data.BookLoaderFactory;
import com.demo.booklist.model.Book;
import com.demo.booklist.util.CommonUtil;
import com.demo.booklist.xml.XmlBookSerializer;

/**
 *
 * @author tjahnsen
 */
@WebServlet(name = "BookListServlet", urlPatterns = {"/BookListServlet"})
public class BookListServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(BookListServlet.class.getName());
	
	public static final String PARAM_FILTER = "FILTER";
	
	public static final String IN_OUT_ENCODING = "UTF-8";
	public static final int OUTPUT_INIT_BUFFER = 2000;
	
	@Override
	public void init(ServletConfig config)
		throws ServletException {
		super.init(config);
	}

	/**
	 * Accepts FILTER parameter to search by, else returns all books as XML.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(IN_OUT_ENCODING);
		}
		response.setContentType("application/xml;charset=" + IN_OUT_ENCODING);
		Map<String, String> paramMap = CommonUtil.upperCaseParamMap(request.getParameterMap());
		ServletOutputStream out = null;
		try {
			String filter = paramMap.get(PARAM_FILTER);
			BookLoader loader = (new BookLoaderFactory()).getBookLoader();
			List<Book> bookList;
			if (filter != null) {
				bookList = loader.getFilteredBooks(filter);
			} else {
				bookList = loader.getAllBooks();
			}
			XmlBookSerializer xmlSerializer = new XmlBookSerializer();
			ByteArrayOutputStream xmlOutput = new ByteArrayOutputStream(OUTPUT_INIT_BUFFER);
			xmlSerializer.createBookListXmlDoc(bookList, xmlOutput);
			byte[] result = xmlOutput.toByteArray();
			response.setContentLength(result.length);
			out = response.getOutputStream();
			out.write(result);
			out.flush();
		} catch (Exception e1) {
			logger.log(Level.SEVERE, "A fatal error in getting the BookList", e1);
			throw new ServletException(e1);
		} finally {
			CommonUtil.safeClose(out);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Queries the BookList";
	}// </editor-fold>

}
