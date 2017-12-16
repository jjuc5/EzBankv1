<%-- 
    Document   : editassist
    Created on : 16-Apr-2017, 3:04:52 PM
    Author     : John Urbanowicz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>Edit Assistant</title>
    </head>
    <body>
        <main>
            <h1>Edit Assistant</h1>
            <form method="post" action="<c:url value="updateassist.do"/>">
                <h2>Assistant:</h2>
                <p>
                    <input type="hidden" name="originalUser" value="${assistant.username}"/>
                    <label for="assistname">Assistant Username:</label>
                    <input id="assistname" type="text" name="username" 
                           value="${assistant.username}"/>
                    <my:Required/>
                </p>
                <p>
                    <label for="pass">Assistant Role:</label>
                    <input id="pass" type="text" name="role" 
                           value="${assistant.role}"/>
                    <my:Required/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="reset" value="Reset"/>
                    <input type="submit" value="Update Assistant"/>
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
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>