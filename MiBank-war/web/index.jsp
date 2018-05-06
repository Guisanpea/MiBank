<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script></head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <img class="img-fluid" src="resources/MiBankLogo.png">
                </div>
                <div class="col-md-8 text-right">
                    <h2 style="color: DarkGray;">Your Bank and each day <br> more people one's</h2>
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
                        <form method="post" action="Login">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="username" class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" name="pwd">
                        </div>
                        <hr/>
                        <div>
                            <button type="submit" class="btn btn-primary">Log in</button>
                        </div>
                        <a href="#">Forgot your password?
                        </a>
                    </form> 
                </div>
            </div>
        </div>
    </body>
</html>
