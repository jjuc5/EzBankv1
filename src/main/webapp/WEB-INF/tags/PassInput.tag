<%-- 
    Document   : PassInput.tag
    Created on : 20-Dec-2017, 11:32:18 AM
    Author     : melan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="value" rtexprvalue="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="id"%>
<input id="${id}" type="password" class="form-control" name="${name}" value="<c:out value="${value}"/>">