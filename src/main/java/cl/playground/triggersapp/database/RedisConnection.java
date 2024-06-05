package cl.playground.triggersapp.database;

import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static final String REDIS_HOST = System.getenv("REDIS_HOST");
    private static final int REDIS_PORT = Integer.parseInt(System.getenv("REDIS_PORT"));

    private static Jedis jedis;

    static {
        jedis = new Jedis(REDIS_HOST, REDIS_PORT);
    }

    public static Jedis getConnection() {
        return jedis;
    }
}
