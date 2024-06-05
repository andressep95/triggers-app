package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.handlers.ProductQueryHandler;
import cl.playground.triggersapp.entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductQueryServlet", value = "/products")
public class ProductQueryServlet extends HttpServlet {
    private ProductQueryHandler queryHandler = new ProductQueryHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> products = queryHandler.getProducts();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/listadoProductos.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
