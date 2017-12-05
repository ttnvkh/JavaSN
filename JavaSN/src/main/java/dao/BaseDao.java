package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class BaseDao {
    protected Connection getConnection(){
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String JDBC_DB_URL = "jdbc:mysql://localhost:3306/database";
        String JDBC_USER = "root";
        String JDBC_PASS =  "password";
        Connection connObj = null;

        try {
            Class.forName(JDBC_DRIVER);
            connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            connObj.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connObj;
    }
}
