<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <title>Login</title>
        <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css"/>
        <link rel="stylesheet" href="<c:url value="/css/smiles.css" />" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <!-- Css styling -->
        <style type="text/css">
            .centerText{
                text-align: center
            }
            .col-centered{
                float: none;
                margin: 0 auto;
            }
            br {
                line-height: 25%;
            }
        </style> 
    </head>
    <body>
        <main>
            <div class="container">
                <div class="row">
                    <div class="col-xs-6">
                        <p><h2><b>EZ-BANK</b></h2></p>
                    </div>
                </div>
            </div>
            <div class="imgcontainer" >
                <img src="img_banking2.png" img align="middle" alt="banking" class="banking" style="width: 1330px; height: 307px">
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-xs-6">
                        <my:SmileH1><b>Log In</b></my:SmileH1>
                            <p class="message">Please login to continue.</p>
                        </div>   
                        <form action="j_security_check" class="form-inline" method="post">
                            <div class="centerText">
                                <div align="center">
                                    <div class="row">
                                        <div class="col-md-4"></br>
                                            <label for="user_name">Login</label>
                                            <input type="text" name="j_username" class="form-control" id="user_name" maxlength='15' size='15' required/><my:Required/>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group"></br>
                                            <label for="password">Password</label>
                                            <input type="password" name="j_password" class="form-control" id="password" maxlength='15' size='15' required/><my:Required/>
                                        </div>
                                    </div>
                                    <div class="col-xs-4"></br>
                                        <div class="form-group">
                                            <!--<button type="" class="btn btn-primary">Sign up</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                                            <input class="btn btn-primary" type="submit" value="Login">
                                        </div>
                                        <p><a href="<c:url value="signup.do"/>">Sign-Up For An Account</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>  
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
