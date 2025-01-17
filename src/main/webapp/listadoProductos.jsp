<%@ page import="cl.playground.triggersapp.entities.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Productos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Listado de Productos</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            for (Product product : products) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href="crearProducto.jsp" class="btn btn-primary">Crear Producto</a>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
