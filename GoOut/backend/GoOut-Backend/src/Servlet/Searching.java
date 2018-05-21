package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Searching
 */
@WebServlet("/Searching")
public class Searching extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//JDBCDriver.connect();
//		String rep = request.getParameter("sent");
//		response.getWriter().println("i received your msg");
//		response.getWriter().flush();
//		response.getWriter().close();
//		
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		//JDBCDriver.connect();
		
		
		//TESTING THE CONNECTION REMOVE WHEN DONE 
//		try {
//			String rep = request.getParameter("sent");
//			if(rep != null) {
//				response.getWriter().println("i received your msg: " + rep);
//				response.getWriter().flush();
//				response.getWriter().close();
//			}
//			
//		}catch(IOException ioe) {
//			ioe.printStackTrace();
//		}
		
	}

}
