<%-- 
    Document   : nextassist
    Created on : 12-Apr-2017, 2:58:07 PM
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
            <h2>Entered Data:</h2>
            <p>
                <label>Assistant Username:</label> 
                <span class="output"><c:out value="${assistant.username}"/></span>
            </p>
            <p>
                <label for="nextAssPass">Password: Hidden | Length Only:</label>
                <input id="nextAssPass" type="password" value="<c:out value="${assistant.password}"/>"/>
            </p>
            <br>
            <br>
            <p class="message">
                Please, check your data before clicking "Add Assistant".
            </p>
            <form method="post" action="<c:url value="submitassist.do"/>">
                <input type="submit" value="Add Assistant"/>
            </form>
            <p><a href="<c:url value="addassist.do"/>">Go Back to Input</a></p>
            <p><a href="<c:url value="listallassist.do"/>">List All</a></p>
        </main>
    </body>
</html>