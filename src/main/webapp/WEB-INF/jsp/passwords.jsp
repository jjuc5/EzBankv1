<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>Change Password</title>
    </head>
    <body>
        <main>
            <my:SmileH1>${greeting} - Change Password</my:SmileH1>
            <p class="message">${message}</p>
            <form action="update_password.do">
                <p>
                    <label for="current">Current Password</label>
                    <input id="current" type="password" name="current_password" 
                           value="<c:out value="${param.current_password}"/>">
                </p>
                <p>
                    <label for="new_1">New Password</label>
                    <input id="new_1" type="password" name="new_password_1" 
                           value="<c:out value="${param.new_password_1}"/>">
                </p>
                <p>
                    <label for="new_2">New Password</label>
                    <input id="new_2" type="password" name="new_password_2" 
                           value="<c:out value="${param.new_password_2}"/>">
                </p>
                <p>
                    <input type="submit" value="Change Password"/>
                </p>
            </form>
                <p><a href="<c:url value="listall.do"/>">List All</a></p>
                <p><a href="<c:url value="input.do"/>">Go Back to Input</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
        </main>
    </body>
    <footer><my:Sheridan/></footer>
</html>
