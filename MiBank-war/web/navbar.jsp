<%@page import="mibank.entities.User"%>
<%@page import="support.SessionSupport.AgentType" %>
<%@page import="static support.SessionSupport.checkSession"  %>

<%
    HttpSession currentSession = request.getSession(false);
    if(checkSession(getServletContext(), currentSession, request, response)){
     // User logged = (User) currentSession.getAttribute("user");
        AgentType agentKind = (AgentType) currentSession.getAttribute("agentKind");
    %>
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="navbar-brand" href="#"> 
                        <img src="resources/MiBankLogo.png" alt="Logo" height="32" width="32">
                    </a>
                </li>
                
            <%  if (agentKind == AgentType.USER) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="#">User</a>
                    </li>
            <%  }%>
                
                <li class="nav-item">
                    <a class="nav-link" href="#">Transfer</a>
                </li>
                
            <%  if (agentKind == AgentType.EMPLOYEE) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="employee">Employee</a>
                    </li>
            <%  } %>
            </ul>
        </div>
        </nav>
<%  } %>
