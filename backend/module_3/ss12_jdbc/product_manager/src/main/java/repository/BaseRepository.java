package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/product_management?useSSL=false&allowPublishKeyRetrieval=true&useUnicode=yes&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Not found mysql library .check file build.gradle");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("connect db failure. check USER, PASSWORD, URL");
            e.printStackTrace();
        }
        return connection;
    }
}
