<%@ page import="cl.playground.triggersapp.entities.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andressepulveda
  Date: 03-06-24
  Time: 12:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de Productos</title>
</head>
<body>
    <h1>Listado de Productos</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
        </tr>
        <%
            List<Product> listaProductos = (List<Product>) request.getAttribute("listaProductos");
            for (Product product : listaProductos) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
