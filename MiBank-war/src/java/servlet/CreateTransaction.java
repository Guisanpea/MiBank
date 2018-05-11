/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Objects;
import static java.util.Objects.nonNull;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mibank.ejb.AccountFacade;
import mibank.ejb.TransferFacade;
import mibank.entities.Account;
import mibank.entities.Transfer;
import mibank.entities.User;

/**
 *
 * @author ubuntie
 */
@WebServlet(name = "CreateTransfer", urlPatterns = {"/CreateTransfer"})
public class CreateTransaction extends HttpServlet {

    @EJB
    AccountFacade accountFacade;

    @EJB
    TransferFacade transferFacade;
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

        User user = ((User) request.getSession().getAttribute("user"));
        String account = request.getParameter("accountBank")
                + request.getParameter("accountOffice")
                + request.getParameter("accountControl")
                + request.getParameter("accountId");

        Account destination = accountFacade.findByAccountNumber(account);

        Account userAccount = accountFacade.findByUser(user);
        if (nonNull(destination)) {
            BigInteger amount = new BigInteger(request.getParameter("amount"));
            if (amount.compareTo(userAccount.getBalance()) > 0) {
                request.setAttribute("notFunds", "");
            } else {
                String description = request.getParameter("description");
                Transfer transfer = new Transfer();
                transfer.setAmount(amount);
                transfer.setDescription(description);
                transfer.setAccount(destination);
                transfer.setAccountFrom(userAccount);
                
                transferFacade.create(transfer);
            }
        } else {
            request.setAttribute("nonExistingAccount", "");
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user");
        requestDispatcher.forward(request, response);
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
