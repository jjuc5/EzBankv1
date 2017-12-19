<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>Thanks</title>
    </head>
    <body>
        <main>
            <my:SmileH1>EzBank Account Registration Complete!</my:SmileH1>
            <h2>Dear 
                <c:out value="${customer.first_name} ${customer.last_name}"/>:
            </h2>
            <p class="message">
                Thank you for registering.  You may now begin banking with EzBank!
            </p>
            <p><a href="<c:url value="input.do"/>">Go Back to Input</a></p>
            <p><a href="<c:url value="transaction.do"/>">Transactions</a></p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
