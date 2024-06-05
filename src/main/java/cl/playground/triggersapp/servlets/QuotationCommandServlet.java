package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.commands.CreateQuotationCommand;
import cl.playground.triggersapp.commands.DeleteQuotationCommand;
import cl.playground.triggersapp.commands.UpdateQuotationCommand;
import cl.playground.triggersapp.entities.QuotationItem;
import cl.playground.triggersapp.handlers.QuotationCommandHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuotationCommandServlet", value = "/quotation")
public class QuotationCommandServlet extends HttpServlet {
    private QuotationCommandHandler commandHandler = new QuotationCommandHandler();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] productIds = req.getParameterValues("product");
            String[] quantities = req.getParameterValues("quantity");

            List<QuotationItem> items = new ArrayList<>();
            for (int i = 0; i < productIds.length; i++) {
                int productId = Integer.parseInt(productIds[i]);
                int quantity = Integer.parseInt(quantities[i]);
                QuotationItem item = new QuotationItem();
                item.setProductId(productId);
                item.setQuantity(quantity);
                items.add(item);
            }

            CreateQuotationCommand command = new CreateQuotationCommand(items);
            commandHandler.handle(command);
            resp.sendRedirect("/quotations");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        double total = Double.parseDouble(req.getParameter("total"));
        UpdateQuotationCommand command = new UpdateQuotationCommand(id, total);

        try {
            commandHandler.handle(command);
            resp.sendRedirect("/quotations");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        DeleteQuotationCommand command = new DeleteQuotationCommand(id);

        try {
            commandHandler.handle(command);
            resp.sendRedirect("/quotations");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
