<%-- Alex Tetervak, Sheridan College, Ontario --%>
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
    </head>
    <body>
        <main>
            <my:SmileH1>XYZ Online Banking System</my:SmileH1>
            <form action="j_security_check" method="post">
                <p>
                    <label>Username</label>
                    <input type="text" name="j_username" maxlength='15' size='15' required/><my:Required/>
                </p>
                <p>
                    <label>Password</label>
                    <input type="password" name="j_password" maxlength='15' size='15' required/><my:Required/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Login">    
                </p>
            </form>
            <p><a href="<c:url value="input.do"/>">Sign up</a></p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
