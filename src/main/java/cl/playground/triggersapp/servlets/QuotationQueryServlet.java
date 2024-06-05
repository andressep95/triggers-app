package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.handlers.QuotationQueryHandler;
import cl.playground.triggersapp.entities.Quotation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuotationQueryServlet", value = "/quotations")
public class QuotationQueryServlet extends HttpServlet {
    private QuotationQueryHandler queryHandler = new QuotationQueryHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Quotation> quotations = queryHandler.getQuotations();
            req.setAttribute("listaCotizaciones", quotations);
            req.getRequestDispatcher("/listadoCotizaciones.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
