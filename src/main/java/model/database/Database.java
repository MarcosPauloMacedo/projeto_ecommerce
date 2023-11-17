package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection connection;
    private static String user = "admin";
    private static String password = "admin123";
    private static String connectionDatabase = "jdbc:mysql://atendimentos.cj1qdgzb6cxq.us-east-2.rds.amazonaws.com:3306/ecommerce";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionDatabase, user, password);

        } catch (ClassNotFoundException | SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public static boolean executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement prepareStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}
