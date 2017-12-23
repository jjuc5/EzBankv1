<!--
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>Hello</title>
    </head>
    <body>
        <main>
            <my:SmileH1>${greeting} - Hello</my:SmileH1>
            <h2>Hello <c:out value='${cookie["userName"].value}'/></h2>
            <p class="message">Welcome back to our page.</p>
            <p><a href="forget.do">Forget Me</a></p>
            <p><a href="listall.do">List All</a></p>
        </main>
    </body>
</html>
