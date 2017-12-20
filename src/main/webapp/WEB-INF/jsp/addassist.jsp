<%-- 
    Document   : addassist
    Created on : 10-Apr-2017, 3:20:59 PM
    Author     : John Urbanowicz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>Input</title>
    </head>
    <body>
        <main>
            <h1>Add An Assistant</h1>
            <form method="POST" action="<c:url value="nextassist.do"/>">
                <h2>Enter Assistant Username:</h2>
                <p>
                    <label for="assistU">Username:</label>
                    <input id="assistU" type="text" name="assistUser"/>
                </p>
                <h2>Enter Assistant's Access Password</h2>
                <p>
                    <label for="password">Password:</label>
                    <input id="password" type="password" name="pass"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Continue">
                </p>
            </form>
                <p><a href="input.do">Add A Student</a></p>
                <p><a href="listall.do">List All Students</a></p>
            <my:IfLoggedIn>
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="addassist.do"/>">Add An Assistant</a></p>
                <p><a href="<c:url value="listallassist.do"/>">List All Assistants</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
            </my:IfLoggedIn>
            <p><my:Required/>) Required inputs.</p>
        </main>
    </body>
</html>
