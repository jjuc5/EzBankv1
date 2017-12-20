<%-- 
    Document   : deleteassist
    Created on : 16-Apr-2017, 6:03:40 PM
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
        <title>Output</title>
    </head>
    <body>
        <main>
            <h1>Delete Assistant</h1>
            <h2>Record Data:</h2>
            <p>
                <label>Username:</label> 
                <span class="output"><c:out value="${assistant.username}"/></span>
            </p>
            <p>
                <label>Role:</label>
                <span class="output"><c:out value="${assistant.role}"/></span>
            </p>
            <p class="message">
                Do you really want to remove this record?
            </p>
            <form method="post" action="<c:url value="removeassist.do"/>">
                <input type="hidden" name="username" value="${assistant.username}">
                <input type="submit" value="Remove Record">
            </form>
            <my:IfLoggedIn>
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="addassist.do"/>">Add An Assistant</a></p>
                <p><a href="<c:url value="listallassist.do"/>">List All Assistants</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
            </my:IfLoggedIn>
                <p><a href="<c:url value="input.do"/>">Go Back to Input</a></p>
            <p><a href="<c:url value="listall.do"/>">List All</a></p>
        </main>
     </body>
</html>
