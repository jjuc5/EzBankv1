<%-- 
    Document   : transaction
    Created on : 13-Apr-2017, 4:36:33 PM
    Author     : John Urbanowicz
--%>

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
        <title>Accounts Summary</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                <!--stacked menu-->    
                    <h2>EzBank</h2>
                    <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Task Menu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">View Customers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Print Statements</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Modify Accounts</a>
                    </li>
                        <li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li>
                    </ul>
                 </div>
                <div class="col-md-8">
                    <main>
                        <hr>
                        <h2>Welcome Back, <c:out value="${customer.first_name} ${customer.last_name}"/>!</h2>
                        <hr>
                        <my:SmileH1>Administration</my:SmileH1>
                        <c:choose>
                            <c:when test="${not empty accounts}">
                                <table>
                                    <tr>
                                        <th>Account ID</th>
                                        <th>Account Type</th>
                                        <th>Balance</th>
                                    </tr>
                                    <c:forEach items="${accounts}" var="a" varStatus="s">
                                        <tr>
                                            <td><c:out value="${a.account_id}"/></td>
                                            <td><c:if test="${a.account_Typesaccount_type == 1}">
                                                    Chequing
                                                </c:if>
                                                <c:if test="${a.account_Typesaccount_type == 2}">
                                                    Savings
                                                </c:if>
                                            </td>
                                            <td><c:out value="${a.balance}"/></td>
                                        </tr>  
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p class="message">No accounts found to summarize.</p>
                            </c:otherwise>
                        </c:choose>
                    </main>
                </div> 
            </div>
        </div>
        <footer><my:Sheridan/></footer>
    </body>
</html>