<%-- Alex Tetervak, Sheridan College, Ontario --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="value" rtexprvalue="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="id"%>
<input id="${id}" type="text" class="form-control" name="${name}" value="<c:out value="${value}"/>">