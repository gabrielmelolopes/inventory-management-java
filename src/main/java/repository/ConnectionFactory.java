package repository;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/estoque_banco";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    public static Connection getConnection(){

        try{
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
