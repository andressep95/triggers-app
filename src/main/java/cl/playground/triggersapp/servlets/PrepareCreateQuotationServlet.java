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

@WebServlet(name = "PrepareCreateQuotationServlet", value = "/prepare-create-quotation")
public class PrepareCreateQuotationServlet extends HttpServlet {
    private ProductQueryHandler productQueryHandler = new ProductQueryHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> products = productQueryHandler.getProducts();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/crearCotizacion.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
