<%@ page import="cl.playground.triggersapp.entities.Quotation" %>
<%@ page import="java.util.List" %>
<%@ page import="cl.playground.triggersapp.entities.QuotationItem" %>
<%@ page import="cl.playground.triggersapp.entities.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Cotizaciones</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Listado de Cotizaciones</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Total</th>
            <th>Items</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Quotation> listaCotizaciones = (List<Quotation>) request.getAttribute("listaCotizaciones");
            if (listaCotizaciones != null) {
                for (Quotation quotation : listaCotizaciones) {
        %>
        <tr>
            <td><%= quotation.getId() %></td>
            <td><%= quotation.getCreatedAt() %></td>
            <td><%= quotation.getTotal() %></td>
            <td>
                <ul>
                    <%
                        if (quotation.getItems() != null) {
                            for (QuotationItem item : quotation.getItems()) {
                                Product product = item.getProduct();
                    %>
                    <li>
                        <%= product.getName() %> - Cantidad: <%= item.getQuantity() %> - Precio Unitario: <%= product.getPrice() %>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <h2 class="mt-4">Crear Cotización</h2>
    <a href="prepare-create-quotation" class="btn btn-primary">Crear Cotización</a>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
