<%@ page import="cl.playground.triggersapp.entities.Quotation" %>
<%@ page import="java.util.List" %>
<%@ page import="cl.playground.triggersapp.entities.QuotationItem" %>
<%@ page import="cl.playground.triggersapp.entities.Product" %><%--
  Created by IntelliJ IDEA.
  User: andressepulveda
  Date: 03-06-24
  Time: 1:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Listado de Cotizaciones</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Total</th>
            <th>Items</th>
        </tr>
        <%
            List<Quotation> listaCotizaciones = (List<Quotation>) request.getAttribute("listaCotizaciones");
            for (Quotation quotation : listaCotizaciones) {
        %>
        <tr>
            <td><%= quotation.getId() %></td>
            <td><%= quotation.getCreatedAt() %></td>
            <td><%= quotation.getTotal() %></td>
            <td>
                <ul>
                    <%
                        for (QuotationItem item : quotation.getItems()) {
                            Product product = item.getProduct();
                    %>
                    <li>
                        <%= product.getName() %> - Cantidad: <%= item.getQuantity() %> - Precio Unitario: <%= product.getPrice() %>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
