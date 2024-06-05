<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="cl.playground.triggersapp.entities.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Crear Cotización</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
  <h1>Crear Cotización</h1>
  <form action="quotation" method="post">
    <div id="product-container">
      <div class="product-item mb-3">
        <label for="product1" class="form-label">Producto</label>
        <select class="form-select" id="product1" name="product" required>
          <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null) {
              for (Product product : products) {
          %>
          <option value="<%= product.getId() %>"><%= product.getName() %> - Precio: <%= product.getPrice() %></option>
          <%
            }
          } else {
          %>
          <option value="">No hay productos disponibles</option>
          <%
            }
          %>
        </select>
        <label for="quantity1" class="form-label mt-2">Cantidad</label>
        <input type="number" class="form-control" id="quantity1" name="quantity" required>
      </div>
    </div>
    <button type="button" class="btn btn-secondary" onclick="addProduct()">Agregar Producto</button>
    <button type="submit" class="btn btn-primary mt-3">Crear</button>
  </form>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
  let productCount = 1;
  function addProduct() {
    productCount++;
    const productContainer = document.getElementById('product-container');
    const productItem = document.createElement('div');
    productItem.classList.add('product-item', 'mb-3');
    productItem.innerHTML = `
            <label for="product${productCount}" class="form-label">Producto</label>
            <select class="form-select" id="product${productCount}" name="product" required>
                ${document.getElementById('product1').innerHTML}
            </select>
            <label for="quantity${productCount}" class="form-label mt-2">Cantidad</label>
            <input type="number" class="form-control" id="quantity${productCount}" name="quantity" required>
        `;
    productContainer.appendChild(productItem);
  }
</script>
</body>
</html>
