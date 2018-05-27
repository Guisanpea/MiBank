/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigInteger;
import java.util.Date;
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
import support.LocalBankAttributes;

/**
 *
 * @author ubuntie
 */
@WebServlet(name = "CreateTransfer", urlPatterns = {"/createTransfer"})
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
        Account userAccount = accountFacade.findByUser(user);
        Account destinationAccount = null;
        
        try {
            destinationAccount = setDestinationAccount(request);
        } catch (Exception e) {}

        if (nonNull(destinationAccount)) {
            createTransfer(request, userAccount, destinationAccount);
        } else {
            request.setAttribute("error", "The account number is not valid");
        }
        
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user");
        requestDispatcher.forward(request, response);
    }

    private Account setDestinationAccount(HttpServletRequest request) throws NumberFormatException {
        Account destinationAccount;
        int destinationBank = parseInt(request.getParameter("accountBank"));
        int destinationOffice = parseInt(request.getParameter("accountOffice"));
        int destinationControl = parseInt(request.getParameter("accountControl"));
        int destinationId = parseInt(request.getParameter("accountId"));
        destinationAccount = accountFacade.find(destinationId);
        return destinationAccount;
    }

    private void createTransfer(HttpServletRequest request, Account userAccount, Account destinationAccount) throws NumberFormatException {
        Transfer transfer = new Transfer();
        
        long amount = Long.parseLong(request.getParameter("amount"));
        transfer.setAmount(amount);
        
        String description = request.getParameter("description");
        transfer.setDescription(description);
        transfer.setFromAccountId(userAccount.getId());
        transfer.setFromLocalAccount();
        
        transfer.setAccount(destinationAccount);
        
        transfer.setCreatedAt(new Date());
        
        transferFacade.create(transfer);
    }

    private boolean isLocalAccount(int destinationBank, int destinationOffice, int destinationControl) {
        return destinationBank == LocalBankAttributes.BANKID
                && destinationOffice == LocalBankAttributes.OFFICEID
                && destinationControl == LocalBankAttributes.CONTROL;
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
