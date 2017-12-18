<%-- 
    This little trick provides a SESSIONID cookie to the first real page of the application,
    if the browser accepts cookies (it is a way to check it).
--%>
<%@ page session="true"%>
<% response.sendRedirect("login.do"); %>
