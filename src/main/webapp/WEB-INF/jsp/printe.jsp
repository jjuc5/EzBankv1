<%-- 
    Document   : transfer
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
                        <!-- <a href="#" class="list-group-item active"></a> -->
                         <div class="panel panel-default">
                          <div class="panel-heading">
                             <h4 class="panel-title">
                                 <a data-toggle="collapse" href="#collapse1"><h4><a>Personal Accounts</a></h4></div>
                                 <div id="collapse1" class="panel-collapse collapse">
                                 <ul class="list-group">
                                     <li><a href="<c:url value="transaction.do"/>" class="list-group-item list-group-item-action">Accounts Overview</a></li>
                                     <li><a href="<c:url value="chequing.do"/>" class="list-group-item list-group-item-action">Chequing Account</a></li>
                                     <li><a href="<c:url value="savings.do"/>" class="list-group-item list-group-item-action">Savings Account</a></li>
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
                                        <li><a href="<c:url value="transfer.do"/>" class="list-group-item list-group-item-action">Transfer</a></li>
                                        <li><a href="<c:url value="deposite.do"/>" class="list-group-item list-group-item-action">Deposit E-Cheque</a></li>
                                        <li><a href="<c:url value="printe.do"/>" class="list-group-item list-group-item-action">Print E-Cheque</a></li>
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
                    <my:SmileH1>Print E-Cheque</my:SmileH1>
                    <form>
                        <div class="col-md-8"><!--col-xs-2col-sm-6-->
                          <div class="form-group row">
                            <label for="account_name">Choose&nbsp;account&nbsp;to&nbsp;withdraw&nbsp;from:&nbsp;</label> 
                            <my:Select name="account_name" id="account_name" options="${provinces}" value="${param.province}"/>
                            <my:Required/>
                          </div>
                        </div>
                               
                        <div class="col-md-8">
                           <div class="form-group row">
                              <label for="amount" class="col-sm-2 col-form-label">Amount:</label>
                                <div class="col-sm-10">
                                   <my:TextInput name="amount" id="amount" value="${param.amount}"/>
                                   <my:Required/>
                                </div>
                           </div>
                        </div>
                                  
                        <div class="col-md-8">
                          <div class="form-group row">
                                  <label for="month" class="col-sm-2 col-form-label">Print&nbsp;Options</label>
				<!-- MONTH Name name: month-->
				<select class="form-control" name="month" id="month">
				<option>Download as a pdf file</option>
				<option>Email the form to me</option>
				</select>
                          </div>
                        </div>
                          </br>
                            <div class="col-md-8">
                                <div class="form-group row">
                                <input type="submit" class="btn btn-primary" value="Continue">
                            </div>
                        </div>
                    </form>  
                    </main>
                </div> 
            </div>
        </div>
    </body>
</html>
