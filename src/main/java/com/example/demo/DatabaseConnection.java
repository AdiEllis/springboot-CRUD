package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection dbConnection = null;
    private static Connection connection;

    public DatabaseConnection() {
    }
    public static DatabaseConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DatabaseConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/project5?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
            String driver = "com.mysql.cj.jdbc.Driver";
            String username = "root";
            String password = "1234";

            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public void closeConnection(Connection con) throws SQLException {
        try {
            if (connection != null || !connection.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
