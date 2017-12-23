<!--
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
-->
<%@attribute name="value" rtexprvalue="true" %>
<%@attribute name="option" required="true" rtexprvalue="true" %>
<%@attribute name="id" %>
<%@attribute name="name" required="true" %>
<input id="${id}" type="checkbox" name="${name}" value="${option}" ${(value == option)?"checked":""}>
