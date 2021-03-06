<%@page import="java.util.List"%>
<%@page import="mibank.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<User> userList = (List<User>) request.getAttribute("userList");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MiBank - Employee</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-4" style="padding-right:20px; padding-left:20px; border-right: 1px solid #ccc;">
                    <div class="row">
                        <h1>Create user:</h1>
                        <br/>
                    </div>
                    <form action="CreateUser" method="post">
                        <div class="form-group row">
                            <label for="name" class="form-check-label col-md-3 col-form-label">Name:</label>
                            <input type="text" class="form-control col-md-6" name="name">
                        </div>
                        <div class="form-group row">
                            <label for="surname" class="form-check-label col-md-3 col-form-label">Surname: </label>
                            <input type="text" class="form-control col-md-6" name="surname">
                        </div>
                        <div class="form-group row">
                            <label for="mail" class="form-check-label col-md-3 col-form-label">Mail:</label>
                            <input type="email" class="form-control col-md-6" name="mail">
                        </div>
                        <div class="form-group row">
                            <label for="phone" class="form-check-label col-md-3 col-form-label">Phone:</label>
                            <input type="text" class="form-control col-md-6" name="phone">
                        </div>
                        <div class="form-group row">
                            <label for="phone_prefix" class="form-check-label col-md-3 col-form-label">Phone prefix:</label>
                            <input type="text" class="form-control col-md-6" name="phone_prefix">
                        </div>
                        <div class="form-group row">
                            <label for="dni" class="form-check-label col-md-3 col-form-label">DNI: </label>
                            <input type="text" class="form-control col-md-6" name="dni">
                        </div>
                        <div class="form-group row">
                            <label for="address" class="form-check-label col-md-3 col-form-label">Address: </label>
                            <input type="text" class="form-control col-md-6" name="address">
                        </div>
                        <div class="form-group row">
                            <label for="password" class="form-check-label col-md-3 col-form-label">Password: </label>
                            <input type="password" class="form-control col-md-6" name="password">
                        </div>

                        <button type="submit" class="btn btn-primary">Create User</button>   
                    </form>
                </div>
                <div class="col-md-8">
                    <h1>User List:</h1>
                    </br>
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">DNI </th>
                                <th scope="col">Name</th>
                                <th scope="col">Surname</th>
                                <th scope="col">Email</th>
                                <th scope="col">Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%if (userList != null) { %>

                            <%
                                for (User user : userList) {
                            %>
                            <tr>
                                <th scope="row"><%= user.getDni()%></th>
                                <td> <%= user.getName()%></td>
                                <td> <%= user.getSurname()%></td>
                                <td> <%= user.getEmail()%></td>
                                <td>
                                    <form action="userAttributes" method="POST">
                                        <button type="submit" class="btn btn-warning">
                                            <i class="fa fa-pencil fa-lg"></i>
                                        </button>
                                        <input type="hidden" name="userId" value="<%= user.getDni()%>"/>
                                    </form>
                                    <form action='deleteUser' method='POST'>
                                        <button type="submit" class="btn btn-danger"> 
                                            <i class="fa fa-times fa-lg"></i> 
                                        </button>

                                        <input type="hidden" name="dni" value="<%= user.getDni()%>"/>
                                    </form>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            <%
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
