<%-- Alex Tetervak, Sheridan College, Ontario --%>
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
            <my:SmileH1>${greeting} - Output</my:SmileH1>
            <h2>Entered Data:</h2>
            <p>
                <label>First Name:</label> 
                <span class="output"><c:out value="${student.firstName}"/></span>
            </p>
            <p>
                <label>Last Name:</label>
                <span class="output"><c:out value="${student.lastName}"/></span>
            </p>
            <p>
                <label>Program:</label>  
                <span class="output"><c:out value="${student.program}"/></span>
            </p>
            <p>
                <label>Year:</label> 
                <span class="output"><c:out value="${student.year}"/></span>
            </p>
            <p>
                <label>Co-op:</label> 
                <span class="output"><c:out value="${student.coop}"/></span>
            </p>
            <p class="message">
                Please, check your data before clicking "Register".
            </p>
            <form method="post" action="<c:url value="submit.do"/>">
                <input type="submit" value="Register"/>
            </form>
            <p><a href="<c:url value="input.do"/>">Go Back to Input</a></p>
            <p><a href="<c:url value="listall.do"/>">List All</a></p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
