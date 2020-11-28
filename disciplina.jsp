<%-- 
    Document   : disciplina
    Created on : 3 de out de 2020, 12:31:08
    Author     : mandi
--%>
<%@page import="br.poo.Disciplina" %>
<%@page import="web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String exceptionMessage = null;
    if(request.getParameter("Cancelar")!=null){
        response.sendRedirect(request.getRequestURI());
    }
    if(request.getParameter("FormInsert")!=null){
        try{
            String nome = request.getParameter("nome");
            String ementa = request.getParameter("ementa");
            int ciclo = Integer.parseInt(request.getParameter("ciclo"));
            Disciplina.insert(nome, ementa, ciclo);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
    if(request.getParameter("FormUpdate")!=null){
        try{
            long rowId = Long.parseLong(request.getParameter("rowId"));
            String nome = request.getParameter("nome");
            String ementa = request.getParameter("ementa");
            int ciclo = Integer.parseInt(request.getParameter("ciclo"));
            Disciplina.update(rowId, nome, ementa, ciclo);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
    if(request.getParameter("FormDelete")!=null){
        try{
            long rowId = Long.parseLong(request.getParameter("rowId"));
            Disciplina.delete(rowId);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            exceptionMessage = ex.getLocalizedMessage();
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>Disciplinas</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <h3>Disciplinas</h3>
        <%if(request.getParameter("prepararInsert")!=null){%>
            <h3>Incluir disciplina</h3>
            <form>
                Nome:<input type="text" name="nome"/>
                Ementa:<input type="text" name="ementa"/>
                Ciclo:<input type="text" name="ciclo"/>
                <input type="submit" name="FormInsert" value="Inserir"/>
                <input type="submit" name="Cancelar" value="Cancelar"/>
            </form>
        <%}else if(request.getParameter("prepararUpdate")!=null){%>
            <h3>Alterar disciplina</h3>
            <form>
                <%
                    String rowId = request.getParameter("rowId");
                    String nome = request.getParameter("nome");
                    String ementa = request.getParameter("ementa");
                    String ciclo = request.getParameter("ciclo");
                %>
                <input type="hidden" name="rowId" value="<%= rowId %>"/>
                Nome:<input type="text" name="nome" value="<%= nome %>"/>
                Ementa:<input type="text" name="ementa" value="<%= ementa %>"/>
                Ciclo:<input type="text" name="ciclo" value="<%= ciclo %>"/>
                <input type="submit" name="FormUpdate" value="Alterar"/>
                <input type="submit" name="Cancelar" value="Cancelar"/>
            </form>
        <%}else if(request.getParameter("prepararDelete")!=null){%>
            <h3>Excluir disciplina</h3>
            <form>
                <%String rowId = request.getParameter("rowId");%>
                <input type="hidden" name="rowId" value="<%= rowId %>"/>
                Excluir a disciplina <b><%= rowId %></b>?
                <input type="submit" name="FormDelete" value="Excluir"/>
                <input type="submit" name="Cancelar" value="Cancelar"/>
            </form>
        <%}else{%>
            <form method="post">
                <input type="submit" name="prepararInsert" value="Inserir"/>
            </form>
        <%}%>
        <h3>Lista de Disciplinas:</h3>
        <table border="1">
            <tr>
                <th>Índice</th>
                <th>Disciplina</th>
                <th>Ementa</th>
                <th>Ciclo</th>
                <th>Nota</th>
            </tr>
            <% for(Disciplina d: Disciplina.getList()){%>
            <tr>
                <th><%= d.getRowId() %></th>
                <th><%= d.getNome() %></th>
                <th><%= d.getEmenta() %></th>
                <th><%= d.getCiclo() %></th>
                <th>
                    <form>
                        <input type="hidden" name="rowId" value="<%= d.getRowId() %>"/>
                        <input type="hidden" name="nome" value="<%= d.getNome() %>"/>
                        <input type="hidden" name="ementa" value="<%= d.getEmenta() %>"/>
                        <input type="hidden" name="ciclo" value="<%= d.getCiclo() %>"/>
                        <input type="submit" name="prepararUpdate" value="Alterar"/>
                        <input type="submit" name="prepararDelete" value="Excluir"/>
                    </form>
                </th>
            </tr>    
            <%}%>
        </table>
        <%@include file="WEB-INF/jspf/body_scripts.jspf" %>
    </body>
</html>
