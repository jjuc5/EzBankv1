<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied</title>
        <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css"/>
        <link rel="stylesheet" href="<c:url value="/css/smiles.css" />" type="text/css"/>
    </head>
    <body>
        <main>
            <h1 class="surprise">Access Denied</h1>
            <p class="message">Your are not authorized to access this resource.</p>
            <p><a href="<c:url value="/redirect.jsp" />"> Back to the front page</a></p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>


