<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h2>Datos del Usuario</h2>
    <form action="users" method="POST">
        <p><label>DNI:</label><input type="text" name="dni"></p>
        <p><label>Nombre:</label><input type="text" name="nombre"></p>
        <p><label>Apellido:</label><input type="text" name="apellido"></p>
        <p><label>Telefono:</label><input type="text" name="telefono"></p>
        <button type="submit">Enviar</button>
    </form>

    <h2>Ver lista de Usuarios</h2>
    <form action="users" method="GET">
        <button type="submit">Mostrar Usuarios</button>
    </form>

    <h2>Ver lista de Productos</h2>
    <form action="products" method="GET">
        <button type="submit">Mostrar Productos</button>
    </form>
</body>
</html>