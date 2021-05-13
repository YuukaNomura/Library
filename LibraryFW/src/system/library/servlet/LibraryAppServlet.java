package system.library.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import library.bl.BookBL;
import system.library.obj.BookList;

/**
 * Servlet implementation class LibraryAppServlet
 */
@WebServlet("/LibraryAppServlet")
public class LibraryAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post");
		System.out.println(request.getParameter("KEYWORD_1"));

		BookBL bBL = new BookBL();
		BookList bookList = new BookList();
		try {
			bookList.setBookList(bBL.findByConditions(
					bBL.createBook("", "", "", "")));
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		try {

			String bookListJson = mapper.writeValueAsString(bookList);

			response.getWriter().write(bookListJson);


		}catch(JsonProcessingException e) {

		}



		//doGet(request, response);
	}

}
