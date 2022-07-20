package com.askchaitanya.blackops.bootstrap;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import okhttp3.OkHttpClient;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private volatile static HikariDataSource dataSource = null;

    // TODO: Configuration secrets to be moved into environment variables
    private static HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/blackops");
        config.setUsername("blackops");
        config.setPassword("blackOps");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized (DataSource.class) {
                if (dataSource == null) {
                    dataSource = getDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }
}
