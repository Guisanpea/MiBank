<%-- 
    Document   : User
    Created on : May 6, 2018, 10:00:09 PM
    Author     : ubuntie
--%>

<%@page import="mibank.entities.Transfer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User) request.getSession(false).getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MiBank - User</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container">
            <div class="row">
                <h1>Welcome back <%=user.getName() + " " + user.getSurname()%>! </h1>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <%@include file="transactionList.jsp" %>
                </div>
                <div class="col-md-6">
                    <h3>Create new transaction</h3>
                    <form action="createTransaction" method="post">
                        <h6>Destination Account</h6>
                        <div class="form-group row">
                            <label for="accountBank" class="form-check-label col-md-3 col-form-label">Entity: </label>
                            <input type="text" class="form-control col-md-6" name="accountBank">
                        </div>
                        <div class="form-group row">
                            <label for="accountOffice" class="form-check-label col-md-3 col-form-label">Office: </label>
                            <input type="text" class="form-control col-md-6" name="accountOffice">
                        </div>
                        <div class="form-group row">
                            <label for="accountControl" class="form-check-label col-md-3 col-form-label">Control: </label>
                            <input type="text" class="form-control col-md-6" name="phone">
                        </div>
                        <div class="form-group row">
                            <label for="accountId" class="form-check-label col-md-3 col-form-label">Account Id</label>
                            <input type="text" class="form-control col-md-6" name="accountId">
                        </div>
                        <hr/>
                        <div class="form-group row">
                            <label for="amount" class="form-check-label col-md-3 col-form-label">Amount:</label>
                            <input type="text" class="form-control col-md-6" name="amount">
                        </div>
                        <div class="form-group row">
                            <label for="description" class="form-check-label col-md-3 col-form-label">Description: </label>
                            <input type="text" class="form-control col-md-6" name="description">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
