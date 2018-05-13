<%@page import="java.util.Objects"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MiBank</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script></head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <img class="img-fluid" src="resources/MiBankLogo.png">
                </div>
                <div class="col-md-8 text-right">
                    <h2 style="color: DarkGray;">Share resources <br/> in the world of today</h2>
                </div>
            </div>
        </div>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <img class="img-fluid" src="resources/bank.png">
                </div>
                <div class="col-md-4">
                    <p>Sign in</p>
                    <hr/>
                    <form method="POST" action="login">
                        <div class="form-group">
                            <label for="DNI">DNI:</label>
                            <input type="text" class="form-control" name="dni">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" name="password">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="imEmployee" value="true" id="imEmployee">
                            <label class="form-check-label" for="imEmployee">I am an employee</label>
                        </div>
                        <hr/>
                        <div>
                            <button type="submit" class="btn btn-primary">Log in</button>
                        </div>
                        <a href="#">Forgot your password?</a>
                    </form> 
                    <% if (Objects.nonNull(request.getAttribute("incorrect"))) { %>
                    <div class="alert alert-warning" role="alert">
                        Invalid credentials, please check your attributes and try again
                    </div>
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>
