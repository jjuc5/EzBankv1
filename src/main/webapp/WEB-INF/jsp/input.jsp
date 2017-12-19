
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css"/>
        <link rel="stylesheet" href="<c:url value="/css/smiles.css" />" type="text/css"/>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <title>Input</title>
    </head>
    <body>
        <main>
                <div class="container">
                <div class="row">
                <div class="col">
                    <h1><b>EZ-Bank</b></h2>
                </div>
                <div class="col-md-6">
                <my:SmileH1>Account Sign-Up</my:SmileH1>
                </div>
                <div class="col">
                </div>
                </div>
                
            
            
            <c:if test="${not empty errors}">
                <h3 class="error">Input Errors:</h3>
            </c:if>
            <form method="get" action="<c:url value="next.do"/>">
                <h4><b>Personal Information:</b></h4>
                <!--<p>
                    <label for="first_name">First Name:</label>
                    <my:TextInput id="first_name" name="first_name" value="${param.first_name}"/>
                    <my:Error property="first_name"/><my:Required/>
                </p>
                <p>
                    <label for="last_name">Last Name:</label>
                    <my:TextInput id="last_name" name="last_name" value="${param.last_name}"/>
                    <my:Error property="last_name"/><my:Required/>
                </p>
                <p>
                    <label for="birth_date">Date Of Birth:</label>
                    <my:TextInput id="birth_date" name="birth_date" value="${param.birth_date}"/>
                    <my:Error property="birth_date"/><my:Required/>
                </p>
               -->
               
               
                <div class="container">
                <div class="form-group row">
                <div class="col-md-4">
                    
                
		<label for="first_name" class="col-sm-2 col-form-label">First&nbsp;Name:</label>
		<!-- First Name textbox name: first_name-->
                
                <my:TextInput name="first_name" id="first_name" value="${param.first_name}"/>
		<my:Error property="first_name"/><my:Required/>
                
                </div>
                    
                <div class="col-md-4">
               <label for="last_name" class="col-sm-2 col-form-label">Last&nbsp;Name:</label>
		<!-- Last Name Name  name: last_name-->
		<my:TextInput name="last_name" id="last_name" value="${param.last_name}"/>
		<my:Error property="last_name"/><my:Required/>
                </div>
              
                </div>
                </div>
               
               
                <div class="container">
                <div class="form-group row">
                <div class="col-md-4">
                    <label for="last_name" class="col-sm-2 col-form-label">Social&nbsp;Security&nbsp;</label>
                    
                 <my:TextInput name="social_security_no" id="social_security_no"  value="${param.social_security_no}"/>
                    <my:Error property="social_security_no"/><my:Required/>
                    
                </div>
                <div class="col-md-4">
                <label for="tel_no" class="col-sm-2 col-form-label">Telephone&nbsp;Number:</label>
                    <my:TextInput id="tel_no" name="tel_no" value="${param.tel_no}"/>
                    <my:Error property="tel_no"/><my:Required/>
                </div>
                <div class="col-md-4">
                  <label for="email" class="col-sm-2 col-form-label">Email&nbsp;Address:</label>
                    <my:TextInput id="email" name="email" value="${param.email}"/>
                    <my:Error property="email"/><my:Required/>  
                </div>
                </div>
                
                
                
                
                
                
                
                
                <!-- <p>
                    <label for="social_security_no">SSN:</label>
                    <my:TextInput name="social_security_no" id="social_security_no"  value="${param.social_security_no}"/>
                    <my:Error property="social_security_no"/><my:Required/>
                </p>
                <p>
                    <label for="tel_no" >Telephone Number:</label>
                    <my:TextInput id="tel_no" name="tel_no" value="${param.tel_no}"/>
                    <my:Error property="tel_no"/><my:Required/>
                </p>
                <p>
                    <label for="email">Email Address:</label>
                    <my:TextInput id="email" name="email" value="${param.email}"/>
                    <my:Error property="email"/><my:Required/>
                </p>-->
                    
                    
                    
                    
                    
                    
                    
                <hr>
                <h4><b>Address Information:</b></h4>
                
                <div class="container">
                <div class="form-group row">
                <div class="col-sm-4">
                   <label for="street_no" class="col-sm-2 col-form-label">Street&nbsp;Number:</label>
                    <my:TextInput id="street_no" name="street_no" value="${param.street_no}"/>
                    <my:Error property="street_no"/><my:Required/>
                </div>
                <div class="col-sm-4">
                 <label for="street_name" class="col-sm-2 col-form-label">Street&nbsp;Name:</label>
                    <my:TextInput id="street_name" name="street_name" value="${param.street_name}"/>
                    <my:Error property="street_name"/><my:Required/>
                </div>
                <div class="col-sm-4">
                    <label for="city" class="col-sm-2 col-form-label">City:</label>
                    <my:TextInput id="city" name="city" value="${param.city}"/>
                    <my:Error property="city"/><my:Required/>
                </div>
                </div>
                
                
                
                
                
                
               <!-- <p>
                    <label for="street_no">Street Number:</label>
                    <my:TextInput id="street_no" name="street_no" value="${param.street_no}"/>
                    <my:Error property="street_no"/><my:Required/>
                </p>
                <p>
                    <label for="street_name">Street Name:</label>
                    <my:TextInput id="street_name" name="street_name" value="${param.street_name}"/>
                    <my:Error property="street_name"/><my:Required/>
                </p>
                <p>
                    <label for="city">City:</label>
                    <my:TextInput id="city" name="city" value="${param.city}"/>
                    <my:Error property="city"/><my:Required/>
                </p>
                <p>
                    <label for="province">Province:</label>
                    <my:Select id="province" name="province" options="${provinces}" value="${param.province}"/>
                    <my:Error property="province"/><my:Required/>
                </p>
                <p>
                    <label for="postal_code">Postal Code:</label>
                    <my:TextInput id="postal_code" name="postal_code" value="${param.postal_code}"/>
                    <my:Error property="postal_code"/><my:Required/>
                </p>
               -->
                <hr>
                <h4><b>Login Information:</b></h4>
                <div class="container">
                <div class="row">
                <div class="col-md-4">
                    <label for="username" class="col-sm-2 col-form-label">Username:</label>
                    <my:TextInput id="username" name="username" value="${param.username}"/>
                    <my:Error property="username"/><my:Required/>
                </div>
                <div class="col-md-4">
                <label for="password" class="col-sm-2 col-form-label">Password:</label>
                    <my:TextInput id="password" name="password" value="${param.password}"/>
                    <my:Error property="password"/><my:Required/>
                </div>
                <div class="col-md-4">
                    <label for="password2" class="col-sm-2 col-form-label">Confirm&nbsp;Password:</label>
                    <my:TextInput id="password2" name="password2" value="${param.password2}"/>
                    <my:Required/>
                </div>
                </div>
                
                <!--<p>
                    <label for="username">Username:</label>
                    <my:TextInput id="username" name="username" value="${param.username}"/>
                    <my:Error property="username"/><my:Required/>
                </p>
                <p>
                    <label for="password">Password:</label>
                    <my:TextInput id="password" name="password" value="${param.password}"/>
                    <my:Error property="password"/><my:Required/>
                </p>
                <p>
                    <label for="password2">Confirm Password:</label>
                    <my:TextInput id="password2" name="password2" value="${param.password2}"/>
                    <my:Required/>
                </p>-->
                <hr>
                <h4><b>Please Check The Account Types You Would Like To Activate:</b></h4>
                <div class="container">
                <div class="row">
                <div class="col-md-4">
                    <label for="chequingAcct">Chequing Account?:</label>
                    <my:Checkbox id="chequingAcct" name="chequingAcct" option="yes" value="${param.chequingAcct}"/>
                </div>
                <div class="col-md-4">
               <label for="savingsAcct">Savings Account?:</label>
                    <my:Checkbox id="savingsAcct" name="savingsAcct" option="yes" value="${param.savingsAcct}"/>
                </div>
                <div class="col-md-4">
                   
                </div>
                </div>
                <!--<p>
                    <label for="chequingAcct">Chequing Account?:</label>
                    <my:Checkbox id="chequingAcct" name="chequingAcct" option="yes" value="${param.chequingAcct}"/>
                </p>
                <p>
                    <label for="savingsAcct">Savings Account?:</label>
                    <my:Checkbox id="savingsAcct" name="savingsAcct" option="yes" value="${param.savingsAcct}"/>
                </p>
                
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Continue">
                </p>-->
            </form>
            <p><a href="<c:url value="listall.do"/>">List All / Login</a></p>
            <my:IfLoggedIn>
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="add_assistant.do"/>">Add An Assistant</a></p>
                <p><a href="<c:url value="list_assistant.do"/>">List All Assistants</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
            </my:IfLoggedIn>
            <p>(<my:Required/>) Required inputs.</p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>