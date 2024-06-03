package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.database.DatabaseConnection;
import cl.playground.triggersapp.entities.Product;
import cl.playground.triggersapp.entities.Quotation;
import cl.playground.triggersapp.entities.QuotationItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuotationServlet", value = "/quotations")
public class QuotationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Quotation> listaCotizaciones = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM quotations")) {

            while (resultSet.next()) {
                Quotation quotation = new Quotation();
                quotation.setId(resultSet.getInt("id"));
                quotation.setCreatedAt(resultSet.getTimestamp("created_at"));
                quotation.setTotal(resultSet.getDouble("total"));

                // Get items for the quotation
                List<QuotationItem> items = new ArrayList<>();
                try (PreparedStatement itemStmt = connection.prepareStatement("SELECT * FROM quotation_items WHERE quotation_id = ?")) {
                    itemStmt.setInt(1, quotation.getId());
                    ResultSet itemResultSet = itemStmt.executeQuery();
                    while (itemResultSet.next()) {
                        QuotationItem item = new QuotationItem();
                        item.setQuotationId(itemResultSet.getInt("quotation_id"));
                        item.setProductId(itemResultSet.getInt("product_id"));
                        item.setQuantity(itemResultSet.getInt("quantity"));

                        // Get product details
                        try (PreparedStatement productStmt = connection.prepareStatement("SELECT * FROM products WHERE id = ?")) {
                            productStmt.setInt(1, item.getProductId());
                            ResultSet productResultSet = productStmt.executeQuery();
                            if (productResultSet.next()) {
                                Product product = new Product();
                                product.setId(productResultSet.getInt("id"));
                                product.setName(productResultSet.getString("name"));
                                product.setPrice(productResultSet.getDouble("price"));
                                item.setProduct(product);
                            }
                        }

                        items.add(item);
                    }
                }
                quotation.setItems(items);
                listaCotizaciones.add(quotation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("listaCotizaciones", listaCotizaciones);
        req.getRequestDispatcher("/listadoCotizaciones.jsp").forward(req, resp);
    }
}
