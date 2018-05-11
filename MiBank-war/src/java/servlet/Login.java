/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mibank.ejb.EmployeeFacade;
import mibank.ejb.UserFacade;
import mibank.entities.User;
import mibank.entities.Employee;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import support.SessionSupport.AgentType;

/**
 *
 * @author ubuntie
 */
public class Login extends HttpServlet {

    String id;
    String password;
    AgentType agentType;
    String outputPage;

    @EJB
    private UserFacade userFacade;
    
    @EJB
    private EmployeeFacade employeeFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        initialize(request);

        if (agentType == AgentType.USER) {
            userLogin(request);
        } else {
            employeeLogin(request);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(outputPage);
        dispatcher.forward(request, response);
    }

    private void userLogin(HttpServletRequest request) {
        User user = userFacade.find(id);
        if (nonNull(user) && correctPassword(user, password)) {
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", user);
            newSession.setAttribute("agentKind", AgentType.USER);
            newSession.setMaxInactiveInterval((int) TimeUnit.MINUTES.toSeconds(15));
            outputPage = "/user";
        } else {
            request.setAttribute("incorrect", "");
            outputPage = "/index.jsp";
        }
    }
    
    private void employeeLogin(HttpServletRequest request) {
        Employee employee = employeeFacade.find(Integer.valueOf(id));
        if (nonNull(employee) && correctPassword(employee, password)) {
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("employee", employee);
            newSession.setAttribute("agentKind", AgentType.EMPLOYEE);
            newSession.setMaxInactiveInterval((int) TimeUnit.MINUTES.toSeconds(15));
            outputPage = "/employee";
        } else {
            request.setAttribute("incorrect", "");
            outputPage = "/index.jsp";
        }
    }

    private void initialize(HttpServletRequest request) {
        id = request.getParameter("dni");
        password = request.getParameter("password");
        agentType = isNull(request.getParameter("imEmployee")) ? AgentType.USER : AgentType.EMPLOYEE;
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
        return "Short description";
    }// </editor-fold>

    private boolean correctPassword(User user, String password) {
        return user.getPassword().equals(DigestUtils.sha512Hex(password));
    }
    
    private boolean correctPassword(Employee employee, String password) {
        return employee.getPassword().equalsIgnoreCase(DigestUtils.sha512Hex(password));
    }
}
