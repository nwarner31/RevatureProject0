package net.revature.nwarner.project0.database.utility;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {

    private static Connection conn;

    private DBConnection() {

    }

    public static Connection getConnection() {

            if(conn == null) {
                try {
                    FileInputStream propInput = new FileInputStream("E:\\Programming\\Revature\\docs\\project0.properties");
                    Properties props = new Properties();
                    props.load(propInput);
                    String url = (String) props.get("url");
                    String username = (String) props.get("username");
                    String password = (String) props.get("password");
                    conn = DriverManager.getConnection(url, username, password);
                    return conn;
                } catch(Exception e) {
                    return null;
                }

        } else {
            return conn;
        }
    }




}
