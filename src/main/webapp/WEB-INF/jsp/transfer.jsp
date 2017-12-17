<%-- 
    Document   : listallassist
    Created on : 13-Apr-2017, 4:36:33 PM
    Author     : John Urbanowicz
--%>

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
        <title>Insert title here</title>
       
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <title>List All</title>
    </head>
    <body>
                  

       <div class="container">
         
		<!-- Example row of columns -->
		<div class="row">
		<div class="col-md-4">
		<h2>EZ-BANK</h2>
		
                <div class="divTable">
		<div class="panel-group">
		<a href="#" class="list-group-item active"></a>
		<div class="panel panel-default">
		<div class="panel-heading">
		<h4 class="panel-title">
		<a data-toggle="collapse" href="#collapse1">Personal Accounts</a></h4></div>
		<div id="collapse1" class="panel-collapse collapse">
		<ul class="list-group">
		<li><a href="account.jsp" class="list-group-item list-group-item-action">Accounts
                Overview</a></li>
		<li><a href="transaction.jsp" class="list-group-item list-group-item-action">Transaction History</a></li>
		</ul>
		<!--  <div class="panel-footer">Footer</div> -->
		<div class="panel-footer"></div>
		</div>
		</div>
		</div>
		</div>
                
                <div class="divTable">
		<div class="panel-group2">
		<a href="#" class="list-group-item active2"></a>
		<div class="panel panel-default">
		<div class="panel-heading">
		<h4 class="panel-title">
		<a data-toggle="collapse" href="#collapse2">Transactions</a>
		</h4>
		</div>
		<div id="collapse2" class="panel-collapse collapse">
		<ul class="list-group">
		<li><a href="#"	class="list-group-item list-group-item-action">Transfer</a></li>
		<li><a href="#"	class="list-group-item list-group-item-action">Deposit E-Cheque</a></li>
		<li><a href="#"	class="list-group-item list-group-item-action">Print E-Cheque</a></li>
		</ul>
		<!--  <div class="panel-footer">Footer</div> -->
                <div class="panel-footer"></div>
		</div>
		</div>
		</div>
		</div>
                </div>
                    
                <div class="col-md-8">
                <main>
            <my:SmileH1>Transfer Funds</my:SmileH1>
            <form>
            <!--old code
             <label for="prog">Program: </label>
                    <my:Select id="prog" name="program" options="${programs}" value="${param.program}"/>
                    <my:Error property="program"/><my:Required/>
            -->
            
            <!--bootstrap input textbox-->
            
            <div class="col-md-8"><!--col-xs-2col-sm-6-->
            
            <!-- ACCOUNT NAME dropdown name: account_name -->
            <!--  <label for="account_name">Choose account to transfer</label> -->
            <div class="form-group">
            <label for="account_name">Choose&nbsp;account&nbsp;to&nbsp;transfer&nbsp;from:&nbsp;</label> 
            </div>
            <select class="form-control" id="account_name">
            <option>Chequing</option>
            <option>Savings</option>
            </select>
                    
            </div>
            
            <div class="col-md-8"><!--col-xs-2col-sm-6-->
               
            <!-- ACCOUNT NAME dropdown name: account_name -->
            <!--  <label for="account_name">Choose account to transfer</label> -->
           
            <div class="form-group">
            <label for="account_name">Choose&nbsp;account&nbsp;to&nbsp;transfer&nbsp;to:&nbsp;</label> 
            </div>
            <select class="form-control" id="account_name">
            <option>Chequing</option>
            <option>Savings</option>
            </select>
            </br>
             <div class="form-group row">
            <label for="transfer_amount" class="col-sm-2 col-form-label">Amount:</label>
            <div class="col-sm-10">
            <input type="email" class="form-control" id="transfer_amount" placeholder="Amount to Transfer">
            </div>
           
            </div>
            </div>
            
            
            </form>  
        
           
            
            <!--<label for="prog">Program: </label>
            <my:Select id="prog" name="program" options="${programs}" value="${param.program}"/>
            <my:Error property="program"/><my:Required/>
            -->
             
            <c:choose>
                <c:when test="${not empty visits}">
                    <table>
                        <tr>
                            <!--code for the table
                            <th>Date</th>
                            <th>Description Type</th>
                            <th>Amount</th>
                            <th>Balance</th>
                            -->
                            <th colspan="2">Update</th>
                            <!--working code
                            <th>Count</th>
                            <th>Username</th>
                            <th>Role</th>
                            <th colspan="2">Update</th>
                            -->
                        </tr>
                        <c:forEach items="${visits}" var="v" varStatus="s">
                            <tr>
                                <!--Working code
                                <td><c:out value="${s.count}"/></td>
                                <td><c:out value="${v.username}"/></td>
                                <td><c:out value="${v.role}"/></td>
                                -->
                                <!-- altered code
                                <td><c:out value="${a.date}"/></td>
                                <td><c:out value="${a.description}"/></td>
                                <td><c:out value="${a.amount}"/></td>
                                <td><c:out value="${a.balance}"/></td>
                                -->   
                              </tr>  
                                <c:url value="editassist.do" var="ref_editassist">
                                    <c:param name="username" value="${v.username}"/>
                                </c:url>
                                <td><a href="${ref_editassist}">Edit</a></td>
                                <c:url value="deleteassist.do" var="ref_deleteassist">
                                    <c:param name="username" value="${v.username}"/>
                                </c:url>
                                <td><a href="${ref_deleteassist}">Delete</a></td>
                            </tr>
                        </c:forEach>
                            
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="message">Transfers to complete</p>
                </c:otherwise>
            </c:choose>
                <p><a href="input.do">Add A Student</a></p>
                <p><a href="listall.do">List All Students</a></p>
           <my:IfLoggedIn>
               <!--
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="addassist.do"/>">Add An Assistant</a></p>
                <p><a href="<c:url value="listallassist.do"/>">List All Assistants</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p> 
               -->
            </my:IfLoggedIn>
            </main>
           </div> 
           </div>
       </div>
       
        <footer><my:Sheridan/></footer>
                
    
    </body>
</html>