/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.io.IOException;
import static java.util.Objects.isNull;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author ubuntie
 */
public class SessionSupport {
    
    public enum AgentType{USER,EMPLOYEE};

    private SessionSupport() {}
    
    public static boolean checkSession(ServletContext servletContext, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isNull(session.getAttribute("agentKind"))) {
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return false;
        }
        return true;
    }
    
    
}
