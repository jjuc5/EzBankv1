<!--
    Project Deliverable 3
    Group Members: John Urbanowicz, Richard Paul, Melanie Iarocci
    Professor: Gurdeep Gill
    Date: 23 Dec 2017
    Sheridan College
-->
<%@tag pageEncoding="UTF-8"%>
<%@tag import="java.util.Collection"%>
<%
    Collection errors = (Collection) request.getAttribute("errors");
    String smile;
    if (errors == null || errors.isEmpty()) {
        String[] smiles = {"glasses", "grin", "smile", "wink"};
        smile = smiles[(int) (smiles.length * Math.random())];
    } else {
        smile = "surprise";
    }
%>
<h1 class="<%=smile%>"><jsp:doBody/></h1>