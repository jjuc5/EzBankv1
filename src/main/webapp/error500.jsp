<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css">
        <link rel="stylesheet" href="<c:url value="/css/smiles.css" />" type="text/css">
        <title>Error 500</title>
    </head>
    <body>
        <main>
            <h1 class="surprise">Error 500</h1>
            <p class="message">Internal server error.</p>
            <p><a href="<c:url value="/redirect.jsp" />"> Back to the front page</a></p>
        </main>
     </body>
</html>