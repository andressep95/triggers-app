package cl.playground.triggersapp.handlers;

import cl.playground.triggersapp.commands.CreateProductCommand;
import cl.playground.triggersapp.commands.UpdateProductCommand;
import cl.playground.triggersapp.commands.DeleteProductCommand;
import cl.playground.triggersapp.database.DatabaseConnection;
import cl.playground.triggersapp.database.RedisConnection;
import cl.playground.triggersapp.events.ProductCreatedEvent;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductCommandHandler {

    public void handle(CreateProductCommand command) throws Exception {
        // Insertar en la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, command.getName());
                statement.setDouble(2, command.getPrice());
                statement.executeUpdate();
            }
        }

        // Publicar evento a Redis
        ProductCreatedEvent event = new ProductCreatedEvent(command.getName(), command.getPrice());
        try (Jedis jedis = RedisConnection.getConnection()) {
            jedis.publish("productCreated", event.toString());
        }

        // Invalidate cache
        try (Jedis jedis = RedisConnection.getConnection()) {
            jedis.del("products");
        }
    }

    public void handle(UpdateProductCommand command) throws Exception {
        // Actualizar en la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, command.getName());
                statement.setDouble(2, command.getPrice());
                statement.setInt(3, command.getId());
                statement.executeUpdate();
            }
        }

        // Invalidate cache
        try (Jedis jedis = RedisConnection.getConnection()) {
            jedis.del("products");
        }
    }

    public void handle(DeleteProductCommand command) throws Exception {
        // Eliminar de la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM products WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, command.getId());
                statement.executeUpdate();
            }
        }

        // Invalidate cache
        try (Jedis jedis = RedisConnection.getConnection()) {
            jedis.del("products");
        }
    }
}
