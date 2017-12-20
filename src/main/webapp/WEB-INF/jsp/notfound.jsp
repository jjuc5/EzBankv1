<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>Not Found</title>
    </head>
    <body>
        <main>
            <h1 class="surprise">${greeting} - Not Found</h1>
            <p class="message">
                Oops, your data is not found.
            </p>
            <p><a href=".">Go Back</a></p>
        </main>
      </body>
</html>
