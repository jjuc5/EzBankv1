<%-- 
    Document   : listallassist
    Created on : 13-Apr-2017, 4:36:33 PM
    Author     : John Urbanowicz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/smiles.css">
        <title>List All</title>
    </head>
    <body>
        <main>
            <my:SmileH1>Assistants - List All</my:SmileH1>
            <c:choose>
                <c:when test="${not empty visits}">
                    <table>
                        <tr>
                            <th>Count</th>
                            <th>Username</th>
                            <th>Role</th>
                            <th colspan="2">Update</th>
                        </tr>
                        <c:forEach items="${visits}" var="v" varStatus="s">
                            <tr>
                                <td><c:out value="${s.count}"/></td>
                                <td><c:out value="${v.username}"/></td>
                                <td><c:out value="${v.role}"/></td>
                                <c:url value="editassist.do" var="ref_editassist">
                                    <c:param name="username" value="${v.username}"/>
                                </c:url>
                                <td><a href="${ref_editassist}">Edit</a></td>
                                <c:url value="deleteassist.do" var="ref_deleteassist">
                                    <c:param name="username" value="${v.username}"/>
                                </c:url>
                                <td><a href="${ref_deleteassist}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="message">Nobody registered assistants yet.</p>
                </c:otherwise>
            </c:choose>
                <p><a href="input.do">Add A Student</a></p>
                <p><a href="listall.do">List All Students</a></p>
           <my:IfLoggedIn>
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="addassist.do"/>">Add An Assistant</a></p>
                <p><a href="<c:url value="listallassist.do"/>">List All Assistants</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
            </my:IfLoggedIn>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>