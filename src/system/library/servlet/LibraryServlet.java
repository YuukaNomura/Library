package system.library.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import system.library.controller.LibraryController;

/**
 * Servlet implementation class LibraryServlet
 */
@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());


		//forward
		RequestDispatcher rd = request.getRequestDispatcher("url");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String url = new LibraryController().controller(request);
		System.out.println("url : " + url);
		//forward
		RequestDispatcher rd = request.getRequestDispatcher(url);
		//System.out.println(rd);
		rd.forward(request, response);

		//doGet(request, response);
	}

}
