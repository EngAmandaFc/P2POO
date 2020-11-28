<%-- 
    Document   : index
    Created on : 3 de out de 2020, 12:25:18
    Author     : mandi
--%>

<%@page import="br.poo.Disciplina"%>
<%@page import="web.DbListener" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prova</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <br>
        <br>
        <h1 style="color:lightslategray;"><a href="sobre.jsp">Aluna Amanda Fernandez Caetano</a></h1>
        <h2 style="color:lightslategray;">FATEC PG</h2>
        <br>
        <h2 style="color:lightslategray;">RA: 1290481912050</h2>
        <br>
        <h2 style="color:lightslategray;"><a href="disciplina.jsp">Cursando <%= Disciplina.getList().size()  %> matérias.</a></h2>
        <br>
    </body>
</html>
