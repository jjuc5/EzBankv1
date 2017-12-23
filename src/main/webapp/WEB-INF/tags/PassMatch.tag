<!--
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="value" rtexprvalue="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="id"%>
<input id="${id}" type="password" class="form-control" name="${name}" value="<c:out value="${value}"/>" onChange="checkPasswordMatch();">