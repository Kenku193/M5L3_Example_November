package org.example.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionPool {

    private final HikariDataSource ds;

    //      WITHOUT SPRING PROPERTY SOURCE (HARDCODE)
//    public ConnectionPool() {
//
//        HikariConfig config = new HikariConfig();
//
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/game");
//        config.setUsername("root");
//        config.setPassword("qwerty");
//
//        ds = new HikariDataSource(config);
//    }

    //      WITH SPRING PROPERTY SOURCE
    public ConnectionPool(
            @Value("${spring.datasource.url}")
            String dbUrl,

            @Value("${spring.datasource.user}")
            String dbUser,

            @Value("${spring.datasource.password}")
            String dbPassword) {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}