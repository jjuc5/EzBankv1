<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@page contentType="text/html" pageEncoding="utf-8" session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%
    String login = request.getRemoteUser();
    if (login == null) {
        response.sendRedirect("./");
    } else {
        request.logout();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Logout</title>
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
        <link rel="stylesheet" href="css/smiles.css"  type="text/css"/>
    </head>
    <body>
        <main>
            <my:SmileH1>Logout</my:SmileH1>
            <p class="message">
                User <span class="user"><%= login %></span> has been logged out.
            </p>
            <p><a href="redirect.jsp"> Back to the front page</a></p>
        </main>
      </body>
</html>