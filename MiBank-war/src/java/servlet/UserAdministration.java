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
import static java.util.Objects.isNull;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mibank.ejb.AccountFacade;
import mibank.ejb.TransferFacade;
import mibank.entities.Account;
import mibank.entities.Transfer;
import mibank.entities.User;
import static support.SessionSupport.checkSession;

/**
 *
 * @author ubuntie
 */
@WebServlet(name = "User", urlPatterns = {"/user"})
public class UserAdministration extends HttpServlet {

    @EJB
    private TransferFacade transferFacade;

    @EJB
    private AccountFacade accountFacade;
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
        HttpSession session = request.getSession();

        if (checkSession(getServletContext(), session, request, response)) {
            User user = (User) session.getAttribute("user");
            Account userAccount = accountFacade.findByUser(user);
            
            List<Transfer> transferList = userAccount.getTransferList();
            List<Transfer> madeTransferList = transferFacade.findMadeTransferences(userAccount.getId());
            
            int balance = getBalance(transferList, madeTransferList);

            setAttributes(request, transferList, madeTransferList, balance, userAccount);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void setAttributes(HttpServletRequest request, List<Transfer> transferList, List<Transfer> madeTransferList, int balance, Account userAccount) {
        request.setAttribute("transferList", transferList);
        request.setAttribute("madeTransferList", madeTransferList);
        request.setAttribute("balance", balance);
        request.setAttribute("currency", userAccount.getCurrency());
    }

    private int getBalance(List<Transfer> transferList, List<Transfer> madeTransferList) {
        int balance = 0;
        for(Transfer transfer : transferList)
            balance += transfer.getAmount();
        for(Transfer transfer : madeTransferList)
            balance -= transfer.getAmount();
        return balance;
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
