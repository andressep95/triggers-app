package cl.playground.triggersapp.handlers;

import cl.playground.triggersapp.commands.CreateQuotationCommand;
import cl.playground.triggersapp.commands.DeleteQuotationCommand;
import cl.playground.triggersapp.commands.UpdateQuotationCommand;
import cl.playground.triggersapp.database.DatabaseConnection;
import cl.playground.triggersapp.database.RedisConnection;
import cl.playground.triggersapp.entities.QuotationItem;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuotationCommandHandler {
    public void handle(CreateQuotationCommand command) throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO quotations (total) VALUES (0)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }

            for (QuotationItem item : command.getItems()) {
                String itemSql = "INSERT INTO quotation_items (quotation_id, product_id, quantity) VALUES (currval('quotations_id_seq'), ?, ?)";
                try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                    itemStatement.setInt(1, item.getProductId());
                    itemStatement.setInt(2, item.getQuantity());
                    itemStatement.executeUpdate();
                }
            }
        }

        try (Jedis jedis = RedisConnection.getConnection()) {
            jedis.del("quotations");
        }
    }

    public void handle(UpdateQuotationCommand command) throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE quotations SET total = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, command.getTotal());
                statement.setInt(2, command.getId());
                statement.executeUpdate();
            }
        }

        try (Jedis jedis = RedisConnection.getConnection()) {
            jedis.del("quotations");
        }
    }

    public void handle(DeleteQuotationCommand command) throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM quotations WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, command.getId());
                statement.executeUpdate();
            }

            String itemSql = "DELETE FROM quotation_items WHERE quotation_id = ?";
            try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                itemStatement.setInt(1, command.getId());
                itemStatement.executeUpdate();
            }
        }

        try (Jedis jedis = RedisConnection.getConnection()) {
            jedis.del("quotations");
        }
    }
}
