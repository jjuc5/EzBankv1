<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <my:SmileH1>${greeting} - Edit</my:SmileH1>
            <c:if test="${not empty errors}">
                <h3 class="error">Input Errors:</h3>
                <ul>
                    <c:forEach items="${errors}" var="e">
                        <li class="error">${e.message}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <form method="post" action="<c:url value="update.do"/>">
                <input type="hidden" name="id" value="${student.id}"/>
                <h2>Student Name:</h2>
                <p>
                    <label for="first">First Name:</label>
                    <my:TextInput id="first" name="firstName" value="${student.firstName}"/>
                    <my:Required/>
                </p>
                <p>
                    <label for="last">Last Name:</label>
                    <my:TextInput id="last" name="lastName" value="${student.lastName}"/>
                    <my:Required/>
                </p>
                <h2>Student Info:</h2>
                <p>
                    <label for="prog">Program: </label>
                    <my:Select id="prog" name="program" options="${programs}" value="${student.program}"/>
                </p>
                <p>
                    <label>Year:</label>
                    <my:Radio name="year" value="${student.year}" options="${yearOptions}" labels="${yearLabels}"/>
                </p>
                <p>
                    <label for="coop">Co-op:</label>
                    <my:Checkbox id="coop" name="coop" option="yes" value="${student.coop}"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="reset" value="Undo"/>
                    <input type="submit" value="Update"/>
                </p>
            </form>
            <p><a href="<c:url value="listall.do"/>">List All</a></p>
            <p><my:Required/>) Required inputs.</p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
