<!--
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <style>

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Transfer Funds</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h2>EZ-BANK</h2>
                    <div class="divTable">
                      <div class="panel-group">
                       <!-- <a href="#" class="list-group-item active"></a> -->
                         <div class="panel panel-default">
                          <div class="panel-heading">
                             <h4 class="panel-title">
                                 <a data-toggle="collapse" href="#collapse1">Personal Accounts</a></h4></div>
                                 <div id="collapse1" class="panel-collapse collapse">
                                 <ul class="list-group">
                                     <li><a href="<c:url value="transaction.ez"/>" class="list-group-item list-group-item-action">Accounts Overview</a></li>
                                     <li><a href="<c:url value="chequing.ez"/>" class="list-group-item list-group-item-action">Chequing Account</a></li>
                                     <li><a href="<c:url value="savings.ez"/>" class="list-group-item list-group-item-action">Savings Account</a></li>
                                  </ul>
                                  <div class="panel-footer"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="divTable">
                        <div class="panel-group2">
                           <!-- <a href="#" class="list-group-item active2"></a> -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse2">Transactions</a>
                                    </h4>
                                    </div>
                                    <div id="collapse2" class="panel-collapse collapse">
                                    <ul class="list-group">
                                        <li><a href="<c:url value="transfer.ez"/>" class="list-group-item list-group-item-action">Transfer</a></li>
                                        <li><a href="<c:url value="deposite.ez"/>" class="list-group-item list-group-item-action">Deposit E-Cheque</a></li>
                                        <li><a href="<c:url value="printe.ez"/>" class="list-group-item list-group-item-action">Print E-Cheque</a></li>
                                    </ul>
                                    <!--  <div class="panel-footer">Footer</div> -->
                                    <div class="panel-footer"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="post" action="<c:url value="logout.ez"/>">
                        <input type="submit" class="btn btn-primary" value="Logout">
                    </form>
                </div>
                <div class="col-md-8">
                    <main>
                        <my:SmileH1>Transfer Funds</my:SmileH1>
                        <form method="post" action="<c:url value="submitTransfer.ez"/>">
                               <div class="form-group">
                                    <label for="accountsSource">Source Account: </label> &nbsp;
                                    <select id="accountsSource" name="accountsSource">
                                        <c:forEach items="${accounts}" var="accS">
                                            <option value="${accS.account_id}">${accS.account_id} - 
                                                <c:if test="${accS.account_Typesaccount_type == 1}">
                                                    Chequing -
                                                </c:if>
                                                <c:if test="${accS.account_Typesaccount_type == 2}">
                                                    Savings -
                                                </c:if>
                                                    ${accS.balance}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <hr>
                                    <div class="form-group">
                                        <label for="accountsDestination">Destination Account: </label>
                                        <select id="accountsDestination" name="accountsDestination">
                                            <c:forEach items="${accounts}" var="accD">
                                                <option value="${accD.account_id}">${accD.account_id} - 
                                                    <c:if test="${accD.account_Typesaccount_type == 1}">
                                                        Chequing -
                                                    </c:if>
                                                    <c:if test="${accD.account_Typesaccount_type == 2}">
                                                        Savings -
                                                    </c:if>
                                                        ${accD.balance}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-8"><!--col-xs-2col-sm-6-->
                                        <div class="form-group row">
                                            <label for="amount" class="col-sm-2 col-form-label">Amount:</label>&nbsp;&nbsp;&nbsp;
                                            <div class="col-sm-10">
                                            <my:TextInput name="amount" id="amount" value="${param.amount}"/>
                                            <my:Error property="amount"/><my:Required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-8"><!--col-xs-2col-sm-6-->
                                        <input type="submit" class="btn btn-primary" value="Submit">&nbsp;&nbsp;
                                    </div>
                        </form>  
                    </main>
                </div>
    </body>
</html>
