<%@ page import="java.util.List" %>
<%@ page import="cl.playground.triggersapp.entities.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: andressepulveda
  Date: 01-06-24
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mostrar Usuarios</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <%
        List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");
        int i = 1;
        for (Usuario user : listaUsuarios) {
    %>
            <p><b>Usuario Nro. <%=i%></b></p>
            <p>DNI: <%=user.getDni()%></p>
            <p>Nombre: <%=user.getNombre()%></p>
            <p>Apellido: <%=user.getApellido()%></p>
            <p>Telefono: <%=user.getTelefono()%></p>
            <p>---------------------------------</p>
            <%i +=1;%>
    <%
        }
    %>
</body>
</html>
