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
        <title>List All</title>
    </head>
    <body>
        <main>
            <my:SmileH1>${greeting} - List All</my:SmileH1>
            <c:choose>
                <c:when test="${not empty visits}">
                    <table>
                        <tr>
                            <th>Count</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Program</th>
                            <th>Year</th>
                            <th>Co-op</th>
                            <th colspan="2">Update</th>
                        </tr>
                        <c:forEach items="${visits}" var="v" varStatus="s">
                            <tr>
                                <td><c:out value="${s.count}"/></td>
                                <td><c:out value="${v.firstName}"/></td>
                                <td><c:out value="${v.lastName}"/></td>
                                <td><c:out value="${v.program}"/></td>
                                <td><c:out value="${v.year}"/></td>
                                <td><c:out value="${v.coop}"/></td>
                                <c:url value="edit.do" var="ref_edit">
                                    <c:param name="id" value="${v.id}"/>
                                </c:url>
                                <td><a href="${ref_edit}">Edit</a></td>
                                <c:url value="delete.do" var="ref_delete">
                                    <c:param name="id" value="${v.id}"/>
                                </c:url>
                                <td><a href="${ref_delete}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p><a href="<c:url value="clearall.do"/>">Clear All</a></p>
                </c:when>
                <c:otherwise>
                    <p class="message">Nobody registered yet.</p>
                </c:otherwise>
            </c:choose>
            <my:IfLoggedIn>
                <p><a href="<c:url value="change_password.do"/>">Change password</a></p>
                <p><a href="<c:url value="addassist.do"/>">Add An Assistant</a></p>
                <p><a href="<c:url value="listallassist.do"/>">List All Assistants</a></p>
                <p><a href="<c:url value="logout.jsp"/>">Logout</a></p>
            </my:IfLoggedIn>
            <p><a href="<c:url value="input.do"/>">Go Back to Input</a></p>
        </main>
        <footer><my:Sheridan/></footer>
    </body>
</html>
