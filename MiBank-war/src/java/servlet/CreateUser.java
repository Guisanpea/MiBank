/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
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
import mibank.entities.User;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ubuntie
 */
@WebServlet(name = "CreateUser", urlPatterns = {"/CreateUser"})
public class CreateUser extends HttpServlet {
    
    final private int bankId = 9313;
    final private int officeId = 1998;
    final private int control = 05;

    @EJB
    private UserFacade userFacade;
    
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
        
        if (! userExist(request)){
            createNewUser(request);
        }
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/employee");
        
        dispatcher.forward(request, response);
    }

    private void createNewUser(HttpServletRequest request) throws NumberFormatException {
        User newUser = new User();
        String hashPassword = DigestUtils.sha512Hex(request.getParameter("password"));
        
        newUser.setDni(request.getParameter("dni"));
        newUser.setName(request.getParameter("name"));
        newUser.setSurname(request.getParameter("surname"));
        newUser.setEmail(request.getParameter("mail"));
        newUser.setPhone(Integer.valueOf(request.getParameter("phone")));
        newUser.setPhonePrefix(request.getParameter("phone_prefix"));
        newUser.setAddress(request.getParameter("address"));
        newUser.setPassword(hashPassword);
                
        userFacade.create(newUser);
        addAccount(newUser);
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

    private boolean userExist(HttpServletRequest request) {
        User possibleUser = userFacade.find(request.getParameter("dni"));
        return nonNull(possibleUser);
    }

    private void addAccount(User user) {
        Account userAccount = new Account();
        userAccount.setCurrency("EUR");
        userAccount.setUser(user);
        
        accountFacade.create(userAccount);
    }

}
