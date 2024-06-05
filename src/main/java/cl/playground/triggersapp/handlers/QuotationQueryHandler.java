package cl.playground.triggersapp.handlers;

import cl.playground.triggersapp.database.DatabaseConnection;
import cl.playground.triggersapp.entities.Product;
import cl.playground.triggersapp.entities.Quotation;
import cl.playground.triggersapp.entities.QuotationItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuotationQueryHandler {
    public List<Quotation> getQuotations() throws Exception {
        List<Quotation> quotationList = new ArrayList<>();
        Map<Integer, Quotation> quotationMap = new HashMap<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM cotizaciones_completas";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int quotationId = resultSet.getInt("cotizacion_id");
                    Quotation quotation = quotationMap.get(quotationId);
                    if (quotation == null) {
                        quotation = new Quotation(
                                quotationId,
                                resultSet.getTimestamp("created_at"),
                                resultSet.getDouble("total")
                        );
                        quotationMap.put(quotationId, quotation);
                    }

                    Product product = new Product(
                            resultSet.getInt("producto_id"),
                            resultSet.getString("producto_nombre"),
                            resultSet.getDouble("producto_precio")
                    );

                    QuotationItem item = new QuotationItem(
                            quotationId,
                            resultSet.getInt("producto_id"),
                            resultSet.getInt("cantidad"),
                            product
                    );

                    quotation.getItems().add(item);
                }
            }

            quotationList.addAll(quotationMap.values());
        }

        return quotationList;
    }
}
