/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mibank.ejb.AccountFacade;
import mibank.ejb.TransferFacade;
import mibank.ejb.UserFacade;
import mibank.entities.Account;
import mibank.entities.Employee;
import mibank.entities.Transfer;
import support.LocalBankAttributes;

/**
 *
 * @author ubuntie
 */
@WebServlet(name = "CreateMovement", urlPatterns = {"/createMovement"})
public class CreateMovement extends HttpServlet {

    @EJB
    AccountFacade accountFacade;
    
    @EJB
    UserFacade userFacade;
    
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
        
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        
        createTransfer(request, employee);
                
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userAttributes");
        dispatcher.forward(request, response);
    }

    private void createTransfer(HttpServletRequest request, Employee employee) throws NumberFormatException {
        Transfer transfer = new Transfer();
        
        transfer.setEmployeeInvolved(employee);
        
        String userId = request.getParameter("userId");
        Account userAccount = accountFacade.findByUser(userFacade.find(userId));
        transfer.setAccount(userAccount);
        
        long amount = Long.valueOf(request.getParameter("amount"));
        transfer.setAmount(amount);
        
        String description = request.getParameter("description");
        transfer.setDescription(description);
        
        transfer.setCreatedAt(new Date());
        
        transfer.setAccountBank(LocalBankAttributes.BANKID);
        transfer.setAccountOffice(LocalBankAttributes.OFFICEID);
        transfer.setAccountControl(LocalBankAttributes.CONTROL);
        transferFacade.create(transfer);
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
