package cl.playground.triggersapp.handlers;

import cl.playground.triggersapp.database.DatabaseConnection;
import cl.playground.triggersapp.database.RedisConnection;
import cl.playground.triggersapp.entities.Product;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductQueryHandler {

    public List<Product> getProducts() throws Exception {
        List<Product> productList = new ArrayList<>();

        try (Jedis jedis = RedisConnection.getConnection()) {
            if (jedis.exists("products")) {
                List<String> products = jedis.lrange("products", 0, -1);
                for (String productStr : products) {
                    String[] parts = productStr.split(",");
                    Product product = new Product();
                    product.setId(Integer.parseInt(parts[0]));
                    product.setName(parts[1]);
                    product.setPrice(Double.parseDouble(parts[2]));
                    productList.add(product);
                }
            } else {
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "SELECT * FROM products";
                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(sql)) {
                        while (resultSet.next()) {
                            Product product = new Product();
                            product.setId(resultSet.getInt("id"));
                            product.setName(resultSet.getString("name"));
                            product.setPrice(resultSet.getDouble("price"));
                            productList.add(product);
                            jedis.rpush("products", product.getId() + "," + product.getName() + "," + product.getPrice());
                        }
                    }
                }
            }
        }
        return productList;
    }
}
