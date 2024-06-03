package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.database.DatabaseConnection;
import cl.playground.triggersapp.entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Generamos un elemento en donde guardar los productos.
        List<Product> listaProductos = new ArrayList<>();

        // 2. Realizamos la conexion a la bd.
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")
             ) {
            // Llenamos el arreglo con los productos que vamos obteniendo.
            while (resultSet.next()) {
                Product product = new Product(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price")
                );
                listaProductos.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        req.setAttribute("listaProductos", listaProductos);
        req.getRequestDispatcher("/listadoProductos.jsp").forward(req, resp);

    }
}
