package com.elkased.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    private static String url = "jdbc:mysql://localhost:3306/todolist";
    private static String username = "root";
    private static String password = "11904231@$";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return connection;
    }
}
