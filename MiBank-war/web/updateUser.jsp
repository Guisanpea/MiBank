<%-- 
    Document   : modifyUser
    Created on : May 11, 2018, 12:34:19 PM
    Author     : ubuntie
--%>
<%@page import="mibank.entities.Employee"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="mibank.entities.Transfer"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.List"%>
<%@page import="mibank.entities.User"%>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
    User selectedUser = (User) request.getAttribute("user");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>MiBank - Modify User</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container">
            <h1>Here you can modify user attributes and its transferences</h1>
            <br/>
            <div class="row">
                <div class="col-md-4" style="padding-right:20px; padding-left:20px; border-right: 1px solid #ccc;">

                    <h2>Choose an user: </h2>


                    <form action="userAttributes" method="get">
                        <div class="form-group">
                            <label for="userList">User List</label>
                            <select class="form-control" name="idUser" id="userList">
                                <%  for (User user : userList) {%>
                                <option value="<%= user.getDni()%>"><%= user.getDni()%></option>
                                <% }%>
                                <input type="hidden" name="a" value="1" />
                            </select>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Select</button>
                        </div>
                    </form>


                    <% if (Objects.nonNull(selectedUser)) {%>
                    <br/>
                    <form action="updateUser" method="post">
                        <div class="form-group row">
                            <label for="name" class="form-check-label col-md-3 col-form-label">Name:</label>
                            <input type="text" class="form-control col-md-6" name="name" value="<%=selectedUser.getName()%>">
                        </div>
                        <div class="form-group row">
                            <label for="surname" class="form-check-label col-md-3 col-form-label">Surname: </label>
                            <input type="text" class="form-control col-md-6" name="surname" value="<%=selectedUser.getSurname()%>">
                        </div>
                        <div class="form-group row">
                            <label for="mail" class="form-check-label col-md-3 col-form-label">Mail:</label>
                            <input type="email" class="form-control col-md-6" name="mail" value="<%=selectedUser.getEmail()%>">
                        </div>
                        <div class="form-group row">
                            <label for="phone" class="form-check-label col-md-3 col-form-label">Phone:</label>
                            <input type="text" class="form-control col-md-6" name="phone" value="<%=selectedUser.getPhone()%>">
                        </div>
                        <div class="form-group row">
                            <label for="phone_prefix" class="form-check-label col-md-3 col-form-label">Phone prefix:</label>
                            <input type="text" class="form-control col-md-6" name="phone_prefix" value="<%=selectedUser.getPhonePrefix()%>">
                        </div>
                        <div class="form-group row">
                            <label for="dni" class="form-check-label col-md-3 col-form-label">DNI: </label>
                            <input type="text" class="form-control col-md-6" name="dni" value="<%=selectedUser.getDni()%>">
                        </div>
                        <div class="form-group row">
                            <label for="address" class="form-check-label col-md-3 col-form-label">Address: </label>
                            <input type="text" class="form-control col-md-6" name="address" value="<%=selectedUser.getAddress()%>">
                        </div>
                        <div class="form-group row">
                            <label for="password" class="form-check-label col-md-3 col-form-label">Password: </label>
                            <input type="password" class="form-control col-md-6" name="password">
                        </div>

                        <button type="submit" class="btn btn-warning">Modify User</button>   
                    </form>
                    <% }%>
                </div>
                <% if (Objects.nonNull(selectedUser)) {%>
                <div class="col-md-8">
                    <%@include file="transactionList.jsp" %>
                </div>
                <% }%>
            </div>
        </div>
    </body>
</html>
