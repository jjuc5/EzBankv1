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
            <my:SmileH1>Confirm Your Registration Details</my:SmileH1>
            <h2>Entered Data:</h2>
            <p>
                <label>First Name:</label> 
                <span class="output"><c:out value="${customer.first_name}"/></span>
            </p>
            <p>
                <label>Last Name:</label>
                <span class="output"><c:out value="${customer.last_name}"/></span>
            </p>
            <p>
                <label>Birth Date:</label>  
                <span class="output"><c:out value="${customer.birth_date}"/></span>
            </p>
            <p>
                <label>SSN:</label> 
                <span class="output"><c:out value="${customer.social_security_no}"/></span>
            </p>
            <p>
                <label>Telephone Number:</label> 
                <span class="output"><c:out value="${customer.tel_no}"/></span>
            </p>
            <p>
                <label>Email Address:</label> 
                <span class="output"><c:out value="${customer.email}"/></span>
            </p>
            <p>
                <label>Street Number:</label> 
                <span class="output"><c:out value="${customer.street_no}"/></span>
            </p>
            <p>
                <label>Street Name:</label> 
                <span class="output"><c:out value="${customer.street_name}"/></span>
            </p>
            <p>
                <label>City:</label> 
                <span class="output"><c:out value="${customer.city}"/></span>
            </p>
            <p>
                <label>Province:</label> 
                <span class="output"><c:out value="${customer.province}"/></span>
            </p>
            <p>
                <label>Postal Code:</label> 
                <span class="output"><c:out value="${customer.postal_code}"/></span>
            </p>
            <p>
                <label>Username:</label> 
                <span class="output"><c:out value="${customer.username}"/></span>
            </p>
            <p>
                <label>Password:</label> 
                <span class="output"><c:out value="${customer.password}"/></span>
            </p>
            <p>
                <label>Chequing:</label> 
                <span class="output"><c:out value="${customer.chequing}"/></span>
            </p>
            <p>
                <label>Savings:</label> 
                <span class="output"><c:out value="${customer.savings}"/></span>
            </p>
            <p class="message">
                Please, check your data before clicking "Register".
            </p>
            <form method="post" action="<c:url value="submit.do"/>">
                <input type="submit" value="Register"/>
            </form>
            <p><a href="<c:url value="input.do"/>">Go Back to Input</a></p>
            <%--<p><a href="<c:url value="listall.do"/>">List All</a></p>--%>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
