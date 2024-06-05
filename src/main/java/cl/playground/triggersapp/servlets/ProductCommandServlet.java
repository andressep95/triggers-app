package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.commands.CreateProductCommand;
import cl.playground.triggersapp.commands.DeleteProductCommand;
import cl.playground.triggersapp.commands.UpdateProductCommand;
import cl.playground.triggersapp.handlers.ProductCommandHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductCommandServlet", value = "/product")
public class ProductCommandServlet extends HttpServlet {
    private ProductCommandHandler commandHandler = new ProductCommandHandler();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Manejar la creación de productos
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        CreateProductCommand command = new CreateProductCommand(name, price);

        try {
            commandHandler.handle(command);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Manejar la actualización de productos
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        UpdateProductCommand command = new UpdateProductCommand(id, name, price);

        try {
            commandHandler.handle(command);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Manejar la eliminación de productos
        int id = Integer.parseInt(req.getParameter("id"));
        DeleteProductCommand command = new DeleteProductCommand(id);

        try {
            commandHandler.handle(command);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
