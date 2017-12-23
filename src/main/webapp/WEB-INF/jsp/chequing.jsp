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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Chequing Account Transactions</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h2>EzBank</h2>
                    <div class="divTable">
                        <div class="panel-group">
                            <!--<a href="#" class="list-group-item active"></a>-->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse1">Personal Accounts</a></h4>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse">
                                    <ul class="list-group">
                                        <li><a href="<c:url value="transaction.do"/>" class="list-group-item list-group-item-action">
                                                Accounts Overview</a></li>
                                        <li><a href="<c:url value="chequing.do"/>" class="list-group-item list-group-item-action">Chequing Account</a></li>
                                        <li><a href="<c:url value="savings.do"/>" class="list-group-item list-group-item-action">Savings Account</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="divTable">
                        <div class="panel-group2">
                            <!--<a href="#" class="list-group-item active2"></a>-->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" href="#collapse2">Transaction Options</a>
                                    </h4>
                                </div>
                                <div id="collapse2" class="panel-collapse collapse">
                                    <ul class="list-group">
                                        <li><a href="<c:url value="transfer.do"/>" class="list-group-item list-group-item-action">Transfer Funds</a></li>
                                        <li><a href="<c:url value="deposite.do"/>" class="list-group-item list-group-item-action">Deposit E-Cheque</a></li>
                                        <li><a href="<c:url value="printe.do"/>" class="list-group-item list-group-item-action">Print E-Cheque</a></li>
                                    </ul>
                                    <div class="panel-footer"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <main>
                        <my:SmileH1>Chequing Account Transactions</my:SmileH1>
                        <c:choose>
                            <c:when test="${not empty transactionsChequing}">
                                <table>
                                    <tr>
                                        <th>Transaction Count</th>
                                        <th>Transaction Date</th>
                                        <th>Transaction ID</th>
                                        <th>Transaction Type</th>
                                        <th>Transaction Amount</th>
                                    </tr>
                                    <c:forEach items="${transactionsChequing}" var="t" varStatus="tc">
                                        <tr>
                                            <td><c:out value="${tc.count}"/></td>
                                            <td><c:out value="${t.transaction_date}"/></td>
                                            <td><c:out value="${t.accountsaccount_id}"/></td>
                                            <td>
                                                <c:if test="${t.transtype == 1}">
                                                    Deposit
                                                </c:if>
                                                <c:if test="${t.transtype == 2}">
                                                    Withdrawal
                                                </c:if>
                                            </td>
                                            <td><c:out value="${t.transaction_amount}"/></td>
                                        </tr>  
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p class="message">No transaction history found for Chequing Account.</p>
                            </c:otherwise>
                        </c:choose>
                    </main>
                </div> 
            </div>
        </div>
    </body>
</html>