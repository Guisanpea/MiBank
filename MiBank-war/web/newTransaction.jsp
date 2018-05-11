<%-- 
    Document   : newTransaction
    Created on : May 9, 2018, 12:03:23 PM
    Author     : ubuntie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MiBank - New Transaction</title>
    </head>
    <body>
        <h1>Create new transaction</h1>
        <form action="CreateUser" method="post">
            <h6>Destination Account</h6>
            <div class="form-group row">
                <label for="accountBank" class="form-check-label col-md-3 col-form-label">Entidad: </label>
                <input type="text" class="form-control col-md-6" name="accountBank">
            </div>
            <div class="form-group row">
                <label for="accountOffice" class="form-check-label col-md-3 col-form-label">Oficina: </label>
                <input type="text" class="form-control col-md-6" name="accountOffice">
            </div>
            <div class="form-group row">
                <label for="accountControl" class="form-check-label col-md-3 col-form-label">Control: </label>
                <input type="text" class="form-control col-md-6" name="phone">
            </div>
            <div class="form-group row">
                <label for="accountId" class="form-check-label col-md-3 col-form-label">AccountId</label>
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
    </body>
</html>
