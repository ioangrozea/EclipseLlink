package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.msg.ro.persistence.user.dao.PermissionManagement;
import edu.msg.ro.persistence.user.dao.UserManagement;
import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;

@WebServlet(urlPatterns = { "/TestServlet" })
public class TestServlet extends HttpServlet {


	@EJB
	private PermissionManagement permissionManagement;

	@EJB
	private UserManagement userManagement;


	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {


		Role r1 = new Role("type1");
		Role r2 = new Role("type2");
		Permission p1 = new Permission("type1","desc1");
		Permission p2 = new Permission("type2","desc2");


		permissionManagement.addPermission(p1);

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Test EJB Bean New</title>");
			out.println("</head>");
			out.println("<body>");
			out.println(userManagement.getAllUsers());
			out.println("</body>");
			out.println("</html>");
		}


		//permissionManagement.addPermission(p1);
		//permissionManagement.addPermission(p2);


		//permissionManagement.addPermission(p1);
		//permissionManagement.createPermissionForRole(r1,p1);
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
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
		return "Short description";
	}// </editor-fold>
}


/*
@Singleton
@Startup
class TestingClass{

	@PostConstruct
	public void printBeforeTime(){
		System.out.println("BEFORE:: " + java.time.LocalTime.now());
	}

	@PreDestroy
	public void printAfterTime(){
		System.out.println("AFTER:: " + java.time.LocalTime.now());
	}


}
*/