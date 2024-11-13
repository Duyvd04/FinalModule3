package org.example.finalmodule3.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_Url="jdbc:mysql://localhost:3306/QuanLyPhongTro";
    private static final String  DB_User= "root";
    private static  final String DB_PASSWORD="01112004";
    public  static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_Url, DB_User, DB_PASSWORD);
            System.out.println("connection successfully ");
        } catch (ClassNotFoundException sqlConnection) {
            System.out.println("Failed to load JDBC driver");
        } catch (SQLException e) {
            System.out.println( e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }
}
