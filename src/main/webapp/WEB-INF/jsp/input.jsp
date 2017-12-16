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
            <my:SmileH1>Account Sign-Up</my:SmileH1>
            <c:if test="${not empty errors}">
                <h3 class="error">Input Errors:</h3>
            </c:if>
            <form method="get" action="<c:url value="next.do"/>">
                <h2>Personal Information:</h2>
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
                <p>
                    <label for="birth_date">Date Of Birth:</label>
                    <my:TextInput id="birth_date" name="birth_date" value="${param.birth_date}"/>
                </p>
                <p>
                    <label for="social_security_no">Social Security Number (SIN):</label>
                    <my:TextInput id="social_security_no" name="social_security_no" value="${param.social_security_no}"/>
                </p>
                <p>
                    <label for="tel_no">Telephone Number:</label>
                    <my:TextInput id="tel_no" name="tel_no" value="${param.tel_no}"/>
                </p>
                <hr>
                <h2>Address Information:</h2>
                <p>
                    <label for="street_no">Street Number:</label>
                    <my:TextInput id="street_no" name="street_no" value="${param.street_no}"/>
                </p>
                <p>
                    <label for="street_name">Street Name:</label>
                    <my:TextInput id="street_name" name="street_name" value="${param.street_name}"/>
                </p>
                <p>
                    <label for="Provincesprovince">Province:</label>
                    <my:TextInput id="Provincesprovince" name="Provincesprovince" value="${param.Provincesprovince}"/>
                </p>
                <p>
                    <label for="postal_code">Postal Code:</label>
                    <my:TextInput id="postal_code" name="postal_code" value="${param.postal_code}"/>
                </p>
                <hr>
                <h2>Login Information:</h2>
                <p>
                    <label for="user_id">Username:</label>
                    <my:TextInput id="user_id" name="user_id" value="${param.user_id}"/>
                </p>
                <p>
                    <label for="password">Password:</label>
                    <my:TextInput id="password" name="password" value="${param.password}"/>
                </p>
                <p>
                    <label for="password2">Confirm Password:</label>
                    <my:TextInput id="password2" name="password2" value="${param.password2}"/>
                </p>
                <hr>
                <h2>Please Check The Account Types You Would Like To Activate:</h2>
                <p>
                    <label for="savingsAcct">Co-op:</label>
                    <my:Checkbox id="savingsAcct" name="savingsAcct" option="yes" value="${param.savingsAcct}"/>
                </p>
                <p>
                    <label for="chequingAcct">Co-op:</label>
                    <my:Checkbox id="chequingAcct" name="chequingAcct" option="yes" value="${param.chequingAcct}"/>
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