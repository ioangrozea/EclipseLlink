package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.msg.ro.boundary.UserManagement;
import edu.msg.ro.exceptions.BusinessException;
import edu.msg.ro.persistence.user.dao.PermissionManagement;
import edu.msg.ro.persistence.user.dao.UserPersistenceManagement;
import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.service.UserManagementBean;
import edu.msg.ro.transfer.UserDTO;

@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @EJB
    private UserManagement userManagementBean;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("adsf@msggroup.com");
        userDTO.setFirstName("Dorel");
        userDTO.setLastName("Dorel");
        userDTO.setPassword("asdsdfdfg");
        userDTO.setPhoneNumber("1234");
        try {
            userManagementBean.createUser(userDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Test EJB Bean New</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}