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
        <title>Input</title>
    </head>
    <body>
        <main>
            <my:SmileH1>${greeting} - Input</my:SmileH1>
            <c:if test="${not empty errors}">
                <h3 class="error">Input Errors:</h3>
            </c:if>
            <form method="get" action="<c:url value="next.do"/>">
                <h2>Enter Your Name:</h2>
                <p>
                    <label for="first">First Name:</label>
                    <my:TextInput id="first" name="firstName" value="${param.firstName}"/>
                    <my:Error property="firstName"/><my:Required/>
                </p>
                <p>
                    <label for="last">Last Name:</label>
                    <my:TextInput id="last" name="lastName" value="${param.lastName}"/>
                    <my:Error property="lastName"/><my:Required/>
                </p>
                <h2>Your Student Info:</h2>
                <p>
                    <label for="prog">Program: </label>
                    <my:Select id="prog" name="program" options="${programs}" value="${param.program}"/>
                    <my:Error property="program"/><my:Required/>
                </p>
                <p>
                    <label>Year:</label>
                    <my:Radio name="year" value="${param.year}" options="${yearOptions}" labels="${yearLabels}"/>
                    <my:Error property="year"/><my:Required/>
                </p>
                <p>
                    <label for="coop">Co-op:</label>
                    <my:Checkbox id="coop" name="coop" option="yes" value="${param.coop}"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Continue">
                </p>
            </form>
            <p><a href="<c:url value="listall.do"/>">List All / Login</a></p>
            <my:IfLoggedIn>
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="add_assistant.do"/>">Add An Assistant</a></p>
                <p><a href="<c:url value="list_assistant.do"/>">List All Assistants</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
            </my:IfLoggedIn>
            <p><my:Required/>) Required inputs.</p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
