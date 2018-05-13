/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.nonNull;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mibank.ejb.AccountFacade;
import mibank.ejb.UserFacade;
import mibank.entities.Account;
import mibank.entities.Transfer;
import mibank.entities.User;

/**
 *
 * @author ubuntie
 */
@WebServlet(name = "UserAttributes", urlPatterns = {"/userAttributes"})
public class UserAttributes extends HttpServlet {

    @EJB
    UserFacade userFacade;

    @EJB
    AccountFacade accountFacade;

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
        List<Transfer> transferList = new ArrayList<>();
        User user = null;
        
        String userId = request.getParameter("userId");
        List<User> userList = userFacade.findAll();
        
        if (nonNull(userId)) {
            user = userFacade.find(userId);
            Account userAccount = accountFacade.findByUser(user);
            transferList = userAccount.getTransferList();
        }

        request.setAttribute("userList", userList);
        request.setAttribute("transferList", transferList);
        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/updateUser.jsp");
        dispatcher.forward(request, response);
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

}
